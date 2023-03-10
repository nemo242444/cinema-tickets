package uk.gov.dwp.uc.pairtest;

import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.Assert.*;
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;

public class TicketServiceUtilTest {

    private final TicketServiceUtil ticketServiceUtil = new TicketServiceUtil();

    @Test
    public void Should_ReturnTrue_When_ValidAccountId() {
        assertTrue(ticketServiceUtil.validateId(1L));
    }

    @Test
    public void Should_ReturnFalse_When_InvalidAccountId() {
        assertFalse(ticketServiceUtil.validateId(0L));
    }

    @Test
    public void Should_ReturnTrue_When_TicketRequestHasAdult() {
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 1);
        assertTrue(ticketServiceUtil.validateTicketRequest(ticketRequestAdult));
    }

    @Test
    public void Should_ReturnTrue_When_TicketRequestHasAdultWithInfant() {
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketRequestInfant = new TicketTypeRequest(INFANT, 1);
        assertTrue(ticketServiceUtil.validateTicketRequest(ticketRequestAdult, ticketRequestInfant));
    }

    @Test
    public void Should_ReturnFalse_When_TicketRequestHasNoTickets() {
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 0);
        assertFalse(ticketServiceUtil.validateTicketRequest(ticketRequestAdult));
    }

    @Test
    public void Should_ReturnFalse_When_TicketRequestHasChildWithoutAdult() {
        TicketTypeRequest ticketRequestChild = new TicketTypeRequest(CHILD, 1);
        assertFalse(ticketServiceUtil.validateTicketRequest(ticketRequestChild));
    }

    @Test
    public void Should_ReturnFalse_When_TicketRequestHasChildWithInfant() {
        TicketTypeRequest ticketRequestChild = new TicketTypeRequest(CHILD, 1);
        TicketTypeRequest ticketRequestInfant = new TicketTypeRequest(INFANT, 1);
        assertFalse(ticketServiceUtil.validateTicketRequest(ticketRequestChild, ticketRequestInfant));
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

    @Test
    public void Should_Return1_When_TicketIsForAdult() {
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketRequestInfant = new TicketTypeRequest(INFANT, 1);
        assertEquals(1,
                     ticketServiceUtil.generateTickets(
                             ticketRequestAdult,
                             ticketRequestInfant
                     ).getTotalNumOfSeats()
        );
    }

    @Test
    public void Should_Return1_When_TicketIsForAdultAndInfant() {
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketRequestInfant = new TicketTypeRequest(INFANT, 1);
        assertEquals(1,
                     ticketServiceUtil.generateTickets(
                             ticketRequestAdult,
                             ticketRequestInfant
                     ).getTotalNumOfSeats()
        );
    }

    @Test
    public void Should_Return2_When_TicketIsForAdultChildAndInfant() {
        TicketTypeRequest ticketRequestAdult = new TicketTypeRequest(ADULT, 1);
        TicketTypeRequest ticketRequestChild = new TicketTypeRequest(CHILD, 1);
        TicketTypeRequest ticketRequestInfant = new TicketTypeRequest(INFANT, 1);
        assertEquals(2,
                     ticketServiceUtil.generateTickets(
                             ticketRequestAdult,
                             ticketRequestChild,
                             ticketRequestInfant
                     ).getTotalNumOfSeats()
        );
    }

    @Test
    public void Should_Return7_When_TicketIsFor7Adults() {
        TicketTypeRequest ticketRequestAdults = new TicketTypeRequest(ADULT, 7);
        assertEquals(7,
                     ticketServiceUtil.generateTickets(ticketRequestAdults).getTotalNumOfSeats()
        );
    }
}