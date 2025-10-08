package com.example.airsec;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsec.adapter.FlightsAdapter;
import com.example.airsec.database.AppDb;
import com.example.airsec.model.Vuelo;
import com.example.airsec.repo.FlightRepository;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightsActivity extends AppCompatActivity {

    private FlightsAdapter adapter;
    private EditText etBuscar;
    private Chip chipAbiertos, chipCerrados;
    private FloatingActionButton fabNuevo;
    private FlightRepository repo;

    private List<Vuelo> data = new ArrayList<>();
    private String searchText = "";
    private boolean filterAbiertos = true;
    private boolean filterCerrados = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        // Toolbar
        MaterialToolbar tb = findViewById(R.id.toolbarFlight);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Información del vuelo");


        repo = new FlightRepository(this);

        RecyclerView rv = findViewById(R.id.rvVuelos);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FlightsAdapter(v -> {
            // abrir cabecera del vuelo seleccionado
            Intent i = new Intent(this, FlightActivity.class);
            i.putExtra("flight_id", v.id);
            startActivity(i);
        });
        rv.setAdapter(adapter);

        etBuscar = findViewById(R.id.etBuscarVuelo);
        chipAbiertos = findViewById(R.id.chipAbiertos);
        chipCerrados = findViewById(R.id.chipCerrados);
        fabNuevo = findViewById(R.id.fabNuevoVuelo);

        // Búsqueda
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                searchText = s == null ? "" : s.toString().trim();
                aplicarFiltrosYMostrar();
            }
        });

        // Filtros
        chipAbiertos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            filterAbiertos = isChecked;
            aplicarFiltrosYMostrar();
        });
        chipCerrados.setOnCheckedChangeListener((buttonView, isChecked) -> {
            filterCerrados = isChecked;
            aplicarFiltrosYMostrar();
        });

        // Crear nuevo
        fabNuevo.setOnClickListener(v -> {
            new Thread(() -> {
                // Si luego tienes login, pasa el userId real aquí
                long id = repo.crearVueloInicial(null);
                runOnUiThread(() -> {
                    Intent i = new Intent(this, FlightActivity.class);
                    i.putExtra("flight_id", id);
                    startActivity(i);
                });
            }).start();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargar();
    }

    private void cargar() {
        new Thread(() -> {
            List<Vuelo> list = AppDb.get(this).vueloDao().list();
            if (list == null) list = Collections.emptyList();
            final List<Vuelo> finalList = list;
            runOnUiThread(() -> {
                data.clear();
                data.addAll(finalList);
                aplicarFiltrosYMostrar();
            });
        }).start();
    }

    private void aplicarFiltrosYMostrar() {
        String q = searchText.toLowerCase();
        List<Vuelo> out = new ArrayList<>();
        for (Vuelo v : data) {
            boolean matchTexto =
                    q.isEmpty() ||
                            (safe(v.origen).contains(q) ||
                                    safe(v.destino).contains(q) ||
                                    safe(v.matricula).contains(q) ||
                                    safe(v.fecha).contains(q) ||
                                    safe(v.numeroVueloLlegando).contains(q) ||
                                    safe(v.numeroVueloSaliendo).contains(q));

            boolean matchEstado =
                    (filterAbiertos && !v.appCerrado) ||
                            (filterCerrados && v.appCerrado) ||
                            (!filterAbiertos && !filterCerrados); // sin filtros -> todo

            if (matchTexto && matchEstado) out.add(v);
        }
        adapter.actualizar(out);
        findViewById(R.id.emptyState).setVisibility(out.isEmpty() ? android.view.View.VISIBLE : android.view.View.GONE);
    }

    private static String safe(String s) {
        return s == null ? "" : s.toLowerCase();
    }
}
