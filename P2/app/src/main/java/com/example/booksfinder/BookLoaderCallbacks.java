package com.example.booksfinder;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.List;

public class BookLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<BookInfo>> {


    public static final String EXTRA_QUERY = "queryString";
    public static final String EXTRA_PRINT_TYPE = "printType";
    private Context context;
    private BooksResultListAdapter booksResultListAdapter;

    public BookLoaderCallbacks(Context context, BooksResultListAdapter booksResultListAdapter){
        super();
        this.context = context;
        this.booksResultListAdapter = booksResultListAdapter;

    }

    @NonNull
    @Override
    public Loader<List<BookInfo>> onCreateLoader(int id, @Nullable Bundle args) {
        return new BookLoader(this.context, args.getString(EXTRA_QUERY), args.getString(EXTRA_PRINT_TYPE));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<BookInfo>> loader, List<BookInfo> data) {


    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<BookInfo>> loader) {

        //clean the loader to print other results
        loader.reset();
        //notify to adapter to refresh the recyclerview
        booksResultListAdapter.notifyDataSetChanged();
    }
}
