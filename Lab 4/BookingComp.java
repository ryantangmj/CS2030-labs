import java.util.Comparator;

class BookingComp implements Comparator<Booking> {
    public int compare(Booking b1, Booking b2) {
        if (b1.price() - b2.price() < 0) {
            return -1;
        } else if (b1.price() - b2.price() > 0) {
            return 1;
        } else if (b1.getWaitingTime() - b2.getWaitingTime() < 0) {
            return -1;
        } else if (b1.getWaitingTime() - b2.getWaitingTime() > 0) {
            return 1;
        } else {
            return 0;
        }   
    }
}

