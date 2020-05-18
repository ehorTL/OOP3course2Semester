package com.yehorpolishchuk.periodicals.services;

import com.yehorpolishchuk.periodicals.datastructures.ContactInfo;
import com.yehorpolishchuk.periodicals.db.dao.ContactInfoDao;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;

public class ContactInfoService {

    public static ContactInfo getContactInfoByReaderId(int id) throws ServerException {
        return ContactInfoDao.getContactInfoByReaderId(id, null);
    }

    public static String addContactMail(int readerId, String mail) throws ServerException {
        return ContactInfoDao.addContactMailByReaderId(readerId, mail, null);
    }

    public static String addContactNumber(int readerId, String number) throws ServerException {
        return ContactInfoDao.addContactNumberByReaderId(readerId, number, null);
    }
}
