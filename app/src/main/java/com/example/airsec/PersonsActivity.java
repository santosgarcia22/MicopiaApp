package com.example.airsec;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsec.adapter.PersonAdapter;
import com.example.airsec.model.Acceso;
import com.example.airsec.repo.FlightRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class PersonsActivity extends AppCompatActivity {

    private FlightRepository repo;
    private long flightId;

    private TextInputEditText etBuscarDoc;
    private RecyclerView rv;
    private PersonAdapter adapter;

    private void goTo(Class<?> target) {
        Intent i = new Intent(this, target);
        i.putExtra("flight_id", flightId);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        flightId = getIntent().getLongExtra("flight_id", -1);

        if (flightId == -1) {
            Toast.makeText(this, "ID de vuelo no recibido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        android.util.Log.d("PersonsActivity", "Vuelo asociado ID = " + flightId);
        Toast.makeText(this, "Flight ID del Vuelo: " + flightId, Toast.LENGTH_SHORT).show();


        repo = new FlightRepository(this);
        flightId = getIntent().getLongExtra("flight_id", -1);

        etBuscarDoc = findViewById(R.id.etBuscarDoc);
        rv = findViewById(R.id.rvPersonas);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PersonAdapter(
                // onEntrada
                person -> registrarEntrada(person.identificacion),
                // onSalida
                person -> registrarSalida(person.identificacion)
        );
        rv.setAdapter(adapter);

        // Nav inferior
        findViewById(R.id.btnNavCabecera).setOnClickListener(v -> goTo(FlightActivity.class));
        findViewById(R.id.btnNavTiempos).setOnClickListener(v -> goTo(TimesActivity.class));
        findViewById(R.id.btnNavPersonas).setOnClickListener(v -> goTo(PersonsActivity.class));

        // Botón "Salida" de la tarjeta superior

        // Botón "Registrar Acceso" (muestra diálogo para nueva persona)
        findViewById(R.id.btnAgregarPersona).setOnClickListener(v -> mostrarDialogoEntrada());

        cargar();
    }

    private void cargar() {
        new Thread(() -> {
            List<Acceso> lista = repo.listarAccesos(flightId);
            runOnUiThread(() -> adapter.submit(lista));
        }).start();
    }

    /** Marca entrada (E1 o E2 según corresponda) para el documento. */
    private void registrarEntrada(String doc) {
        new Thread(() -> {
            repo.registrarEntradaPorDoc(flightId, doc);
            runOnUiThread(() -> {
                toast("Entrada registrada");
                cargar();
            });
        }).start();
    }

    /** Marca salida (S1 o S2 según corresponda) para el documento. */
    private void registrarSalida(String doc) {
        new Thread(() -> {
            repo.registrarSalidaPorDoc(flightId, doc);
            runOnUiThread(() -> {
                toast("Salida registrada");
                cargar();
            });
        }).start();
    }

    private void mostrarDialogoEntrada() {
        final View view = getLayoutInflater().inflate(R.layout.dialog_person_entry, null, false);
        final TextInputEditText etNombre  = view.findViewById(R.id.etNombre);
        final TextInputEditText etIdent   = view.findViewById(R.id.etIdentificacion);
        final TextInputEditText etEmpresa = view.findViewById(R.id.etEmpresaArea);
        final com.google.android.material.checkbox.MaterialCheckBox cbTools =
                view.findViewById(R.id.cbHerramientas);
        final TextInputEditText etMotivo  = view.findViewById(R.id.etMotivo);

        new AlertDialog.Builder(this)
                .setTitle("Registrar entrada")
                .setView(view)
                .setPositiveButton("Guardar Acceso", (d, w) -> {
                    String nombre = textOf(etNombre);
                    String idDoc  = textOf(etIdent);
                    String emp    = textOf(etEmpresa);
                    String mot    = textOf(etMotivo);
                    byte tools    = (byte) (cbTools.isChecked() ? 1 : 0);

                    if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(idDoc)) {
                        toast("Nombre e identificación son requeridos");
                        return;
                    }

                    // 💾 Guardar localmente + enviar al servidor en segundo plano
                    new Thread(() -> {

                        try {
                        // crea registro si no existe + marca la primera entrada
                        repo.crearOActualizarAccesoYPrimeraEntrada(
                                flightId, nombre, idDoc, emp, tools, mot);
                        runOnUiThread(() -> {
                            toast("✅ Acceso guardado localmente y enviado al servidor");
                            cargar();
                        });

                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(() -> toast("❌ Error al guardar acceso"  + e.getMessage()));
                        }
                    }).start();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }




    // Helpers
    private String textOf(TextInputEditText e) {
        return e.getText() == null ? "" : e.getText().toString().trim();
    }
    private void toast(String m) { Toast.makeText(this, m, Toast.LENGTH_SHORT).show(); }
}
