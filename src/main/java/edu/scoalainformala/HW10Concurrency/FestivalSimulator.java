package edu.scoalainformala.HW10Concurrency;

import java.util.Random;


class FestivalSimulator {
    private final FestivalGate festivalGate;

    public FestivalSimulator(FestivalGate festivalGate) {
        this.festivalGate = festivalGate;
    }

    public void simulateTicketing(int minNumberOfPeople, int maxNumberOfPeople) {
        for (int i = 0; i < minNumberOfPeople + new Random().nextInt(maxNumberOfPeople - minNumberOfPeople + 1); i++) {
            FestivalAttendeeThread person = new FestivalAttendeeThread(festivalGate, TicketType.randomTicketType());
            person.start();
        }
    }
}
