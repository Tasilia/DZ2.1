package ru.netology.selenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AppOrderTest {
    @Test
    void testSuccessMessage() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий-Иван Иванов");
        $("[type='tel']").setValue("+77777777777");
        $(".checkbox__box").click();
        $("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(text("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void phoneValidationIfMoreThan11Digits() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий Иванов");
        $("[type='tel']").setValue("+777777777777");
        $("[type='button']").click();
        $("[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void phoneValidationIfLessThan11Digits() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий Иванов");
        $("[type='tel']").setValue("+77777");
        $("[type='button']").click();
        $("[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void phoneValidationWithoutAPlus() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий Иванов");
        $("[type='tel']").setValue("77777777777");
        $("[type='button']").click();
        $("[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void phoneValidationWhenPlusNotTheFirst() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий Иванов");
        $("[type='tel']").setValue("7+7777777777");
        $("[type='button']").click();
        $("[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void phoneValidationWithLetters() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий Иванов");
        $("[type='tel']").setValue("Юрий Иванов");
        $("[type='button']").click();
        $("[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void phoneValidationWithSymbols() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий Иванов");
        $("[type='tel']").setValue("+7 (777) 777-77-77");
        $("[type='button']").click();
        $("[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void phoneValidation() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий Иванов");
        $("[type='button']").click();
        $("[data-test-id='phone']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void nameValidationWithDigits() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий7");
        $("[type='button']").click();
        $("[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только" +
                " русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameValidationWithEnglishLetters() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Petya");
        $("[type='button']").click();
        $("[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только" +
                " русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameValidationWithSpecialSymbols() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий'");
        $("[type='button']").click();
        $("[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только" +
                " русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameValidation() {
        open("http://localhost:9999");
        $("[type='button']").click();
        $("[data-test-id='name']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void test() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Юрий-Иван Иванов");
        $("[type='tel']").setValue("+77777777777");
        $("[type='button']").click();
        $(".input_invalid").shouldHave(text("Я соглашаюсь с условиями обработки и" +
                " использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
