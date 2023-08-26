package clone.twitter;

import clone.twitter.controller.auth.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TwitterApplicationTests {

	@Autowired
	private AuthController authController;

	@Test
	void contextLoads() {
		assertThat(this.authController).isNotNull();
	}

}
