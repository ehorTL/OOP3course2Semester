package com.yehorpolishchuk.periodicals.services;

import com.yehorpolishchuk.periodicals.Constants;
import com.yehorpolishchuk.periodicals.datastructures.Address;
import com.yehorpolishchuk.periodicals.db.dao.AddressDao;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;

public class AddressSevice {

    public static Address addAddress(Address address) throws ServerException {
        int keyGenerated =  AddressDao.addAddress(address, null);

        if (keyGenerated == Constants.NOT_ID_STUB){
            return null;
        }
        address.setId(Integer.toString(keyGenerated));

        return address;
    }

    public static Address getAddressId(int id) throws ServerException {
        return AddressDao.getAddressById(id);
    }

    public static Address getAddressByReaderId(int readerId) throws ServerException {
        return AddressDao.getAddressByReaderId(readerId);
    }
}
