package com.example.booksfinder;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookLoader extends AsyncTaskLoader<String> {

    /*variables*/

    // log in
    private static final String LOG_TAG = BookLoader.class.getSimpleName();
    // base URL for Books API
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    // parameter for the search string
    private static final String QUERY_PARAM = "q";
    // parameter that limits search results
    private static final String MAX_RESULTS = "maxResults";
    // parameter to filter by print type
    private static final String PRINT_TYPE = "printType";

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

        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String input = "";

        try{
            // build the URI request
            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, printType)
                    .build();

            URL requestURL = new URL(builtURI.toString());

            // open connection
            conn = (HttpURLConnection) requestURL.openConnection();
            // make a request
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // response treatment
            InputStream inputStream = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }
            if (builder.length() == 0) {
                return null ;
            }
            input = builder.toString();

            System.out.println(input);

        }
        catch(Exception e){
            System.err.println("Error en la conexión con el API");
        }

        Log.d(LOG_TAG, input);
        return input;

    }




}
