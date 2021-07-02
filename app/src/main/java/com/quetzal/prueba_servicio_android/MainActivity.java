package com.quetzal.prueba_servicio_android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quetzal.prueba_servicio_android.Helpers.Helper;
import com.quetzal.prueba_servicio_android.Helpers.OutputInternet;
import com.quetzal.prueba_servicio_android.Retrofit.SearchInformation;
import com.quetzal.prueba_servicio_android.View.showResults;
import com.quetzal.prueba_servicio_android.jsonParceable.ResultJSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Clase Principal de Ejecucion del Pryecto
public class MainActivity extends Activity {

    //    Variables del XML
    Button btnConsultar;
    EditText edtSearch;
    Spinner spinnerType;
    String Search, Type;

    List<String> NameRusult = new ArrayList<>();
    List<String> TypeResult = new ArrayList<>();
    List<String> wTeaserResult = new ArrayList<>();
    List<String> wUrlResult = new ArrayList<>();
    List<String> yUrlResult = new ArrayList<>();
    List<String> yIDResult = new ArrayList<>();

    //    Instacias Clase como general
    Helper helper = new Helper();
    OutputInternet outputInternet = new OutputInternet();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://tastedive.com/api/") // dirección api primera parte
            .addConverterFactory(GsonConverterFactory.create()) // Convertidor Gson
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtSearch = findViewById(R.id.edtSearch);
        spinnerType = findViewById(R.id.spinnerType);

        btnConsultar = findViewById(R.id.btnAceptar);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (outputInternet.salidaInternet(getApplicationContext()) != false) {
                    Type = spinnerType.getSelectedItem().toString();

                    if (edtSearch.getText().toString().isEmpty() && Type.equals("Seleccione")) {
                        Toast.makeText(MainActivity.this, "Verifica los Campos de busqueda", Toast.LENGTH_SHORT).show();
                    } else {
                        Search = edtSearch.getText().toString();

                        SearchInformation searchInformation = retrofit.create(SearchInformation.class);
                        // Solicitud de red del paquete
                        Call<ResultJSON> call = searchInformation.getSearch(Search, "1", Type, "418127-QRAcceso-EY6M1GF6");

                        call.enqueue(new Callback<ResultJSON>() {
                            @Override
                            public void onResponse(Call<ResultJSON> call, Response<ResultJSON> response) {
                                int result = response.code();
                                if (result != 200) {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    Log.d("mark", "onResponse: " + response.code());
                                } else {
                                    ResultJSON datos = response.body();

                                    for (int i = 0; i < datos.getSimilar().getInfo().size(); i++) {
                                        NameRusult.add(datos.getSimilar().getInfo().get(i).getName());
                                        TypeResult.add(datos.getSimilar().getInfo().get(i).getType());
                                        wTeaserResult.add(datos.getSimilar().getInfo().get(i).getwTeaser());
                                        wUrlResult.add(datos.getSimilar().getInfo().get(i).getwUrl());
                                        yUrlResult.add(datos.getSimilar().getInfo().get(i).getyUrl());
                                        yIDResult.add(datos.getSimilar().getInfo().get(i).getyID());
                                    }

                                    for (int i = 0; i < datos.getSimilar().getResults().size(); i++) {
                                        NameRusult.add(datos.getSimilar().getResults().get(i).getName());
                                        TypeResult.add(datos.getSimilar().getResults().get(i).getType());
                                        wTeaserResult.add(datos.getSimilar().getResults().get(i).getwTeaser());
                                        wUrlResult.add(datos.getSimilar().getResults().get(i).getwUrl());
                                        yUrlResult.add(datos.getSimilar().getResults().get(i).getyUrl());
                                        yIDResult.add(datos.getSimilar().getResults().get(i).getyID());
                                    }

                                    Bundle parmetros = new Bundle();

                                    parmetros.putStringArrayList("NameResult", (ArrayList<String>) NameRusult);
                                    parmetros.putStringArrayList("TypeResult", (ArrayList<String>) TypeResult);
                                    parmetros.putStringArrayList("wTeaserResult", (ArrayList<String>) wTeaserResult);
                                    parmetros.putStringArrayList("wUrlResult", (ArrayList<String>) wUrlResult);
                                    parmetros.putStringArrayList("yUrlResult", (ArrayList<String>) yUrlResult);

                                    Intent intent = new Intent(MainActivity.this, showResults.class);
                                    intent.putExtras(parmetros);
                                    edtSearch.setText("");
                                    spinnerType.clearFocus();
                                    startActivity(intent);
                                    finish();
                                }
                                Log.d("mark", "onResponse: " + response.body());
                            }

                            @Override
                            public void onFailure(Call<ResultJSON> call, Throwable t) {
                                Log.d("mark", "onResponse: " + call);
                                Log.d("mark", "onResponse: " + t);
                            }
                        });
                    }
                } else {
                    helper.SinConexion(MainActivity.this, "Verifica tu conexión a Internet");
                }
            }
        });
    }
}