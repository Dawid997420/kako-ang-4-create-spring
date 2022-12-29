package com.example.kakoang4create;

import com.example.kakoang4create.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class KakoAng4CreateApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakoAng4CreateApplication.class, args);
	}

}
