package com.example.airsec.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsec.R;
import com.example.airsec.model.Vuelo;

import java.util.List;
public class VueloAdapter extends RecyclerView.Adapter<VueloAdapter.VueloViewHolder> {

    private final List<Vuelo> vuelos;

    public VueloAdapter(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    @NonNull
    @Override
    public VueloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flight, parent, false);
        return new VueloViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VueloViewHolder holder, int position) {
        Vuelo vuelo = vuelos.get(position);

        // --- Título principal ---
        String titulo = "";
        if (vuelo.numeroVueloLlegando != null && !vuelo.numeroVueloLlegando.isEmpty()
                && vuelo.numeroVueloSaliendo != null && !vuelo.numeroVueloSaliendo.isEmpty()) {
            titulo = vuelo.numeroVueloLlegando + " → " + vuelo.numeroVueloSaliendo;
        } else {
            titulo = "— → —";
        }
        holder.tvTitulo.setText(titulo);

        // --- Subtítulo (origen, destino y fecha formateada) ---
        String fechaCorta = "Sin fecha";
        if (vuelo.fecha != null && !vuelo.fecha.isEmpty()) {
            try {
                java.text.SimpleDateFormat formatoEntrada = new java.text.SimpleDateFormat("yyyy-MM-dd");
                java.text.SimpleDateFormat formatoSalida = new java.text.SimpleDateFormat("dd MMM yyyy");
                java.util.Date fechaObj = formatoEntrada.parse(vuelo.fecha);
                fechaCorta = formatoSalida.format(fechaObj);
            } catch (Exception e) {
                fechaCorta = vuelo.fecha;
            }
        }

        String sub = (vuelo.origen != null ? vuelo.origen : "—") + " → " +
                (vuelo.destino != null ? vuelo.destino : "—") +
                " • " + fechaCorta;
        holder.tvSub.setText(sub);

        // --- Horas (formateadas a HH:mm) ---
        String salida = parseHora(vuelo.horaSalidaItinerario != null
                ? vuelo.horaSalidaItinerario
                : vuelo.horaSalidaPushback);

        String llegada = parseHora(vuelo.horaLlegadaReal);

        holder.tvSalida.setText(salida + "  Salida Programada");
        holder.tvArribo.setText(llegada + "  Arribo Estimado");

        // --- Estado ---
        holder.tvEstado.setText(vuelo.appCerrado ? "Cerrado" : "Abierto");
        holder.tvEstado.setBackgroundResource(
                vuelo.appCerrado ? R.drawable.badge_state_closed : R.drawable.badge_state_open
        );
    }

    // Método auxiliar para formatear hora ISO
    private String parseHora(String horaIso) {
        if (horaIso == null || horaIso.isEmpty()) return "—";
        try {
            // intenta con formato completo ISO
            java.text.SimpleDateFormat formatoEntrada =
                    new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            java.text.SimpleDateFormat formatoSalida =
                    new java.text.SimpleDateFormat("HH:mm");
            java.util.Date date = formatoEntrada.parse(horaIso.substring(0, 19)); // corta el .000000Z
            return formatoSalida.format(date);
        } catch (Exception e) {
            return "—";
        }
    }



    @Override
    public int getItemCount() {
        return vuelos.size();
    }

    static class VueloViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvSub, tvSalida, tvArribo, tvEstado;

        public VueloViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvSub = itemView.findViewById(R.id.tvSub);
            tvSalida = itemView.findViewById(R.id.tvSalida);
            tvArribo = itemView.findViewById(R.id.tvArribo);
            tvEstado = itemView.findViewById(R.id.tvEstado);
        }
    }
}
