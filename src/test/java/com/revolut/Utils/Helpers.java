package com.revolut.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.revolut.util.Messages.APPLICATION_JSON;

/**
 * Helper class that provides a method to make post requests on integration tests
 *
 * @author m4ndr4ck
 */
public class Helpers {
    /**
     * Try to make money transfer through a POST request on http://localhost:8080/sendMoney
     *
     * @param senderId Money sender
     * @param receiverId Money receiver
     * @param value value to send
     */
    public static boolean sendMoneyTest(int senderId, int receiverId, Double value) throws  Exception {

        final String POST_PARAMS = "{\n" + "\"senderId\": "+senderId+",\r\n" +
                "\"receiverId\": "+receiverId+",\r\n" +
                "\"value\": \""+value.toString()+"\"" + "\n}";
        System.out.println(POST_PARAMS);
        URL obj = new URL("http://localhost:8080/sendMoney");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", APPLICATION_JSON);
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        String inputLine;
        StringBuffer response;
        if (responseCode == 200) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            System.out.println(response.toString());
            return true;
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getErrorStream()));
            response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            System.out.println(response.toString());
            return false;
        }
    }
}
