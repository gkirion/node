package com.george.node.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.george.node.services.NodeService;

@RestController
public class NodeController {

	@Autowired
	private NodeService nodeService;

	@RequestMapping(path = "/node", method = RequestMethod.POST)
	public void put(@RequestParam String key, @RequestParam String value) {
		nodeService.put(key, value);
	}

	@RequestMapping(path = "/node", method = RequestMethod.GET)
	public String get(@RequestParam String key) {
		return nodeService.get(key);
	}

}
