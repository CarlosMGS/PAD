package com.example.booksfinder;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static List<BookInfo> fromJsonResponse(String input){

        /* guess we have to analyse the book request we have just made */


        List<BookInfo> items = new ArrayList<BookInfo>();

        URL infoLink = null;
        String title = "", authors = "";
        List<BookInfo> booksList = null;


        try{
            JSONObject json = new JSONObject(input);

            if((int)json.get("totalItems") > 0){

                JSONArray itemsArray = (JSONArray) json.get("items");

                for(int i = 0; i < itemsArray.length(); i++) {
                    JSONObject book = itemsArray.getJSONObject(i);

                    items.add(readItem(book));

                }

            }


        }catch(Exception e){

        }


        return items;
    }

    private static BookInfo readItem(JSONObject json) throws MalformedURLException {

        String title = "";
        String authors = "";
        URL link = null;

        try{

            if(json.has("volumeInfo")){
                JSONObject vi = (JSONObject) json.get("volumeInfo");

                if(vi.has("authors")) {

                    JSONArray authorsArray = (JSONArray) vi.get("authors");

                    if(authorsArray.length() > 0) {
                        authors += authorsArray.get(0);

                        for (int i = 1; i < authorsArray.length(); i++) {
                            authors += ", " + authorsArray.get(i);
                        }
                    }
                }
                if(vi.has("title")) {
                    title = vi.getString("title");
                }
                if(vi.has("infoLink")) {
                    link = new URL(vi.getString("infoLink"));
                }
            }
        }catch (Exception e){

        }




        return new BookInfo(authors, title,  link);

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
