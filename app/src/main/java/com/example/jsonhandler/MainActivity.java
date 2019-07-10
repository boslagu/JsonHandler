package com.example.jsonhandler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> jArray;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jArray = new ArrayList<>();
        listView = (ListView)findViewById(R.id.lstVw);
    }

    public void fetchData(View view){
        getJson();
    }

    public void getJson(){
        //    This method(function) is used to fetch data from server
        MyConnection myBackground = new MyConnection(this);
        myBackground.execute();
    }
}