package uk.gov.dwp.uc.pairtest.model;

import java.util.List;

public class Reservation {

    List<Ticket> ticket;
    int totalPrice = 0;
    int totalNumOfSeats = 0;

    public Reservation() {}

    public Reservation(List<Ticket> ticket, int totalPrice, int totalNumOfSeats) {
        this.ticket = ticket;
        this.totalPrice = totalPrice;
        this.totalNumOfSeats = totalNumOfSeats;
    }

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalNumOfSeats() {
        return totalNumOfSeats;
    }

    public void setTotalNumOfSeats(int totalNumOfSeats) {
        this.totalNumOfSeats = totalNumOfSeats;
    }
}
