package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest
{
	@Autowired
	UserRepository userRepository;

	@Disabled
	@ParameterizedTest
	@ValueSource(strings = { "tanvi", "vipul" })
	public void testFindByUserName(String name)
	{
		assertNotNull(userRepository.findByUserName(name), "failed for: " + name);
	}

	@Disabled
	@ParameterizedTest
	@CsvSource({ "1,1,2" })
	public void test(int a, int b, int expected)
	{
		assertEquals(expected, a + b);
	}
}
