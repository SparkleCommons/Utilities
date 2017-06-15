package com.qrokodial.sparkle.utilities.io.web;

import com.qrokodial.sparkle.utilities.io.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class WebUtils {
    public static String getContent(String url, String encoding) throws IOException {
        return StreamUtils.readFully(getStream(url, encoding), encoding);
    }

    public static InputStream getStream(String url, String encoding) throws IOException {
        try {
            return toUrl(url, encoding).openStream();
        } catch (IOException e) {
            throw new IOException("Error getting stream from URL", e);
        }
    }

    public static URL toUrl(String urlString, String encoding) throws IOException {
        try {
            URL url = new URL(URLDecoder.decode(urlString, encoding));
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            return uri.toURL();
        } catch (URISyntaxException | MalformedURLException | UnsupportedEncodingException e) {
            throw new IOException("Error building URL from string", e);
        }
    }
}
