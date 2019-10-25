package edu.calvin.cs262.sa35.homework2;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//An AsyncTaskLoading that takes URL string and loads in the source code
public class SearchURL extends AsyncTaskLoader {
    private String urlString;

    public SearchURL(@NonNull Context context, String qString){
        super(context);
        urlString = qString;
    }

    public String loadInBackground(){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String text = " ";

        try{
            Log.d("|||||||||||", urlString);

            URL url = new URL(urlString);
            HttpURLConnection response = (HttpURLConnection) url.openConnection();

            InputStream in = response.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder str = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                str.append(line);
            }
            in.close();
            text = str.toString();

            if (text.length() == 0) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "URL was not found :(";
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d("|||||||||", text);
        return text;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}

