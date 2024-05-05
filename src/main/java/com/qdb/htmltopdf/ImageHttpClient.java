package com.qdb.htmltopdf;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ImageHttpClient {

    public byte[] execute(String url) {
        HttpResponse httpResponse = null;
        byte[] response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            httpResponse = httpClient.execute(new HttpGet(url));
            response = httpResponse.getEntity().getContent().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
