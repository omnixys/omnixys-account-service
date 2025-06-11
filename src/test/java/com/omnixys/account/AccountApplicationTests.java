package com.omnixys.account;

import com.omnixys.account.utils.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class AccountApplicationTests {

	@BeforeAll
	protected static void setup() {
		new Env();
	}

	@Test
	void contextLoads() {
	}

}
