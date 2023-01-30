package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.model.Reservation;
import uk.gov.dwp.uc.pairtest.model.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.ADULT;

public class TicketServiceUtil {

    public boolean validateId(Long accountId) {
        return accountId.compareTo(1L) >= 0;
    }

    public boolean validateTicketRequest(TicketTypeRequest... ticketTypeRequests) {
        return shouldContainAdultInTicketRequests(ticketTypeRequests) &&
                shouldOnlyContainRequestWith1PlusTickets(ticketTypeRequests);
    }

    private boolean shouldContainAdultInTicketRequests(TicketTypeRequest... ticketTypeRequests) {
        return Arrays.stream(ticketTypeRequests).anyMatch(
                ticketTypeRequest -> ADULT.equals(ticketTypeRequest.getTicketType())
        );
    }

    private boolean shouldOnlyContainRequestWith1PlusTickets(TicketTypeRequest... ticketTypeRequests) {
        return Arrays.stream(ticketTypeRequests).allMatch(
                ticketTypeRequest -> ticketTypeRequest.getNoOfTickets() > 0
        );
    }

    public Reservation generateTickets(TicketTypeRequest... ticketTypeRequests) {
        Reservation reservation = new Reservation();
        List<Ticket> tickets = new ArrayList<>();

        for (TicketTypeRequest request : ticketTypeRequests) {

            for (int i = 0; i < request.getNoOfTickets(); i++) {
                tickets.add(
                        buildTicketByPersonType(request.getTicketType())
                );
            }
        }

        reservation.setTicket(tickets);
        reservation.setTotalPrice(tickets.stream().mapToInt(Ticket::getPrice).sum());

        return reservation;
    }

    private Ticket buildTicketByPersonType (TicketTypeRequest.Type type) {
        Ticket ticket;

        switch (type) {
            case ADULT:
                ticket = new Ticket(20, true);
                break;
            case CHILD:
                ticket = new Ticket(10, true);
                break;
            default:
                ticket = new Ticket(0, false);
                break;
        }

        return ticket;
    }
}
