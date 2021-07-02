package com.quetzal.prueba_servicio_android.jsonParceable;

import java.util.List;

public class ResultJSONSimilar {

    List<ResultJSONSimilarInfo> Info;
    List<ResultJSONSimilarResults> Results;

    public List<ResultJSONSimilarInfo> getInfo() {
        return Info;
    }

    public void setInfo(List<ResultJSONSimilarInfo> info) {
        Info = info;
    }

    public List<ResultJSONSimilarResults> getResults() {
        return Results;
    }

    public void setResults(List<ResultJSONSimilarResults> results) {
        Results = results;
    }
}
