package edu.scoalainformala.HW10Concurrency;

class FestivalStatisticsThread extends Thread {
    private final FestivalGate festivalGate;
    private int lastCount = 0;
    private boolean running = true;

    public FestivalStatisticsThread(FestivalGate festivalGate) {
        this.festivalGate = festivalGate;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(5000); // Sleep for 5 seconds
            } catch (InterruptedException e) {
                running = false;
                continue;
            }

            int currentCount = 0;
            for (int count : festivalGate.getTicketCounts()) {
                currentCount += count;
            }

            if (currentCount > lastCount) {
                lastCount = currentCount;
                System.out.println("Statistics:");
                System.out.println(currentCount + " people entered since the last count.");

                int[] ticketCounts = festivalGate.getTicketCounts();
                System.out.println(ticketCounts[TicketType.FULL.ordinal()] + " people have " + TicketType.FULL + " tickets.");
                System.out.println(ticketCounts[TicketType.FULL_VIP.ordinal()] + " people have " + TicketType.FULL_VIP + " passes.");
                System.out.println(ticketCounts[TicketType.FREE_PASS.ordinal()] + " people have " + TicketType.FREE_PASS + " passes.");
                System.out.println(ticketCounts[TicketType.ONE_DAY.ordinal()] + " people have " + TicketType.ONE_DAY + " passes.");
                System.out.println(ticketCounts[TicketType.ONE_DAY_VIP.ordinal()] + " people have " + TicketType.ONE_DAY_VIP + " passes.");
            } else if (currentCount == 0) {
                System.out.println("No people have entered since the last count.");
            }
        }
    }

    public void stopThread() {
        running = false;
        this.interrupt();
    }
}