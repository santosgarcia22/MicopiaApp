package com.example.airsec.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
public class ApiClient {
    // 👉 Reemplaza por la URL real de tu servidor (con / al final)
    public static final String BASE_URL = "http://10.0.2.2:8000/api/v1/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void enviarAcceso(long vueloId, JSONObject data) {
        HttpURLConnection conn = null;
        try {
            // ✅ Endpoint correcto con el id del vuelo
            URL url = new URL(BASE_URL + "vuelos/" + vueloId + "/accesos");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(data.toString().getBytes("UTF-8"));
            os.close();

            int responseCode = conn.getResponseCode();

            // --- Leer la respuesta del servidor (útil para depurar)
            InputStream is = (responseCode < HttpURLConnection.HTTP_BAD_REQUEST)
                    ? conn.getInputStream() : conn.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) response.append(line);
            br.close();

            if (responseCode == 200 || responseCode == 201) {
                System.out.println("✅ Acceso enviado correctamente: " + response);
            } else {
                System.err.println("⚠️ Error al enviar acceso: HTTP " + responseCode + " -> " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.disconnect();
        }
    }


}
