package menu;

import okhttp3.*;

import java.io.IOException;

public class HttpClient {

    // one instance, reuse
    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String key) throws Exception {

        HttpClient obj = new HttpClient();
        String response = obj.sendGet(key);


    }

    public String sendGet(String key) throws Exception {

        String httpresponse;
        Request request = new Request.Builder()
                .url("https://api.weather.yandex.ru/v2/forecast?lat=55.75396&lon=37.620393&limit=3&hours=false")
                .addHeader("X-Yandex-API-Key", key)  // add request headers
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            // System.out.println(response.body().string());
            return response.body().string();

        }

    }



}