package uk.gov.dwp.uc.pairtest;

import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import static org.junit.Assert.*;

import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.ADULT;

public class TicketServiceImplTest {

    private TicketServiceImpl ticketService = new TicketServiceImpl();

    @Test(expected = InvalidPurchaseException.class)
    public void Should_ReturnInvalidPurchaseException_When_InvalidRequestId() {

        Long accountId = 0L;
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 3);
        ticketService.purchaseTickets(accountId, ticketRequestAdult);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void Should_ReturnInvalidPurchaseException_When_InvalidTicketRequest() {

        Long accountId = 2L;
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 0);
        ticketService.purchaseTickets(accountId, ticketRequestAdult);
    }

    @Test
    public void Should_ReturnNothing_When_ValidRequest() {
        Long accountId = 2L;
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 2);
        ticketService.purchaseTickets(accountId, ticketRequestAdult);
    }
}
