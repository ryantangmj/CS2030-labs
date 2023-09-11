import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    public int compare(Event a, Event b) {
        ImList<String> types = new ImList<String>();
        types = types.add("Done").add("Leave").add("Arrive").add("Serve");
        double diff = a.getTime() - b.getTime();
        if (diff < 0) {
            return -1; 
        } else if (diff > 0) {
            return 1;
        } else {
            int aType = types.indexOf(a.getState());
            int bType = types.indexOf(b.getState());
            int typeDiff = aType - bType;
            if (typeDiff < 0) {
                return -1;
            } else if (typeDiff > 0) {
                return 1;
            } else {
                int aID = a.getCustomer().getCustID();
                int bID = b.getCustomer().getCustID();
                return aID - bID;
            }
        }
    } 
}
