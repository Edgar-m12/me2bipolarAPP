package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    EditText IdPaciente, nombre, telefono, correo, genero, pwd; //nombre de las variables
    Button btnAgregar, btnConsultar, btnModificar, btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IdPaciente = (EditText) findViewById(R.id.txt_IdPaciente); //txt son los identificadores de los cuadros
        nombre = (EditText) findViewById(R.id.txt_nombre);
        telefono = (EditText) findViewById(R.id.txt_telefono);
        correo = (EditText) findViewById(R.id.txt_correo);
        genero = (EditText) findViewById(R.id.txt_genero);
        pwd = (EditText) findViewById(R.id.txt_pwd);
        btnAgregar = (Button) findViewById(R.id.btnAgregarUsuario);
        btnConsultar = (Button) findViewById(R.id.btnConsultarUsuario);
        btnModificar = (Button) findViewById(R.id.btnModificarUsuario);
        btnBorrar = (Button) findViewById(R.id.btnBorrarUsuario);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarUsuario();
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultaUsuario();
            }
        });
    }

    public Connection conexionBD() {
        Connection conexion = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://192.140.1.37;DatabaseName = Developer");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }

    public void agregarUsuario() {
        try {
            PreparedStatement pat = conexionBD().prepareStatement("Introduce en usuarios ");
            pat.setString(1, IdPaciente.getText().toString());
            pat.setString(2, nombre.getText().toString());
            pat.setString(3, telefono.getText().toString());
            pat.setString(4, correo.getText().toString());
            pat.setString(5, genero.getText().toString());
            pat.setString(6, pwd.getText().toString());
            Toast.makeText(getApplicationContext(), "Agregado con exito", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void consultaUsuario() {
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("SELECT*FROM paciente WHERE IdPaciente=" + IdPaciente.getText().toString() + "");
            if (rs.next()) {
                nombre.setText(rs.getString(2));
                telefono.setText(rs.getString(3));
                correo.setText(rs.getString(4));
                genero.setText(rs.getString(5));
                pwd.setText(rs.getString(6));
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

/* //Codigo viejo
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
    }*/
}