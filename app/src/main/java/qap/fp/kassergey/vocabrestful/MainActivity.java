package qap.fp.kassergey.vocabrestful;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import qap.fp.kassergey.vocabrestapp.RetrieveWordsTask;
import qap.fp.kassergey.vocabrestapp.Vocabulary;

public class MainActivity extends AppCompatActivity{

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView)findViewById(R.id.textView);
        final Vocabulary voc = new Vocabulary(new CallBackTextView(tv));
        voc.getWords();
        SearchView sv = (SearchView)findViewById(R.id.searchView);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                voc.getWords(newText);
                return true;
            }
        });
    }
}
/*public class MainActivity extends AppCompatActivity{

    protected void uploadWords()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}*/
