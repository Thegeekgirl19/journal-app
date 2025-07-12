package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	UserService userService;

	@PutMapping()
	public ResponseEntity<?> updateUser(@RequestBody User user)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();

		User userInDb = userService.findByUserName(userName);

		userInDb.setPassword(user.getPassword());
		userInDb.setUserName(user.getUserName());
		userService.saveUser(userInDb);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@DeleteMapping()
	public ResponseEntity<User> createEntry()
	{
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		try
		{
			userService.deleteUser(userName);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
