package com.quetzal.prueba_servicio_android.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

public class OutputInternet  {

    public boolean salidaInternet(Context context){
        boolean salida = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Si hay conexión a Internet en este momento
            salida = true;
        } else {
            // No hay conexión a Internet en este momento
            salida = false;
        }
        return salida;
    }


}
