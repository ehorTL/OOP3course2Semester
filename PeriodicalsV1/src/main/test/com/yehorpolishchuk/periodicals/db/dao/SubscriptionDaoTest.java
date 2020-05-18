package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.Constants;
import com.yehorpolishchuk.periodicals.datastructures.Subscription;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.*;

public class SubscriptionDaoTest {

    Subscription subscription = null;

    @Before
    public void setUp() throws Exception {
        subscription = new Subscription();

        subscription.setQuantity(1);
        subscription.setPaid(false);
        subscription.setTermInMonth(6);
        subscription.setPaymentId(Constants.NOT_ID_STUB);
        subscription.setReaderId("4");
        subscription.setPeriodicalId("2");
        subscription.setFrom(new Date(Calendar.getInstance().getTime().getTime()));
    }

    @Test
    public void addGetTest(){
//        SubscriptionDao.addSubscription(subscription, null);
    }
}