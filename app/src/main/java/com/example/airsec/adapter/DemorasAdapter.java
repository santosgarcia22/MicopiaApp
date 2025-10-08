package com.example.airsec.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsec.R;
import com.example.airsec.model.Demora;

import java.util.ArrayList;
import java.util.List;

/** Adapter simple para listar demoras. */
public class DemorasAdapter extends RecyclerView.Adapter<DemorasAdapter.VH> {

    public interface Listener {
        void onItemClick(Demora d);
        void onItemLongClick(Demora d);
    }

    private final List<Demora> data = new ArrayList<>();
    @Nullable private Listener listener;

    public void setListener(@Nullable Listener l) { this.listener = l; }

    public void submit(List<Demora> list) {
        data.clear();
        if (list != null) data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_demora, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int position) {
        Demora d = data.get(position);
        TextView tvMotivo = h.itemView.findViewById(R.id.tvMotivo);
        TextView tvMinutos = h.itemView.findViewById(R.id.tvMinutos);

        tvMotivo.setText(d.motivo != null ? d.motivo : "(sin motivo)");
        tvMinutos.setText("Minutos: " + (d.minutos != null ? d.minutos : 0));

        h.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(d);
        });
        h.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onItemLongClick(d);
            return true;
        });
    }

    @Override
    public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        VH(View itemView) { super(itemView); }
    }
}

