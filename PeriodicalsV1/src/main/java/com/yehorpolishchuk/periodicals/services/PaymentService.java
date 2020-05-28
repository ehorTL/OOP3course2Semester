package com.yehorpolishchuk.periodicals.services;

import com.yehorpolishchuk.periodicals.Constants;
import com.yehorpolishchuk.periodicals.datastructures.Payment;
import com.yehorpolishchuk.periodicals.db.dao.PaymentDao;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;

public class PaymentService {

    public static Payment addPayment(Payment payment) throws ServerException {
        int key = PaymentDao.addPayment(payment, null);
        if (key == Constants.NOT_ID_STUB){
            return null;
        }
        payment.setId(Integer.toString(key));

        return payment;
    }
}
