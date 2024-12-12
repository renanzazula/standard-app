package com.standard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;

//@Import(SecurityBeans.class)
@SpringBootTest
@Sql("/scripts/dataset.sql")
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop", "spring.flyway.enabled=false"})
public class StandardAppApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void listBeans() {
		Arrays.stream(applicationContext.getBeanDefinitionNames())
				.sorted()
				.forEach(System.out::println);
	}
}
