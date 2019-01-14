# money-transfer-api
Simple Java 8 API based on Spark to handle money transfer

- Clone repo
- Make sure there is nothing already running on port 8080
- To just test do mvn test
- Generate jar file with mvn clean package
- Execute java -jar /path/to/money-transfer-api/target/money-transfer-api-1.0-SNAPSHOT-jar-with-dependencies.jar
- Sample data and requests are available below:

 Account ID 1 has balance 820.56
 Account ID 2 has balance 28320.13
 Account ID 3 has balance 1460

To add new country:

JSON POST to http://localhost:8080/sendMoney

  {
    "senderId" : 1,
    "receiverId" : 2,
    "value" : 150
  }
