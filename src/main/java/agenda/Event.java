package agenda;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class Event {

    private String myTitle;
    private LocalDateTime myStart;
    private Duration myDuration;

    private ChronoUnit frequency = null;
    private LocalDate terminationDateInclusive = null;
    private Integer numberOfOccurrences = null;

    private Set<LocalDate> exceptions = new HashSet<>();

    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    public void setRepetition(ChronoUnit frequency) {
        this.frequency = frequency;
    }

    public void addException(LocalDate date) {
        exceptions.add(date);
    }

    public void setTermination(LocalDate terminationInclusive) {
        this.terminationDateInclusive = terminationInclusive;
        this.numberOfOccurrences = calculateOccurrencesFromTerminationDate();
    }

    public void setTermination(long numberOfOccurrences) {
        this.numberOfOccurrences = (int) numberOfOccurrences;
        this.terminationDateInclusive = calculateTerminationDateFromOccurrences();
    }

    public int getNumberOfOccurrences() {
        return this.numberOfOccurrences;
    }

    public LocalDate getTerminationDate() {
        return this.terminationDateInclusive;
    }

    public boolean isInDay(LocalDate aDay) {
        if (aDay.isBefore(myStart.toLocalDate())) return false;

        if (frequency == null) {
            LocalDate endDate = myStart.plus(myDuration).toLocalDate();
            return !aDay.isBefore(myStart.toLocalDate()) && !aDay.isAfter(endDate);
        }

        LocalDate currentOccurrence = myStart.toLocalDate();
        while (!currentOccurrence.isAfter(aDay)) {
            if (currentOccurrence.equals(aDay) && !exceptions.contains(aDay)) {
                return true;
            }

            currentOccurrence = currentOccurrence.plus(1, frequency);
        }

        return false;
    }

    private int calculateOccurrencesFromTerminationDate() {
        if (frequency == null || terminationDateInclusive == null) return 0;

        long daysBetween = ChronoUnit.DAYS.between(myStart.toLocalDate(), terminationDateInclusive);

        long frequencyInDays = frequency.getDuration().toDays();
        long occurrences = daysBetween / frequencyInDays;

        occurrences++;
        if (occurrences > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Le nombre d'occurrences est trop grand pour un int.");
        }

        return (int) occurrences;
    }



    private LocalDate calculateTerminationDateFromOccurrences() {
        if (frequency == null || numberOfOccurrences == null) return null;
        return myStart.toLocalDate().plus(numberOfOccurrences - 1, frequency);
    }

    public String getTitle() {
        return myTitle;
    }

    public LocalDateTime getStart() {
        return myStart;
    }

    public Duration getDuration() {
        return myDuration;
    }

    @Override
    public String toString() {
        return "Event{title='%s', start=%s, duration=%s}".formatted(myTitle, myStart, myDuration);
    }

}