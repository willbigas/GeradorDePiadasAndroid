package br.com.willbigas.api;

import android.view.textclassifier.TextLinks;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.com.willbigas.model.Piada;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DaoServer {

    private static String URL_BASE = "https://us-central1-kivson.cloudfunctions.net/charada-aleatoria";

    public static String getURL() throws IOException {
        URL url = new URL(URL_BASE);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("content-type", "application/json");
        connection.connect();
        String jsonResposta = new Scanner(connection.getInputStream()).next();
        return jsonResposta;
    }

    public static Piada getOkHttp(String json) throws IOException {
        Piada piada = new Piada();
        Gson gson = new Gson();

        OkHttpClient httpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(URL_BASE);
        builder.addHeader("Accept", "application/json");
        builder.addHeader("content-type", "application/json");
        builder.get();
        Request request = builder.build();
        ResponseBody responseBody = httpClient.newCall(request).execute().body();

        piada = gson.fromJson(responseBody.string(), Piada.class);
        return piada;
    }

    public static Piada get() throws IOException {
        String result = getURL();
        return getOkHttp(result);
    }

}
