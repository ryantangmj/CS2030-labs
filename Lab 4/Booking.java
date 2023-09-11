import java.lang.Math;

class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
    }

    double price() {
        return driver.lowestPrice(request);
    }
    
    String getDriver() {
        return driver.getCheaperDriver(request);
    }
    
    int getWaitingTime() {
        return driver.getWaitingTime();
    }

    Driver returnDriver() {
        return driver;
    }

    Request getRequest() {
        return request;
    }

    @Override 
    public int compareTo(Booking other) {
        if (this.price() - other.price() < 0) {
            return -1;
        } else if (this.price() - other.price() > 0) {
            return 1;
        } else if (this.getWaitingTime() - other.getWaitingTime() < 0) {
            return -1;
        } else if (this.getWaitingTime() - other.getWaitingTime() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("$%.2f using %s (%s)", price(), driver.toString(), getDriver());
    }
}
