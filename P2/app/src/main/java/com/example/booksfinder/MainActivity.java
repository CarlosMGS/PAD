package com.example.booksfinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookInfo>>{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String editText;
    private String editText2;
    private RadioGroup radioGroup;
    private static final int BOOK_LOADER_ID = 101;

    private BooksResultListAdapter booksrla;
    private RecyclerView bookRecyclerView;

    public static final String EXTRA_QUERY = "queryString";
    public static final String EXTRA_PRINT_TYPE = "printType";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Loader initialization*/
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if (loaderManager.getLoader(BOOK_LOADER_ID) != null){
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        }
        
        
        List<BookInfo> lista = new ArrayList<BookInfo>();

        bookRecyclerView = findViewById(R.id.bookRecyclerView);
        booksrla = new BooksResultListAdapter(this, lista);

        bookRecyclerView.setAdapter(booksrla);
        // Give the RecyclerView a default layout manager.
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void searchBooks(View view){

        /* variables */
        editText = ((EditText) findViewById(R.id.titleText)).getText().toString();
        editText2 = ((EditText) findViewById(R.id.authorsText)).getText().toString();
        /* concat authors names and title */
        String queryString = editText + " " + editText2;

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

        //set the printType: all, books, magazines
        String printType = radioButton.getText().toString();

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        ((TextView) findViewById(R.id.infoText)).setText("Loading...");

        if(isConnected){
            Bundle queryBundle = new Bundle();
            queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, queryString);
            queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);
            LoaderManager.getInstance(this).restartLoader(BOOK_LOADER_ID, queryBundle, this);
        }


    }

    public void updateBooksResultList(List<BookInfo> bookInfo){

        booksrla.setBooksData(bookInfo);
        booksrla.notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Loader<List<BookInfo>> onCreateLoader(int id, @Nullable Bundle args) {
        return new BookLoader(this, args.getString(EXTRA_QUERY), args.getString(EXTRA_PRINT_TYPE));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<BookInfo>> loader, List<BookInfo> data) {

        List<BookInfo> newbook = new ArrayList<BookInfo>();

        if (data!= null && data.size()>0) {

            //actualiza los datos de la recyclerview
            this.updateBooksResultList(data);
            ((TextView)findViewById(R.id.infoText)).setText("Results");
        }
        else {

            //no se encuentran casos
            updateBooksResultList(newbook);
            ((TextView)findViewById(R.id.infoText)).setText("No Results Found");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<BookInfo>> loader) {
        //clean the loader to print other results
        loader.reset();
        //notify to adapter to refresh the recyclerview
        booksrla.notifyDataSetChanged();
    }

    public void showWeb(View view){
        try{

            Uri requestURL = Uri.parse(((TextView)findViewById(R.id.url)).getText().toString());

            Intent intent = new Intent(Intent.ACTION_VIEW, requestURL);

            //abre un navegador con el link del libro
            startActivity(intent);

        }catch(Exception e){

            Log.e(LOG_TAG, "Error en la conexión con el API");

        }

    }
}
