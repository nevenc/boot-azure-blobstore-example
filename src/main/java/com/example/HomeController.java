package com.example;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

@RestController
public class HomeController {
	
	@Autowired
	private BlobStorageConfigurator blobConfig;
	
	private CloudBlobClient client;
	
	@PostConstruct
	public void initializeClient() throws InvalidKeyException, URISyntaxException {
		 blobConfig.initializeBlobClient();
		 this.client = blobConfig.getBlobClient();
		 System.out.println("Blob client configured: " + blobConfig.getBlobClient());
	}
	
	@GetMapping("/")
	public String home() {
		return "Azure Storage Account test. Try /containers";
	}
	
	@GetMapping("/containers")
	public String listContainers() {

		try {
			
			StringBuffer sb = new StringBuffer();
			sb.append("Storage URI: " + client.getEndpoint().toString());
			sb.append(System.lineSeparator());

			sb.append("Containers: " + System.lineSeparator());
			for (CloudBlobContainer container : client.listContainers()) {
				sb.append(container.getName());
				sb.append(System.lineSeparator());
			}
			sb.append(System.lineSeparator());
			
			sb.append("List complete.");
			sb.append(System.lineSeparator());
			
			return sb.toString();

		} catch (Exception se) {
			se.printStackTrace();
			return "ERROR: " + se;
		}
			
	}
	
}
