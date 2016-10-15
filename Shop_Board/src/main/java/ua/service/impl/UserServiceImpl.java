package ua.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.entity.City;
import ua.entity.Role;
import ua.entity.User;
import ua.form.UserForm;
import ua.form.filter.UserFilterForm;
import ua.repository.CityRepository;
import ua.repository.UserRepository;
import ua.service.UserService;

//@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	@Autowired
	private UserRepository repository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void save(String firstName, String lastName, String login,
			String password, String telephoneNumber, String mail, int cityId) {
		City city = cityRepository.findOne(cityId);
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setLogin(login);
		user.setPassword(password);
		user.setTelephoneNumber(telephoneNumber);
		user.setMail(mail);
		user.setCity(city);
		repository.save(user);
	}
	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}
	@Override
	public List<User> findAll() {
		return repository.findAll();
	}
	@Override
	public User findByFirstName(String firstName) {
		return repository.findByFirstName(firstName);
	}
	@Override
	public User findByLastName(String lastName) {
		return repository.findByLastName(lastName);
	}
	@Override
	public User findByLogin(String login) {
		return repository.findByLogin(login);
	}
	@Override
	public User findByPassword(String password) {
		return repository.findByPassword(password);
	}
	@Override
	public User findByTelephoneNumber(String telephoneNumber) {
		return repository.findByTelephoneNumber(telephoneNumber);
	}
	@Override
	public User findByMail(String mail) {
		return repository.findByMail(mail);
	}
	@Override
	public void delete(int id) {
		repository.delete(id);
	}
	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
	@Override
	public User findOne(int id) {
		return repository.findOne(id);
	}
	@Override
	public Page<User> findAll(UserFilterForm form, Pageable pageable) {
		return repository.findAll(pageable);
	}
	@Override
	public void save(UserForm form) {
		User user = new User();
		user.setRole(Role.ROLE_USER);
		user.setCity(form.getCity());
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setLogin(form.getLogin());
		user.setPassword(form.getPassword());
		user.setPassword(encoder.encode(user.getPassword()));
		user.setMail(form.getMail());
		user.setTelephoneNumber(form.getTelephoneNumber());
		repository.save(user);
	}
	@Override
	public UserForm findForForm(int id) {
		User user = repository.findOneUserInited(id);
		UserForm form = new UserForm();
		form.setId(user.getId());
		form.setCity(user.getCity());
		form.setFirstName(user.getFirstName());
		form.setLastName(user.getLastName());
		form.setLogin(user.getLogin());
		form.setPassword(user.getPassword());
		form.setMail(user.getMail());
		form.setTelephoneNumber(user.getTelephoneNumber());
		return form;
	}
	@Override
	public void save(User user, int cityId) {
		City city = cityRepository.findOne(cityId);
		user.setCity(city);
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
	}
	
	@PostConstruct
	public void saveAdmin(){
		User user = repository.findOne(1);
		if(user==null){
			user = new User();
			user.setFirstName("Yura");
			user.setLastName("Ivanytsky");
			user.setMail("y.ivanytsky@gmail.com");
			user.setTelephoneNumber("380634332611");
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(encoder.encode("admin"));
			user.setLogin("admin");
			user.setId(1);
			repository.save(user);
		}
	}
	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		if(Pattern.matches("^[0-9]{1,8}$", login)){
			return repository.findOne(Integer.valueOf(login));
		}
		return repository.findByLogin(login);
	}
	public void setEncoder(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}
	@Override
	public User findById(int id) {
		return repository.findOne(id);
	}
}
