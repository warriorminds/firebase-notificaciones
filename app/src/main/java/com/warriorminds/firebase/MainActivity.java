package com.warriorminds.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
    }
}
