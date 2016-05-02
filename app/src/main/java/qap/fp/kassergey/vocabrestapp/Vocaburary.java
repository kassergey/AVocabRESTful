package qap.fp.kassergey.vocabrestapp;

import java.util.LinkedList;
import java.util.List;

import qap.fp.kassergey.observer.Observable;
import qap.fp.kassergey.observer.Observer;

/**
 * Created by kassergey on 02.05.2016.
 */
public class Vocaburary implements Observable {
    List<Observer> observers = new LinkedList<>();
    List<WordModel> cards = new LinkedList<>();

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
    public void UpdateCards(String wordOrigin)
    {
        (new RetrieveWordsTask(wordOrigin)).execute();
    }
    @Override
    public void Notify() {
        for(Observer observer : observers)
        {
            observer.update(this, cards);
        }
    }
}
