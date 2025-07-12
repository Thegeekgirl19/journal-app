package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserDetailServiceImplTest
{
	@InjectMocks
	UserDetailsServiceImpl userDetailsService;

	@Mock
	UserRepository userRepository;

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void loadUserByUsernameTest()
	{
		User mockUser = User.builder().userName("akshit").password("akshit").roles(List.of("USER")).build();

		when(userRepository.findByUserName("akshit")).thenReturn(mockUser);

		UserDetails userDetails = userDetailsService.loadUserByUsername("akshit");
		assertNotNull(userDetails);
		System.out.println("Loaded user: " + userDetails.getUsername());
	}

}
