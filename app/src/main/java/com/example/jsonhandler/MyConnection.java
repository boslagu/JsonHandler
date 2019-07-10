package com.example.jsonhandler;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MyConnection extends AsyncTask<String, String, String>{

    ArrayList<String> jArray;
    ListView listView;
    Context context;

    public MyConnection(Context context){
//        this method(function) is used to get the context from MainActivity
        this.context = context;
    }

    @Override
    protected String doInBackground(String... voids) {
//        this method(function) is used to connect the app to server and get the JSON data
        String result = "";
        String constrng = "http://10.0.2.2/sampleDatabase/";//this is my localhost portable wifi
        try {
            URL url = new URL(constrng);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream ips = http.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }
            bufferedReader.close();
            ips.close();
            http.disconnect();
        } catch (MalformedURLException e){
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
//        this method(function) is used to convert JSON data to ArrayList to add the fetched data to listView
        jArray = new ArrayList<>();
        listView = ((MainActivity)context).findViewById(R.id.lstVw);
        String name ="";
        try{
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                name = obj.getString("firstName") + " " + obj.getString("LastName");
                jArray.add(name);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((MainActivity)context,
                        android.R.layout.simple_list_item_1, jArray);
                listView.setAdapter(arrayAdapter);
            }
            if (name == ""){
                listView.setAdapter(null);
            }
        }catch (Throwable t){
            Toast.makeText(context, "Throwned: " + t,Toast.LENGTH_LONG).show();
        }

    }
}
