import java.util.List;
import java.util.Comparator;

class EventComp implements Comparator<Event> {

    public int compare(Event eventOne, Event eventTwo) {
        ImList<String> events = new ImList<String>(List.of("Done", "Serve", "Arrive", "Leave"));
        double timeDifference = eventOne.getEventDoneTime() - eventTwo.getEventDoneTime();

        if (timeDifference < 0) {
            return -1;
        } else if (timeDifference > 0) {
            return 1;
        }

        double eventDifference = events.indexOf(eventOne.getEventType()) - 
            events.indexOf(eventTwo.getEventType());

        if (eventDifference < 0) {
            return -1;
        } else if (eventDifference > 0) {
            return 1;
        } else {
            return eventOne.getCustomer().getCustomerID() - 
                eventTwo.getCustomer().getCustomerID();
        }
    }
}
