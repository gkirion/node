package com.george.node.services.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.george.node.services.NodeService;

@Service("Node")
public class NodeServiceImpl implements NodeService {

	private final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

	public void put(String key, String value) {
		map.put(key, value);
	}

	public String get(String key) {
		return map.get(key);
	}

}
