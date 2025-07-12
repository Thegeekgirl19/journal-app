package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController
{
	@Autowired
	UserService userService;

	@GetMapping("/health-check")
	public String healthCheck()
	{
		return "Ok";
	}

	@PostMapping("/create-user")
	public ResponseEntity<User> createEntry(@RequestBody User user)
	{
		try
		{
			userService.saveUserByEncoding(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
