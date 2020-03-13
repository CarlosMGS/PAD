package com.example.booksfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class MainActivity<BookLoaderCallbacks> extends AppCompatActivity {

    private String editText;
    private String editText2;
    private RadioGroup radioGroup;
    private static final int BOOK_LOADER_ID = -1;
    private BookLoaderCallbacks bookLoaderCallbacks = new BookLoaderCallbacks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Loader initialization*/
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if (loaderManager.getLoader(BOOK_LOADER_ID) != null){
            loaderManager.initLoader(BOOK_LOADER_ID, null, bookLoaderCallbacks);
        }
    }

    public void searchBooks(View view){

        /* variables */
        editText = ((EditText) findViewById(R.id.editText)).getText().toString();
        editText2 = ((EditText) findViewById(R.id.editText2)).getText().toString();
        /* concat authors names and title */
        String queryString = editText + " " + editText2;

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        String printType = radioButton.getText().toString();

        Bundle queryBundle = new Bundle();
        queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, queryString);
        queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);
        LoaderManager.getInstance(this).restartLoader(BOOK_LOADER_ID, queryBundle, bookLoaderCallbacks);


    }

    void updateBooksResultList(List<BookInfo> bookInfo){

    }
}
