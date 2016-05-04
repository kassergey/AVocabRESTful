package qap.fp.kassergey.vocabrestful;
import qap.fp.kassergey.vocabrestapp.*;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
    /*@Test
    public void getListWordsSucess() throws Exception{
        Vocabulary voc = new Vocabulary(new TestCallBackSucess());
        voc.getWords();
        wait(5000);
    }
    @Test
    public void getListWordsFail() throws Exception{
        Vocabulary voc = new Vocabulary(new TestCallBackFail());
        voc.getWords();
        wait(5000);
    }*/
}

class TestCallBackSucess implements CallbackInterface
{
    @Override
    public void doWhile(Object obj) {

    }

    @Override
    public void doAfter(Object obj) throws Exception {
        if(!(obj instanceof List)) throw new Exception("obj isn't example of List");
        List<WordModel> lst = (List<WordModel>)obj;
        List<WordModel> lstExample = new LinkedList<WordModel>();
        lstExample.add(new WordModel("hello", "Привет"));
        lstExample.add(new WordModel("hi", "Привет"));
        assertEquals(lstExample, lst);
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
        List<WordModel> lst = (List<WordModel>)obj;
        List<WordModel> lstExample = new LinkedList<WordModel>();
        lstExample.add(new WordModel("hello0", "Привет"));
        lstExample.add(new WordModel("hi", "Привет"));
        assertNotEquals(lstExample, lst);
    }

    @Override
    public void doBefore(Object obj) {

    }
}