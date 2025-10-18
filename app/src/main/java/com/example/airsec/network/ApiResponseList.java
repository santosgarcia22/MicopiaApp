package com.example.airsec.network;

import java.util.List;

public class ApiResponseList<T> {
    public boolean ok;
    public DataWrapper<T> data;

    public static class DataWrapper<T> {
        public int current_page;
        public List<T> data;
        // puedes añadir last_page, total, etc si los usás
    }
}
