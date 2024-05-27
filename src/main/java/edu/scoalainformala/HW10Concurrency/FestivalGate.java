package edu.scoalainformala.HW10Concurrency;


class FestivalGate {
    private final int[] ticketCounts = new int[TicketType.values().length];
    private int lastCount = 0;

    public synchronized void addTicket(TicketType ticketType) {
        ticketCounts[ticketType.ordinal()]++;
        int currentCount = 0;
        for (int count : ticketCounts) {
            currentCount += count;
        }
        if (currentCount > lastCount) {
            lastCount = currentCount;
            notify();
        }
    }

    public synchronized int[] getTicketCounts() {
        return ticketCounts.clone();
    }

    public synchronized boolean isEmpty() {
        for (int count : ticketCounts) {
            if (count > 0) {
                return false;
            }
        }
        return true;
    }
}

