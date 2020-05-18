package com.yehorpolishchuk.periodicals.db.dao;

import com.yehorpolishchuk.periodicals.datastructures.Reader;
import com.yehorpolishchuk.periodicals.db.providers.ConnectionProvider;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;

public class ReaderDao {
    private static final Logger logger = LogManager.getRootLogger();

    private static final String queryGetReader = "SELECT * FROM reader WHERE rid=?";
    private static final String queryAddReader = "INSERT INTO reader (firstname, lastname, address) VALUES (?, ?, ?)";

    public static Reader getReader(int id) throws ServerException {
        Reader reader = null;
        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement ps = conn.prepareStatement(queryGetReader);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                reader = new Reader();
                reader.setId(Integer.toString(rs.getInt(1)));
                reader.setFirstName(rs.getString(2));
                reader.setLastName(rs.getString(3));

                int addressId = rs.getInt(4);
                reader.setAddress(AddressDao.getAddressById(addressId));

                reader.setContactInfo(ContactInfoDao.getContactInfoByReaderId(rs.getInt(1), null));
            }

        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
        }

        return reader;
    }

    /**
     * Reader primary key generated is returned ( > 0 ).
     * */
    public static int addReader(Reader reader) throws ServerException {
        int readerId = -1;
        try (Connection conn = ConnectionProvider.getConnection()){
            conn.setAutoCommit(false);

            int newAddressId = AddressDao.addAddress(reader.getAddress(), conn);

            PreparedStatement ps = conn.prepareStatement(queryAddReader, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, reader.getFirstName());
            ps.setString(2, reader.getLastName());
            ps.setInt(3, newAddressId);
            ps.executeUpdate();

            ResultSet rsKeys = ps.getGeneratedKeys();
            if (rsKeys.next()){
                readerId = rsKeys.getInt("rid");
            }

            int finalReaderId = readerId;
            for (int i = 0; i < reader.getContactInfo().getMailAddresses().size(); i++) {
                ContactInfoDao.addContactMailByReaderId(finalReaderId, reader.getContactInfo().getMailAddresses().get(i), conn);
            }
            for (int i = 0; i < reader.getContactInfo().getNumbers().size(); i++) {
                ContactInfoDao.addContactNumberByReaderId(finalReaderId, reader.getContactInfo().getNumbers().get(i), conn);
            }

            conn.commit();
        } catch (IOException | SQLException e) {
            logger.error(AddressDao.class.getName() + " Connection error: " + e.getMessage());
        }

        return readerId;
    }
}
