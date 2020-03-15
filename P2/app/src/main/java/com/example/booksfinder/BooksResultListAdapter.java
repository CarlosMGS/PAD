package com.example.booksfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class BooksResultListAdapter extends RecyclerView.Adapter<BooksResultListAdapter.ViewHolder> {


    List<BookInfo> mBooksData;
    LayoutInflater inflater;

    public <BookLoaderCallbacks> BooksResultListAdapter(Context context, List<BookInfo> bookInfos) {
        mBooksData = bookInfos;

        inflater = LayoutInflater.from(context);


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView authors;
        TextView url;

        BooksResultListAdapter adapter;




        public ViewHolder(View view, BooksResultListAdapter adapter) {
            super(view);

            title = (TextView) view.findViewById(R.id.Title);
            authors = (TextView) view.findViewById(R.id.Authors);
            url = (TextView) view.findViewById(R.id.url);

            this.adapter = adapter;

        }
    }


    @NonNull
    @Override
    public BooksResultListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.book_layout, parent, false);
        return new ViewHolder(rootView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksResultListAdapter.ViewHolder holder, int position) {
        BookInfo mCurrent = mBooksData.get(position);
        holder.title.setText(mCurrent.getTitle());
        holder.authors.setText(mCurrent.getAuthors());
        holder.url.setText(mCurrent.getInfolink().toString());

    }

    @Override
    public int getItemCount() {

        return mBooksData.size();
    }

    public void setBooksData(List<BookInfo> data){
        mBooksData =  data;
    }
}
