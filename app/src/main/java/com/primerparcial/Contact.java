package com.primerparcial;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private  String nombre, tel, grupo;

    public Contact(String nombre, String tel, String grupo) {
        this.nombre = nombre;
        this.tel = tel;
        this.grupo = grupo;
    }

    protected Contact(Parcel p){
        nombre = p.readString();
        tel = p.readString();
        grupo = p.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel p) {
           return new Contact(p);
        }
        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(nombre);
        dest.writeString(tel);
        dest.writeString(grupo);
    }
}
