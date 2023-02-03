package com.study.microservices.springclouddiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDiscoveryServerApplication.class, args);
	}

}
