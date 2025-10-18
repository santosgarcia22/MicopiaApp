package com.example.airsec;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsec.adapter.FlightsAdapter;
import com.example.airsec.model.Vuelo;
import com.example.airsec.repo.FlightRepository;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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

        try {
            setContentView(R.layout.activity_flights);

            // Toolbar
            MaterialToolbar tb = findViewById(R.id.toolbarFlight);
            setSupportActionBar(tb);
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Información del vuelo");

            repo = new FlightRepository(this);

            RecyclerView rv = findViewById(R.id.rvVuelos);
            rv.setLayoutManager(new LinearLayoutManager(this));

            // ✅ Protección al abrir FlightActivity
            adapter = new FlightsAdapter(v -> {
                try {
                    Intent i = new Intent(this, FlightActivity.class);
                    i.putExtra("flight_id", v.id);

                    // Debug visual
                    Toast.makeText(this, "✈️ Abriendo vuelo ID: " + v.id, Toast.LENGTH_SHORT).show();

                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(this, "❌ Error abriendo vuelo: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
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
                    try {
                        searchText = s == null ? "" : s.toString().trim();
                        aplicarFiltrosYMostrar();
                    } catch (Exception e) {
                        Toast.makeText(FlightsActivity.this, "Error filtrando: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });

            // Filtros
            chipAbiertos.setOnCheckedChangeListener((b, isChecked) -> {
                filterAbiertos = isChecked;
                aplicarFiltrosYMostrar();
            });

            chipCerrados.setOnCheckedChangeListener((b, isChecked) -> {
                filterCerrados = isChecked;
                aplicarFiltrosYMostrar();
            });

            // Crear nuevo vuelo
            fabNuevo.setOnClickListener(v -> {
                try {
                    new Thread(() -> {
                        try {
                            long id = repo.crearVueloInicial(null);
                            runOnUiThread(() -> {
                                try {
                                    Intent i = new Intent(this, FlightActivity.class);
                                    i.putExtra("flight_id", id);
                                    Toast.makeText(this, "🆕 Nuevo vuelo ID: " + id, Toast.LENGTH_SHORT).show();
                                    startActivity(i);
                                } catch (Exception e) {
                                    Toast.makeText(this, "❌ Error al abrir nuevo vuelo: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }
                            });
                        } catch (Exception e) {
                            runOnUiThread(() -> Toast.makeText(this, "❌ Error creando vuelo: " + e.getMessage(), Toast.LENGTH_LONG).show());
                            e.printStackTrace();
                        }
                    }).start();
                } catch (Exception e) {
                    Toast.makeText(this, "❌ Error en hilo nuevo vuelo: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "❌ Error en onCreate: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            cargar();
        } catch (Exception e) {
            Toast.makeText(this, "❌ Error al recargar vuelos: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void cargar() {
        try {
            com.example.airsec.network.ApiService api =
                    com.example.airsec.network.ApiClient.getClient().create(com.example.airsec.network.ApiService.class);

            api.getVuelos(null, null, 1).enqueue(new retrofit2.Callback<com.example.airsec.network.ApiResponseList<com.example.airsec.model.Vuelo>>() {
                @Override
                public void onResponse(retrofit2.Call<com.example.airsec.network.ApiResponseList<com.example.airsec.model.Vuelo>> call,
                                       retrofit2.Response<com.example.airsec.network.ApiResponseList<com.example.airsec.model.Vuelo>> response) {
                    try {
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            List<Vuelo> list = response.body().data.data;
                            if (list == null) list = new ArrayList<>();
                            data.clear();
                            data.addAll(list);
                            aplicarFiltrosYMostrar();
                        } else {
                            Toast.makeText(FlightsActivity.this, "⚠️ Error al obtener datos del servidor", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(FlightsActivity.this, "❌ Error procesando respuesta: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<com.example.airsec.network.ApiResponseList<com.example.airsec.model.Vuelo>> call,
                                      Throwable t) {
                    Toast.makeText(FlightsActivity.this, "🚫 Fallo de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    new Thread(() -> {
                        try {
                            List<Vuelo> locals = repo.listarVuelos();
                            runOnUiThread(() -> {
                                data.clear();
                                data.addAll(locals);
                                aplicarFiltrosYMostrar();
                            });
                        } catch (Exception e) {
                            runOnUiThread(() -> Toast.makeText(FlightsActivity.this, "❌ Error cargando locales: " + e.getMessage(), Toast.LENGTH_LONG).show());
                            e.printStackTrace();
                        }
                    }).start();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "❌ Error general al cargar: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void aplicarFiltrosYMostrar() {
        try {
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
                                (!filterAbiertos && !filterCerrados);

                if (matchTexto && matchEstado) out.add(v);
            }

            adapter.actualizar(out);
            findViewById(R.id.emptyState)
                    .setVisibility(out.isEmpty() ? android.view.View.VISIBLE : android.view.View.GONE);

        } catch (Exception e) {
            Toast.makeText(this, "❌ Error mostrando lista: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private static String safe(String s) {
        return s == null ? "" : s.toLowerCase();
    }
}
