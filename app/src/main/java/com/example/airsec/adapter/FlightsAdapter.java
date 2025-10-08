package com.example.airsec.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsec.R;
import com.example.airsec.model.Vuelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador de la lista de Vuelos.
 * Mapea cada objeto {@link Vuelo} al layout item_flight.xml.
 */
public class FlightsAdapter extends RecyclerView.Adapter<FlightsAdapter.VistaVuelo> {

    /**
     * Interacciones que notificará el adaptador (por ahora, solo click).
     */
    public interface Interacciones {
        void alPulsar(Vuelo vuelo);
    }

    // Fuente de datos que renderiza el RecyclerView
    private final List<Vuelo> lista = new ArrayList<>();

    // Oyente para los eventos de la tarjeta
    @Nullable
    private final Interacciones oyente;

    /**
     * Constructor.
     * @param oyente callbacks para eventos (puede ser null si no se necesitan)
     */
    public FlightsAdapter(@Nullable Interacciones oyente) {
        this.oyente = oyente;
    }

    /**
     * Reemplaza el contenido de la lista y refresca el RecyclerView.
     * Úsalo cuando obtengas nuevos datos desde la API/BD.
     */
    public void actualizar(@Nullable List<Vuelo> nuevaLista) {
        lista.clear();
        if (nuevaLista != null) lista.addAll(nuevaLista);
        notifyDataSetChanged(); // sencillo; si quieres animaciones, pasamos a DiffUtil
    }

    // ===== Métodos obligatorios del RecyclerView.Adapter =====

    /** Infla el layout del ítem y crea el ViewHolder. */
    @Override
    public VistaVuelo onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flight, parent, false);
        return new VistaVuelo(vista);
    }

    /** Enlaza los datos de un Vuelo a las vistas del ViewHolder. */
    @Override
    public void onBindViewHolder(VistaVuelo h, int position) {
        Vuelo v = lista.get(position);

        // --- TÍTULO: "NUM_SALIDA → NUM_LLEGADA"
        String titulo = seguro(v.numeroVueloLlegando) + " \u2192 " + seguro(v.numeroVueloSaliendo); // flecha →
        h.tvTitulo.setText(titulo);

        // --- SUBTÍTULO: "ORIGEN → DESTINO • FECHA (si existe)"
        StringBuilder sub = new StringBuilder();
        sub.append(seguro(v.origen))
                .append(" \u2192 ")
                .append(seguro(v.destino));
        if (noVacio(v.fecha)) sub.append(" \u2022 ").append(v.fecha);                // •
        h.tvSub.setText(sub.toString());

        // --- ESTADO (badge)
        aplicarEstado(h.tvEstado, v.appCerrado);

        // --- HORARIOS (opcionales)
        if (noVacio(v.horaLlegadaReal)) {
            h.tvSalida.setText(v.horaLlegadaReal + "  Salida Programada");
            h.tvSalida.setVisibility(View.VISIBLE);
        } else {
            h.tvSalida.setVisibility(View.GONE);
        }

        if (noVacio(v.horaSalidaItinerario)) {
            h.tvArribo.setText(v.horaSalidaItinerario + "  Arribo Estimado");
            h.tvArribo.setVisibility(View.VISIBLE);
        } else {
            h.tvArribo.setVisibility(View.GONE);
        }

        // --- Click en la tarjeta
        h.itemView.setOnClickListener(view -> {
            if (oyente != null) oyente.alPulsar(v);
        });
    }


    /** Cantidad de elementos que mostrará el RecyclerView. */
    @Override
    public int getItemCount() {
        return lista.size();
    }

    // ===== ViewHolder =====

    /**
     * Contiene las referencias a las vistas del item.
     * Evita búsquedas repetidas (findViewById) en cada bind.
     */
    static class VistaVuelo extends RecyclerView.ViewHolder {
        final TextView tvTitulo;
        final TextView tvSub;
        final TextView tvEstado;
        final TextView tvSalida;
        final TextView tvArribo;

        VistaVuelo(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvSub    = itemView.findViewById(R.id.tvSub);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvSalida = itemView.findViewById(R.id.tvSalida); // nuevo en el layout
            tvArribo = itemView.findViewById(R.id.tvArribo); // nuevo en el layout
        }
    }

    // ===== Utilidades internas =====

    /**
     * Aplica el estado visual al "badge" de estado.
     * @param vistaEstado TextView del badge
     * @param cerrado true si el vuelo está cerrado; false si está abierto
     */
    private void aplicarEstado(TextView vistaEstado, boolean cerrado) {
        if (cerrado) {
            vistaEstado.setText("Cerrado");
            // Usa tu drawable. Si aún tienes badge_red/badge_green, cámbialos aquí.
            vistaEstado.setBackgroundResource(R.drawable.badge_state_closed);
        } else {
            vistaEstado.setText("Abierto");
            vistaEstado.setBackgroundResource(R.drawable.badge_state_open);
        }
    }

    // ===== Utilidades =====
    private static boolean noVacio(@Nullable String s) {
        return s != null && !s.trim().isEmpty();
    }
    private static String seguro(@Nullable String s) {
        return noVacio(s) ? s : "—";
    }


}// cierre


