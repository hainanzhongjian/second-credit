package com.second.credit.spider.utils;

import org.apache.http.HttpHost;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class MyPlainConnectionSocketFactory implements ConnectionSocketFactory {
    private static final Logger LOG = Logger.getLogger(MyPlainConnectionSocketFactory.class);
    private InetSocketAddress proxy;

    public MyPlainConnectionSocketFactory() {
        super();
    }

    public MyPlainConnectionSocketFactory(InetSocketAddress proxy) {
        super();
        this.proxy = proxy;
    }

    public Socket createSocket(final HttpContext context) throws IOException { // NOSONAR
        LOG.info("MyPlainConnectionSocketFactory.createSocket with proxy " + proxy);
        if (this.proxy != null) {
            Proxy proxys = new Proxy(Proxy.Type.SOCKS, this.proxy);
            return new Socket(proxys);// NOSONAR
        }
        return new Socket();// NOSONAR
    }

    public Socket connectSocket(final int connectTimeout, final Socket socket, final HttpHost host, // NOSONAR
            final InetSocketAddress remoteAddress, final InetSocketAddress localAddress, final HttpContext context)
            throws IOException {
        final Socket sock = socket != null ? socket : createSocket(context);
        if (localAddress != null) {
            sock.bind(localAddress);
        }
        try {
            sock.connect(remoteAddress, connectTimeout);
        } catch (final IOException ex) {
            try {
                sock.close();
            } catch (final IOException ignore) {
                throw new RuntimeException(ignore);
            }
            throw ex;
        }
        return sock;
    }

    public InetSocketAddress getProxy() {
        return proxy;
    }

    public void setProxy(InetSocketAddress proxy) {
        this.proxy = proxy;
    }
}
