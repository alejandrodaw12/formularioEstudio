package com.example.formularioestudio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edt_dni;
    private EditText edt_nombre;
    private Spinner sp_curso;
    private EditText edt_fecha;
    private EditText edt_hora;

    private String dni;
    private String nombre;
    private String curso;
    private String fecha;
    private String hora;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_dni = (EditText) findViewById(R.id.edt_dni);
        edt_nombre = (EditText) findViewById(R.id.edt_nombre);
        sp_curso = (Spinner) findViewById(R.id.sp_curso);
        edt_fecha = (EditText) findViewById(R.id.edt_fecha);
        edt_hora = (EditText) findViewById(R.id.edt_hora);


        if(sp_curso != null){
            String[] cursos = {"1º DAM", "2º DAM", "1ºSMR", "2º SME"};
            //ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.sa_cursos, R.layout.estilospinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilospinner,cursos);
            sp_curso.setAdapter(adapter);
            sp_curso.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        curso = adapterView.getItemAtPosition(i).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void monstrar(View view) {
        dni = String.valueOf(edt_dni.getText());
        nombre = String.valueOf(edt_nombre.getText());
        fecha = String.valueOf(edt_fecha.getText());
        hora = String.valueOf(edt_hora.getText());

        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("Datos de usuario");
        alerta1.setMessage("¿Son correctos los datos proporcionados?");
        alerta1.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                monstrarAlumnos();
            }
        });
        alerta1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                monstrarMensaje();
            }
        });
        alerta1.show();

    }

    private void monstrarMensaje() {
        Toast.makeText(this, "VUELVE A ESCRIBIR LOS DATOS", Toast.LENGTH_LONG).show();
    }

    private void monstrarAlumnos() {
        Toast.makeText(this,"dni-> " + dni + "\n" + "nombre-> "+ nombre + "\n"+ "fecha de nacimiento-> " + fecha +
                "\n" + "hora preferida de llamada-> " + hora + "\n" + "curso->" + curso, Toast.LENGTH_LONG).show();
    }

    public void coger_fecha(View view) {
        datePickerFragment calendario1 = new datePickerFragment();
        calendario1.show(getSupportFragmentManager(), "DataPicker");
    }

    public void coger_hora(View view) {
        timePickerFragment reloj1 = new timePickerFragment();
        reloj1.show(getSupportFragmentManager(), "TimePicker");
    }

    public void creFecha(int anyo, int mes, int dia) {
        String texto_anyo = String.valueOf(anyo);
        String texto_mes = String.valueOf(mes);
        String texto_dia = String.valueOf(dia);
        fecha = texto_dia + "-" + texto_mes + "-" + texto_anyo;
        edt_fecha.setText(fecha);
    }

    public void crearHora(int horas, int minutos) {
        String texto_hora;
        String texto_minutos;
        if(horas <10){
            texto_hora = "0" + String.valueOf(horas);
        }
        else {
            texto_hora = String.valueOf(horas);
        }
        if(minutos < 10){
            texto_minutos = "0" + String.valueOf(minutos);
        }
        else {
            texto_minutos = String.valueOf(minutos);
        }

        hora = texto_hora + ":" + texto_minutos;
        edt_hora.setText(hora);
    }
}