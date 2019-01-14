import com.revolut.service.WalletService;
import org.apache.log4j.BasicConfigurator;

import static spark.Spark.port;

/**
 * Main class that initializes the applications
 *
 * @author m4ndr4ck
 */
public class Initializer {

    public synchronized static void main(String[] args) {
        port(8080);
        new WalletService().init();
    }

}
