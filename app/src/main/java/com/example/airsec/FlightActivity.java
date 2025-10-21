package com.example.airsec;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.airsec.database.AppDb;
import com.example.airsec.model.Demora;
import com.example.airsec.model.Operador;
import com.example.airsec.model.Vuelo;
import com.example.airsec.network.ApiResponseSingle;
import com.example.airsec.repo.FlightRepository;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import android.widget.ArrayAdapter;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import com.example.airsec.network.ApiClient;
import com.example.airsec.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/** Cabecera del vuelo + dropdowns + mini-form de Demora (única por vuelo). */
public class FlightActivity extends AppCompatActivity {

    private FlightRepository repo;
    private long flightId;

    // Campos cabecera
    private EditText etFecha, etDestino, etOrigenNumero, etMatricula, etPosicion;
    private EditText etNumeroVueloLlegando, etNumeroVueloSaliendo;
    private EditText etHoraLlegada, etHoraSalidaItinerario, etHoraPushback, etTotalPax;

    // Demora
    private EditText etDemoraMotivo, etDemoraMinutos;
    private Button btnGuardarDemora, btnBorrarDemora;
    private TextView tvDemoraResumen;
    private View cardDemoraResumen;
    private boolean hasDemora = false;

    // Dropdowns
    private MaterialAutoCompleteTextView dropOperador, dropCoordinador;
    private final List<Operador> operadores = new ArrayList<>();
    private final List<Operador> coordinadores = new ArrayList<>();
    private Long selectedOperadorId = null, selectedCoordinadorId = null;

    // ---- Navegación
    private void goTo(Class<?> target) {
        Intent i = new Intent(this, target);
        i.putExtra("flight_id", flightId);
        startActivity(i);
    }

    private void setupExposedDropdown(MaterialAutoCompleteTextView v) {
        v.setThreshold(0);
        v.setOnClickListener(x -> v.showDropDown());
        v.setOnFocusChangeListener((x, hasFocus) -> { if (hasFocus) v.showDropDown(); });
    }

    // Habilita/deshabilita solo los controles de Demora
    private void setDemoraUiEnabled(boolean editable) {
        etDemoraMotivo.setEnabled(editable && !hasDemora);
        etDemoraMinutos.setEnabled(editable && !hasDemora);
        btnGuardarDemora.setEnabled(editable && !hasDemora);

        btnBorrarDemora.setVisibility(hasDemora ? View.VISIBLE : View.GONE);
        cardDemoraResumen.setVisibility(hasDemora ? View.VISIBLE : View.GONE);
    }

    // Habilita/deshabilita el resto del formulario (no toca Demora)
    private void setEnabledForm(boolean enabled) {
        etFecha.setEnabled(enabled);
        etDestino.setEnabled(enabled);
        etOrigenNumero.setEnabled(enabled);
        etMatricula.setEnabled(enabled);
        etPosicion.setEnabled(enabled);
        etNumeroVueloLlegando.setEnabled(enabled);
        etNumeroVueloSaliendo.setEnabled(enabled);
        etHoraLlegada.setEnabled(enabled);
        etHoraSalidaItinerario.setEnabled(enabled);
        etHoraPushback.setEnabled(enabled);
        etTotalPax.setEnabled(enabled);
        dropOperador.setEnabled(enabled);
        dropCoordinador.setEnabled(enabled);

        findViewById(R.id.btnGuardarVuelo).setEnabled(enabled);
        findViewById(R.id.btnBloquear).setEnabled(enabled);
        // Navegación siempre activa
        findViewById(R.id.btnIrTiempos).setEnabled(true);
        findViewById(R.id.btnIrPersonas).setEnabled(true);
        findViewById(R.id.btnIrListado).setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
        setContentView(R.layout.activity_flight);

        flightId = getIntent().getLongExtra("flight_id", -1);

        // Debug rápido para confirmar qué ID llega
        android.widget.Toast.makeText(this, "Flight ID del vuelo: " + flightId, android.widget.Toast.LENGTH_SHORT).show();
        android.util.Log.d("FlightActivityDebug", "Flight ID recibido = " + flightId);

        // === Toolbar ===
        MaterialToolbar tb = findViewById(R.id.toolbarFlight);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Información del vuelo");

        // === Inicializar repo y obtener ID ===
        repo = new FlightRepository(this);
        // Aseguramos capturar ambos posibles nombres del intent


        if (flightId == -1) {
            flightId = getIntent().getLongExtra("flight_id", -1);
        }

        if (flightId <= 0) {
            Toast.makeText(this, "Modo sin vuelo (solo vista)", Toast.LENGTH_SHORT).show();
            return;
        }

        // === findViewById: cabecera ===
        etFecha = findViewById(R.id.etFecha);
        etDestino = findViewById(R.id.etDestino);
        etOrigenNumero = findViewById(R.id.etOrigenNumero);
        etMatricula = findViewById(R.id.etMatricula);
        etPosicion = findViewById(R.id.etPosicion);
        etNumeroVueloLlegando = findViewById(R.id.etNumeroVueloLlegando);
        etNumeroVueloSaliendo = findViewById(R.id.etNumeroVueloSaliendo);
        etHoraLlegada = findViewById(R.id.etHoraLlegada);
        etHoraSalidaItinerario = findViewById(R.id.etHoraSalidaItinerario);
        etHoraPushback = findViewById(R.id.etHoraPushback);
        etTotalPax = findViewById(R.id.etTotalPax);

        // === Demora ===
        etDemoraMotivo  = findViewById(R.id.etDemoraMotivo);
        etDemoraMinutos = findViewById(R.id.etDemoraMinutos);
        btnGuardarDemora = findViewById(R.id.btnGuardarDemora);
        btnBorrarDemora  = findViewById(R.id.btnBorrarDemora);
        tvDemoraResumen  = findViewById(R.id.tvDemoraResumen);
        cardDemoraResumen= findViewById(R.id.cardDemoraResumen);

        // === Dropdowns ===
        dropOperador = findViewById(R.id.dropOperador);
        dropCoordinador = findViewById(R.id.dropCoordinador);
        setupExposedDropdown(dropOperador);
        setupExposedDropdown(dropCoordinador);

        // === Botones ===
        Button btnGuardar   = findViewById(R.id.btnGuardarVuelo);
        Button btnBloquear  = findViewById(R.id.btnBloquear);
        Button btnIrTiempos = findViewById(R.id.btnIrTiempos);
        Button btnIrPersonas= findViewById(R.id.btnIrPersonas);
        Button btnCerrar    = findViewById(R.id.btnCerrarVuelo);
        Button btnIrListado = findViewById(R.id.btnIrListado);

        btnIrTiempos.setOnClickListener(v -> goTo(TimesActivity.class));
        btnIrPersonas.setOnClickListener(v -> goTo(PersonsActivity.class));
        btnIrListado.setOnClickListener(v -> {
            Intent i = new Intent(this, FlightsActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });

        btnGuardar.setOnClickListener(v -> guardarTodo());
        btnBloquear.setOnClickListener(v -> bloquear());
        btnCerrar.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("Cerrar vuelo")
                .setMessage("¿Seguro que deseas cerrar este vuelo? No podrás editar ni registrar más datos.")
                .setPositiveButton("Cerrar", (d, w) -> cerrarVueloYSalir())
                .setNegativeButton("Cancelar", null)
                .show());

        btnGuardarDemora.setOnClickListener(v -> guardarDemora());
        btnBorrarDemora.setOnClickListener(v -> borrarDemora());

        // === Cargar datos ===
        cargarListasDropdowns(() -> {
            cargarCabecera();   // ahora ya seguro y con API
            cargarDemora();
        });


        } catch (Exception e) {
            Toast.makeText(this, "❌ Error iniciando actividad: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /** Carga operadores/coordinadores desde Room; si no hay datos, usa mock. */
    private void cargarListasDropdowns(Runnable onDone) {
        new Thread(() -> {
            List<Operador> all = AppDb.get(this).operadorDao().all();
            boolean usarMock = (all == null || all.isEmpty());


            operadores.clear();
            coordinadores.clear();

            if (usarMock) {
                // Mock de ejemplo
                Operador op1 = new Operador(); op1.id = 101L; op1.nombre = "TAI";
                Operador op2 = new Operador(); op2.id = 102L; op2.nombre = "LRC";
                Operador op3 = new Operador(); op3.id = 103L; op3.nombre = "AV";
                Operador op4 = new Operador(); op4.id = 103L; op4.nombre = "GUG";
                operadores.add(op1); operadores.add(op2); operadores.add(op3); operadores.add(op4);

                Operador c1 = new Operador(); c1.id = 201L; c1.nombre = "Pedro Ruiz";
                Operador c2 = new Operador(); c2.id = 202L; c2.nombre = "Cesar FLores";
                coordinadores.add(c1); coordinadores.add(c2);
            } else {
                operadores.addAll(all);
                coordinadores.addAll(all); // misma tabla
            }

            List<String> nombresOp = new ArrayList<>();
            for (Operador o : operadores) nombresOp.add(o.nombre);

            List<String> nombresCoord = new ArrayList<>();
            for (Operador o : coordinadores) nombresCoord.add(o.nombre);

            runOnUiThread(() -> {
                dropOperador.setAdapter(new ArrayAdapter<>(
                        this, android.R.layout.simple_dropdown_item_1line, nombresOp));
                dropCoordinador.setAdapter(new ArrayAdapter<>(
                        this, android.R.layout.simple_dropdown_item_1line, nombresCoord));

                dropOperador.setOnItemClickListener((parent, view, position, id) -> {
                    if (position >= 0 && position < operadores.size())
                        selectedOperadorId = operadores.get(position).id;
                });
                dropCoordinador.setOnItemClickListener((parent, view, position, id) -> {
                    if (position >= 0 && position < coordinadores.size())
                        selectedCoordinadorId = coordinadores.get(position).id;
                });

                if (onDone != null) onDone.run();
            });
        }).start();
    }

    // ----- Cabecera

    private void cargarCabecera() {
        new Thread(() -> {
            try {
                Vuelo f = repo.obtenerVuelo(flightId);

                if (f == null) {
                    ApiService api = ApiClient.getClient().create(ApiService.class);

                    api.getVueloById(flightId).enqueue(new Callback<ApiResponseSingle<Vuelo>>() {
                        @Override
                        public void onResponse(Call<ApiResponseSingle<Vuelo>> call,
                                               Response<ApiResponseSingle<Vuelo>> response) {
                            try {
                                if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                                    Vuelo vuelo = response.body().data;
                                    runOnUiThread(() -> {
                                        try {
                                            llenarCamposVuelo(vuelo);
                                            Toast.makeText(FlightActivity.this, "Vuelo cargado desde servidor", Toast.LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            Toast.makeText(FlightActivity.this, "❌ Error llenando campos: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        }
                                    });
                                } else {
                                    runOnUiThread(() -> {
                                        Toast.makeText(FlightActivity.this, "⚠️ Vuelo no encontrado o respuesta vacía", Toast.LENGTH_LONG).show();
                                        limpiarCampos();
                                    });
                                }
                            } catch (Exception e) {
                                runOnUiThread(() -> Toast.makeText(FlightActivity.this, "❌ Error procesando respuesta: " + e.getMessage(), Toast.LENGTH_LONG).show());
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponseSingle<Vuelo>> call, Throwable t) {
                            runOnUiThread(() -> {
                                Toast.makeText(FlightActivity.this, "🚫 Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
                                limpiarCampos();
                            });
                            t.printStackTrace();
                        }
                    });
                    return;
                }

                final boolean cerrado = Boolean.TRUE.equals(f.appCerrado);

                runOnUiThread(() -> {
                    try {
                        llenarCamposVuelo(f);
                        setEnabledForm(!cerrado);
                        findViewById(R.id.btnCerrarVuelo).setEnabled(!cerrado);
                    } catch (Exception e) {
                        Toast.makeText(FlightActivity.this, "❌ Error mostrando datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                });

            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(FlightActivity.this, "❌ Error cargando cabecera: " + e.getMessage(), Toast.LENGTH_LONG).show());
                e.printStackTrace();
            }
        }).start();
    }


    private void llenarCamposVuelo(Vuelo f) {
        try {
            etFecha.setText(formatDate(f.fecha));
            etDestino.setText(nvl(f.destino));
            etOrigenNumero.setText(nvl(f.origen));
            etMatricula.setText(nvl(f.matricula));
            etPosicion.setText(nvl(f.posicionLlegada));
            etNumeroVueloLlegando.setText(nvl(f.numeroVueloLlegando));
            etNumeroVueloSaliendo.setText(nvl(f.numeroVueloSaliendo));
            etHoraLlegada.setText(nvl(f.horaLlegadaReal));
            etHoraSalidaItinerario.setText(nvl(f.horaSalidaItinerario));
            etHoraPushback.setText(nvl(f.horaSalidaPushback));
            etTotalPax.setText(f.totalPax == null ? "" : String.valueOf(f.totalPax));

            if (operadores != null && f.operadorId != null) {
                int pos = indexOfId(operadores, f.operadorId);
                if (pos >= 0) {
                    dropOperador.setText(operadores.get(pos).nombre, false);
                    selectedOperadorId = operadores.get(pos).id;
                }
            }

            if (f.coordinadorId != null) {
                int pos = indexOfId(coordinadores, f.coordinadorId);
                if (pos >= 0) {
                    dropCoordinador.setText(coordinadores.get(pos).nombre, false);
                    selectedCoordinadorId = coordinadores.get(pos).id;
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "❌ Error llenando campos vuelo: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    private void limpiarCampos() {
        etFecha.setText(getFechaActual());
        etDestino.setText("");
        etOrigenNumero.setText("");
        etMatricula.setText("");
        etPosicion.setText("");
        etNumeroVueloLlegando.setText("");
        etNumeroVueloSaliendo.setText("");
        etHoraLlegada.setText("");
        etHoraSalidaItinerario.setText("");
        etHoraPushback.setText("");
        etTotalPax.setText("");

        setEnabledForm(true);
        findViewById(R.id.btnCerrarVuelo).setEnabled(false);
    }



    private void guardarTodo() {
        try {
            final String fecha = txt(etFecha), destino = txt(etDestino), origen = txt(etOrigenNumero),
                    matricula = txt(etMatricula), posicion = txt(etPosicion),
                    numLleg = txt(etNumeroVueloLlegando), numSal = txt(etNumeroVueloSaliendo),
                    hLleg = txt(etHoraLlegada), hItin = txt(etHoraSalidaItinerario), hPush = txt(etHoraPushback);

            final Long opId = selectedOperadorId, coordId = selectedCoordinadorId;
            final Integer pax = safeParseInt(txt(etTotalPax));

            final String motivo = txt(etDemoraMotivo);
            final Integer minutos = safeParseInt(txt(etDemoraMinutos));

            new Thread(() -> {
                try {
                    Vuelo f = repo.obtenerVuelo(flightId);
                    if (f != null && !Boolean.TRUE.equals(f.appCerrado)) {
                        f.fecha = fecha; f.destino = destino; f.origen = origen;
                        f.matricula = matricula; f.posicionLlegada = posicion;
                        f.numeroVueloLlegando = numLleg; f.numeroVueloSaliendo = numSal;
                        f.horaLlegadaReal = hLleg; f.horaSalidaItinerario = hItin; f.horaSalidaPushback = hPush;
                        f.totalPax = pax; f.operadorId = opId; f.coordinadorId = coordId;
                        repo.actualizarVuelo(f);
                    }
                    if (!hasDemora && (!motivo.isEmpty() || minutos != null)) {
                        repo.guardarDemora(flightId, motivo, minutos, null);
                        hasDemora = true;
                    }
                    runOnUiThread(() -> Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(this, "❌ Error guardando: " + e.getMessage(), Toast.LENGTH_LONG).show());
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            Toast.makeText(this, "❌ Error preparando datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    private Integer safeParseInt(String s) {
        try { return TextUtils.isEmpty(s) ? null : Integer.parseInt(s); }
        catch (Exception ignored) { return null; }
    }

    private void bloquear() {
        new Thread(() -> {
            Vuelo f = repo.obtenerVuelo(flightId);
            if (f == null) return;
            f.appBloqueado = true;
            repo.actualizarVuelo(f);
            runOnUiThread(() -> {
                setEnabledForm(false);
                Toast.makeText(this, "Cabecera bloqueada", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    private void cerrarVueloYSalir() {
        new Thread(() -> {
            Vuelo f = repo.obtenerVuelo(flightId);
            if (f == null) return;
            f.appCerrado = true;
            repo.actualizarVuelo(f);
            getSharedPreferences("cfg", MODE_PRIVATE).edit().remove("active_flight_id").apply();
            runOnUiThread(() -> {
                Toast.makeText(this, "Vuelo cerrado", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, FlightsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            });
        }).start();
    }

    // ----- Demora

    /** Carga demora y estado del vuelo en background; actualiza UI en main.  por la versión async:*/
    private void cargarDemora() {
        // 1) leer local en background y pintar en UI
        repo.obtenerDemoraAsync(flightId, d -> pintarDemoraEnUI(d));

        // 2) (opcional) sincronizar desde servidor y repintar
        repo.sincronizarDemora(flightId,
                () -> repo.obtenerDemoraAsync(flightId, d -> pintarDemoraEnUI(d)),
                msg -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        );
    }

    private void pintarDemoraEnUI(@Nullable Demora d) {
        if (d == null) {
            tvDemoraResumen.setText("Sin demora");
            cardDemoraResumen.setVisibility(View.GONE);
            return;
        }
        cardDemoraResumen.setVisibility(View.VISIBLE);
        tvDemoraResumen.setText("Motivo: " + (d.motivo==null?"":d.motivo) +
                "  ·  Minutos: " + (d.minutos==null?0:d.minutos));
        // opcional: setear los EditText con lo que hay
        etDemoraMotivo.setText(d.motivo != null ? d.motivo : "");
        etDemoraMinutos.setText(d.minutos != null ? String.valueOf(d.minutos) : "");
    }



    private void guardarDemora() {
        String motivo = etDemoraMotivo.getText().toString();
        int minutos = 0;
        try { minutos = Integer.parseInt(etDemoraMinutos.getText().toString().trim()); } catch (Exception ignored) {}

        // Guardar local + enviar a servidor
        repo.guardarDemoraYEnviar(flightId, motivo, minutos, /*agenteId*/ null,
                () -> runOnUiThread(() -> {
                    Toast.makeText(this, "Demora guardada", Toast.LENGTH_SHORT).show();
                    cargarDemora(); // refresca UI
                }),
                msg -> runOnUiThread(() -> Toast.makeText(this, msg, Toast.LENGTH_LONG).show())
        );
    }


    private void borrarDemora() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar demora")
                .setMessage("¿Desea eliminar la demora de este vuelo?")
                .setPositiveButton("Eliminar", (d,w) -> {
                    repo.eliminarDemoraAsync(flightId, () -> {
                        Toast.makeText(this, "Demora eliminada", Toast.LENGTH_SHORT).show();
                        cargarDemora();
                    });
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private String formatDate(String raw) {
        if (raw == null || raw.trim().isEmpty() || raw.equals("----")) return "";
        try {
            // Si el backend manda algo como 2025-10-13 o 2025-10-13T00:00:00.000Z
            if (raw.contains("T")) raw = raw.split("T")[0];
            String[] parts = raw.split("-");
            if (parts.length == 3) {
                return parts[2] + "/" + parts[1] + "/" + parts[0]; // dd/MM/yyyy
            }
        } catch (Exception ignored) {}
        return raw;
    }


    private String getFechaActual() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        return sdf.format(new java.util.Date());
    }

    // ---- utils
    private static String nvl(String s) { return s == null ? "" : s; }
    private static String txt(EditText e) {
        return e.getText() == null ? "" : e.getText().toString().trim();
    }
    private static int indexOfId(List<Operador> list, Long id) {
        if (id == null) return -1;
        for (int i = 0; i < list.size(); i++) if (id.equals(list.get(i).id)) return i;
        return -1;
    }
}
