package com.yehorpolishchuk.periodicals.datastructures;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.postgresql.util.PGmoney;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Payment {

    /**
     * Payment id to be stored in database
     * */
    private String id;

    /**
     * All info about account the sum has been transferred to.
     * Preferably, the bank account number of payee, giro or card number.
     * Not standardized.
     * */
    private String transferredToAccount;

    /**
     * The sum of payment (transfer).
     * */
    private double sum;

    /**
     * Currency code should be set accordingly to ISO4217 standard
     * Read more https://www.iban.com/currency-codes
     * https://en.wikipedia.org/wiki/ISO_4217
     * */
    private String currencyCode;

    /**
     * Read more about Data types correspondence: https://jdbc.postgresql.org/documentation/head/8-date-time.html
     * LocalDateTime in java <=> TIMESTAMP WITH TIMEZONE in PostgreSQL
     * ISO-8601 calendar system, such as 2007-12-03T10:15:30+01:00
     *
     * ZoneOffset zoneOffSet = ZoneOffset.of("+02:00");
     * OffsetDateTime offsetDateTime = OffsetDateTime.now(zoneOffSet);
     *
     * Read more: https://www.baeldung.com/java-zoneddatetime-offsetdatetime
     * */
    private LocalDateTime paymentDateTime;

    /**
     * Paid services description.
     * */
    private String description;

    /**
     * Extra field for quick filtering reader payments.
     * */
    private Reader payer;


    /**
     * Payer (reader) id
     * */
    private String readerId;
}
