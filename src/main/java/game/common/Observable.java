package game.common;

import game.model.object.ObjectType;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    ObjectType is();
}
