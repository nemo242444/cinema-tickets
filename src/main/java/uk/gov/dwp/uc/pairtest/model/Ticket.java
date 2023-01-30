package uk.gov.dwp.uc.pairtest.model;

public class Ticket {

    private int price;

    private boolean isSeatRequired;

    public Ticket(int price, boolean isSeatRequired) {
        this.price = price;
        this.isSeatRequired = isSeatRequired;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getIsSeatRequired() {
        return isSeatRequired;
    }

    public void setIsSeatRequired(boolean isSeatRequired) {
        this.isSeatRequired = isSeatRequired;
    }
}
