package com.msd.cart;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class CartServiceApplicationTests {

	@Test
	  void contextLoads(ApplicationContext context) {
	    assertThat(context).isNotNull();
	  }

}
