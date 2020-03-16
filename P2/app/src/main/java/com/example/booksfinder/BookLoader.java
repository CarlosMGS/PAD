package com.example.booksfinder;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
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
        List<BookInfo> list = null;
        try {
            list = this.networkConnection.getBookInfoJson(this.queryString, this.printType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }


}
