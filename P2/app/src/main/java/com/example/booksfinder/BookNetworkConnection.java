package com.example.booksfinder;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BookNetworkConnection {

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

    public List<BookInfo> getBookInfoJson(String queryString, String printType) throws IOException {

        /* variables */
        List<BookInfo> bookInfo = null;
        HttpURLConnection conn = null;
        BufferedReader reader;
        String input = "";
        InputStream inputStream = null;

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
            inputStream = conn.getInputStream();
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
        } finally {
            conn.disconnect();
            if (inputStream != null){
                inputStream.close();
            }
        }

        bookInfo = BookInfo.fromJsonResponse(input);
        Log.d(LOG_TAG, String.valueOf(bookInfo));

        return bookInfo;

    }
}
