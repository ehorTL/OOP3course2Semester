package com.yehorpolishchuk.periodicals.services;

import com.yehorpolishchuk.periodicals.Constants;
import com.yehorpolishchuk.periodicals.datastructures.Payment;
import com.yehorpolishchuk.periodicals.datastructures.Subscription;
import com.yehorpolishchuk.periodicals.db.dao.SubscriptionDao;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;

import java.util.ArrayList;

public class SubscriptionService {

    public static Subscription addSubscriptionWithoutPayment(Subscription subscription) throws ServerException {
        subscription.setPaid(false);
        subscription.setPaymentId(Constants.NOT_ID_STUB);

        int key = SubscriptionDao.addSubscription(subscription, null);

        if (key == Constants.NOT_ID_STUB){
            return null;
        }

        subscription.setId(Integer.toString(key));
        return subscription;
    }

    public static Payment paySubscription(int subscriptionId, Payment payment) throws ServerException {
        return SubscriptionDao.paySubscriptions(new int[]{subscriptionId}, payment);
    }

    public static Payment paySubscriptions(int[] subscriptionIds, Payment payment) throws ServerException {
        return SubscriptionDao.paySubscriptions(subscriptionIds, payment);
    }

    public static Subscription addSubscriptionWithPayment(Subscription subscription, Payment payment) throws ServerException {
        int key = SubscriptionDao.addSubscription(subscription, null);
        if (key == Constants.NOT_ID_STUB){
            return null;
        }

        subscription.setId(Integer.toString(key));
        return subscription;
    }

    public static Payment addSubscriptionsAndPayment(ArrayList<Subscription> subscriptions, Payment payment) throws ServerException {
        return SubscriptionDao.addSubscriptionsAndPayment(subscriptions, payment);
    }

}
