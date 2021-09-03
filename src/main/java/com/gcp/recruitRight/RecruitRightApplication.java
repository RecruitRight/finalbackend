package com.gcp.recruitRight;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RecruitRightApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitRightApplication.class, args);
		//new Context(context);
	}

}
