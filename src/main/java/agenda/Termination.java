package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Termination {

    private final LocalDate start;
    private final ChronoUnit frequency;
    private LocalDate terminationDateInclusive;
    private long numberOfOccurrences;

    public Termination(LocalDate start, ChronoUnit frequency, LocalDate terminationInclusive) {
        this.start = start;
        this.frequency = frequency;
        this.terminationDateInclusive = terminationInclusive;
        this.numberOfOccurrences = ChronoUnit.DAYS.between(start, terminationInclusive) / frequency.getDuration().toDays();
    }

    public Termination(LocalDate start, ChronoUnit frequency, long numberOfOccurrences) {
        this.start = start;
        this.frequency = frequency;
        this.numberOfOccurrences = numberOfOccurrences;
        this.terminationDateInclusive = start.plus(numberOfOccurrences - 1, frequency);
    }

    public LocalDate terminationDateInclusive() {
        return terminationDateInclusive;
    }

    public long numberOfOccurrences() {
        return numberOfOccurrences;
    }
}
