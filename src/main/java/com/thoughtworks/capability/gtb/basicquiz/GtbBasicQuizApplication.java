package com.thoughtworks.capability.gtb.basicquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.thoughtworks.capability.gtb.basicquiz.repository"})
public class GtbBasicQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(GtbBasicQuizApplication.class, args);
	}

}
