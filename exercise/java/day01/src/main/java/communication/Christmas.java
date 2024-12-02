package communication;

public class Christmas {
    private final int numberOfDaysBeforeChristmas;

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
