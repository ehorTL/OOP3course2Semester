package com.yehorpolishchuk.periodicals.services;

import com.yehorpolishchuk.periodicals.Constants;
import com.yehorpolishchuk.periodicals.datastructures.Payment;
import com.yehorpolishchuk.periodicals.datastructures.Periodical;
import com.yehorpolishchuk.periodicals.datastructures.SubscribeUtil;
import com.yehorpolishchuk.periodicals.datastructures.Subscription;
import com.yehorpolishchuk.periodicals.db.dao.PeriodicalDao;
import com.yehorpolishchuk.periodicals.db.dao.SubscriptionDao;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

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
        return SubscriptionDao.addSubscriptionsAndPayment(subscriptions, payment, null);
    }

    /**
     * Date and time must be set here.
     * Price will be calculated here again
     * */
    public static SubscribeUtil addSubscription(SubscribeUtil subscribeUtil) throws ServerException {

        double priceCalculated = calculatePriceOfSubscription(Integer.parseInt(subscribeUtil.getSubscription().getPeriodicalId()),
                subscribeUtil.getSubscription().getTermInMonth());
        subscribeUtil.getPayment().setSum(priceCalculated);
        subscribeUtil.getPayment().setPaymentDateTime(LocalDateTime.now());
        subscribeUtil.getPayment().setTransferredToAccount(Constants.OWNER_BANK_ACCOUNT_NUMBER);
        subscribeUtil.getPayment().setCurrencyCode(Constants.CURRENCY_CODE_DEFAULT);
        subscribeUtil.getSubscription().setFrom(new Date(Calendar.getInstance().getTime().getTime()));

        return SubscriptionDao.addSubscription(subscribeUtil);
    }

    public static double calculatePriceOfSubscription(int periodicalId, int inMonthSubscriptionTerm) throws ServerException {
        Periodical periodical = PeriodicalDao.getPeriodicalById(periodicalId);
        if (periodical == null){
            throw new ServerException();
        }

        double pricePerOne = periodical.getPricePerOne();
        double timesPerMonth = periodical.getFrequencyPerYear() / 12.0;

        return Math.round(timesPerMonth * inMonthSubscriptionTerm) * pricePerOne;
    }
}
