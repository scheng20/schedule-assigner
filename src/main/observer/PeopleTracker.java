package observer;

import java.util.Observable;
import java.util.Observer;

public class PeopleTracker implements Observer {

    int totalPeople = 0;

    @Override
    public void update(Observable o, Object arg) {
        totalPeople++;
    }

    public int getTotalPeople() {
        return totalPeople;
    }
}
