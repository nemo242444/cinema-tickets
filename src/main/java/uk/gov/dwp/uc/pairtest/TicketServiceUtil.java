package uk.gov.dwp.uc.pairtest;

public class TicketServiceUtil {

    public boolean validateId(Long accountId) {
        return accountId.compareTo(1L) >= 0;
    }
}
