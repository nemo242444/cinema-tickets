package uk.gov.dwp.uc.pairtest;

import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.Assert.assertEquals;
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;

public class TicketServiceImplTest {

    private TicketServiceUtil ticketServiceUtil = new TicketServiceUtil();

    @Test
    public void Should_ReturnTrue_When_ValidAccountId() {
        assertEquals(true, ticketServiceUtil.validateId(1L));
    }

    @Test
    public void Should_ReturnFalse_When_InvalidAccountId() {
        assertEquals(false, ticketServiceUtil.validateId(0L));
    }

    @Test
    public void Should_ReturnTrue_When_TicketRequestHasAdult() {
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 1);
        assertEquals(
                true,
                ticketServiceUtil.validateTicketRequest(ticketRequestAdult)
        );
    }

    @Test
    public void Should_ReturnTrue_When_TicketRequestHasAdultWithInfant() {
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketRequestInfant = new TicketTypeRequest(INFANT, 1);
        assertEquals(
                true,
                ticketServiceUtil.validateTicketRequest(ticketRequestAdult, ticketRequestInfant)
        );
    }

    @Test
    public void Should_ReturnFalse_When_TicketRequestHasNoTickets() {
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 0);
        assertEquals(
                false,
                ticketServiceUtil.validateTicketRequest(ticketRequestAdult)
        );
    }

    @Test
    public void Should_ReturnFalse_When_TicketRequestHasChildWithoutAdult() {
        TicketTypeRequest ticketRequestChild = new TicketTypeRequest(CHILD, 1);
        assertEquals(
                false,
                ticketServiceUtil.validateTicketRequest(ticketRequestChild)
        );
    }

    @Test
    public void Should_ReturnFalse_When_TicketRequestHasChildWithInfant() {
        TicketTypeRequest ticketRequestChild = new TicketTypeRequest(CHILD, 1);
        TicketTypeRequest ticketRequestInfant = new TicketTypeRequest(INFANT, 1);
        assertEquals(
                false,
                ticketServiceUtil.validateTicketRequest(ticketRequestChild, ticketRequestInfant)
        );
    }

    @Test
    public void Should_Return20_When_TicketIsForAdult() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ADULT, 1);
        assertEquals(20, ticketServiceUtil.generateTickets(ticketTypeRequest).getTotalPrice());
    }

    @Test
    public void Should_Return10_When_TicketIsForChild() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(CHILD, 1);
        assertEquals(10, ticketServiceUtil.generateTickets(ticketTypeRequest).getTotalPrice());
    }

    @Test
    public void Should_Return0_When_TicketIsForInfant() {
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(INFANT, 1);
        assertEquals(0, ticketServiceUtil.generateTickets(ticketTypeRequest).getTotalPrice());
    }

    @Test
    public void Should_Return100_When_TicketIsFor5AdultsAnd1Infant() {
        TicketTypeRequest ticketRequestAdults = new TicketTypeRequest(ADULT, 5);
        TicketTypeRequest ticketRequestInfant = new TicketTypeRequest(INFANT, 1);
        assertEquals(100,
                     ticketServiceUtil.generateTickets(
                             ticketRequestAdults,
                             ticketRequestInfant
                     ).getTotalPrice()
        );
    }

    @Test
    public void Should_Return110_When_TicketIsFor5AdultsAnd1Child() {
        TicketTypeRequest ticketRequestAdults = new TicketTypeRequest(ADULT, 5);
        TicketTypeRequest ticketRequestChild = new TicketTypeRequest(CHILD, 1);
        assertEquals(110,
                     ticketServiceUtil.generateTickets(
                             ticketRequestAdults,
                             ticketRequestChild
                     ).getTotalPrice()
        );
    }
}