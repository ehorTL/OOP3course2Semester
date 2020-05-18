package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.Constants;
import com.yehorpolishchuk.periodicals.datastructures.Address;
import com.yehorpolishchuk.periodicals.db.providers.ConnectionProvider;

import java.io.IOException;
import java.sql.*;

import com.yehorpolishchuk.periodicals.exceptions.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressDao {

    private static final Logger logger = LogManager.getRootLogger();
    private static final String queryGetAddressById = "SELECT * FROM address WHERE address.aid = ?";
    private static final String queryAddAddress = "INSERT INTO address (country, countrycode, cityareaordistrict, " +
            "citytownvillage, street, housenumber, postalcode, extrainfo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String queryDeleteAddress = "DELETE FROM address WHERE address.aid=?";
    private static final String queryGetAddressReaderId = "SELECT * FROM address WHERE aid IN " +
            "(SELECT address FROM reader WHERE rid=?)";

    public static Address getAddressById(int id) throws ServerException {
        Address address = null;
        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement ps = conn.prepareStatement(queryGetAddressById);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                address = new Address();
                address.setId(Integer.toString(rs.getInt(1)));
                address.setCountry(rs.getString(2));
                address.setCountryCode(rs.getString(3));
                address.setCityAreaOrDistrict(rs.getString(4));
                address.setCityTownVillage(rs.getString(5));
                address.setStreet(rs.getString(6));
                address.setHouseNumber(rs.getString(7));
                address.setPostalCode(rs.getString(8));
                address.setExtraInfo(rs.getString(9));
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return address;
    }

    /**
     * Key generated is returned.
     * Pass connection while using transactions.
     * */
    public static int addAddress(Address address, Connection conn) throws ServerException {
        int keyGenerated = Constants.NOT_ID_STUB;
        if (address == null){
            return keyGenerated;
        }

        try {
            if (conn == null){
                conn = ConnectionProvider.getConnection();
            }

            PreparedStatement ps = conn.prepareStatement(queryAddAddress, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getCountryCode());
            ps.setString(3, address.getCityAreaOrDistrict());
            ps.setString(4, address.getCityTownVillage());
            ps.setString(5, address.getStreet());
            ps.setString(6, address.getHouseNumber());
            ps.setString(7, address.getPostalCode());
            ps.setString(8, address.getExtraInfo());

            int rowsAffected = ps.executeUpdate();

            ResultSet rsKeys = ps.getGeneratedKeys();
            if (rsKeys.next()){
                keyGenerated = rsKeys.getInt("aid");
            }

            logger.debug( AddressDao.class.getName() + " addAddress: "  + rowsAffected + " rowsAffected");
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return keyGenerated;
    }

    public static void deleteAddressById(int id){
        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement ps = conn.prepareStatement(queryDeleteAddress);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            logger.debug(AddressDao.class.getName() + " deleteAddressById: " + rowsAffected + " rowsAffected");

        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
        }
    }

    public static Address getAddressByReaderId(int readerId) throws ServerException {
        Address address = null;
        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement ps = conn.prepareStatement(queryGetAddressReaderId);
            ps.setInt(1, readerId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                address = new Address();
                address.setId(Integer.toString(rs.getInt(1)));
                address.setCountry(rs.getString(2));
                address.setCountryCode(rs.getString(3));
                address.setCityAreaOrDistrict(rs.getString(4));
                address.setCityTownVillage(rs.getString(5));
                address.setStreet(rs.getString(6));
                address.setHouseNumber(rs.getString(7));
                address.setPostalCode(rs.getString(8));
                address.setExtraInfo(rs.getString(9));
            }

        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return address;
    }
}
