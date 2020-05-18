package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.Constants;
import com.yehorpolishchuk.periodicals.datastructures.Payment;
import com.yehorpolishchuk.periodicals.db.providers.ConnectionProvider;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class PaymentDao {

    private static final Logger logger = LogManager.getRootLogger();
    private static final  String queryAddPayment = "INSERT INTO payment (toaccount, sum, currencycode, paymentdatetime, description, rid) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final  String queryGetPaymentById = "SELECT * FROM payment WHERE pid=?";
    private static final String queryDeletePayment = "DELETE FROM payment WHERE pid=?";


    /**
     * Return key generated
     * */
    public static int addPayment(Payment payment, Connection conn) throws ServerException {
        int key = Constants.NOT_ID_STUB;
        if (payment == null){
            return key;
        }

        try {
            if (conn == null){
                conn = ConnectionProvider.getConnection();
            }

            PreparedStatement ps = conn.prepareStatement(queryAddPayment, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, payment.getTransferredToAccount());
            ps.setDouble(2, payment.getSum());
            ps.setString(3, payment.getCurrencyCode());
            ps.setObject(4, payment.getPaymentDateTime());
            ps.setString(5, payment.getDescription());
            ps.setDouble(6, Double.parseDouble(payment.getReaderId()));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0){
                logger.warn("Cannot insert payment");
                return key;
            }

            ResultSet rsKeys = ps.getGeneratedKeys();
            if (rsKeys.next()){
                key = rsKeys.getInt(1);
            }

            logger.debug(AddressDao.class.getName() + " addPayment: " + rowsAffected + " rowsAffected");
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return key;
    }

    public static Payment getPaymentById(int id) throws ServerException {
        Payment payment = null;
        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement ps = conn.prepareStatement(queryGetPaymentById);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                payment = new Payment();
                payment.setTransferredToAccount(rs.getString(1));
                payment.setSum(rs.getDouble(2));
                payment.setCurrencyCode(rs.getString(3));
                payment.setPaymentDateTime((LocalDateTime) rs.getObject(4));
                payment.setDescription(rs.getString(5));
                payment.setReaderId(Integer.toString(rs.getInt(6)));
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return payment;
    }

    public static void deletePayment(int id) throws ServerException {
        try{
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement ps = conn.prepareStatement(queryDeletePayment);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0){
                logger.warn("Cannot delete payment");
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }
    }
}
