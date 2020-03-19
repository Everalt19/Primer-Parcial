package com.primerparcial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.exit;


public class MainActivity extends AppCompatActivity {



    public static final String TAG = MainActivity.class.getSimpleName();

    Spinner grupo;
    EditText nombre,tel;
    List<Contact> list;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    void initView(){
        list = new ArrayList<>();
        nombre = findViewById(R.id.txt_nombre);
        tel = findViewById(R.id.txt_telefono);
        grupo = findViewById(R.id.spinner_grupo);
    }

    void initListener(){

        findViewById(R.id.btn_cancelar).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                    exit(0);
            }
        });

        findViewById(R.id.btn_guardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validar()){
                     Contact contact = new Contact(
                            nombre.getText().toString(),
                            tel.getText().toString(),
                            grupo.getItemAtPosition(grupo.getSelectedItemPosition()).toString());


                    Toast.makeText(getApplicationContext(), "Contacto Agregado", Toast.LENGTH_SHORT).show();
                    tel.setText("");
                    nombre.setText("");
                    nombre.requestFocus();

                    list.add(contact);

                }
            }
        });

        grupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_diseno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {



        switch (item.getItemId()) {
            case R.id.btn_limpiar:
                tel.setText("");
                nombre.setText("");
                nombre.requestFocus();
                break;

            case R.id.btn_lista:

                Intent i = new Intent(MainActivity.this, ActivityLista.class);
                i.putParcelableArrayListExtra("listData", (ArrayList<? extends Parcelable>) list);
                startActivityForResult(i, 200);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200){
            if (resultCode == RESULT_OK){
                if (data.getBooleanExtra("clear", false))
                    list.clear();
            }
        }
    }

    private boolean Validar(){
        if (nombre.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingrese Un Nombre", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tel.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingrese Un Telefono", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
