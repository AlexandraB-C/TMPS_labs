package com.example.patterns.behavioral.observer;

public interface ISubject {
    void addObserver(IObserver o);
    void removeObserver(IObserver o);
    void notifyObservers();
}
