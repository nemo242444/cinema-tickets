package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.model.Reservation;


public class TicketServiceImpl implements TicketService {

    private TicketServiceUtil ticketServiceUtil = new TicketServiceUtil();
    private TicketPaymentService ticketPaymentService = new TicketPaymentServiceImpl();
    private SeatReservationService seatReservationService = new SeatReservationService() {
        @Override
        public void reserveSeat(long accountId, int totalSeatsToAllocate) {
            /*
                I added this here instead of a separate Impl class as the task mentions
                not modifying the "thirdparty" package.
            */
        }
    };


    /**
     * Should only have private methods other than the one below.
     */
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        Reservation reservation = ticketServiceUtil.generateTickets(ticketTypeRequests);

        if (!isRequestValid(accountId, ticketTypeRequests) || reservation.getTotalNumOfSeats() > 20) {
            throw new InvalidPurchaseException();
        } else {
            ticketPaymentService.makePayment(accountId, reservation.getTotalPrice());
            seatReservationService.reserveSeat(accountId, reservation.getTotalNumOfSeats());
        }
    }

    private boolean isRequestValid(Long accountId, TicketTypeRequest... ticketTypeRequests) {
        return ticketServiceUtil.validateId(accountId) &&
                ticketServiceUtil.validateTicketRequest(ticketTypeRequests);
    }
}
