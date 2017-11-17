import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class CreateSocketTest {
    public static void main(String args[]) throws Exception {
        System.setProperty("javax.net.debug", "all");
        System.setProperty("javax.net.ssl.trustStore", "");
        System.setProperty("javax.net.ssl.trustStorePassword", "");

        String urlString = "";
        URL url = new URL(urlString);

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket)factory.createSocket(url.getHost(), 443);

        SSLSession session = ((SSLSocket) socket).getSession();
        Certificate[] cchain = session.getPeerCertificates();
        System.out.println("The Certificates used by peer");
        for (int i = 0; i < cchain.length; i++) {
            System.out.println(((X509Certificate) cchain[i]).getSubjectDN());
        }
        System.out.println("Peer host is " + session.getPeerHost());
        System.out.println("Cipher is " + session.getCipherSuite());
        System.out.println("Protocol is " + session.getProtocol());
        System.out.println("ID is " + new BigInteger(session.getId()));
        System.out.println("Session created in " + session.getCreationTime());
        System.out.println("Session accessed in " + session.getLastAccessedTime());

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String x = in.readLine();
        System.out.println(x);
        in.close();
    }
}
