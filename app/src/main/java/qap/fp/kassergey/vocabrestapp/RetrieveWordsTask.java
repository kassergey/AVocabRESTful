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

/**
 * Created by kassergey on 26.04.2016.
 */


public class RetrieveWordsTask extends AsyncTask<Void, Void, String>{

    private Exception exception;
    private String _req;
    private CallbackInterface _cbi;
    List<WordModel> cards = new LinkedList<WordModel>();

    public RetrieveWordsTask(String req, CallbackInterface cbi)
    {
        _req = req;
        _cbi = cbi;
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
            if(response.equalsIgnoreCase("null\n")){
                _cbi.doAfter(null);
            }
            else if(_req.equalsIgnoreCase("")) {
                JSONArray jsonRootArray = new JSONArray(response);
                for (int i = 0; i < jsonRootArray.length(); i++) {
                    JSONObject jsonObject = jsonRootArray.getJSONObject(i);
                    cards.add(new WordModel(jsonObject.optString("wordOrigin").toString(),
                            jsonObject.optString("wordTranslation").toString()));
                }
            }
            else
            {
                JSONObject jsonObject = new JSONObject(response);
                cards.add(new WordModel(jsonObject.optString("wordOrigin").toString(),
                        jsonObject.optString("wordTranslation").toString()));
            }
            _cbi.doAfter(cards);
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
