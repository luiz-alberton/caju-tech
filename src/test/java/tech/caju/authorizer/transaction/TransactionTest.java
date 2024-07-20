package tech.caju.authorizer.transaction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = TransactionController.class)
public class TransactionTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void givenHappyDay() throws Exception {

    mockMvc.perform(post("/account"));

    var request = """
        {
        	"account": "1",
        	"totalAmount": 100.00,
        	"mcc": "5811",
        	"merchant": "PADARIA DO ZE               SAO PAULO BR"
        }
        """;

    mockMvc.perform(post("/authorize")
        .contentType(MediaType.APPLICATION_JSON)
        .content(request));

  }
}
