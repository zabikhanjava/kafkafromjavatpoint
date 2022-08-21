package com.valtech.kafkafromjavatpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkafromjavatpointApplication {

	
	@Value("${foo}")
	static String s;
	
	public static void main(String[] args) {
		System.out.println(s);
		SpringApplication.run(KafkafromjavatpointApplication.class, args);
	}

}
