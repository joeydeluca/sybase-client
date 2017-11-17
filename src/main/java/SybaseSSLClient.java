import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SybaseSSLClient {

    public static void main(String args[]) throws Exception {
        System.setProperty("javax.net.debug", "all");
        System.setProperty("javax.net.ssl.trustStore", "");
        System.setProperty("javax.net.ssl.trustStorePassword", "");

        final String dbUrl = "jdbc:sybase:Tds:xxx";
        final String username = "";
        final String password = "";

        try {
            Connection connection = DriverManager.getConnection(dbUrl + "?SYBSOCKET_FACTORY=com.bns.baas.MySSLSocketFactory", username, password);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
