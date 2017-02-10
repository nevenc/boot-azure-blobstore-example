# boot-azure-blobstore-example

This is a simple SpringBoot application to test connectivity to your BLOBSTORAGE containers.

## Usage

* Update `application.properties` file with your Azure Blob Store account name (lowercase letters and numbers only, 3-24 characters), Azure Blob Store access key (base64 encoded) and protocol (http/https).
* Package the application, `mvn clean package`
* Deploy the application, `cf push`

## TODO
* add container creation test
* add file upload test
* add support for platform bound STORAGE services (read from VCAP_SERVICES)

