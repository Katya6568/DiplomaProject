package data;

import lombok.Value;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import static org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.toString;


public class DataGenerator {
    private DataGenerator() {
    }
    @Value
    public static class CardInfo {
        String number;
    }

    public static CardInfo getArrpovedCardInfo() {
        return new CardInfo("4444 4444 4444 4441");
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo("4444 4444 4444 4442");
    }

    private static Faker faker = new Faker(new Locale("en"));
    private String generateValidMonth() {
//        String maxMonth = LocalDate.now().getMonthValue().minus(1).toString();
//        Byte max = Byte.valueOf(maxMonth);
//        return faker.number().numberBetween();
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



    public int generateInvalidYearExpired(String pattern) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yy");
        String maximumYear = dateFormat.format(currentDate);
        int maxYear = Integer.valueOf(maximumYear);
        Random random = new Random();
        return random.nextInt(maxYear);
    }

    public int generateInvalidYearFuture(String pattern) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yy");
        String minimumYear = dateFormat.format(currentDate);
        int minYear = Integer.valueOf(minimumYear);
        return (int) (Math.random()*(100 - minYear)) + minYear;
    }
    public int generateValidYear(String pattern) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yy");
        String year = dateFormat.format(currentDate);
        int currentYear = Integer.valueOf(year);
        int minYear = currentYear - 5;
        int maxYear = currentYear;
        return faker.number().numberBetween(minYear, maxYear);
    }

    public String generateValidOwnerName() {
        return  faker.name().fullName();
    }

    public String generateInvalidOneWordOwnerName(){
        return faker.name().username();
    }

    public String generateCyrillicName() {
        Faker faker1 = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public String generateValidCVCCode() {
        return faker.number().digits(3);
    }







}
