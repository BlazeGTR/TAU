package pl.pjatk.testy.lab6;

import org.springframework.stereotype.Component;

@Component
public class QuickPayClientStub implements QuickPayClient {
    @Override
    public String callEndpoint(String endpoint, String method) {
        // To jest tylko "zaślepka", żeby Spring mógł wystartować.
        // W testach i tak zostanie zastąpiona przez Mockito.
        return "{}";
    }
}