Feature: Testy automatyczne Selenium w Cucumberze

  Scenario: Nieudane logowanie do serwisu GitHub
    Given Uzytkownik otwiera strone logowania GitHub w przegladarce Firefox
    When Uzytkownik wpisuje bledny login "nieistniejacy_user" i haslo "bledneHaslo123"
    And Klika przycisk logowania
    Then Powinien zobaczyc komunikat o bledzie "Incorrect username or password"

  Scenario: Dodanie produktu do koszyka w sklepie SauceDemo
    Given Uzytkownik jest zalogowany na stronie SauceDemo w przegladarce Chrome
    When Uzytkownik klika przycisk Add to Cart przy produkcie Backpack
    Then Ikona koszyka powinna wyswietlac liczbe "1"