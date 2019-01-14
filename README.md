# money-transfer-api
Simple Java 8 API based on Spark to handle money transfer

<b>How to use</b>

- Clone repo
- Make sure there is nothing already running on port 8080
- If you just want to test
```
mvn test
```
- Generate jar file
```
mvn clean package
```
- Run the application
```
java -jar /path/to/money-transfer-api/target/money-transfer-api-1.0-SNAPSHOT-jar-with-dependencies.jar
```
- Sample data and requests are available below

 Account ID 1 has balance 820.56<br>
 Account ID 2 has balance 28320.13<br>
 Account ID 3 has balance 1460

JSON POST to http://localhost:8080/sendMoney

```
  {
    "senderId" : 1,
    "receiverId" : 2,
    "value" : 150
  }
```
HTTP 200 - Money has been transfered with success
<br><br>
```
  {
    "senderId" : 2,
    "receiverId" : 3,
    "value" : 1792.35
  }
```
HTTP 200 - Money has been transfered with success
<br><br>
```
  {
    "senderId" : 1,
    "receiverId" : 2,
    "value" : 999999
  }
```
HTTP 400 - Balance insufficient
<br><br>
```
  {
    "senderId" : 5,
    "receiverId" : 1,
    "value" : 150
  }
```
HTTP 400 - Sender not found
<br><br>
```
  {
    "senderId" : 1,
    "receiverId" : 5,
    "value" : 150
  }
```
HTTP 400 - Receiver not found
<br><br>
```
{ 
  "senderId" : "aaaaazz", 
  "receiverId" : 5,
  "value" : 200
}
```
HTTP 400 - There was an error parsing your request
<br><br>
```
{ 
  "senderId" : 1, 
  "receiverId" : 1,
  "value" : 250
}
```
HTTP 400 - There was an error parsing your request
