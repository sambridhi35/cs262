/*
 * Homework 2, CS262
 * @author Sambridhi Acharya
 *
 * */
package edu.calvin.cs262.sa35.homework2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    Spinner protocolName;
    String[] dropdownOps = {"https://", "http://"};
    EditText URL;
    TextView SrcCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        protocolName = (Spinner) findViewById(R.id.protocol);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dropdownOps);

        URL = (EditText)findViewById(R.id.URLtype);
        SrcCode = (TextView)findViewById(R.id.SourceCode);

    }

    public void getSourceCode(View view) {
        String URLtxt = URL.getText().toString();
        String protocolType = protocolName.getSelectedItem().toString();

        InputMethodManager inpManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inpManager != null ) {
            inpManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager conManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (conManager != null) {
            netInfo = conManager.getActiveNetworkInfo();

        }
        if (netInfo != null && netInfo.isConnected() && URLtxt.length() != 0 && protocolType.length()!= 0) {
            String finalString = protocolType.concat(URLtxt);
            Bundle qBundle = new Bundle();
            qBundle.putString("URLtxt", finalString);

            // Restart loader to get results
            getSupportLoaderManager().restartLoader(0, qBundle, this);
            SrcCode.setText("Loading..");
        }
        else {
            if (URLtxt.length()==0){
                SrcCode.setText("");
            }
        }
        }

        @NonNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable Bundle a) {
            String URLSrc = "";
            if(a!=null){URLSrc = a.getString("urlTxt");}
            return  new SearchURL(this , URLSrc);
        }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        String results = data;
        if (results != null) {
            SrcCode.setText(results);

        } else {
            SrcCode.setText("");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }

}
