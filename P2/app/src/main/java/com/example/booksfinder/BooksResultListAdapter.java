package com.example.booksfinder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BooksResultListAdapter extends RecyclerView.Adapter<BooksResultListAdapter.ViewHolder> {


    ArrayList<BookInfo> mBooksData;


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView authors;
        TextView url;




        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            //authors = (TextView) view.findViewById(R.id.authors);
            //url = (TextView) view.findViewById(R.id.url);

        }
    }


    @NonNull
    @Override
    public BooksResultListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BooksResultListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setBooksData(List<BookInfo> data){

    }
}
