package qap.fp.kassergey.observer;

/**
 * Created by kassergey on 02.05.2016.
 */
public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void Notify();
}
