
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CreditPage;
import pages.MainPage;
import pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class TourPayingTest {

    PaymentPage paymentPage;
    CreditPage creditPage;
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }
    @Test
    @DisplayName("Should open payment page")
    void shouldOpenPaymentPage() {
        var mainPage = new MainPage();
        paymentPage = mainPage.tourPayment();
    }
    @Test
    @DisplayName("Should open credit page")
    void shouldOpenCreditPage() {
        var mainPage = new MainPage();
        creditPage = mainPage.tourCreditPayment();
    }
}
