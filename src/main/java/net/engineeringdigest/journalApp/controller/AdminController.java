package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController
{
	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<?> getAllUsers()
	{
		List<User> all = userService.getAll();
		if (!all.isEmpty())
		{
			return new ResponseEntity<>(all, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/create-admin-user")
	public ResponseEntity<?> createAdmin(@RequestBody User user){
		userService.saveAdminByEncoding(user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
