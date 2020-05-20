package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.datastructures.ContactInfo;
import com.yehorpolishchuk.periodicals.db.providers.ConnectionProvider;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContactInfoDao {
    private static final Logger logger = LogManager.getRootLogger();

    private static final String queryGetContactPhoneNumberByReaderId = "SELECT * FROM contactphonenumber WHERE rid=?";
    private static final String queryGetContactMailByReaderId = "SELECT * FROM contactmail WHERE rid=?";
    private static final String queryAddContactPhoneNumberByReaderId = "INSERT INTO contactphonenumber (rid, phonenumber) VALUES(?, ?)";
    private static final String queryAddContactMailByReaderId = "INSERT INTO contactmail (rid, email) VALUES(?, ?)";

    /**
     * Pass connection if using transactions, else pass null as a connection.
     * */
    public static ContactInfo getContactInfoByReaderId(int id, Connection conn) throws ServerException {
        ContactInfo contactInfo = null;
        try {
            if (conn == null){
                conn = ConnectionProvider.getConnection();
            }

            contactInfo = new ContactInfo();
            contactInfo.setReaderId(id);
            ArrayList<String> contactMail = new ArrayList<>(),
                            contactNumber = new ArrayList<>();

            PreparedStatement ps1 = conn.prepareStatement(queryGetContactPhoneNumberByReaderId);
            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                contactNumber.add(rs1.getString(2));
            }

            PreparedStatement ps2 = conn.prepareStatement(queryGetContactMailByReaderId);
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                contactMail.add(rs2.getString(2));
            }

            contactInfo.setMailAddresses(contactMail);
            contactInfo.setNumbers(contactNumber);
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return contactInfo;
    }

    public static String addContactNumberByReaderId(int id, String contactNumber, Connection conn) throws ServerException {
        boolean commitInTheEnd = (conn == null);

        if (contactNumber == null){
            return null;
        }

        try {
            if (conn == null){
                conn = ConnectionProvider.getConnection();
            }
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(queryAddContactPhoneNumberByReaderId);
            ps.setInt(1, id);
            ps.setString(2, contactNumber);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0){
                logger.warn("Cannot insert contact number");
                return null;
            }

            logger.debug(AddressDao.class.getName() + " addContactNumberByReaderId: " + rowsAffected + " rowsAffected");
            if (commitInTheEnd){
                conn.commit();
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return contactNumber;
    }

    public static String addContactMailByReaderId(int id, String email, Connection conn) throws ServerException {
        boolean commitInTheEnd = (conn == null);

        if (email == null){
            return null;
        }

        try {
            if (conn == null){
                conn = ConnectionProvider.getConnection();
            }

            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(queryAddContactMailByReaderId);
            ps.setInt(1, id);
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0){
                logger.warn("Cannot insert contact mail");
                return null;
            }

            logger.debug(AddressDao.class.getName() + " addContactMailByReaderId: " + rowsAffected + " rowsAffected");
            if (commitInTheEnd){
                conn.commit();
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return email;
    }
}
