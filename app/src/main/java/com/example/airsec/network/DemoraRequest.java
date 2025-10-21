package com.example.airsec.network;

public class DemoraRequest {
    public String motivo;
    public Integer minutos;
    public Long agente_id; // opcional

    public DemoraRequest(String motivo, Integer minutos, Long agenteId) {
        this.motivo = motivo;
        this.minutos = minutos;
        this.agente_id = agenteId;
    }
}
