package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //Declaramos Variables
    EditText txtNumero;
    ProgressBar pbProgreso;
    DatePicker dpFecha;
    TimePicker tpHora;
    TextView lblFechaHora;

    private Calendar selectedDateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializamos controles
        this.InicializarControles();

        // Agregar un listener al EditText para actualizar el ProgressBar
        txtNumero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Obtener el valor ingresado en el EditText
                String value = charSequence.toString();

                if (!value.isEmpty()) {
                    // Convertir el valor a un número entero
                    int progress = Integer.parseInt(value);

                    // Actualizar el progreso del ProgressBar
                    pbProgreso.setProgress(progress);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Listener para el DatePicker
        dpFecha.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Actualizar el calendario con la fecha seleccionada
                selectedDateTime.set(Calendar.YEAR, year);
                selectedDateTime.set(Calendar.MONTH, monthOfYear);
                selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Actualizar el TextView con la fecha seleccionada
                updateSelectedDateTimeTextView();
            }
        });

        // Listener para el TimePicker
        tpHora.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Actualizar el calendario con la hora seleccionada
                selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                selectedDateTime.set(Calendar.MINUTE, minute);

                // Actualizar el TextView con la hora seleccionada
                updateSelectedDateTimeTextView();
            }
        });
    }
    private void InicializarControles() {
        txtNumero = (EditText)findViewById(R.id.txtNumero);

        pbProgreso = (ProgressBar)findViewById(R.id.pbProgreso);

        dpFecha = (DatePicker)findViewById(R.id.dpFecha);
        tpHora = (TimePicker)findViewById(R.id.tpHora);

        lblFechaHora = (TextView)findViewById(R.id.lblFechaHora);
    }

    //Concatenado la fecha y hora
    private void updateSelectedDateTimeTextView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String selectedDateTimeString = dateFormat.format(selectedDateTime.getTime());
        lblFechaHora.setText(selectedDateTimeString);
    }
}