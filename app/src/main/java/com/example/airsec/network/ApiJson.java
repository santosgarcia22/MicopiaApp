package com.example.airsec.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
public class ApiJson {
    private static final Gson G = new Gson();

    public static boolean ok(JsonElement root) {
        if (root == null || !root.isJsonObject()) return false;
        JsonObject o = root.getAsJsonObject();
        return o.has("ok") && o.get("ok").getAsBoolean();
    }

    public static JsonElement data(JsonElement root) {
        if (root == null || !root.isJsonObject()) return null;
        JsonObject o = root.getAsJsonObject();
        return o.has("data") ? o.get("data") : null;
    }

    public static <T> List<T> getList(JsonElement data, Class<T> cls) {
        if (data == null) return Collections.emptyList();
        if (data.isJsonArray()) {
            Type t = TypeToken.getParameterized(List.class, cls).getType();
            return G.fromJson(data, t);
        }
        if (data.isJsonObject()) {
            JsonObject o = data.getAsJsonObject();
            if (o.has("data") && o.get("data").isJsonArray()) {
                Type t = TypeToken.getParameterized(List.class, cls).getType();
                return G.fromJson(o.get("data"), t);
            }
        }
        return Collections.emptyList();
    }

    public static <T> T getObject(JsonElement data, Class<T> cls) {
        if (data == null) return null;
        if (data.isJsonObject()) {
            JsonObject o = data.getAsJsonObject();
            if (o.has("data") && o.get("data").isJsonObject()) {
                return G.fromJson(o.get("data"), cls);
            }
            return G.fromJson(o, cls);
        }
        return null;
    }

}
