package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_IdPaciente , et_nombre, et_telefono,et_correo, et_genero, et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_IdPaciente = (EditText)findViewById(R.id.txt_IdPaciente);
        et_nombre = (EditText)findViewById(R.id.txt_nombre);
        et_telefono = (EditText)findViewById(R.id.txt_telefono);
        et_correo = (EditText)findViewById(R.id.txt_correo);
        et_genero = (EditText)findViewById(R.id.txt_genero);
        et_pwd = (EditText)findViewById(R.id.txt_pwd);
    }

    //Metodo para registrar
    public void Registrar(View view) {
        AdminSQL admin = new AdminSQL(context; this, name; "administracion", factory; null, version; 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String IdPaciente = et_IdPaciente.getText().toString();
        String nombre = et_nombre.getText().toString();
        String telefono = et_telefono.getText().toString();
        String correo = et_correo.getText().toString();
        String genero = et_genero.getText().toString();
        String pwd = et_pwd.getText().toString();

        if(!IdPaciente.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty() && !correo.isEmpty() && !genero.isEmpty() && !pwd.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("IdPaciente",IdPaciente);
            registro.put("nombre",nombre);
            registro.put("telefono",telefono);
            registro.put("correo",correo);
            registro.put("genero",genero);
            registro.put("pwd",pwd);

            BaseDeDatos.insert(table; "pacientes", nullColumnHack; null, registro);
            BaseDeDatos.close();
            et_IdPaciente.setText("");
            et_nombre.setText("");
            et_telefono.setText("");
            et_correo.setText("");
            et_genero.setText("");
            et_pwd.setText("");

            Toast.makeText(context; this, text; "Registro exitoso",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context; this, text; "Faltan camps de ser llenados",Toast.LENGTH_SHORT).show();
        }
    }

    //Merodo para buscar pacientes
    public void Buscar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context; this, name; "paciente", factory; null, version; 1);
        SQLiteOpenHelper BaseDeDatos = admin.getWritableDatabase();

        String IdPaciente = et_codigo.getText().toString();

        if (!IdPaciente.isEmpty()) {
            Cursor fila = BaseDeDatosbase.rawQuery(sql; "select nombre,telefono,correo,genero,pwd from paciente where IdPaciente =" + IdPaciente, selectionArgs; null);
            if (fila.MoveToFirst) {
                et_id.setText(fila.getString(i:0));
                et_nombre.setText(fila.getString(i; 1));
                et_telefono.setText(fila.getString(i; 2));
                et_correo.setText(fila.getString(i; 3));
                et_genero.setText(fila.getString(i; 4));
                et_pwd.setText(fila.getString(i; 5));
                BaseDeDatosbase.close();
            } else {
                Toast.makeText(context; this, text; "No existe individuo", Toast.LENTH_SHORT).show();
                BaseDeDatosbase();
            }
        }
        else {
            Toast.makeText(context; this, text;"Introduce la contraseña de usuario", Toast.LENGTH_SHORT).show();
        }
    }
}