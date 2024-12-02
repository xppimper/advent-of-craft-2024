package communication;

import java.time.Clock;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Christmas {
    private final int numberOfDaysBeforeChristmas;

    public Christmas(Clock clock) {
        Date currentDate = Date.from(Instant.now(clock));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        Calendar christmas = new GregorianCalendar(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 25);
        numberOfDaysBeforeChristmas = christmas.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
    }

    private Christmas(int numberOfDaysBeforeChristmas) {
        this.numberOfDaysBeforeChristmas = numberOfDaysBeforeChristmas;
    }

    public static Christmas declareChristmasAt(int numberOfDaysBeforeChristmas) {
        return new Christmas(numberOfDaysBeforeChristmas);
    }

    public int getNumberOfDaysBeforeChristmas() {
        return numberOfDaysBeforeChristmas;
    }
}
