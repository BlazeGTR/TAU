# QuickPay REST API - Dokumentacja Atrap (Mocks)

System symuluje bramkę płatniczą. API zwraca dane w formacie JSON.

| Metoda | Endpoint             | Opis                                  | Przykładowy JSON (Mock) |
|:-------|:---------------------|:--------------------------------------|:------------------------|
| GET    | `/payments`          | Lista wszystkich transakcji           | `[{"id":1, "amt":100}, {"id":2, "amt":50}]` |
| GET    | `/payments/{id}`     | Szczegóły konkretnej płatności        | `{"id":101, "status":"SUCCESS"}` |
| POST   | `/payments`          | Utworzenie nowej płatności            | `{"id":202, "status":"PENDING"}` |
| DELETE | `/payments/{id}`     | Anulowanie transakcji                 | `{"result":"DELETED", "id":101}` |
| GET    | `/balance`           | Aktualny stan portfela                | `{"amount":1500.0, "currency":"PLN"}` |
| GET    | `/rates/USD`         | Kurs wymiany dla dolara               | `{"currency":"USD", "rate":4.02}` |
| POST   | `/refunds`           | Zlecenie zwrotu środków               | `{"refund_id":"RF99", "status":"ACCEPTED"}` |
| GET    | `/users/{id}/history`| Historia operacji użytkownika         | `{"user_id":7, "total_ops":15}` |
| PUT    | `/limits`            | Aktualizacja limitu dziennego         | `{"new_limit":5000, "status":"OK"}` |
| GET    | `/health`            | Status techniczny systemu             | `{"status":"HEALTHY", "uptime":"24h"}` |