import java.util.List;
import java.util.Comparator;

class EventComp implements Comparator<Event> {

    public int compare(Event eventOne, Event eventTwo) {
        double timeDifference = eventOne.getEventStartTime() - eventTwo.getEventStartTime();

        if (timeDifference < 0) {
            return -1;
        } else if (timeDifference > 0) {
            return 1;
        }

        double priorityDifference = eventOne.getPriorityNumber() - 
            eventTwo.getPriorityNumber();

        if (priorityDifference < 0) {
            return -1;
        } else if (priorityDifference > 0) {
            return 1;
        } else {
            return eventOne.getCustomer().getCustomerID() - 
                eventTwo.getCustomer().getCustomerID();
        }
    }
}
