# Laboratorium 2 - Testy Selenium (Cucumber Ready)

## Wybrane aplikacje:
1. **Allegro** (lub inny sklep e-commerce) - test wyszukiwania i koszyka.
2. **GitHub** - test walidacji formularza logowania.

## Scenariusze Testowe:

### Scenariusz 1: Dodanie produktu do koszyka
**Aplikacja:** Sklep Internetowy
**Przeglądarka:** Chrome

**Kroki:**
1. Otwórz stronę główną.
2. Wpisz frazę "Laptop" w polu wyszukiwania.
3. Kliknij ikonę lupy.
4. Kliknij w nazwę pierwszego wyświetlonego produktu.
5. Kliknij przycisk "Dodaj do koszyka".
6. Zweryfikuj, czy produkt znajduje się w koszyku.

### Scenariusz 2: Logowanie z błędnymi danymi
**Aplikacja:** Serwis społecznościowy/GitHub
**Przeglądarka:** Firefox

**Kroki:**
1. Przejdź do strony logowania.
2. Wpisz e-mail: `student_test@wp.pl`.
3. Wpisz dowolne błędne hasło.
4. Kliknij "Zaloguj".
5. Sprawdź, czy pojawił się czerwony alert z informacją o błędzie.