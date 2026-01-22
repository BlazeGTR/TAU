package pl.pjatk.testy.lab6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PaymentService {
    private final QuickPayClient client;

    @Autowired(required = false)
    public PaymentService(QuickPayClient client) {
        this.client = client;
    }

    public String request(String endpoint, String method) {
        return client.callEndpoint(endpoint, method);
    }
}