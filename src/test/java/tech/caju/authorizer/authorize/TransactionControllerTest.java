package tech.caju.authorizer.authorize;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import tech.caju.authorizer.account.AccountService;
import tech.caju.authorizer.balance.BalanceService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {

  @LocalServerPort
  private Integer port;

  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:latest"
  );

  @BeforeAll
  static void beforeAll() {
    postgres.start();
  }

  @AfterAll
  static void afterAll() {
    postgres.stop();
  }

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  AccountService accountService;
  @Autowired
  BalanceService balanceService;

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost:" + port;
  }

  @Test
  @Order(value = 1)
  void shouldHaveFourAccountsByDefault() {
    Assertions.assertThat(accountService.count()).isEqualTo(4);
  }

  @Test
  @Order(value = 2)
  void shouldRetrieveDefaultAccount() {
    var account = accountService.findAccountByIdWithBalance(1L);
    Assertions.assertThat(account.getName()).isEqualTo("CONTA 1");
    Assertions.assertThat(account.getBalances().size()).isEqualTo(3);
  }

  @Test
  @Order(value = 3)
  void testHappyDay() {
    var bal = balanceService.getBalanceFromAccountByMcc(1L, "5811").get();

    Assertions.assertThat(bal.getAmount().toLong()).isEqualTo(10000L);


    var request = """
        {
        	"account": "1",
        	"totalAmount": 10.00,
        	"mcc": "5811",
        	"merchant": "PADARIA DO ZE               SAO PAULO BR"
        }
        """;

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .post("/transaction/authorize")
        .then()
        .statusCode(200)
        .body("code", equalTo("00"));

    bal = balanceService.getBalanceFromAccountByMcc(1L, "5811").get();
    Assertions.assertThat(bal.getAmount().toLong()).isEqualTo(9000L);
  }

  @Test
  @Order(value = 4)
  void testIncorrectMccShouldSubtractFromMerchantCategory() {
    var bal = balanceService.getBalanceCategoryFromMerchant(1L, "PADARIA DO ZE               SAO PAULO BR").get();

    Assertions.assertThat(bal.getAmount().toLong()).isEqualTo(10000L);


    var request = """
        {
        	"account": "1",
        	"totalAmount": 10.00,
        	"mcc": "9999",
        	"merchant": "PADARIA DO ZE               SAO PAULO BR"
        }
        """;

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .post("/transaction/authorize")
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("code", equalTo("00"));

    bal = balanceService.getBalanceCategoryFromMerchant(1L, "PADARIA DO ZE               SAO PAULO BR").get();
    Assertions.assertThat(bal.getAmount().toLong()).isEqualTo(9000L);
  }

  @Test
  @Order(value = 5)
  void testIncorrectMccShouldSubtractFromCashCategory() {
    var bal = balanceService.getBalanceCashFromAccount(3L).get();

    Assertions.assertThat(bal.getAmount().toLong()).isEqualTo(10000L);


    var request = """
        {
        	"account": "3",
        	"totalAmount": 10.00,
        	"mcc": "9999",
        	"merchant": "PADARIA DO ZE               SAO PAULO BR"
        }
        """;

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .post("/transaction/authorize")
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("code", equalTo("00"));

    bal = balanceService.getBalanceCashFromAccount(3L).get();
    Assertions.assertThat(bal.getAmount().toLong()).isEqualTo(9000L);
  }

  @Test
  @Order(value = 6)
  void testAccountWithoutBalanceShouldReturnError() {
    var request = """
        {
        	"account": "4",
        	"totalAmount": 10.00,
        	"mcc": "5411",
        	"merchant": "PADARIA DO ZE               SAO PAULO BR"
        }
        """;

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .post("/transaction/authorize")
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("code", equalTo("07"));

  }

  @Test
  @Order(value = 7)
  void testAccountWithBalanceShouldReturnErrorWhenTotalAmountMoreThanAmountInBalance() {
    var bal = balanceService.getBalanceFromAccountByMcc(1L, "5411").get();
    Assertions.assertThat(bal.getAmount().toLong()).isEqualTo(9000L);

    var request = """
        {
        	"account": "1",
        	"totalAmount": 110.00,
        	"mcc": "5411",
        	"merchant": "PADARIA DO ZE               SAO PAULO BR"
        }
        """;

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .post("/transaction/authorize")
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("code", equalTo("51"));

    bal = balanceService.getBalanceFromAccountByMcc(1L, "5411").get();
    Assertions.assertThat(bal.getAmount().toLong()).isEqualTo(9000L);

  }

}
