package com.example.booksfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    RadioGroup radioGroup;
    private static final int BOOK_LOADER_ID = -1;
    private BookLoaderCallbacks bookLoaderCallbacks = new BookLoaderCallbacks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*editText initialization*/
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        /* Loader initialization*/
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if (loaderManager.getLoader(BOOK_LOADER_ID) != null){
            loaderManager.initLoader(BOOK_LOADER_ID, null, bookLoaderCallbacks);
        }
    }

    public void searchBooks(View view){

        /*initialize the variables*/

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        /* concat authors names and title */
        String queryString = editText.getText() + " " + editText2.getText();
        String printType = new String();
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton;
        if (radioButtonId != BOOK_LOADER_ID){
            radioButton = (RadioButton) radioGroup.findViewById(radioButtonId);
            if (radioButton != null){
                printType = (String) radioButton.getText().toString();
            }
        }

        Bundle queryBundle = new Bundle();
        queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, queryString);
        queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);
        LoaderManager.getInstance(this).restartLoader(BOOK_LOADER_ID, queryBundle, bookLoaderCallbacks);


    }

    void updateBooksResultList(List<BookInfo> bookInfos){

    }
}
