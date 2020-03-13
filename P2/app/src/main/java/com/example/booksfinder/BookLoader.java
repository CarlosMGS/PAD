package com.example.booksfinder;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<BookInfo>> {

    private String queryString, printType;
    private BookNetworkConnection networkConnection;

    public BookLoader(Context context, String queryString, String printType) {
        super(context);
        this.queryString = queryString;
        this.printType = printType;
        this.networkConnection = new BookNetworkConnection();
    }

    @Nullable
    @Override
    public List<BookInfo> loadInBackground() {
        return null;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }


}
