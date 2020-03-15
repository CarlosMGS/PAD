package com.example.booksfinder;

import android.util.JsonReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookInfo {

    private String authors;
    private String title;
    private URL infolink;

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public URL getInfolink() {
        return infolink;
    }

    public BookInfo(String authors, String title, URL infolink) {
         this.authors = authors;
         this.title = title;
         this.infolink = infolink;
    }

    public static List<BookInfo> fromJsonResponse(JsonReader json){

        /* guess we have to analyse the book request we have just made */


        List<BookInfo> items = new ArrayList<BookInfo>();

        try{
            json.beginObject();
            json.nextInt();

            json.beginArray();

            while (json.hasNext()) {
                // Leer objeto
                items.add(readItem(json));
            }

            json.endArray();

        }catch(Exception e){

        }




        return items;
    }

    private static BookInfo readItem(JsonReader json) throws MalformedURLException {

        String title = "";
        String authors = "";
        String link = "";

        try{

            json.beginObject();
            while (json.hasNext()) {
                String name = json.nextName();
                switch (name) {
                    case "selfLink":
                        link = json.nextString();
                        break;
                    case "volumeInfo":
                        title = json.nextString();
                        authors = readAuthors(json);
                        break;
                    default:
                        json.skipValue();
                        break;
                }
            }
        }catch (Exception e){

        }


        URL ulink = new URL(link);

        return new BookInfo(authors, title,  ulink);

    }

    private static String readAuthors(JsonReader json){
        String authors= "";

        try{
            json.beginArray();
            while(json.hasNext()){
                authors += json.nextString();
            }
        }catch(Exception e){

        }

        return authors;
    }

}
