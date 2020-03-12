package com.example.booksfinder;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.net.HttpURLConnection;
import java.net.URL;

public class BookLoader extends AsyncTaskLoader<String> {

    public BookLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return null;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    public String getBookInfoJson(String queryString, String printType){

        String base_url = "https://www.googleapis.com/books/v1/volumes?";
        String max_results = "maxResults";
        String print_type = "printType";
        String query_param = "q";

        Uri builtURI = Uri.parse(base_url).buildUpon()
                .appendQueryParameter(query_param, queryString)
                .appendQueryParameter(max_results, "10")
                .appendQueryParameter(print_type, printType)
                .build();

        try{
            URL requestURL = new URL(builtURI.toString());

            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();

            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

        }
        catch(Exception e){
            System.err.println("Error en la conexión con el API");
        }

        return "";

    }




}
