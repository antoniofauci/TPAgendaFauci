package agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event e) {
        events.add(e);
    }

    public List<Event> eventsInDay(LocalDate day) {
        List<Event> eventsInDay = new ArrayList<>();
        for (Event e : events) {
            if (e.isInDay(day)) {
                eventsInDay.add(e);
            }
        }
        return eventsInDay;
    }
}
