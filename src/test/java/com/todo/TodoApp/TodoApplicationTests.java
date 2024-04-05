package com.todo.TodoApp;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.todo.TodoApp.home.controllers.HomeController;

@SpringBootTest()
class TodoApplicationTests {

	@Autowired
	private HomeController homeController;

	@Test
	void contextLoads() throws Exception {
		assertThat(homeController).isNotNull();
	}

}
