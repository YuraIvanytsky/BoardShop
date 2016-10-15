package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByFirstName(String firstName);
	User findByLastName(String lastName);
	User findByLogin(String login);
	User findByPassword(String password);
	User findByTelephoneNumber(String telephoneNumber);
	User findByMail(String mail);
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.city WHERE u.id=:id")
	User findOneUserInited(@Param("id")int id);
	
}
