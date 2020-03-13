package com.example.booksfinder;

import java.net.URL;
import java.util.List;

public class BookInfo {

    private String authors;
    private String title;
    private URL infolink;

    public BookInfo(String authors, String title, URL infolink) {
         this.authors = authors;
         this.title = title;
         this.infolink = infolink;
    }

    public static List<BookInfo> fromJsonResponse(String s){

        /* guess we have to analyse the book request we have just made */
        URL link;
        String t = "";
        String a = "";


        return null;
    }

}
