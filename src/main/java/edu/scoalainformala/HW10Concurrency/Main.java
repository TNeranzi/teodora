package edu.scoalainformala.HW10Concurrency;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        FestivalGate festivalGate = new FestivalGate();
        FestivalSimulator festivalSimulator = new FestivalSimulator(festivalGate);

        festivalSimulator.simulateTicketing(10, 40);
        FestivalStatisticsThread festivalStatisticsThread = new FestivalStatisticsThread(festivalGate);
        festivalStatisticsThread.start();
    }
}