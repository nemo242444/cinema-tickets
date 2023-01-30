package uk.gov.dwp.uc.pairtest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}