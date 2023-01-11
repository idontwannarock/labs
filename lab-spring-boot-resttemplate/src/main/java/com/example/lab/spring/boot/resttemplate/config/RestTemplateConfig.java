package com.example.lab.spring.boot.resttemplate.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
public class RestTemplateConfig {

	private final CloseableHttpClient httpClient;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	@Bean
	HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setHttpClient(httpClient);
		clientHttpRequestFactory.setBufferRequestBody(false);
		return clientHttpRequestFactory;
	}

	@Bean
	TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("poolScheduler");
		scheduler.setPoolSize(100);
		return scheduler;
	}
}
