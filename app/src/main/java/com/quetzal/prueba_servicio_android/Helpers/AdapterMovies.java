package com.quetzal.prueba_servicio_android.Helpers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.quetzal.prueba_servicio_android.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMovies extends ArrayAdapter<String> {
    Activity context;
    List<String> NameInfo;
    List<String> TypeInfo;
    List<String> wTeaserInfo;
    List<String> wUrlInfo;
    List<String> yUrlInfo;

    public AdapterMovies(Activity context, List<String> NameInfo, List<String> TypeInfo, List<String> wTeaserInfo, List<String> wUrlInfo, List<String> yUrlInfo) {
        super(context, R.layout.activity_show_results, NameInfo);
        this.context = context;
        this.NameInfo = NameInfo;
        this.TypeInfo = TypeInfo;
        this.wTeaserInfo = wTeaserInfo;
        this.wUrlInfo = wUrlInfo;
        this.yUrlInfo = yUrlInfo;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.list_result, null, true);

//        Intent intent = YouTubeStandalonePlayer.createVideoIntent(context,"AIzaSyDVuU11qKXYSrLzat9JNM6PNdGa1HIazUc", "oHg5SJYRHA0");
//        context.startActivity(intent);
        TextView txtTitle = rowView.findViewById(R.id.txtTitle);
        TextView txtType = rowView.findViewById(R.id.txtType);
        TextView txtwUrl = rowView.findViewById(R.id.txtwUrl);
        TextView txtyUrl = rowView.findViewById(R.id.txtyUrl);
        TextView txtTexto = rowView.findViewById(R.id.txtTexto);

        txtTitle.setText(NameInfo.get(position));
        txtType.setText(TypeInfo.get(position));
        txtwUrl.setText(wUrlInfo.get(position));
        txtyUrl.setText(yUrlInfo.get(position));
        txtTexto.setText(wTeaserInfo.get(position));

        SpannableString content = new SpannableString(txtyUrl.getText());
        content.setSpan(new UnderlineSpan(), 0, txtyUrl.length(), 0);
        txtyUrl.setText(content);
        txtyUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(yUrlInfo.get(position));
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                getContext().startActivity(i);
            }
        });

        SpannableString content1 = new SpannableString(txtwUrl.getText());
        content1.setSpan(new UnderlineSpan(), 0, txtwUrl.length(), 0);
        txtwUrl.setText(content1);
        txtwUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(wUrlInfo.get(position));
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                getContext().startActivity(i);
            }
        });

        return rowView;
    }
}
