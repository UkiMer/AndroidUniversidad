package com.example.actividad;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MostrarUsuariosRegistrados extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuarios_registrados);
        lista = findViewById(R.id.listaUsuarios);

        CargaUsuarios();
    }

    public void CargaUsuarios(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "LeagueofLegends", null, 1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        Cursor fila = BaseDatos.rawQuery("Select * from Usuarios", null);
        ArrayList<String> ListaUsuarios = new ArrayList<>();
        if (fila.moveToFirst()){
            do {
                String IDUser = fila.getString(0);
                String NicknameUser = fila.getString(1);
                String CampeonUser = fila.getString(2);
                String RolUser = fila.getString(3);
                String DivisionUser = fila.getString(4);
                String RolUsuario = "";
                if (RolUser == "1"){
                    RolUsuario = "Top";
                }else if (RolUser == "2"){
                    RolUsuario = "Jg";
                }
                String userInfo = "ID: "+IDUser + ", Apodo usuario: " +
                        NicknameUser + ", Campeón: " + CampeonUser + ", Rol: " + RolUsuario + ", División: " + DivisionUser;
                ListaUsuarios.add(userInfo);
            } while (fila.moveToNext());
        }
        BaseDatos.close();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, ListaUsuarios);
        lista.setAdapter(adapter);
    }

    public void ActualizarTabla(View view){
        CargaUsuarios();
    }
}