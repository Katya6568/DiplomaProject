package data;

import lombok.Value;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;



public class DataGenerator {
    private DataGenerator() {
    }
    @Value
    public static class CardInfo {
        private String number;
    }

    public static CardInfo getApprovedCardInfo() {
        return new CardInfo("4444 4444 4444 4441");
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo("4444 4444 4444 4442");
    }

    public static Faker faker = new Faker(new Locale("en"));

    public static String generateValidMonth() {
        int max = (LocalDate.now().getMonthValue());
        int min = 1;
        Random random = new Random();
        int month = faker.number().numberBetween(min, max);
        return String.format("%02d %n", month); //
    }

    public int generateInvalidMonth(int min, int max) {
        Random random = new Random();
        return random.nextInt(100 - 13) + 13;
    }
//Запуталась с генерацией данных на (не)валидный месяц. Понятно, что валидные данные
//будут в диапазоне (0;текущий месяц],а не валидные - (текущий месяц;12].
//Но всё это верно только при то, что мы имеем ввиду только текущий год.
//Например, сегодня мы заполняем поля с картой до 11-го месяца 2024 года. Тогда любой
//месяц у нас будет валидный, получается. Я не понимаю, как установить зависимость
//данных двух разных полей и генераторов. Можете с этим помочь?

    public static int generateValidYear(String pattern) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yy");
        String minimumYear = dateFormat.format(currentDate);
        int minYear = Integer.valueOf(minimumYear);
        int maxYear = minYear + 6;
        return faker.number().numberBetween(minYear, maxYear);
    }

    public int generateInvalidYearExpired(String pattern) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yy");
        String maximumYear = dateFormat.format(currentDate);
        int maxYear = Integer.valueOf(maximumYear);
        Random random = new Random();
        return random.nextInt(maxYear);
    }

    public static String generateInvalidYearFuture(String pattern) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yy");
        String year = dateFormat.format(currentDate);
        int currentYear = Integer.valueOf(year);
        int minYear = currentYear + 7;
        int maxYear = currentYear + 15;
        int invalidYear = faker.number().numberBetween(minYear, maxYear);
        return Integer.toString(invalidYear);
    }

    public static String generateValidOwnerName() {
        return  faker.name().name();
    }

    public String generateOneWordInvalidOwnerName() {
        return faker.name().username();
    }

    public String generateCyrillicInvalidOwnerName() {
        Faker faker1 = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public int generateInvalidNameNumbers() {
        return faker.number().randomDigitNotZero();
    }

    public static String generateValidCVCCode() {
        return faker.number().digits(3);
    }
    public String generateInvalidCVCCode1Digit() {
        return faker.number().digits(1);
    }
    public String generateInvalidCVCCode2Digits() {
        return faker.number().digits(2);
    }
    public String generateInvalidCardNumberShort() {
        return faker.number().digits(15);
    }
    public String generateCardNumberNotInDB() {
        return faker.business().creditCardNumber();
    }










}
