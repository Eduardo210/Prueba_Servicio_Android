package com.quetzal.prueba_servicio_android.Helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;

import com.quetzal.prueba_servicio_android.R;

public class Helper extends AppCompatActivity {


    public void SinConexion(Context context, String bandera){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Html.fromHtml("<font color='#000000'>Sin Conexión a Internet</font>"));
        builder.setMessage(Html.fromHtml("<font color='#000000'>"+bandera+"</font>"));
        builder.setIcon(R.drawable.alerta);
        builder.setCancelable(false);
        builder.setPositiveButton(Html.fromHtml("<font color='#000000'>Cerrar</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog= builder.create();
        dialog.show();
    }
}
