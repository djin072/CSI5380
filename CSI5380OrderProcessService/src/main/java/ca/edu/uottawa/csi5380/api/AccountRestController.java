package ca.edu.uottawa.csi5380.api;

import ca.edu.uottawa.csi5380.model.Account;
import ca.edu.uottawa.csi5380.service.AccountService;
import io.swagger.annotations.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

/**
 * Provides REST services for account related queries.
 *
 * @author Kenny Byrd
 */
@RestController
@RequestMapping("/api/account")
@Api(value = "/api/account")
public class AccountRestController {

    private static final Logger LOGGER = LogManager.getLogger(OrderRestController.class);

    private final AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "Confirms user login.",
            notes = "Given the username and password, this method will determine if an account " +
                    "in the database exists with these exact credentials.")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Login Successful."),
            @ApiResponse(code = 400, message = "Username/password does not match."),
            @ApiResponse(code = 404, message = "Account not found.")
    })
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void accountLogin(@ApiParam(value = "Customer's email address", required = true) @RequestParam("username") String username,
                             @ApiParam(value = "Base64 encoded password", required = true) @RequestParam("password") String password) {
        LOGGER.info(String.format("accountLogin() called with username %s and password %s", username, password));
        accountService.accountLogin(username, new String(Base64.getDecoder().decode(password)));
    }

    @ApiOperation(value = "Creates a new customer account.",
            notes = "Creates a new account using the Customer information (email, first, last name, password), " +
                    "and stores default billing/shipping address. Fails if the email (account name) already exists. " +
                    "IMPORTANT: Addresses do not need IDs and Customer does not need default shipping IDs.")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void createAccount(@ApiParam(value = "New Customer information", required = true) @RequestBody Account account) {
        LOGGER.info(String.format("createAccount() called with params: %s", account.toString()));
        accountService.createAccount(account);
    }

    @ApiOperation(value = "Get a customer account.",
            notes = "Returns account information containing the Customer and default shipping/billing address. " +
                    "IMPORTANT: Customer's password will be returned in Base64 encoding.",
            response = Account.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getAccount(@ApiParam(value = "Customer's email address", required = true) @RequestParam("username") String username,
                              @ApiParam(value = "Base64 encoded password", required = true) @RequestParam("password") String password) {
        LOGGER.info(String.format("getAccount() called with username %s and password %s", username, password));
        return accountService.getAccount(username, new String(Base64.getDecoder().decode(password)));
    }


}