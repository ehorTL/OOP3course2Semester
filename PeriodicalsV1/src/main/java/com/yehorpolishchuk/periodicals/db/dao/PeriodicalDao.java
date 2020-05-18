package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.datastructures.Periodical;
import com.yehorpolishchuk.periodicals.db.providers.ConnectionProvider;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class PeriodicalDao {
    private static final Logger logger = LogManager.getRootLogger();

    private static final String queryAddPeriodical = "INSERT INTO periodical (name, frequency) VALUES (?, ?)";
    private static final String queryGetPeriodical = "SELECT * FROM periodical WHERE pid=?";
    private static final String queryGetAllPeriodicals = "SELECT * FROM periodical";
    private static final String queryEditPeriodical = "UPDATE periodical SET name=?, frequency=? WHERE pid=?";
    private static final String queryDeletePeriodical = "DELETE FROM periodical WHERE pid=?";

    public static int addPeriodical(Periodical periodical) throws ServerException {
        int key = -1;

        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement ps = conn.prepareStatement(queryAddPeriodical, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, periodical.getName());
            ps.setDouble(2, periodical.getFrequencyPerYear());

            int rowsAffected = ps.executeUpdate();
            logger.debug(AddressDao.class.getName() + " addPeriodical: " + rowsAffected + " rowsAffected");

            ResultSet rsKeys = ps.getGeneratedKeys();
            if (rsKeys.next()){
                key = rsKeys.getInt(1);
            }

        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return key;
    }

    public static Periodical getPeriodicalById(int id) throws ServerException {
        Periodical periodical = null;
        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement ps = conn.prepareStatement(queryGetPeriodical);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                periodical = new Periodical();
                periodical.setId(Integer.toString(rs.getInt(1)));
                periodical.setName(rs.getString(2));
                periodical.setFrequencyPerYear(rs.getDouble(3));
            }

        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw new ServerException();
        }

        return periodical;
    }

    public static ArrayList<Periodical> getAllPeriodicals(Connection conn) throws ServerException {
        ArrayList<Periodical> periodicals = new ArrayList<>();
        try {
            if (conn == null){
                conn = ConnectionProvider.getConnection();
            }

            PreparedStatement ps = conn.prepareStatement(queryGetAllPeriodicals);
            ResultSet rs = ps.executeQuery();

            Periodical pCurrent = null;
            while(rs.next()){
                pCurrent = new Periodical();
                pCurrent.setId(Integer.toString(rs.getInt("pid")));
                pCurrent.setName(rs.getString("name"));
                pCurrent.setFrequencyPerYear(rs.getDouble("frequency"));
                periodicals.add(pCurrent);
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw  new ServerException();
        }

        return periodicals;
    }

    public static Periodical editPeriodical(Periodical periodical) throws ServerException {
        try {
            Connection conn = ConnectionProvider.getConnection();

            PreparedStatement ps = conn.prepareStatement(queryEditPeriodical);
            ps.setString(1, periodical.getName());
            ps.setDouble(2, periodical.getFrequencyPerYear());
            ps.setInt(3, Integer.parseInt(periodical.getId()));

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0){
                logger.warn("Cannot update periodical" + " id : " + periodical.getId());
                return null;
            }
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw  new ServerException();
        }

        return periodical;
    }

    /**
     * Returns the number of rows affected
     * */
    public static int deletePeriodical(int id) throws ServerException {
        int rowsAffected = 0;
        try {
            Connection conn = ConnectionProvider.getConnection();

            PreparedStatement ps = conn.prepareStatement(queryDeletePeriodical);
            ps.setInt(1, id);
            rowsAffected = ps.executeUpdate();

        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
            throw  new ServerException();
        }

        return rowsAffected;
    }
}
