package pl.pjatk.testy.lab6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService service; // Spring wstrzykuje prawdziwy serwis

    @MockitoBean
    private QuickPayClient mockClient; // Spring tworzy atrapę i "wstrzykuje" ją do serwisu powyżej

    @Test void testGetAllPayments() {
        when(mockClient.callEndpoint("/payments", "GET")).thenReturn("[{\"id\":1}, {\"id\":2}]");
        assertTrue(service.request("/payments", "GET").contains("1"));
    }

    @Test void testGetSinglePayment() {
        when(mockClient.callEndpoint("/payments/101", "GET")).thenReturn("{\"id\":101, \"status\":\"SUCCESS\"}");
        assertEquals("{\"id\":101, \"status\":\"SUCCESS\"}", service.request("/payments/101", "GET"));
    }

    @Test void testCreatePayment() {
        when(mockClient.callEndpoint("/payments", "POST")).thenReturn("{\"id\":202, \"status\":\"PENDING\"}");
        assertTrue(service.request("/payments", "POST").contains("PENDING"));
    }

    @Test void testDeletePayment() {
        when(mockClient.callEndpoint("/payments/101", "DELETE")).thenReturn("{\"result\":\"DELETED\"}");
        assertTrue(service.request("/payments/101", "DELETE").contains("DELETED"));
    }

    @Test void testGetBalance() {
        when(mockClient.callEndpoint("/balance", "GET")).thenReturn("{\"amount\":1500.0}");
        assertTrue(service.request("/balance", "GET").contains("1500.0"));
    }

    @Test void testGetRates() {
        when(mockClient.callEndpoint("/rates/USD", "GET")).thenReturn("{\"rate\":4.02}");
        assertTrue(service.request("/rates/USD", "GET").contains("4.02"));
    }

    @Test void testPostRefund() {
        when(mockClient.callEndpoint("/refunds", "POST")).thenReturn("{\"status\":\"ACCEPTED\"}");
        assertTrue(service.request("/refunds", "POST").contains("ACCEPTED"));
    }

    @Test void testUserHistory() {
        when(mockClient.callEndpoint("/users/7/history", "GET")).thenReturn("{\"total_ops\":15}");
        assertTrue(service.request("/users/7/history", "GET").contains("15"));
    }

    @Test void testUpdateLimits() {
        when(mockClient.callEndpoint("/limits", "PUT")).thenReturn("{\"status\":\"OK\"}");
        assertEquals("{\"status\":\"OK\"}", service.request("/limits", "PUT"));
    }

    @Test void testHealthCheck() {
        when(mockClient.callEndpoint("/health", "GET")).thenReturn("{\"status\":\"HEALTHY\"}");
        assertTrue(service.request("/health", "GET").contains("HEALTHY"));
    }
}