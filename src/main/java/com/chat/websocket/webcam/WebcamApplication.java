package com.chat.websocket.webcam;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.chat.websocket.*"})
public class WebcamApplication implements ApplicationRunner{
	public static void main(String[] args) {
		SpringApplication.run(WebcamApplication.class, args);
	}
	
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        
    }

}
