package com.quetzal.prueba_servicio_android.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.gson.Gson;
import com.quetzal.prueba_servicio_android.Helpers.AdapterMovies;
import com.quetzal.prueba_servicio_android.MainActivity;
import com.quetzal.prueba_servicio_android.R;
import com.quetzal.prueba_servicio_android.jsonParceable.ResultJSON;

import java.util.ArrayList;
import java.util.List;

public class showResults extends Activity {

    ListView list;
    List<String> NameInfo = new ArrayList<>();
    List<String> TypeInfo = new ArrayList<>();
    List<String> wTeaserInfo = new ArrayList<>();
    List<String> wUrlInfo = new ArrayList<>();
    List<String> yUrlInfo = new ArrayList<>();
    List<String> yIDInfo = new ArrayList<>();

    List<String> NameRusult = new ArrayList<>();
    List<String> TypeResult = new ArrayList<>();
    List<String> wTeaserResult = new ArrayList<>();
    List<String> wUrlResult = new ArrayList<>();
    List<String> yUrlResult = new ArrayList<>();
    List<String> yIDResult = new ArrayList<>();
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);

        NameRusult = getIntent().getStringArrayListExtra("NameResult");
        TypeResult = getIntent().getStringArrayListExtra("TypeResult");
        wTeaserResult = getIntent().getStringArrayListExtra("wTeaserResult");
        wUrlResult = getIntent().getStringArrayListExtra("wUrlResult");
        yUrlResult = getIntent().getStringArrayListExtra("yUrlResult");
        yIDResult = getIntent().getStringArrayListExtra("yUrlResult");

        list = findViewById(R.id.list);

        AdapterMovies adapterMovies = new AdapterMovies(showResults.this, NameRusult, TypeResult, wTeaserResult, wUrlResult, yUrlResult);
        list.setAdapter(adapterMovies);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}