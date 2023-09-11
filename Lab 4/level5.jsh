void findBestBooking(Request request, List<? extends Driver> drivers) {
    ImList<Booking> bookings = new ImList<Booking>();

    for (int i = 0; i < drivers.size(); i++) {
        bookings = bookings.add(new Booking(drivers.get(i), request));
        bookings = bookings.add(new Lousierbooking(drivers.get(i), request));
    }
    
    bookings = bookings.sort(new BookingComp());

    for (int i = 0; i < bookings.size(); i++) {
        System.out.println(bookings.get(i));
    }
}
