package com.yehorpolishchuk.PeriodicalsSpring.services;

import com.yehorpolishchuk.PeriodicalsSpring.models.*;
import com.yehorpolishchuk.PeriodicalsSpring.repository.*;
import com.yehorpolishchuk.PeriodicalsSpring.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.sql.Date;


@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private PeriodicalRepository periodicalRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReaderPrimitiveRepository readerPrimitiveRepository;


    public double calculatePriceOfSubscription(int periodicalId, int inMonthSubscriptionTerm){
        Optional<Periodical> periodicalOptional = this.periodicalRepository.findById(periodicalId);
        if (!periodicalOptional.isPresent()){
            return -1.0;
        }

        Periodical periodical = periodicalOptional.get();

        double pricePerOne = periodical.getPricePerOne();
        double timesPerMonth = periodical.getFrequencyPerYear() / 12.0;

        return Math.round(timesPerMonth * inMonthSubscriptionTerm) * pricePerOne;
    }

    public Optional<SubscribeUtil> addSubscription(SubscribeUtil subscribeUtil){
        if (!periodicalRepository.existsById(subscribeUtil.getSubscription().getPeriodicalId())){
            return Optional.empty();
        }

        double priceCalculated = calculatePriceOfSubscription(subscribeUtil.getSubscription().getPeriodicalId(),
                subscribeUtil.getSubscription().getTermInMonth());
        if (Double.compare(priceCalculated, 0.0) <= 0){
            return Optional.empty();
        }

        subscribeUtil.getPayment().setSum(priceCalculated);
        subscribeUtil.getPayment().setPaymentDateTime(LocalDateTime.now());
        subscribeUtil.getPayment().setTransferredToAccount(Constants.OWNER_BANK_ACCOUNT_NUMBER);
        subscribeUtil.getPayment().setCurrencyCode(Constants.CURRENCY_CODE_DEFAULT);
        subscribeUtil.getSubscription().setFrom(new Date(Calendar.getInstance().getTime().getTime()));

        subscribeUtil.getReader().setAddress(addressRepository.save(subscribeUtil.getReader().getAddress()));

        Reader reader = subscribeUtil.getReader();
        ReaderPrimitive readerPrimitive = new ReaderPrimitive();
        readerPrimitive.setId(-1);
        readerPrimitive.setFirstName(reader.getFirstName());
        readerPrimitive.setLastName(reader.getLastName());
        readerPrimitive.setAddress(reader.getAddress().getId());
        readerPrimitive = readerPrimitiveRepository.save(readerPrimitive);
        reader.setId(readerPrimitive.getId());

        subscribeUtil.getPayment().setReaderId(readerPrimitive.getId());

        Payment payment = paymentRepository.save(subscribeUtil.getPayment());
        subscribeUtil.getSubscription().setPaid(true);
        subscribeUtil.getSubscription().setPayment(payment);
        subscribeUtil.getSubscription().setPaymentId(payment.getId());
        subscribeUtil.getSubscription().setReaderId(reader.getId());

        subscribeUtil.setSubscription(subscriptionRepository.save(subscribeUtil.getSubscription()));

        subscribeUtil.setPayment(payment);

        return Optional.of(subscribeUtil);
    }

}

