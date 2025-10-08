package com.example.airsec.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsec.R;
import com.example.airsec.model.Acceso;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Muestra cada persona con los 2 botones:
 *  - Registrar entrada
 *  - Registrar salida
 *
 * Reglas:
 *   1) Sin tiempos:         Entrada=ON,  Salida=OFF
 *   2) Entrada1 sin salida: Entrada=OFF, Salida=ON
 *   3) E1 y S1 hechos, E2 vacía: Entrada=ON (para 2ª entrada), Salida=OFF
 *   4) E2 hecha, S2 vacía:  Entrada=OFF, Salida=ON
 *   5) Todo completo (E1/S1/E2/S2): ambos OFF
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.VH> {

    public interface OnEntradaClick {
        void onEntrada(Acceso person);
    }
    public interface OnSalidaClick {
        void onSalida(Acceso person);
    }

    private final List<Acceso> data = new ArrayList<>();
    private final OnEntradaClick onEntrada;
    private final OnSalidaClick onSalida;

    public PersonAdapter(OnEntradaClick onEntrada, OnSalidaClick onSalida) {
        this.onEntrada = onEntrada;
        this.onSalida = onSalida;
    }

    public void submit(List<Acceso> items) {
        data.clear();
        if (items != null) data.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        Acceso a = data.get(pos);

        // Encabezado
        h.tvNombre.setText(a.nombre != null ? a.nombre : "(Sin nombre)");
        String lineaId = (a.identificacion == null ? "" : a.identificacion)
                + (a.empresa == null ? "" : " · " + a.empresa);
        h.tvIdDoc.setText(lineaId.trim());

        // Texto de detalle con las 4 posibles marcas
        String e1 = safeTime(a.horaEntrada);
        String s1 = safeTime(a.horaSalida);
        String e2 = safeTime(a.horaEntrada1);
        String s2 = safeTime(a.horaSalida2);
        String detalle = "Entrada " + e1 + " · Salida " + s1;
        if (a.horaEntrada1 != null || a.horaSalida2 != null) {
            detalle += "\nEntrada " + e2 + " · Salida " + s2;
        }
        h.tvDetalle.setText(detalle);

        // Habilitación de botones según reglas
        boolean entradaEnabled;
        boolean salidaEnabled;

        if (a.horaEntrada == null) {                     // Caso 1
            entradaEnabled = true;
            salidaEnabled  = false;
        } else if (a.horaSalida == null) {               // Caso 2
            entradaEnabled = false;
            salidaEnabled  = true;
        } else if (a.horaEntrada1 == null) {             // Caso 3
            entradaEnabled = true;
            salidaEnabled  = false;
        } else if (a.horaSalida2 == null) {              // Caso 4
            entradaEnabled = false;
            salidaEnabled  = true;
        } else {                                         // Caso 5
            entradaEnabled = false;
            salidaEnabled  = false;
        }

        h.btnEntrada.setEnabled(entradaEnabled);
        h.btnSalida.setEnabled(salidaEnabled);

        h.btnEntrada.setOnClickListener(v -> onEntrada.onEntrada(a));
        h.btnSalida.setOnClickListener(v -> onSalida.onSalida(a));
    }

    @Override
    public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNombre, tvIdDoc, tvDetalle;
        MaterialButton btnEntrada, btnSalida;

        VH(@NonNull View itemView) {
            super(itemView);
            tvNombre   = itemView.findViewById(R.id.tvNombre);
            tvIdDoc    = itemView.findViewById(R.id.tvIdDoc);
            tvDetalle  = itemView.findViewById(R.id.tvDetalle);
            btnEntrada = itemView.findViewById(R.id.btnEntrada);
            btnSalida  = itemView.findViewById(R.id.btnSalida);
        }
    }

    private static String safeTime(String t) {
        if (t == null || t.length() < 16) return "—";
        // "yyyy-MM-ddTHH:mm:ss" -> HH:mm
        try { return t.substring(11, 16); } catch (Throwable ignore) { return t; }
    }
}
