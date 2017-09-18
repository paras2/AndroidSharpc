package com.example.jahan.uidemo;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Personal on 9/11/2017.
 */

public class HttpPostRequest extends AsyncTask<String, Void, String> {
    public static final String REQUEST_METHOD = "POST";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    @Override
    protected String doInBackground(String... params){


        String stringUrl = params[0];
        String result;
        String inputLine;

        try {

            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);



            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();

            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);


            //Connect to our url
            connection.connect();


            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());


            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();


            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }


            reader.close();
            streamReader.close();

            result = stringBuilder.toString();
            System.out.print(result);
        }
        catch(IOException e){
            e.printStackTrace();
            result = null;
        }

        return result;

    }

    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }


}