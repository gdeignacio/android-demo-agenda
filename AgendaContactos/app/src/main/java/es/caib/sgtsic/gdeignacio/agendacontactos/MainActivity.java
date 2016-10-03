package es.caib.sgtsic.gdeignacio.agendacontactos;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView photo;
    private EditText editId, editName, editPhone, editMail, editSocial;
    private ImageButton callButton, emailButton, socialButton;
    private ImageButton btnPrevious, btnNew, btnSave, btnDel, btnNext;

    private Agenda agenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photo = (ImageView) findViewById(R.id.photo);

        editId = (EditText) findViewById(R.id.editId);
        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editMail = (EditText) findViewById(R.id.editMail);
        editSocial = (EditText) findViewById(R.id.editSocial);

        callButton = (ImageButton) findViewById(R.id.callButton);
        emailButton = (ImageButton) findViewById(R.id.emailButton);
        socialButton = (ImageButton) findViewById(R.id.socialButton);

        agenda = Agenda.getAgenda();
        cargaContactos();

        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anteriorContacto();
            }
        });

        btnNew = (ImageButton) findViewById(R.id.btnNew);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearContacto();
            }
        });

        btnSave = (ImageButton) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarContacto();
            }
        });

        btnDel = (ImageButton) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarContacto();
            }
        });

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguienteContacto();
            }
        });

    }

    private void cargaContactos() {
        mostrar(agenda.cargaContactos(this));
    }

    private void anteriorContacto() {
        mostrar(agenda.anteriorContacto(this));
    }

    private void crearContacto() {
        mostrar(agenda.crearContacto(this));
    }

    private void guardarContacto() {
        rellenarContacto();
        mostrar(agenda.guardarContacto(this));
    }

    private void rellenarContacto() {
        Contacto contacto = agenda.getBuffer();
        contacto.setId(editId.getText().toString());
        contacto.setNombre(editName.getText().toString());
        contacto.setTelefono(editPhone.getText().toString());
        contacto.setEmail(editMail.getText().toString());
        contacto.setFacebook(editSocial.getText().toString());
        //contacto.setPhoto();
    }

    private void borrarContacto() {
        mostrar(agenda.borrarContacto(this));
    }

    private void siguienteContacto() {
        mostrar(agenda.siguienteContacto(this));
    }

    private void mostrar(String msg){

        Contacto contacto = agenda.getBuffer();

        editId.setText(contacto.getId());
        editName.setText(contacto.getNombre());
        editPhone.setText(contacto.getTelefono());
        editMail.setText(contacto.getEmail());
        editSocial.setText(contacto.getFacebook());
        //photo.setImageDrawable(contacto.getPhoto());

        if (!"".equals(msg)) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

}
