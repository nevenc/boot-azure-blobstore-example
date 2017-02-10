package com.example;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;

@Component
public class BlobStorageConfigurator {

	private String storageAccountString;
	private CloudBlobClient blobClient;

	public BlobStorageConfigurator(@Value("${azure.blobstore.accountname:your_account_name}") String accountName,
			@Value("${azure.blobstore.accountkey:your_account_key:}") String accountKey,
			@Value("${azure.blobstore.protocol:http}") String protocol) {

		this.storageAccountString = "DefaultEndpointsProtocol=" + protocol + ";"
									+ "AccountName=" + accountName + ";"
									+ "AccountKey=" + accountKey;
		

	}

	public void initializeBlobClient() throws InvalidKeyException, URISyntaxException {
		CloudStorageAccount account = CloudStorageAccount.parse(this.storageAccountString);
		this.blobClient = account.createCloudBlobClient();
	}
	
	public CloudBlobClient getBlobClient() {
		return this.blobClient;
	}

}
