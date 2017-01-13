package com.warriorminds.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Si venimos desde la notificación tendremos los extras y podemos obtener cada extra de esta manera.
         * Se muestra cada llave y valor en un Toast.
         */
        if (getIntent().getExtras() != null) {
            StringBuilder datosRecibidos = new StringBuilder();
            for (String llave : getIntent().getExtras().keySet()) {
                datosRecibidos.append(llave);
                datosRecibidos.append(":");
                datosRecibidos.append(getIntent().getExtras().get(llave));
                datosRecibidos.append(",");
            }
            Toast.makeText(this, datosRecibidos.toString(), Toast.LENGTH_SHORT).show();
        }

        inicializarVistas();
    }

    private void inicializarVistas() {
        Button botonNoticias = (Button) findViewById(R.id.botonNoticias);
        Button botonDeportes = (Button) findViewById(R.id.botonDeportes);
        Button botonEntretenimiento = (Button) findViewById(R.id.botonEntretenimiento);
        Button botonCancelarNoticias = (Button) findViewById(R.id.botonCancelarNoticias);
        Button botonCancelarDeportes = (Button) findViewById(R.id.botonCancelarDeportes);
        Button botonCancelarEntretenimiento = (Button) findViewById(R.id.botonCancelarEntretenimiento);

        botonNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("noticias");
                Toast.makeText(MainActivity.this, "Suscrito a noticias", Toast.LENGTH_SHORT).show();
            }
        });

        botonDeportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("deportes");
                Toast.makeText(MainActivity.this, "Suscrito a deportes", Toast.LENGTH_SHORT).show();
            }
        });

        botonEntretenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("entretenimiento");
                Toast.makeText(MainActivity.this, "Suscrito a entretenimiento", Toast.LENGTH_SHORT).show();
            }
        });

        botonCancelarNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("noticias");
                Toast.makeText(MainActivity.this, "Suscripción cancelada a noticias", Toast.LENGTH_SHORT).show();
            }
        });

        botonCancelarDeportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("deportes");
                Toast.makeText(MainActivity.this, "Suscripción cancelada a deportes", Toast.LENGTH_SHORT).show();
            }
        });

        botonCancelarEntretenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("entretenimiento");
                Toast.makeText(MainActivity.this, "Suscripción cancelada a entretenimiento", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
