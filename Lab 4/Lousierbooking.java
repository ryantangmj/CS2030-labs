import java.lang.Math;

class Lousierbooking extends Booking {
    Lousierbooking(Driver driver, Request request) {
        super(driver, request);
    }
    
    @Override
    double price() {
        return super.returnDriver().higherPrice(super.getRequest());
    }

    @Override
    String getDriver() {
        return super.returnDriver().getExpDriver(super.getRequest());
    }
}
