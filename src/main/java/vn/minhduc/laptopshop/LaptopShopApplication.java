package vn.minhduc.laptopshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LaptopShopApplication {

	public static void main(String[] args) {
		ApplicationContext kirito = SpringApplication.run(LaptopShopApplication.class, args);
		for (String s : kirito.getBeanDefinitionNames()) {
			System.out.println(s);
		}
	}

}
