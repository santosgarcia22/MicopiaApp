package com.example.airsec;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private Button btnAcceso, btnSoon1, btnSoon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        MaterialToolbar tb = findViewById(R.id.toolbarMain);
        setSupportActionBar(tb);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("AS-CONTROL");

        btnAcceso = findViewById(R.id.btnAccesoAeronave);
        btnSoon1  = findViewById(R.id.btnSoon1);
        btnSoon2  = findViewById(R.id.btnSoon2);

        // Opción 1: ir al flujo de Control Acceso Aeronave
        btnAcceso.setOnClickListener(v -> {
            // Abre el listado de vuelos para crear/seleccionar y continuar el flujo
            startActivity(new Intent(this, FlightsActivity.class));
        });

        // Opciones 2 y 3: placeholders
        btnSoon1.setOnClickListener(v ->
                Toast.makeText(this, "Próximamente…", Toast.LENGTH_SHORT).show()
        );
        btnSoon2.setOnClickListener(v ->
                Toast.makeText(this, "Próximamente…", Toast.LENGTH_SHORT).show()
        );
    }
}
