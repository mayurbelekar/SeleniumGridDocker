package com.docker.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DockerCommands {

	public String[] startHub() {
		return new String[] {
				"docker", "run", "-d", "-p", "4446:4444", "--name", "selenium-hub", "-P", "selenium/hub" 
		};
	}
	
	public String[] lauchChromeNode(String nodeName) {
		return new String[] {
				"docker", "run", "-d", "--name", nodeName, "-P", "--link", "selenium-hub:hub", "selenium/node-chrome-debug"
		};
	}
	
	public String[] lauchFirefoxNode(String nodeName) {
		return new String[] {
				"docker", "run", "-d", "--name", nodeName, "-P", "--link", "selenium-hub:hub", "selenium/node-firefox-debug"
		};
	}
	
	public String[] listAllContainers() {
		return new String[] {
				"docker", "ps", "-a"
		};
	}
	
	public String[] listUpContainers() {
		return new String[] {
				"docker", "ps"
		};
	}
	public String[] stopContainer(String containerName) {
		return new String[] {
				"docker", "stop", containerName
		};
	}
	
	public String[] removeContainer(String containerName) {
		return new String[] {
				"docker", "rm", containerName
		};
	}
	
	public String[] containerLogs(String containerName) {
		return new String[] {
				"docker", "logs", containerName
		};
	}
	
	public List<String> executeProcessBuilder(String[] processCommand) {
		List<String> processResponse = new ArrayList<String>();
		System.out.println("Commands: "+Arrays.toString(processCommand));
		Process process = null;
		try {
			process = new ProcessBuilder(processCommand).redirectErrorStream(true).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		try {
			while((line = bReader.readLine()) != null) {
				processResponse.add(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return processResponse;
	}
}
