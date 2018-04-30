package fr.onefox.mywarehouse.controllers;

import fr.onefox.mywarehouse.domain.Transaction;
import fr.onefox.mywarehouse.repositories.TransactionRepositoryService;
import fr.onefox.mywarehouse.services.TransactionService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepositoryService transactionRepositoryService;

    /**
     * Go to home page
     *
     * @return
     */
    @RequestMapping("/")
    public String goToHome() {
        return "home";
    }

    /**
     * Add transaction
     *
     * @param transaction
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity addTransaction(@ApiParam("transaction") @Valid Transaction transaction, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok().body(transactionService.insertTransaction(transaction));
    }

    /**
     * Find transactions
     *
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping(
            value = "/transactions",
            method = RequestMethod.GET
    )
    public ResponseEntity findPaginated(
            @RequestParam(value = "limit", required = false, defaultValue = "50") int limit, @RequestParam(value = "offset", required = false, defaultValue = "0") int offset) {
        return ResponseEntity.ok().body(transactionRepositoryService.listAll(limit, offset));
    }

}
