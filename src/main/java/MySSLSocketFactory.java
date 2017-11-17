// Encrypt.java  Sybase Product Support group,  12/21/98
//  Copyright (c) 1997, Sybase., Emeryville, CA 94608
//                      All Rights Reserved
//
//  TITLE: Encrypt.java
//
//  START-HISTORY:
//
//    21 Dec 98  edit 0 - Maria Chavez Cantu
//               Initial coding.
//
//  END-HISTORY
//
//

import com.sybase.jdbcx.SybSocketFactory;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * <HR>
 * <B><FONT=+2><U>SSL Security Protocol</U></FONT><B>
 * <P>
 * Security Sockets Layer (SSL) is a security protocol for protecting TCP/IP
 * communication between a client and server.  SSL provides three basic
 * security features:
 * <UL>
 *    <LI> Encryption, to ensure the privacy of communication
 *    <LI> Authentication, to verify the identity of a client or server and
 *         prevent impersonations or message forgeries
 *    <LI> Message verification, to make sure that a message has not been
 *         tampered with.
 * </UL>
 * When jConnect is used with SSL, all TDS communication sent to the server
 * is encrypted.
 *
 * <P><B>NOTE:</B>  jConnect with SSL is for Java client applications that
 * need to establish a secure connection with a server.  Currently, no
 * Sybase servers support SSL.  Support is planned for future Sybase products.
 * An applet that needs a secure connection should use an HTTPS gateway.
 * See Appendix A, "Web Server Gateways" in the jConnect Programmer's
 * Reference.
 *
 * See sample2/Encrypt.java for a sample on how to execute/use this
 * SybSocketFactory implementation.
 */
public class MySSLSocketFactory extends SSLSocketFactory implements SybSocketFactory
{
    /**
     * Create a socket, set the cipher suites it can use, return the
     * socket.
     * <BR> Demonstrates how cipher suites could be hard
     * coded into the implementation.
     *
     * @param host - server host name
     * @param port - server port number
     * @return Socket - SSLSocket instance
     */
    public Socket createSocket(String host, int port) throws IOException
    {
        SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        return factory.createSocket(host, port);
    }
    /**
     * return a SSLSocket
     * <BR> Demonstrates how to set cipher suites based on
     * connection properties.
     *
     *  <P>Could have set connection properties like:
     *  <BR> Of course the property names can be user defined.
     *  <UL>
     *  <LI> Properties _props = new Properties();
     *  <LI> Set other url, password, etc properties.
     *  <LI> _props.put("CIPHER_SUITES_1",
     *           "SSL_DH_anon_EXPORT_WITH_RC4_40_MD5");
     *
     *  <P>The following is an example of other cipher suites that could
     *  be set granted your <code>ServerSocket</code> has them enabled.
     *  <LI> _props.put("CIPHER_SUITES_2",
     *           "SSL_DH_DSS_EXPORT_WITH_DES40_CBC_SHA");
     *  <LI> _props.put("CIPHER_SUITES_3",
     *           "SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5");
     *  <LI> _props.put("CIPHER_SUITES_4",
     *           "SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA");
     *  <LI> _conn = _driver.getConnection(url, _props);
     * </UL>
     *
     * @param host - server host name
     * @param port - server port number
     * @param props - connection properties
     * @return Socket - SSLSocket instance
     * @exception IOException
     * @exception UnknownHostException
     * @see com.sybase.jdbcx.SybSocketFactory#createSocket
     */
    public Socket createSocket(String host, int port,
                               Properties props)
        throws IOException, UnknownHostException
    {

        SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        Socket sslSocket = (SSLSocket) factory.createSocket(host, port);
        sslSocket.setKeepAlive(true);
        return sslSocket;
    }

    // javax.net.SSLSocketFactory contains various other methods
    // that need to be implemented because they are abstract.
    // They are not necessary for this simply, therefore we shall
    // simply stub them.

    /**
     *  Not implemented for this sample
     *  @return null
     */
    public String[] getDefaultCipherSuites()
    {
        return null;
    }
    /**
     *  Not implemented for this sample
     *  @return null
     */
    public String[] getSupportedCipherSuites()
    {
        return null;
    }

    @Override
    public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException {
        return null;
    }

    /**
     *  Not implemented for this sample
     *  @return null
     */
    @Override
    public Socket createSocket(String host, int port,
                                        java.net.InetAddress clientAddress, int clientPort)
    {
        return null;
    }
    /**
     *  Not implemented for this sample
     *  @return null
     */
    @Override
    public Socket createSocket(java.net.InetAddress host, int port)
    {
        return null;
    }
    /**
     *  Not implemented for this sample
     *  @return null
     */
    @Override
    public Socket createSocket(java.net.InetAddress host, int
        port, java.net.InetAddress clientAddress, int clientPort)
    {
        return null;

    }
}
