package com.example.airsec.network;
import java.util.List;
public class ApiResponse<T> {
    public boolean ok;
    public DataWrapper<T> data;

    public static class DataWrapper<T> {
        public int current_page;
        public List<T> data;
        // podés agregar otros campos de paginación si los necesitás (last_page, total, etc.)
    }
}
