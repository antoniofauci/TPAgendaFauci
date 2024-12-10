package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class Repetition {

    private final ChronoUnit myFrequency;
    private final Set<LocalDate> exceptions = new HashSet<>();
    private Termination termination;

    public Repetition(ChronoUnit myFrequency) {
        this.myFrequency = myFrequency;
    }

    public ChronoUnit getFrequency() {
        return myFrequency;
    }

    public void addException(LocalDate date) {
        exceptions.add(date);
    }

    public void setTermination(Termination termination) {
        this.termination = termination;
    }
}

