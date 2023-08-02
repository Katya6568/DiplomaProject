package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.protobuf.StringValue;
import data.DataGenerator;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement paymentHeading = $(byText("Оплата по карте"));
    private SelenideElement cardNumber = $$("input.input__control").first();
    private SelenideElement month = $$("input.input__control").get(1);
    private SelenideElement year = $$("input.input__control").get(2);
    private SelenideElement cardOwner = $$("input.input__control").get(3);
    private SelenideElement CVCCode = $$("input.input__control").get(4);
    private SelenideElement continueButton = $$("[type= 'button']").find(Condition.exactText("Продолжить"));//find last

    private SelenideElement errorBankNotification= $$(".notification__content").find(Condition.exactText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement successPayNotification= $$(".notification__content").find(Condition.exactText("Операция одобрена Банком."));

    //private SelenideElement cardError= $$(".input_invalid .input__sub").find();
   // private SelenideElement monthError= $$(".input_invalid .input__sub").find()
   // private SelenideElement yearError= $$(".input_invalid .input__sub").find()
   private SelenideElement ownerError= $(byText("Владелец")).$(By.cssSelector(".input__sub"));
        //$("input__sub");
   // private SelenideElement CVCError= $$(".input_invalid .input__sub").find()






    public PaymentPage(){
        paymentHeading.shouldBe(Condition.visible);
        cardNumber.shouldBe(Condition.visible);
        month.shouldBe(Condition.visible);
        year.shouldBe(Condition.visible);
        cardOwner.shouldBe(Condition.visible);
        CVCCode.shouldBe(Condition.visible);
        continueButton.shouldBe(Condition.visible);
    }

    public void successVerify() {
        successPayNotification.shouldBe(Condition.visible, Duration.ofSeconds(20));
        errorBankNotification.shouldBe(Condition.hidden, Duration.ofSeconds(20));
    }
    public void failureVerify() {
        errorBankNotification.shouldBe(Condition.visible, Duration.ofSeconds(20));
        successPayNotification.shouldBe(Condition.hidden, Duration.ofSeconds(20));
    }
    public void payment(String cardInfo, String expireMonth, String expireYear, String cardHolder, String CVC) {
        cardNumber.setValue(cardInfo);
        month.setValue(expireMonth);
        year.setValue(expireYear);
        cardOwner.setValue(cardHolder);
        CVCCode.setValue(CVC);
        continueButton.click();
    }
    public PaymentPage successfulPayment(String cardInfo, String expireMonth, String expireYear, String cardHolder, String CVC){
        payment(cardInfo, expireMonth, expireYear, cardHolder, CVC);
        successVerify();
        return new PaymentPage();
    }
    public PaymentPage failedPayment(String cardInfo, String expireMonth, String expireYear, String cardHolder, String CVC) {
        payment(cardInfo, expireMonth, expireYear, cardHolder, CVC);
        failureVerify();
        return new PaymentPage();
    }
    public PaymentPage paymentNoCard(String expireMonth, String expireYear,String cardHolder, String CVC) {
        month.setValue(expireMonth);
        year.setValue(expireYear);
        cardOwner.setValue(cardHolder);
        CVCCode.setValue(CVC);
        continueButton.click();
        return new PaymentPage();
    }

    public PaymentPage paymentNoMonth(String cardInfo, String expireYear, String cardHolder, String CVC) {
        cardNumber.setValue(cardInfo);
        year.setValue(expireYear);
        cardOwner.setValue(cardHolder);
        CVCCode.setValue(CVC);
        continueButton.click();
        return new PaymentPage();
    }
    public PaymentPage paymentNoYear(String cardInfo, String expireMonth, String cardHolder, String CVC) {
        cardNumber.setValue(cardInfo);
        month.setValue(expireMonth);
        cardOwner.setValue(cardHolder);
        CVCCode.setValue(CVC);
        continueButton.click();
        return new PaymentPage();
    }
    public PaymentPage paymentNoOwner(String cardInfo, String expireMonth, String expireYear, String CVC) {
        cardNumber.setValue(cardInfo);
        month.setValue(expireMonth);
        year.setValue(expireYear);
        CVCCode.setValue(CVC);
        continueButton.click();
        return new PaymentPage();
    }
    public void ownerEmptyFieldError() {
        ownerError.shouldBe(Condition.visible)
                  .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public PaymentPage paymentNoCVC(String cardInfo, String expireMonth, String expireYear, String cardHolder) {
        cardNumber.setValue(cardInfo);
        month.setValue(expireMonth);
        year.setValue(expireYear);
        cardOwner.setValue(cardHolder);
        continueButton.click();
        return new PaymentPage();
    }







}
