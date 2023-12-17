package hu.cubix.spring.zpalvolgyi.logistics.controller;

import hu.cubix.spring.zpalvolgyi.logistics.dto.DelayDto;
import hu.cubix.spring.zpalvolgyi.logistics.dto.LoginDto;
import hu.cubix.spring.zpalvolgyi.logistics.model.TransportPlan;
import hu.cubix.spring.zpalvolgyi.logistics.repository.TransportPlanRepository;
import hu.cubix.spring.zpalvolgyi.logistics.service.InitDbService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class TransportPlanControllerIT {

    private static final String API_TRANSPORT_PLANS = "/api/transportPlans";
    private static final String API_LOGIN = "/api/login";

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    private InitDbService initDbService;

    @Autowired
    private TransportPlanRepository transportPlanRepository;

    @Test
    @Transactional
    public void testProcessFlow() {
        //ARRANGE
        initDbService.insertTestData();

        //ACT - call delay endpoint with user authority
        //ASSERT - forbidden because of authority issue
        call_delay_endpoint_forbidden(1L,1L);

        //ACT - call delay endpoint with TransportManager authority
        //ASSERT - process completes
        call_delay_endpoint_ok(1L,1L);

        //ASSERT - decreased income to 95 from 100
        TransportPlan transportPlan = transportPlanRepository.findById(1L).orElse(null);
        assertThat(transportPlan.getExpectedIncome()).isLessThan(100);
        System.out.println(transportPlan.getExpectedIncome());
    }

    private String getJwtToken(String username, String password){
        LoginDto loginDto = new LoginDto(username,password);

        return webTestClient
                .post()
                .uri(API_LOGIN)
                .bodyValue(loginDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
    }

    private void call_delay_endpoint_forbidden(long transportPlanId, long milestoneId){
        String bearerToken = getJwtToken("user", "pass");
        DelayDto delayDto = new DelayDto(milestoneId,75);

        webTestClient
                .post()
                .uri(API_TRANSPORT_PLANS + "/" + transportPlanId + "/delay")
                .bodyValue(delayDto)
                .header("Authorization", "Bearer " + bearerToken)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(DelayDto.class);
    }

    private void call_delay_endpoint_ok(long transportPlanId, long milestoneId){
        String bearerToken = getJwtToken("admin2", "pass");
        DelayDto delayDto = new DelayDto(milestoneId,75);

        webTestClient
                .post()
                .uri(API_TRANSPORT_PLANS + "/" + transportPlanId + "/delay")
                .bodyValue(delayDto)
                .header("Authorization", "Bearer " + bearerToken)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DelayDto.class);
    }
}
