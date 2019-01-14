package com.revolut;

import com.revolut.service.WalletService;
import org.junit.*;

import static com.revolut.Utils.Helpers.sendMoneyTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static spark.Spark.*;
/**
 * Integration tests
 */
public class IntegrationTests {
    /**
     * Start spark server to execute tests
     */
    @BeforeClass
    public static void setUp() throws Exception {
        port(8080);
        WalletService.init();
        awaitInitialization();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        stop();
    }
    /**
     * Account 1 balance: 820.56
     * Account 2 balance: 28320.13
     * Account 3 balance: 1460
     */
    @Test
    public void moneyTransfer() throws Exception {
        // Try to send 40 from account 1 to 2
        assertTrue(sendMoneyTest(1, 2, 40.0));

        // Try to send 320 from account 3 to 1
        assertTrue(sendMoneyTest(3, 1, 320.0));

        // Try to send 810 from account 2 to 3
        assertTrue(sendMoneyTest(2, 3, 810.0));
    }

    @Test
    public void checkSenderBalance() throws Exception{
        // Try to send 4000.45 from account 1 to 2 (balance insufficient)
        assertFalse(sendMoneyTest(1 ,2, 4000.45));

        // Try to send 10150 from account 3 to 3 (balance sufficient)
        assertTrue(sendMoneyTest(1, 3, 121.83));
    }

    @Test
    public void checkIfSenderExists() throws  Exception{
        // Sender id 1 exists
        assertTrue(sendMoneyTest(1, 2, 435.2));

        // Sender id 5 does not exist
        assertFalse(sendMoneyTest(5, 2, 435.2));
    }

    @Test
    public void checkIfReceiverExist() throws  Exception{
        // Receiver id 2 exists
        assertTrue(sendMoneyTest(1, 2, 185.2));

        // Receiver id 5 does not exist
        assertFalse(sendMoneyTest(1, 5, 435.2));
    }
}
