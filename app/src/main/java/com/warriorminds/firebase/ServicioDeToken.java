package com.warriorminds.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.Date;

/**
 * @author warriorminds
 */
public class ServicioDeToken extends FirebaseInstanceIdService {

    private static String TAG = "ServicioDeToken";

    @Override
    public void onTokenRefresh() {
        FirebaseInstanceId instanciaIdFirebase = FirebaseInstanceId.getInstance();
        String tokenDeEsteDispositivo = instanciaIdFirebase.getToken();

        Log.d(TAG, "Token de este dispositivo: " + tokenDeEsteDispositivo);
        Log.d(TAG, "Creada el " + new Date(instanciaIdFirebase.getCreationTime()));

        // Enviar token a su servicio web.
    }
}
