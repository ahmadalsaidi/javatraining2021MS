package com.training.servicea;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class ServiceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAApplication.class, args);
	}


	@Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
	    return new RestTemplate();
    }

    @Bean
    CommandLineRunner test(DiscoveryClient discoveryClient){
	    return args->{
            List<ServiceInstance> instances = discoveryClient.getInstances("service-b");

            instances
                    .stream()
                    .forEach(System.out::println);
        };
    }
}
