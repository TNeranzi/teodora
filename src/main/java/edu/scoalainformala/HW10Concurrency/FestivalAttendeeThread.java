package edu.scoalainformala.HW10Concurrency;

import java.util.logging.Logger;

class FestivalAttendeeThread extends Thread {
    private final FestivalGate festivalGate;
    private final TicketType ticketType;

    public FestivalAttendeeThread(FestivalGate festivalGate, TicketType ticketType) {
        this.festivalGate = festivalGate;
        this.ticketType = ticketType;
    }

    @Override
    public void run() {
        festivalGate.addTicket(ticketType);
        System.out.println("A person with a " + ticketType + " pass has entered the festival.");

        try {
            Thread.sleep(1000); // Sleep for 1 second
        } catch (InterruptedException e) {
            Logger.getLogger(FestivalAttendeeThread.class.getName()).severe("Interrupted while sleeping: " + e.getMessage());
        }
    }
}
