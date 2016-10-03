package es.caib.sgtsic.gdeignacio.agendacontactos;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gdeignacio on 1/10/16.
 */

public class Agenda {

    private static int EC_LISTA_VACIA = 0;
    private static int EC_PREVIO_INICIO = 1;
    private static int EC_PRIMERO = 2;
    private static int EC_UNICO = 3;
    private static int EC_INTERCALADO = 4;
    private static int EC_ULTIMO = 5;
    private static int EC_POSTERIOR_ULTIMO = 6;

    private static int ACCION_ANTERIOR = 0;
    private static int ACCION_INSERTAR = 1;
    private static int ACCION_GUARDAR = 2;
    private static int ACCION_BORRAR = 3;
    private static int ACCION_POSTERIOR = 4;

    private int status = 0;
    private int index = 0;

    private static final String TAG ="AGENDA";

    private static Agenda agenda = new Agenda();
    private List<Contacto> contactos = new ArrayList<>();
    private Contacto current= new Contacto();
    private Contacto buffer = new Contacto();
    private boolean prepend = false;
    private Context context;


    /**
     * Construye un objeto de la clase.
     * Este método es privado para forzar el patrón singleton.
     */
    private Agenda(){
        super();
    }

    /**
     * Recupera el objeto singleton de la clase.
     *
     * @return el objeto singleton de la clase Agenda.
     */
    public static Agenda getAgenda(){
        return agenda;
    }


    public List<Contacto> getContactos() {
        return contactos;
    }


    public Contacto getBuffer(){
        return buffer;
    }

    public String cargaContactos(Context context){
        contactos = Persistencia.getPersistencia().obtenerListaContactos(context);
        Log.d(TAG, this.toString());
        if (contactos.isEmpty()) return "No hay contactos";
        current = contactos.get(0);
        buffer = current;
        return "";
    }


    private void gestionarLista(int accion){

        if (status == EC_LISTA_VACIA) {
            if (accion == ACCION_ANTERIOR){
                current = new Contacto();
                buffer = new Contacto();
                index = 0;
                status = EC_LISTA_VACIA;
                return;
            }
            if (accion == ACCION_INSERTAR){
                current = contactos.get(0);
                status = EC_UNICO;
                index = 1;
                return;
            }
            if (accion == ACCION_POSTERIOR){
                current = new Contacto();
                buffer = new Contacto();
                status = EC_LISTA_VACIA;
                index = 0;
                return;
            }
        }

        if (status == EC_PREVIO_INICIO) {
            if (accion == ACCION_ANTERIOR){
                current = new Contacto();
                buffer = new Contacto();
                status = EC_PREVIO_INICIO;
                index = 0;
                return;
            }
            if (accion == ACCION_INSERTAR){
                current = contactos.get(0);
                status = (contactos.size()==1)?EC_UNICO:EC_PRIMERO;
                index = (contactos.size()==1)?1:2;
                return;
            }
            if (accion == ACCION_POSTERIOR){
                current = contactos.get(0);
                buffer = current;
                status = (contactos.size()==1)?EC_UNICO:EC_PRIMERO;
                index = (contactos.size()==1)?0:1;
                return;
            }
        }

        if (status == EC_PRIMERO) {
            if (accion == ACCION_ANTERIOR){
                current = new Contacto();
                buffer = new Contacto();
                status = EC_PREVIO_INICIO;
                index = 0;
                return;
            }
            if (accion == ACCION_INSERTAR){
                current = contactos.get(1);
                status = EC_INTERCALADO;
                index = 2;
                return;
            }
            if (accion == ACCION_GUARDAR){
                current = contactos.get(0);
                status = EC_INTERCALADO;
                index = 1;
                return;
            }
            if (accion == ACCION_BORRAR){
                current = contactos.get(0);
                buffer = current;
                status = (contactos.size()==1)?EC_UNICO:EC_PRIMERO;
                index = 2;
                return;
            }
            if (accion == ACCION_POSTERIOR){
                current = contactos.get(1);
                buffer = current;
                status = (contactos.size()==2)?EC_ULTIMO:EC_INTERCALADO;
                index = (contactos.size()==2)?1:2;
                return;
            }

        }
        if (status == EC_UNICO){
            if (accion == ACCION_ANTERIOR){

                return;
            }
            if (accion == ACCION_INSERTAR){
                return;
            }
            if (accion == ACCION_GUARDAR){
                return;
            }
            if (accion == ACCION_BORRAR){
                return;
            }
            if (accion == ACCION_POSTERIOR){
                return;
            }

        }
        if (status == EC_INTERCALADO) {
            if (accion == ACCION_ANTERIOR){

                return;
            }
            if (accion == ACCION_INSERTAR){
                return;
            }
            if (accion == ACCION_GUARDAR){
                return;
            }
            if (accion == ACCION_BORRAR){
                return;
            }
            if (accion == ACCION_POSTERIOR){
                return;
            }

        }
        if (status == EC_ULTIMO) {
            if (accion == ACCION_ANTERIOR){

                return;
            }
            if (accion == ACCION_INSERTAR){
                return;
            }
            if (accion == ACCION_GUARDAR){
                return;
            }
            if (accion == ACCION_BORRAR){
                return;
            }
            if (accion == ACCION_POSTERIOR){
                return;
            }

        }
        if (status == EC_POSTERIOR_ULTIMO) {
            if (accion == ACCION_ANTERIOR){

                return;
            }
            if (accion == ACCION_INSERTAR){
                return;
            }
            if (accion == ACCION_GUARDAR){
                return;
            }
            if (accion == ACCION_BORRAR){
                return;
            }
            if (accion == ACCION_POSTERIOR){
                return;
            }

        }
    }



    public String anteriorContacto(Context context) {

        Log.d(TAG, this.toString());

        if (contactos.isEmpty()){
            buffer = new Contacto();
            current = new Contacto();
            Log.d(TAG, this.toString());
            return "Lista vacía. Creando el primer contacto";
        }

        int pos = contactos.indexOf(current);

        if (pos==0){
            buffer = new Contacto();
            current = new Contacto();
            prepend = true;
            return "Ha llegado al principio de la lista";
        }

        if (pos==-1){
            if (prepend) {
                return "Ha llegado al principio de la lista";
            } else {
                pos = contactos.size();
            }
        }

        pos--;
        current = contactos.get(pos);
        buffer = current;
        Log.d(TAG, "Retorn " + this.toString());
        return "";

    }

    public String crearContacto(Context context) {
        buffer = new Contacto();
        return "";
    }

    public String guardarContacto(Context context) {

        Persistencia persistencia = Persistencia.getPersistencia();

        Log.d(TAG,"Guardando contacto: " + buffer.getId() + " con current " + current.getId());

        if (buffer.equals(current) && (persistencia.modificarContacto(context, buffer)==1)) {
            current = contactos.set(contactos.indexOf(current),buffer);
            current = buffer;
            prepend = false;
            Log.d(TAG, this.toString());
            return "El contacto se ha guardado correctamente";
        }

        if (persistencia.crearContacto(context, buffer)==1) {

            if (prepend) {
                contactos.add(0, buffer);
                prepend = false;
            }

            int insertIndex = contactos.indexOf(current)+1;

            if (insertIndex == contactos.size()){
                contactos.add(buffer);
            } else {
                contactos.add(insertIndex, buffer);
            }

            Log.d(TAG, this.toString());

            current = buffer;
            return "El contacto se ha guardado correctamente";
        }

        buffer = current;

        return "El contacto no se pudo guardar";

    }

    public String borrarContacto(Context context) {

        Persistencia persistencia = Persistencia.getPersistencia();

        prepend = false;

        if (contactos.isEmpty()) {
            current = new Contacto();
            buffer = new Contacto();
            return "No hay contactos en la lista";
        }

        if (((Contacto) new Contacto()).equals(current)){
            current = new Contacto();
            buffer = new Contacto();
            return "El contacto está vacío";
        }

        int  currentIndex = contactos.indexOf(current);
        String codigo = current.getId();
        Log.d(TAG, this.toString());
        if ((persistencia.eliminarContacto(context, current)==1) && (contactos.remove(current))){

            if (contactos.isEmpty()){
                current = new Contacto();
                buffer = new Contacto();
                return "El contacto " +  codigo  + " se ha borrado. Esta acción no se puede deshacer. No hay contactos en la lista";
            }

            currentIndex = (currentIndex<contactos.size())?currentIndex:(contactos.size()-1);
            Log.d(TAG, this.toString());
            current = contactos.get(currentIndex);
            buffer = current;

            Log.d(TAG, this.toString());

            return "El contacto " +  codigo  + " se ha borrado. Esta acción no se puede deshacer";
        }

        buffer = current;
        return "El contacto no se pudo borrar";

    }

    public String siguienteContacto(Context context) {

        Log.d(TAG, this.toString());

        if (contactos.isEmpty()){
            buffer = new Contacto();
            current = new Contacto();
            Log.d(TAG, this.toString());
            return "Lista vacía. Creando el primer contacto";
        }

        int pos = contactos.indexOf(current);

        if (pos==(contactos.size()-1)){
            prepend = false;
            buffer = new Contacto();
            current = new Contacto();
            return "Ha llegado al final de la lista";
        }

        if ((pos==-1) && (!prepend)){
            return "Ha llegado al final de la lista";
        }

        prepend = false;
        pos++;
        current = contactos.get(pos);
        buffer = current;
        Log.d(TAG, "Retorn " + this.toString());
        return "";


    }

    @Override
    public String toString() {

        String strAgenda = "Lista: ";

        for (Contacto contacto:contactos){
            strAgenda+=contacto.toString();
        }

        strAgenda+=". Pos:" + contactos.indexOf(current);
        //strAgenda+=". Anchor: " + anchor.toString();
        strAgenda+=". Current: " + current.toString();
        strAgenda+=". Buffer: " + buffer.toString();

        return strAgenda;
    }


}
