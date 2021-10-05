package menu;

import okhttp3.*;

import java.io.IOException;

public class HttpClient {

    // one instance, reuse
    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main()  {

       // HttpClient obj = new HttpClient();
       // String response = obj.sendGet(key);


    }

    public String sendGet(String key, double lat, double lng) throws Exception {

        String URL = "https://api.weather.yandex.ru/v2/forecast?lat="+lat+"&lon="+lng+"&limit=3&hours=false";
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("X-Yandex-API-Key", key)  // add request headers
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            // System.out.println(response.body().string());
            return response.body().string();

        }

    }

    public String sendGetTown(String key_town, String town_input) throws Exception {

        String URL= "https://open.mapquestapi.com/geocoding/v1/address?key="+key_town+"&location="+town_input;
        Request request = new Request.Builder()
                .url(URL)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            // System.out.println(response.body().string());
            return response.body().string();

        }

    }


}