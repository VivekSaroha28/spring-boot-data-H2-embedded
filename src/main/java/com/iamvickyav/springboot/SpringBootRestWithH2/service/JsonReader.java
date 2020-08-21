package com.iamvickyav.springboot.SpringBootRestWithH2.service;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

    private static String readAll(BufferedInputStream rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            BufferedInputStream rd = new BufferedInputStream(is);
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();

        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://cdn.crunchify.com/wp-content/uploads/code/json.sample.txt");
        System.out.println(json.toString());
        System.out.println("Nacho : "+((JSONObject)json.get("menu")).getString("id"));
    }
}