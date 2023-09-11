class Simulator {
    private final Shop shop;
    private final PQ<Event> events;

    Simulator(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) { 
        PQ<Event> tempEvents = new PQ<>(new EventComparator());

        for (int i = 0; i < arrivalTimes.size(); i++) {
            tempEvents = tempEvents.add(new Arrive(new Customer(arrivalTimes.get(i),
                            serviceTimes.get(i), i + 1)));
        }

        events = tempEvents;

        shop = new Shop(numOfServers);
    }

    String simulate() {
        String output = "";
        Shop temp = new Shop(shop);
        Customer blankCust = new Customer(0, 0, 0);
        PQ<Event> tempEvent = events;
        int numCustomersServed = 0;
        int numCustomersLeft = 0;

        while (tempEvent.isEmpty() == false) {
            Pair<Event, PQ<Event>> pair = tempEvent.poll();
            Event firstE = pair.first();
            tempEvent = pair.second();

            if (firstE.getState().equals("Arrive")) {
                output += firstE.toString() + "\n";
                tempEvent = tempEvent.add(firstE.run(temp));
            } else if (firstE.getState().equals("Serve")) {
                output += firstE.toString() + "\n";
                temp = temp.updateServer(firstE.getServer(), firstE.getCustomer());
                tempEvent = tempEvent.add(firstE.run(temp));
                numCustomersServed++;
            } else if (firstE.getState().equals("Done")) {
                output += firstE.toString() + "\n";
                temp = temp.updateServer(firstE.getServer(), blankCust);
            } else if (firstE.getState().equals("Leave")) {
                output += firstE.toString() + "\n";
                numCustomersLeft++;
            }
        }

        output += "[" + numCustomersServed + " " + numCustomersLeft + "]"; 
        return output;
    }
}
