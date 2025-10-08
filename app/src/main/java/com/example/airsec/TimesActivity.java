package com.example.airsec;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.airsec.database.AppDb;
import com.example.airsec.model.TiemposOperativos;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class TimesActivity extends AppCompatActivity {

    private long flightId;
    private LinearLayout contenedorChips;

    // Mapea cada id de botón con el nombre de columna en la tabla
    private static class BtnMap {
        final int id;
        final String campo;
        BtnMap(int id, String campo) { this.id = id; this.campo = campo; }
    }

    private final BtnMap[] btns = new BtnMap[] {
            new BtnMap(R.id.btnDesabordajeInicio,  "desabordaje_inicio"),
            new BtnMap(R.id.btnDesabordajeFin,     "desabordaje_fin"),
            new BtnMap(R.id.btnAbordajeInicio,     "abordaje_inicio"),
            new BtnMap(R.id.btnAbordajeFin,        "abordaje_fin"),
            new BtnMap(R.id.btnCabinaInicio,       "inspeccion_cabina_inicio"),
            new BtnMap(R.id.btnCabinaFin,          "inspeccion_cabina_fin"),
            new BtnMap(R.id.btnAseoIngreso,        "aseo_ingreso"),
            new BtnMap(R.id.btnAseoSalida,         "aseo_salida"),
            new BtnMap(R.id.btnTripulacionIngreso, "tripulacion_ingreso"),
            new BtnMap(R.id.btnCierrePuerta,       "cierre_puerta")
    };

    private void goTo(Class<?> target) {
        Intent i = new Intent(this, target);
        i.putExtra("flight_id", flightId);
        startActivity(i);
    }

    private static String nowISO() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);

        flightId = getIntent().getLongExtra("flight_id", -1);
        contenedorChips = findViewById(R.id.contenedorChips);

        // Nav inferior
        findViewById(R.id.btnNavCabecera).setOnClickListener(v -> goTo(FlightActivity.class));
        findViewById(R.id.btnNavTiempos).setOnClickListener(v -> goTo(TimesActivity.class));
        findViewById(R.id.btnNavPersonas).setOnClickListener(v -> goTo(PersonsActivity.class));

        // Handlers
        for (BtnMap b : btns) {
            MaterialButton view = findViewById(b.id);
            view.setOnClickListener(v -> registrarTiempo(b.campo, (MaterialButton) v));
        }

        // Cargar y reflejar estado inicial (chips + botones deshabilitados)
        cargarYRefrescarUI();
    }

    // -------- Persistencia --------

    private void registrarTiempo(String campo, MaterialButton clicked) {
        new Thread(() -> {
            AppDb db = AppDb.get(this);
            TiemposOperativos t = db.tiemposOperativosDao().byVuelo(flightId);
            boolean isNew = false;
            if (t == null) {
                t = new TiemposOperativos();
                t.vueloId = flightId;
                isNew = true;
            }

            String now = nowISO();
            switch (campo) {
                case "desabordaje_inicio":       t.desabordajeInicio       = now; break;
                case "desabordaje_fin":          t.desabordajeFin          = now; break;
                case "abordaje_inicio":          t.abordajeInicio          = now; break;
                case "abordaje_fin":             t.abordajeFin             = now; break;
                case "inspeccion_cabina_inicio": t.inspeccionCabinaInicio  = now; break;
                case "inspeccion_cabina_fin":    t.inspeccionCabinaFin     = now; break;
                case "aseo_ingreso":             t.aseoIngreso             = now; break;
                case "aseo_salida":              t.aseoSalida              = now; break;
                case "tripulacion_ingreso":      t.tripulacionIngreso      = now; break;
                case "cierre_puerta":            t.cierrePuerta            = now; break;
            }
            t.updatedAt = now;
            if (isNew) t.createdAt = now;

            if (isNew) db.tiemposOperativosDao().upsert(t);
            else       db.tiemposOperativosDao().update(t);

            TiemposOperativos finalT = t;
            runOnUiThread(() -> {
                Toast.makeText(this, "Tiempo registrado", Toast.LENGTH_SHORT).show();
                // Deshabilita el botón pulsado inmediatamente
                disableButton(clicked, true);
                // Refresca chips y el resto de botones por si hay cambios concurentes
                pintarChips(finalT);
                reflejarEstadoBotones(finalT);
            });
        }).start();
    }

    private void cargarYRefrescarUI() {
        new Thread(() -> {
            TiemposOperativos t = AppDb.get(this).tiemposOperativosDao().byVuelo(flightId);
            runOnUiThread(() -> {
                pintarChips(t);
                reflejarEstadoBotones(t);
            });
        }).start();
    }

    // -------- UI helpers --------

    private void pintarChips(TiemposOperativos t) {
        contenedorChips.removeAllViews();
        if (t == null) return;

        addChipIfNotNull("Desabordaje inicio", t.desabordajeInicio);
        addChipIfNotNull("Desabordaje fin", t.desabordajeFin);
        addChipIfNotNull("Abordaje inicio", t.abordajeInicio);
        addChipIfNotNull("Abordaje fin", t.abordajeFin);
        addChipIfNotNull("Cabina inicio", t.inspeccionCabinaInicio);
        addChipIfNotNull("Cabina fin", t.inspeccionCabinaFin);
        addChipIfNotNull("Ingreso aseo", t.aseoIngreso);
        addChipIfNotNull("Salida aseo", t.aseoSalida);
        addChipIfNotNull("Ingreso tripulación", t.tripulacionIngreso);
        addChipIfNotNull("Cierre puertas", t.cierrePuerta);
    }

    private void reflejarEstadoBotones(TiemposOperativos t) {
        // Si no hay registro aún, todos habilitados
        if (t == null) {
            for (BtnMap b : btns) disableButton((MaterialButton) findViewById(b.id), false);
            return;
        }
        for (BtnMap b : btns) {
            MaterialButton view = findViewById(b.id);
            boolean shouldDisable = isCampoLleno(t, b.campo);
            disableButton(view, shouldDisable);
        }
    }

    private boolean isCampoLleno(TiemposOperativos t, String campo) {
        try {
            String val = (String) Objects.requireNonNull(
                    TiemposOperativos.class.getDeclaredField(nombreProp(campo)).get(t));
            return val != null && !val.isEmpty();
        } catch (Throwable ignore) {
            // Fallback manual (por si ofuscación / cambios)
            switch (campo) {
                case "desabordaje_inicio":       return notEmpty(t.desabordajeInicio);
                case "desabordaje_fin":          return notEmpty(t.desabordajeFin);
                case "abordaje_inicio":          return notEmpty(t.abordajeInicio);
                case "abordaje_fin":             return notEmpty(t.abordajeFin);
                case "inspeccion_cabina_inicio": return notEmpty(t.inspeccionCabinaInicio);
                case "inspeccion_cabina_fin":    return notEmpty(t.inspeccionCabinaFin);
                case "aseo_ingreso":             return notEmpty(t.aseoIngreso);
                case "aseo_salida":              return notEmpty(t.aseoSalida);
                case "tripulacion_ingreso":      return notEmpty(t.tripulacionIngreso);
                case "cierre_puerta":            return notEmpty(t.cierrePuerta);
            }
            return false;
        }
    }

    // Convierte nombres con guión bajo a los campos Java (por si quisieras usar reflexión)
    private String nombreProp(String campoSql) {
        // Nuestros nombres ya coinciden con los @ColumnInfo, y los campos Java usan camel igual,
        // pero aquí lo dejamos por claridad si cambian.
        return campoSql
                .replace("desabordaje_", "desabordaje")
                .replace("abordaje_", "abordaje")
                .replace("inspeccion_cabina_", "inspeccionCabina")
                .replace("aseo_", "aseo")
                .replace("tripulacion_ingreso", "tripulacionIngreso")
                .replace("cierre_puerta", "cierrePuerta");
    }

    private boolean notEmpty(String s) { return s != null && !s.isEmpty(); }

    private void addChipIfNotNull(String label, String iso) {
        if (iso == null || iso.isEmpty()) return;
        Chip c = new Chip(this, null, com.google.android.material.R.style.Widget_Material3_Chip_Assist_Elevated);
        try {
            String hhmm = iso.length() >= 16 ? iso.substring(11, 16) : iso;
            c.setText(label + " • " + hhmm);
        } catch (Exception e) {
            c.setText(label + " • " + iso);
        }
        c.setCheckable(false);
        c.setClickable(false);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.bottomMargin = 6;
        c.setLayoutParams(lp);
        contenedorChips.addView(c);
    }

    private void disableButton(MaterialButton b, boolean disable) {
        b.setEnabled(!disable);
        // feedback visual suave
        b.setAlpha(disable ? 0.45f : 1f);
    }
}
