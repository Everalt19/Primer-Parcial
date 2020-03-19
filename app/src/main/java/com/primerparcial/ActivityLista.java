package com.primerparcial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActivityLista extends AppCompatActivity  {



    Spinner grupo;
    EditText nombre, tel;
    Button btncancelar, btnguardar;

    ListView lista;
    ContactAdapter adaptador;
    List<Contact> lista_contacto;
    private static final int REQUEST_ADD = 110;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        initView();

    }

    private void initView(){
        grupo = findViewById(R.id.spinner_grupo);
        nombre = findViewById(R.id.txt_nombre);
        tel = findViewById(R.id.txt_telefono);
        lista_contacto = new ArrayList<>();
        lista = findViewById(R.id.lista_total);
        registerForContextMenu(lista);
        lista_contacto = getIntent().getParcelableArrayListExtra("listData");
        adaptador = new ContactAdapter(this,lista_contacto);
        lista.setAdapter(adaptador);

        findViewById(R.id.ord_nombre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(lista_contacto, new Comparator<Contact>() {
                    public int compare(Contact objeto1, Contact objeto2) {
                        return objeto1.getNombre().compareTo(objeto2.getNombre());
                    }
                });
                adaptador.notifyDataSetChanged();
            }
        });

        findViewById(R.id.invertir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(lista_contacto);
                adaptador.notifyDataSetChanged();
            }
        });

        findViewById(R.id.eliminar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_contacto.clear();
                adaptador.notifyDataSetChanged();
                getIntent().putExtra("clear", true);
            }
        });

        findViewById(R.id.ord_grupo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(lista_contacto);
                adaptador.notifyDataSetChanged();
            }
        });
    }



    @Override
    public void onCreateContextMenu(ContextMenu menud, View v, ContextMenu.ContextMenuInfo menu) {
        super.onCreateContextMenu(menud, v, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_flotante, menud);
    }




    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, getIntent());
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ord_nombre:
                Collections.sort(lista_contacto, new Comparator<Contact>() {
                    public int compare(Contact objeto1, Contact objeto2) {
                        return objeto1.getNombre().compareTo(objeto2.getNombre());
                    }
                });
                adaptador.notifyDataSetChanged();
                return true;
            case R.id.ord_grupo:
                Collections.sort(lista_contacto, new Comparator<Contact>() {
                    public int compare(Contact objeto1, Contact objeto2) {
                        return objeto1.getGrupo().compareTo(objeto2.getGrupo());
                    }
                });
                adaptador.notifyDataSetChanged();
                return true;
            case R.id.eliminar:
                lista_contacto.clear();
                adaptador.notifyDataSetChanged();
                return true;
            case R.id.invertir:
                Collections.reverse(lista_contacto);
                adaptador.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menu_flot = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = menu_flot.position;

        switch (item.getItemId()){
            case R.id.btn_aleatorio:
                lista_contacto.get(index).setTel(numeroAleatorio());
                adaptador.notifyDataSetChanged();
                return true;
            case R.id.btn_mayuscula:
                lista_contacto.get(index).setNombre(lista_contacto.get(index).getNombre().toUpperCase());
                adaptador.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private String numeroAleatorio() {
        String phone = "";
        switch (numeroAleatorio(0,2)){
            case 0:
                phone = "300";
                for (int i=0; i<7; i++){
                    phone +=""+numeroAleatorio(0,9);
                }
                return phone;
            case 1:
                phone = "310";
                for (int i=0; i<7; i++){
                    phone +=""+numeroAleatorio(0,9);
                }
                return phone;
            case 2:
                phone = "320";
                for (int i=0; i<7; i++){
                    phone +=""+numeroAleatorio(0,9);
                }
                return phone;
            default:
                return "Error";
        }
    }

    private int numeroAleatorio(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }



}
