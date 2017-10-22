package ca.edu.uottawa.csi5380.service;

import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class AuthorizationService {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationService.class);

    private final AtomicLong counter;
    private final CreditCardValidator creditCardValidator;

    public AuthorizationService() {
        this.counter = new AtomicLong();
        this.creditCardValidator = new CreditCardValidator();
    }

    /**
     * Denies order every 5th request OR if the provided
     * credit card number is in an invalid format.
     *
     * @param creditCardNum
     * @return
     */
    public boolean authorizeOrder(String creditCardNum) {
        // Reject every 5th request
        LOGGER.info("Authorizing Order Request #: " + (counter.get()+1));
        boolean isAutoAuthorized = counter.incrementAndGet() % 5 != 0;
        if (!isAutoAuthorized) {
            LOGGER.info("Order Request was automatically rejected (every 5th request must be rejected).");
            return false;
        }

        boolean isValidCC = validateCreditCard(creditCardNum);
        if (!isValidCC) {
            LOGGER.info("Order Request was rejected. Invalid credit card number format.");
        }
        return isValidCC;
    }

    public boolean validateCreditCard(String creditCardNum) {
        return creditCardValidator.isValid(creditCardNum);
    }

}
