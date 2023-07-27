package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.Random;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement paymentHeading = $(byText("Оплата по карте"));
    private SelenideElement cardNumber = $$("input.input__control").first();
    private SelenideElement month = $$("input.input__control").get(1);
    private SelenideElement year = $$("input.input__control").find(Condition.exactText("Год"));
    private SelenideElement cardOwner = $$("input.input__control").find(Condition.exactText("Владелец"));
    private SelenideElement CVCCode = $$("input.input__control").find(Condition.exactText("CVC/CVV"));
    private SelenideElement continueButton = $$("[type= 'button']").find(Condition.exactText("Продолжить"));//find last

    private SelenideElement errorBankNotification= $$(".notification__content").find(Condition.exactText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement successPayNotification= $$(".notification__content").find(Condition.exactText("Операция одобрена Банком."));

    public PaymentPage(){
        paymentHeading.shouldBe(Condition.visible);
        cardNumber.shouldBe(Condition.visible);
        month.shouldBe(Condition.visible);
//        year.shouldBe(Condition.visible);
//        cardOwner.shouldBe(Condition.visible);
//        CVCCode.shouldBe(Condition.visible);
//        continueButton.shouldBe(Condition.visible);
    }



}
