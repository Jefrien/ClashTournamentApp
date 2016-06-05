package org.jefrienalvizures.clashtournament.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.jefrienalvizures.clashtournament.bean.Usuario;

/**
 * Created by Familia on 02/06/2016.
 */
public class usuarioDB {



    public void insertar(Context c, Usuario u){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(c,"CT",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues user = new ContentValues();
        user.put("id","1");
        user.put("idUsuario",u.getIdUsuario());
        user.put("usuario",u.getUsuario());
        user.put("nombre",u.getNombre());
        user.put("clan",u.getClan());
        user.put("estado",u.getEstado());

        db.insert("usuario",null,user);
        db.close();
        Log.e("SQLite","Usuario agregado con exito");
    }

    public Usuario obtener(Context c){
        Usuario us = null;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(c,"CT",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("" +
                "select idUsuario,usuario,nombre,clan,estado from usuario where id=1" +
                "",null);
        if(fila.moveToFirst()){
            Log.e("Usuario de SQLITE",fila.getString(0));
            us = new Usuario(
                    fila.getInt(0),
                    fila.getString(1),
                    fila.getString(2),
                    fila.getInt(3)
            );
            us.setEstado(fila.getInt(4));
        } else {
            Log.e("Error SQLITE","No existe el usuario");
        }
        db.close();
        return us;
    }

    public void eliminar(Context c){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(c,"CT",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        int res = db.delete("usuario","id=1",null);
        db.close();
        if(res == 1){
            Log.d("Usuario de SQLITE","ELIMINADO CON EXITO");
        } else {
            Log.e("Error SQLITE","No existe el usuario");
        }
    }

    public void modificar(Context c, Usuario u){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(c,"CT",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();


        ContentValues user = new ContentValues();
        user.put("usuario",u.getIdUsuario());
        user.put("nombre",u.getNombre());
        user.put("clan",u.getClan());
        user.put("estado",u.getEstado());

        int res = db.update("usuario",user,"id=1",null);

        db.close();
        if(res == 1){
            Log.d("Usuario de SQLITE","Añadido a un clan");
        } else {
            Log.e("Error SQLITE","No existe el usuario");
        }
    }

    public boolean logueado(Context c){
        boolean res = false;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(c,"CT",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("" +
                "select idUsuario,usuario,nombre,clan from usuario where id=1" +
                "",null);
        if(fila.moveToFirst()){
            Log.e("Usuario de  loggin",fila.getString(0));
            res = true;

        } else {
            Log.e("Error SQLITE","No existe el usuario");
        }
        db.close();

        return res;
    }

}
