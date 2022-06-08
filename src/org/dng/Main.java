package org.dng;

import java.util.*;
import java.util.stream.Stream;

/**
 * 1.	Разработать класс «Банковский счет», который содержит информацию о денежных средствах
 * на данном счете, а так же информацию о валюте данного счета (реализацию валют выбрать самостоятельно,
 * от String до выделенного класса или класса со статическими полями, рекомендуется реализовать аналог
 * перечисления enumeration). Добавить в класс все необходимые методы.
 * 2.	Выполнить задания, указанные ниже, используя stream API
 * 1)	Сформировать список счетов со случайными значениями баланса, но строго заданными значениями валют
 * при инициализации (можно использовать stream.of(<валюты>).map(<приведение к счету)>)
 * 2)	Вывести список на экран через forEach (в дальнейшем использовать данную команду для промежуточного вывода)
 * 3)	Вывести отсортированный список по балансу
 * 4)	Вывести отсортированный список по названию валют
 * 5)	Вывести все долларовые счета
 * 6)	Вывести топ-3 рублевых счетов
 * 7)	Вывести сумму всех счетов валюты «евро»
 * 8)	Посчитать среднее значение (среднее арифметическое) для рублевых счетов
 * 9)	Получить set с валютами (существующие в списке валюты) из списка счетов
 * 10)	Конвертировать все счета в доллары (использовать замыкание для передачи коэффициентов,
 * коэффициенты желательно хранить во вспомогательном классе, здесь хорошо будет реализовать анонимный класс и
 * реализацию соответствующего интерфейса).
 */

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("1. Сформировать список счетов со случайными значениями баланса, но строго заданными значениями валют");
        Random rnd = new Random();
        List<BankAccount> listAccounts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listAccounts.addAll(Stream
                    .of(CurrencyE.values()).map(c ->
                            new BankAccount(c,
                                    "" + rnd.nextInt(1_000_000, 2_000_000),
                                    rnd.nextInt(1_000, 2_000)
                            )
                    )
                    .toList());
        }

        System.out.println("\n2. Вывести список на экран");
        listAccounts.forEach(System.out::println);

        System.out.println("\n3. Вывести отсортированный список по балансу");
        listAccounts.stream()
                .sorted(Comparator.comparingDouble(c -> c.getBalance()))
                .forEach(System.out::println);

        System.out.println("\n4. Вывести отсортированный список по названию валют");
        listAccounts.stream()
                .sorted(Comparator.comparing(BankAccount::getCurrency))
                .forEach(System.out::println);

        System.out.println("\n5. Вывести все долларовые счета");
        listAccounts.stream()
                .filter(c -> CurrencyE.USD.equals(c.getCurrency()))
                .forEach(System.out::println);

        System.out.println("\n6. Вывести топ-3 рублевых счетов");
        listAccounts.stream()
                .filter(c -> CurrencyE.RUB.equals(c.getCurrency()))
                .sorted(Comparator.comparingDouble(c -> c.getBalance()))
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\n7. Вывести сумму всех счетов валюты «евро»");
        System.out.println("sum is: " + listAccounts.stream()
                .filter(c -> CurrencyE.EUR.equals(c.getCurrency()))
                .mapToDouble(x -> x.getBalance())
                .sum()
                + " EUR");

        System.out.println("\n8. Посчитать среднее значение (среднее арифметическое) для рублевых счетов");
        System.out.println("average is: " + listAccounts.stream()
                .filter(c -> CurrencyE.RUB.equals(c.getCurrency()))
                .mapToDouble(x -> x.getBalance())
                .average()
                + " RUB");

        System.out.println("\n9. Получить set с валютами (существующие в списке валюты) из списка счетов");
        Set<BankAccount> setByCurrency = new HashSet<>();
        setByCurrency.addAll(listAccounts.stream()
                .filter(c -> CurrencyE.USD.equals(c.getCurrency()))
                .toList());
        setByCurrency.addAll(listAccounts.stream()
                .filter(c -> CurrencyE.EUR.equals(c.getCurrency()))
                .toList());

        setByCurrency.forEach(System.out::println);

        System.out.println("\n10. Конвертировать все счета в доллары");

        BankAccount bankAccount = new BankAccount(CurrencyE.EUR, "1111111", 1);
        System.out.println("before converting");
        System.out.println(bankAccount.getCurrency());
        System.out.println(bankAccount.getBalance());


        System.out.println("after converting to usd");
        bankAccount.convert(Converter::toConvert, CurrencyE.USD);
        System.out.println(bankAccount.getCurrency());
        System.out.println(bankAccount.getBalance());


        System.out.println("after converting to rub");
        bankAccount.convert(Converter::toConvert, CurrencyE.RUB);
        System.out.println(bankAccount.getCurrency());
        System.out.println(bankAccount.getBalance());

    }
}
