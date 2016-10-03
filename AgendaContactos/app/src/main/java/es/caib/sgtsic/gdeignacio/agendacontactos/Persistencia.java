package es.caib.sgtsic.gdeignacio.agendacontactos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdeignacio on 29/09/16.
 */

public class Persistencia {

    private static Persistencia persistencia = new Persistencia();
    /**
     * Construye un objeto de la clase.
     * Este método es privado para forzar el patrón singleton.
     */
    private Persistencia(){
        super();
    }

    /**
     * Recupera el objeto singleton de la clase.
     *
     * @return el objeto singleton de la clase Agenda.
     */
    public static Persistencia getPersistencia(){
        return persistencia;
    }


    public  String qryCrearTablaContactos(){
        String query =
                "create table contactos (id text primary key, " +
                        "nombre text, " +
                        "telefono text, " +
                        "facebook text, " +
                        "email text," +
                        "photo blob )";
        return query;
    }

    public ContentValues getContentValuesFromContacto(Contacto contacto){

        ContentValues registro = new ContentValues();

        registro.put("id", contacto.getId());
        registro.put("nombre", contacto.getNombre());
        registro.put("telefono", contacto.getTelefono());
        registro.put("facebook", contacto.getFacebook());
        registro.put("email", contacto.getEmail());
        registro.put("photo", contacto.getPhoto());
        
        return registro;
    }

    public int modificarContacto(Context context, Contacto contacto) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id = contacto.getId();
        ContentValues registro = getContentValuesFromContacto(contacto);
        int cantidad = db.update("contactos", registro, "id='"+id + "'", null);
        db.close();
        return cantidad;

    }

    public int eliminarContacto(Context context, Contacto contacto) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = contacto.getId();
        int cantidad = db.delete("contactos", "id='"+id + "'", null);
        db.close();
        return cantidad;

    }

    public int crearContacto(Context context, Contacto contacto){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro = getContentValuesFromContacto(contacto);
        int cantidad = 0;
        db.insert("contactos", null, registro);
        db.close();
        cantidad = 1;
        return cantidad;

    }

    public Contacto recuperarDatosContacto(Context context, Contacto contacto){
        return consultarContactoById(context, contacto.getId());
    }

    public Contacto consultarContactoById(Context context, String id){

        Contacto contacto = null;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select id, nombre, telefono, facebook, email, photo from contactos where id = '"+id+"'",null);

        if (fila.moveToFirst()){
            contacto = new Contacto();
            contacto.setId(fila.getString(0));
            contacto.setNombre(fila.getString(1));
            contacto.setTelefono(fila.getString(2));
            contacto.setFacebook(fila.getString(3));
            contacto.setEmail(fila.getString(4));
            contacto.setPhoto(fila.getBlob(5));
        }

        db.close();

        return contacto;

    }


    public List<Contacto> obtenerListaContactos(Context context){

        List<Contacto> contactos = new ArrayList<>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select id, nombre, telefono, facebook, email, photo from contactos",null);

        if (fila.moveToFirst()){
            do {
                Contacto contacto = new Contacto();
                contacto.setId(fila.getString(0));
                contacto.setNombre(fila.getString(1));
                contacto.setTelefono(fila.getString(2));
                contacto.setFacebook(fila.getString(3));
                contacto.setEmail(fila.getString(4));
                contacto.setPhoto(fila.getBlob(5));
                contactos.add(contacto);
            } while (fila.moveToNext());
        }

        db.close();
        return contactos;

    }



}
