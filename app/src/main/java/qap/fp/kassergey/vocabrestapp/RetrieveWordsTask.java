package qap.fp.kassergey.vocabrestapp;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import qap.fp.kassergey.vocabrestful.WordModel;
import qap.fp.kassergey.observer.Observable;
import qap.fp.kassergey.observer.Observer;

/**
 * Created by kassergey on 26.04.2016.
 */


public class RetrieveWordsTask extends AsyncTask<Void, Void, String> implements Observable {

    private Exception exception;
    List<Observer> observers = new LinkedList<>();
    private String _req;

    public RetrieveWordsTask(String req)
    {
        _req = req;
    }
    protected void onPreExecute() {
    }

    protected String doInBackground(Void... params) {
        // Do some validation here

        try {
            StringBuilder sb = new StringBuilder("http://192.168.56.1:3000/vocab/");
            sb.append(_req);
            URL url = new URL(sb.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;

        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        try {
            StringBuilder retStr = new StringBuilder();
            if(response.equalsIgnoreCase("null\n")){
            }
            else if(_req.equalsIgnoreCase("")) {
                JSONArray jsonRootArray = new JSONArray(response);
                for (int i = 0; i < jsonRootArray.length(); i++) {
                    JSONObject jsonObject = jsonRootArray.getJSONObject(i);
                    retStr.append(jsonObject.optString("wordOrigin").toString() + " - " +
                            jsonObject.optString("wordTranslation").toString() + "\n");
                }
            }
            else
            {
                JSONObject isonRootObject = new JSONObject(response);
                retStr.append(isonRootObject.optString("wordOrigin").toString() + " - " +
                        isonRootObject.optString("wordTranslation").toString() + "\n");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //implementation of Observable
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        if(observers.contains(observer))
        {
            observers.remove(observer);
        }
    }
    @Override
    public void Notify() {
        for(Observer observer : observers)
        {
            observer.update(this, new WordModel());
        }
    }
}
