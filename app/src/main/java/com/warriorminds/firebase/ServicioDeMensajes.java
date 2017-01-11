package com.warriorminds.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Servicio que maneja las notificaciones entrantes, cuando la app está en primer plano.
 *
 * Este servicio debe de heredar de FirebaseMessagingService.
 *
 * @author warriorminds
 */
public class ServicioDeMensajes extends FirebaseMessagingService {

    /**
     * Debemos sobreescribir este método que es el que se ejecuta cuando recibimos una notificación.
     *
     * @param mensajeRemoto Objeto que tiene toda la información recibida.
     */
    @Override
    public void onMessageReceived(RemoteMessage mensajeRemoto) {
        enviarNotificacion(mensajeRemoto);
    }

    /**
     * Método para enviar la notificación.
     * @param mensajeRemoto
     */
    private void enviarNotificacion(RemoteMessage mensajeRemoto) {
        /**
         * El intent será lo que se ejecute cuando el usuario presione la notificación.
         */
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        /**
         * El método getData() del mensaje remoto nos proporciona los datos extras que enviamos. En este ejemplo
         * agregamos cada llave y valor como extra en el intent.
         */
        if (mensajeRemoto.getData().size() > 0) {
            for (String llave : mensajeRemoto.getData().keySet()) {
                intent.putExtra(llave, mensajeRemoto.getData().get(llave));
            }
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        /**
         * Configuramos nuestra notificación.
         */
        Uri uriDefaultDeSonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builderDeNotificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(mensajeRemoto.getNotification().getTitle()) /* getNotification() nos da la información del título y el cuerpo que se recibió. */
                .setContentText(mensajeRemoto.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(uriDefaultDeSonido)
                .setContentIntent(pendingIntent);

        /**
         * Enviamos la notificación.
         */
        NotificationManager manejadorDeNotificaciones = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manejadorDeNotificaciones.notify(0 /* ID de la notificación */, builderDeNotificacion.build());
    }
}
