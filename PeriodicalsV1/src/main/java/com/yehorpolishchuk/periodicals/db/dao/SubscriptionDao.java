package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.Constants;
import com.yehorpolishchuk.periodicals.datastructures.Address;
import com.yehorpolishchuk.periodicals.datastructures.Payment;
import com.yehorpolishchuk.periodicals.datastructures.SubscribeUtil;
import com.yehorpolishchuk.periodicals.datastructures.Subscription;
import com.yehorpolishchuk.periodicals.db.providers.ConnectionProvider;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SubscriptionDao {
    private static final Logger logger = LogManager.getRootLogger();

    private static final String queryGetSubscriptionById = "SELECT * FROM subscriptionper WHERE sid=?";
    private static final String queryGetSubscriptionByReaderIdAndPeriodicalId = "SELECT * FROM subscriptionper WHERE " +
            "readerid=? AND periodicalid=?";

    private static final String queryGetSubscriptionsByReaderId = "";
    private static final String queryGetSubscriptionsByPeriodicalId = "";

    private static final String queryAddSubscription = "INSERT INTO subscriptionper (quantity, ispaid, " +
            "terminmonth, fromdate, paymentid, readerid, periodicalid) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String queryEditSubscriptionPayment = "UPDATE subscriptionper SET ispaid=?, paymentid=? WHERE sid=?";

    public static Subscription getSubscriptionById(int id) throws ServerException {
        Subscription subscription = null;

        try(Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement ps = conn.prepareStatement(queryGetSubscriptionById);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                subscription = new Subscription();
                subscription.setPeriodicalId(Integer.toString(rs.getInt(1)));
                subscription.setQuantity(rs.getInt(2));
                subscription.setPaid(rs.getBoolean(3));
                subscription.setTermInMonth(rs.getInt(4));
                subscription.setFrom(rs.getDate(5));
                subscription.setPayment(PaymentDao.getPaymentById(rs.getInt(6)));
                subscription.setReaderId(Integer.toString(rs.getInt(7)));
                subscription.setPeriodicalId(Integer.toString(rs.getInt(8)));
            }

        }  catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
        }

        return subscription;
    }

    public Subscription getSubscriptionByReaderIdAndPeriodicalId(int periodicalId, int readerId) throws ServerException {
        Subscription subscription = null;
        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement ps = conn.prepareStatement(queryGetSubscriptionByReaderIdAndPeriodicalId);
            ps.setInt(1, readerId);
            ps.setInt(2, periodicalId);
            ResultSet rs1 = ps.executeQuery();
            if (rs1.next()){
                subscription = getSubscriptionById(rs1.getInt(1));
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
        }

        return subscription;
    }

    /**
     * Returns key generated.
     * */
    public static int addSubscription(Subscription subscription, Connection conn) throws ServerException {
        boolean commitInTheEnd = (conn == null);

        int key = Constants.NOT_ID_STUB;
        if (subscription == null){
            return key;
        }

        try{
            if (conn == null){
             conn = ConnectionProvider.getConnection();
            }
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(queryAddSubscription, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, subscription.getQuantity());
            ps.setBoolean(2, subscription.isPaid());
            ps.setInt(3, subscription.getTermInMonth());
            ps.setDate(4, subscription.getFrom());

            if (subscription.getPaymentId() != Constants.NOT_ID_STUB){
                ps.setInt(5, subscription.getPaymentId());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setInt(6, Integer.parseInt(subscription.getReaderId()));
            ps.setInt(7, Integer.parseInt(subscription.getPeriodicalId()));

            ps.executeUpdate();
            ResultSet rsKeys = ps.getGeneratedKeys();
            if (rsKeys.next()){
                key = rsKeys.getInt(1);
            }

            if (commitInTheEnd){
                conn.commit();
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return key;
    }

    public static Payment paySubscriptions(int[] subscriptionIds, Payment payment) throws ServerException {
        if (payment == null){
            return null;
        }

        try{
            for (int sId: subscriptionIds){
                Subscription s = getSubscriptionById(sId);
                if (s == null || s.isPaid()){
                    logger.warn("Cannot pay all");
                    return null;
                }
            }

            Connection conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            int paymentKey = PaymentDao.addPayment(payment, conn);
            if (paymentKey == Constants.NOT_ID_STUB){
                return null;
            }

            payment.setId(Integer.toString(paymentKey));
            for (int sId: subscriptionIds){
                editSubscriptionPayment(sId, paymentKey, true, conn);
            }

            conn.commit();

        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return payment;
    }

    public static boolean editSubscriptionPayment(int subscriptionId, int paymentId, boolean ispaid, Connection conn)
            throws ServerException {
        if (subscriptionId == Constants.NOT_ID_STUB || paymentId == Constants.NOT_ID_STUB) {
            return false;
        }

        try {
            if (conn == null) {
                conn = ConnectionProvider.getConnection();
            }
            PreparedStatement ps = conn.prepareStatement(queryEditSubscriptionPayment);
            ps.setBoolean(1, ispaid);
            ps.setInt(2, paymentId);
            ps.setInt(3, subscriptionId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                return false;
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return true;
    }

    public static Payment addSubscriptionsAndPayment(ArrayList<Subscription> subscriptions, Payment payment, Connection conn) throws ServerException {
        boolean commitInTheEnd = (conn == null);

        if ((payment == null) || (subscriptions == null) || (subscriptions.size() == 0)){
            logger.warn("PAYMENT OR SUBS OR SIZE = 0!");
            return null;
        }

        try{
            if (conn == null){
                conn = ConnectionProvider.getConnection();
            }
            conn.setAutoCommit(false);

            int paymentKey = PaymentDao.addPayment(payment, conn);
            logger.info("PAYMENT KEY : " + paymentKey);
            payment.setId(Integer.toString(paymentKey));

            for (Subscription s : subscriptions){
                s.setPaid(true);
                s.setPaymentId(paymentKey);
                addSubscription(s, conn);
            }

            if (commitInTheEnd){
                conn.commit();
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return payment;
    }

    public static SubscribeUtil addSubscription(SubscribeUtil subscribeUtil) throws ServerException {
        try{
            Connection conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            int readerKey = ReaderDao.addReader(subscribeUtil.getReader(), conn);
            subscribeUtil.getReader().setId(Integer.toString(readerKey));
            subscribeUtil.getSubscription().setReaderId(Integer.toString(readerKey));
            subscribeUtil.getPayment().setReaderId(Integer.toString(readerKey));

            ArrayList<Subscription> subs = new ArrayList<>();
            subs.add(subscribeUtil.getSubscription());
            SubscriptionDao.addSubscriptionsAndPayment(subs, subscribeUtil.getPayment(), conn);

            conn.commit();
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return subscribeUtil;
    }

}
