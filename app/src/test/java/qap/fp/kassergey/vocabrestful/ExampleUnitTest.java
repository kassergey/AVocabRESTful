package qap.fp.kassergey.vocabrestful;
import qap.fp.kassergey.vocabrestapp.*;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void Trololo()
    {
        assertEquals(4, 2+2);
    }
    private CountDownLatch lock = new CountDownLatch(1);
    @Test
    public void getListWordsSucess() throws Exception{
        Vocabulary voc = new Vocabulary(new TestCallBackSucess());
        voc.getWords();
        List<WordModel> lstExample = new LinkedList<WordModel>();
        lstExample.add(new WordModel("hello", "Привет"));
        lstExample.add(new WordModel("hi", "Привет"));
        lock.await(2000, TimeUnit.MILLISECONDS);
        assertEquals(lstExample, lstSuccess);
    }
    static public List<WordModel> lstSuccess;
    @Test
    public void getListWordsFail() throws Exception{
        Vocabulary voc = new Vocabulary(new TestCallBackFail());
        voc.getWords();
        List<WordModel> lstExample = new LinkedList<WordModel>();
        lstExample.add(new WordModel("hello", "Привет"));
        lstExample.add(new WordModel("hi", "Привет"));
        lock.await(2000, TimeUnit.MILLISECONDS);
        assertNotEquals(lstExample, lstFail);
    }
    static public List<WordModel> lstFail;
}

class TestCallBackSucess implements CallbackInterface
{
    @Override
    public void doWhile(Object obj) {

    }

    @Override
    public void doAfter(Object obj) throws Exception {
        if(!(obj instanceof List)) throw new Exception("obj isn't example of List");
        ExampleUnitTest.lstSuccess = (List<WordModel>)obj;

    }

    @Override
    public void doBefore(Object obj) {

    }
}

class TestCallBackFail implements CallbackInterface
{
    @Override
    public void doWhile(Object obj) {

    }
    @Override
    public void doAfter(Object obj) throws Exception {
        if(!(obj instanceof List)) throw new Exception("obj isn't example of List");
        ExampleUnitTest.lstFail = (List<WordModel>)obj;

    }

    @Override
    public void doBefore(Object obj) {

    }
}
