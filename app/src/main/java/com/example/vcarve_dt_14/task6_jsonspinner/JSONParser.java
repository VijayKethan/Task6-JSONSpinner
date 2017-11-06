package com.example.vcarve_dt_14.task6_jsonspinner;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by VCARVE-DT-14 on 25-10-2017.
 */

public class JSONParser extends AsyncTask<Void, Void, Boolean> {

    private Context c;
    private String jsonData;
    private Spinner sp;

    private ProgressDialog pd;
    private ArrayList<String> result = new ArrayList<>();

    public JSONParser(Context c, String jsonData, Spinner sp) {
        this.c = c;
        this.jsonData = jsonData;
        this.sp = sp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Parse JSON");
        pd.setMessage("Parsing...");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        pd.dismiss();
        if(isParsed){
            ArrayAdapter<String>adapter = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, result);
            sp.setAdapter(adapter);

            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(c, result.get(i), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else {
            Toast.makeText(c, "Unable To Parse, Check log output", Toast.LENGTH_SHORT).show();
        }

    }

    private Boolean parse() {
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            result.clear();

            for(int i=0;i<ja.length();i++) {
                jo = ja.getJSONObject(i);

                String name = jo.getString("name");
                result.add(name);
            }

            return true;
        }catch (JSONException e){
            e.printStackTrace();
            return false;
        }
    }

}
