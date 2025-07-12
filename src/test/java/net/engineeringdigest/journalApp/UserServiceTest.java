package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest
{
	@Autowired
	UserRepository userRepository;


//	@BeforeEach
//	@BeforeAll
//	@AfterAll
//	@AfterEach
//	void setUp(){
//
//	}

	@ParameterizedTest
	//	@CsvSource({ "Ram", "tanvi", "vipul" })
	@ValueSource(strings = { "tanvi", "vipul" })
	public void testFindByUserName(String name)
	{
		assertNotNull(userRepository.findByUserName(name), "failed for: " + name);
	} 

	@ParameterizedTest
	@CsvSource({ "1,1,2"})
	public void test(int a, int b, int expected) 
	{
		assertEquals(expected, a + b);
	}
}
