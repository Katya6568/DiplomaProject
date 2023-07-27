package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
//Я очень не люблю индексы в селекторах, но я голову сломала,
//как еще обозначить элементы. Как только я не пробовала, но в тестах он видит их только
//с индексами. Можете дать наводку?..

    private SelenideElement creditHeading = $(byText("Кредит по данным карты"));
    private SelenideElement cardNumber = $$("input.input__control").first();
    private SelenideElement month = $$("input.input__control").get(1);
    private SelenideElement year = $$("input.input__control").get(2);
    private SelenideElement cardOwner = $$("input.input__control").get(3);
    private SelenideElement CVCCode = $$("input.input__control").get(4);
    private SelenideElement continueButton = $$("[type= 'button']").find(Condition.exactText("Продолжить"));//find last

    private SelenideElement errorBankNotification= $$(".notification__content").find(Condition.exactText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement successPayNotification= $$(".notification__content").find(Condition.exactText("Операция одобрена Банком."));

    public CreditPage() {
        creditHeading.shouldBe(Condition.visible);
        cardNumber.shouldBe(Condition.visible);
        month.shouldBe(Condition.visible);
        year.shouldBe(Condition.visible);
        cardOwner.shouldBe(Condition.visible);
        CVCCode.shouldBe(Condition.visible);
        continueButton.shouldBe(Condition.visible);
    }

}
