package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService
{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		try
		{
			User byUserName = userRepository.findByUserName(username);
			if (byUserName != null)
			{
				return org.springframework.security.core.userdetails.User.builder().username(byUserName.getUserName()).password(byUserName.getPassword())
						.roles(byUserName.getRoles().toArray(new String[0])).build();
			}
		}
		catch (Exception e)
		{
			throw new UsernameNotFoundException("Username: " + username + " not found. " + e);
		}
		return null;
	}
}
