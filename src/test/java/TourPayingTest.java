
import data.DataGenerator;
import lombok.val;
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

    @Test
    @DisplayName("Card payment should be successful with APPROVED card")
        //issue
    void shouldSuccessfullyPayIfCardApproved() {
        var mainPage = new MainPage();
        paymentPage = mainPage.tourPayment();
        var approvedCard = DataGenerator.getApprovedCardInfo();
        var validCardPayment = paymentPage.successfulPayment(String.valueOf(DataGenerator.getApprovedCardInfo()),
                DataGenerator.generateValidMonth(),
                String.valueOf(DataGenerator.generateValidYear("yy")),
                DataGenerator.generateValidOwnerName(),
                DataGenerator.generateValidCVCCode());
    }

    @Test
    @DisplayName("Card payment should be failed with DECLINED card")//issue
    void shouldBeErrorIfDeclinedCard() {
        var mainPage = new MainPage();
        paymentPage = mainPage.tourPayment();
        var declinedCard = DataGenerator.getDeclinedCardInfo();
        var invalidCardPayment = paymentPage.failedPayment(String.valueOf(DataGenerator.getDeclinedCardInfo()),
                DataGenerator.generateValidMonth(),
                String.valueOf(DataGenerator.generateValidYear("yy")),
                DataGenerator.generateValidOwnerName(),
                DataGenerator.generateValidCVCCode());
    }

    @Test
    @DisplayName("Should be error if card owner field is empty")
    void shouldBeErrorIfCardOwnerEmpty() {
        var mainPage = new MainPage();
        paymentPage = mainPage.tourPayment();
        var approvedCard = DataGenerator.getApprovedCardInfo();
        var paymentNoOwner = paymentPage.paymentNoOwner(String.valueOf(DataGenerator.getApprovedCardInfo()),
                DataGenerator.generateValidMonth(),
                String.valueOf(DataGenerator.generateValidYear("yy")),
                DataGenerator.generateValidCVCCode());
        paymentPage.ownerEmptyFieldError();
    }
}
