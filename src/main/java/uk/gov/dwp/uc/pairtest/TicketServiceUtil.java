package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Arrays;

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
}
