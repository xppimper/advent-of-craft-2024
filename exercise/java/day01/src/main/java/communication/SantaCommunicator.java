package communication;

public class SantaCommunicator {
    private final int numberOfDaysToRest;

    public SantaCommunicator(int numberOfDaysToRest) {
        this.numberOfDaysToRest = numberOfDaysToRest;
    }

    public String composeMessage(Reindeer reindeer, Location location, Christmas christmas) {
        var daysBeforeReturn = daysBeforeReturn(location.numbersOfDaysForComingBackToSanta(), christmas.getNumberOfDaysBeforeChristmas());

        return "Dear " + reindeer.reindeerName() + ", please return from " + location.locationName() +
                " in " + daysBeforeReturn + " day(s) to be ready and rest before Christmas.";
    }

    public boolean isOverdue(Reindeer reindeer, Location location, Christmas christmas, Logger logger) {
        if (daysBeforeReturn(location.numbersOfDaysForComingBackToSanta(), christmas.getNumberOfDaysBeforeChristmas()) <= 0) {
            logger.log("Overdue for " + reindeer.reindeerName() + " located " + location.locationName() + ".");
            return true;
        }
        return false;
    }

    private int daysBeforeReturn(int numbersOfDaysForComingBack, int numberOfDaysBeforeChristmas) {
        return numberOfDaysBeforeChristmas - numbersOfDaysForComingBack - numberOfDaysToRest;
    }
}