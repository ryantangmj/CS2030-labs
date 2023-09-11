import java.lang.Math;

class PrivateCar extends Driver {

    PrivateCar(String licensePlate, int waitingTime) {
        super(licensePlate, waitingTime);
    }

    @Override
    double lowestPrice(Request request) {
        return Math.min(request.computeFare(new JustRide()) / 100.0, 
            request.computeFare(new ShareARide()) / 100.0);
    }

    @Override
    double higherPrice(Request request) {
        return Math.max(request.computeFare(new JustRide()) / 100.0, 
            request.computeFare(new ShareARide()) / 100.0);
    }

    @Override
    String getCheaperDriver(Request request) {
        if (request.computeFare(new JustRide()) < request.computeFare(new ShareARide())) {
            return new JustRide().toString();
        }

        return new ShareARide().toString();
    }

    @Override
    String getExpDriver(Request request) {
        if (request.computeFare(new JustRide()) < request.computeFare(new ShareARide())) {
            return new ShareARide().toString();
        }

        return new JustRide().toString();
    }

    @Override
    public String toString() {
        return super.toString() + "PrivateCar";
    }
}
