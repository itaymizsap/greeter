package org.example.greeter.clients;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DayTimeClientBasic implements DayTimeClient {

    @Value("${daytime.service.endpoint}") String daytimeEndpoint;

    @Override
    public String getDayTimeText() {
        System.out.println(daytimeEndpoint);
        return getDayTimeStringUrl(daytimeEndpoint + "/api/day-time-simple");
    }

    @Override
    public String getDayTimeText(String hour) {
        return getDayTimeStringUrl(daytimeEndpoint + "/api/day-time?hour=" +hour);
    }

    private String getDayTimeStringUrl(String url) {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            String bodyJson = response.body().string();
            JsonObject jsonObject = new Gson().fromJson(bodyJson, JsonObject.class);
            String daytime = jsonObject.get("daytime").getAsString();
            return daytime;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Communication failed with 'Day-Time Service', reason: " + e.getMessage(), e);
        }
    }
}
