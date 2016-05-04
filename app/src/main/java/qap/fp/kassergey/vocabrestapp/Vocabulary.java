package qap.fp.kassergey.vocabrestapp;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kassergey on 02.05.2016.
 */
public class Vocabulary {
    List<WordModel> cards = new LinkedList<WordModel>();
    CallbackInterface callbackinterface;
    public Vocabulary(CallbackInterface cbi)
    {
        callbackinterface = cbi;
    }
    public void getWords() {
        (new RetrieveWordsTask("", callbackinterface)).execute();
    }
    public void getWords(String word) {
        (new RetrieveWordsTask(word, callbackinterface)).execute();
    }
}
