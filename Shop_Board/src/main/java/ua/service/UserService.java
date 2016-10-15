package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.User;
import ua.form.UserForm;
import ua.form.filter.UserFilterForm;

public interface UserService{
	void save(String firstName, String lastName, String login, String password, String telephoneNumber, String mail, int cityId);
	List<User> findAll();
	User findByFirstName(String firstName);
	User findByLastName(String lastName);
	User findByLogin(String login);
	User findByPassword(String password);
	User findByTelephoneNumber(String telephoneNumber);
	User findByMail(String mail);
	void delete(int id);
	void deleteAll();
	User findOne(int id);
	Page<User> findAll(UserFilterForm form, Pageable pageable);
	void save(UserForm form);
	UserForm findForForm(int id);
	void save(User user, int cityId);
	User findById(int Id);
}
