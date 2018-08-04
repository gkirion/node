package com.george.node.util;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.george.node.domain.Node;

@ComponentScan("com.george.node")
@SpringBootApplication
public class Launcher {

	@Autowired
	private Node node;

	private static final Logger log = LoggerFactory.getLogger(Launcher.class);

	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
	}

	@PostConstruct
	public void join() {
		log.info(node.getIp());
		log.info(node.getPort() + "");
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject("http://localhost:8080/thiroros", node, String.class);
		if (!response.equals("Node already exists")) {
			node.setId(Long.parseLong(response));
			log.info("node joined the network with node id: " + response);
		} else {
			log.info(response);
		}
		log.info(node.toString());
	}

	@PreDestroy
	public void leave() {
		log.info("leaving cluster");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate
				.delete(URI.create(String.format("http://localhost:8080/thiroros/%s", String.valueOf(node.getId()))));
	}

}
