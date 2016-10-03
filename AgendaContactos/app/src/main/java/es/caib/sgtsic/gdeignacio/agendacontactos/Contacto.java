package es.caib.sgtsic.gdeignacio.agendacontactos;

import java.io.Serializable;

/**
 * Created by gdeignacio on 29/09/16.
 */

public class Contacto implements Serializable {

    private String id;
    private String nombre;
    private String telefono;
    private String facebook;
    private String email;
    private byte[] photo;

    public Contacto(){
        this.id = "";
        this.nombre = "";
        this.telefono = "";
        this.facebook = "";
        this.email = "";
        this.photo = null;
    }

    public Contacto(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contacto other = (Contacto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contacto{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono +
                 ", facebook=" + facebook + ", email=" + email +'}';
    }

}
