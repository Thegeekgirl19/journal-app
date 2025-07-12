package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService
{

	@Autowired
	UserRepository userRepository;

	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User saveUserByEncoding(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		return userRepository.save(user);
	}

	public User saveAdminByEncoding(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
		return userRepository.save(user);
	}

	public User saveUser(User user)
	{
		return userRepository.save(user);
	}

	public void deleteUser(String userName)
	{
		userRepository.deleteByUserName(userName);
		return;
	}

	public Optional<User> findById(ObjectId id)
	{
		return userRepository.findById(id);
	}

	public void deleteById(ObjectId id)
	{
		userRepository.deleteById(id);
		return;
	}

	public User findByUserName(String name)
	{
		return userRepository.findByUserName(name);
	}

	public List<User> getAll(){
		return userRepository.findAll();
	}
}
