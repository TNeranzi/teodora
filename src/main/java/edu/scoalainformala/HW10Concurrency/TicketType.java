package edu.scoalainformala.HW10Concurrency;

import java.util.Random;

public enum TicketType {
    FULL( "Full"),
    FULL_VIP ( "Full VIP"),
    FREE_PASS ("Free Pass"),
    ONE_DAY ( "One Day"),
    ONE_DAY_VIP ( "One Day VIP");

    private final String ticketType;

    TicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public static TicketType randomTicketType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    @Override
    public String toString() {
        return String.valueOf(ticketType);
    }
}