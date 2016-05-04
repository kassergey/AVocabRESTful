package qap.fp.kassergey.vocabrestful;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import qap.fp.kassergey.vocabrestapp.CallbackInterface;
import qap.fp.kassergey.vocabrestapp.WordModel;

/**
 * Created by kassergey on 04.05.2016.
 */
public class CallBackTextView implements CallbackInterface{
    TextView _tv;//на всяк случай
    public CallBackTextView(TextView tv)
    {
        _tv = tv;
    }
    @Override
    public void doWhile(Object obj) {

    }

    @Override
    public void doAfter(Object obj) throws Exception {
        if(!(obj instanceof List)) throw new Exception("obj isn't example of List");
        List<WordModel> lst = (List<WordModel>)obj;
        StringBuilder sb = new StringBuilder();
        for(WordModel wd : lst)
        {
            sb.append(wd.WordOrigin+" - " + wd.WordTranslation+"\n");
        }
        _tv.setText(sb.toString());
    }

    @Override
    public void doBefore(Object obj) {

    }
}
