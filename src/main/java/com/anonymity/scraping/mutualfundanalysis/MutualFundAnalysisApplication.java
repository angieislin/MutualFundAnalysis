package com.anonymity.scraping.mutualfundanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MutualFundAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutualFundAnalysisApplication.class, args);
	}

}
