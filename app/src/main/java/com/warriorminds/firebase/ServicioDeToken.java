package com.warriorminds.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.Date;

/**
 * Servicio que maneja el cambio o asignación de un token para cada usuario.
 *
 * Este servicio debe de heredar de la clase FirebaseInstanceIdService.
 *
 * @author warriorminds
 */
public class ServicioDeToken extends FirebaseInstanceIdService {

    private static String TAG = "ServicioDeToken";

    /**
     * Debemos sobreescribir este método, que es el que se ejecuta cuando hay un cambio en el estado del Token.
     */
    @Override
    public void onTokenRefresh() {
        /**
         * El objeto FirebaseInstanceId es el que nos proporciona el Token del usuario con el método getToken().
         */
        FirebaseInstanceId instanciaIdFirebase = FirebaseInstanceId.getInstance();
        String tokenDeEsteDispositivo = instanciaIdFirebase.getToken();

        Log.d(TAG, "Token de este dispositivo: " + tokenDeEsteDispositivo);

        /**
         * Entre otras cosas, nos permite obtener la fecha de creación del token.
         */
        Log.d(TAG, "Creada el " + new Date(instanciaIdFirebase.getCreationTime()));

        /**
         * Deberían enviar el token a su servicio web y asociarlo con su usuario.
         */
    }
}
