package tech.caju.authorizer.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping(value = "/authorize", produces = "application/json" )
  public ResponseEntity<?> authorizeTransaction(@RequestBody TransactionDto transactionDto) {
    transactionService.handleTransaction(transactionDto);
    return ResponseEntity.ok("{\"code\": \"00\"}");
  }
}
