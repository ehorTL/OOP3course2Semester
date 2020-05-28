package com.yehorpolishchuk.periodicals.services;

import com.yehorpolishchuk.periodicals.datastructures.Periodical;
import com.yehorpolishchuk.periodicals.db.dao.PeriodicalDao;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;

import java.util.ArrayList;

public class PeriodicalService {

    public static Periodical getPeriodical(int id ) throws ServerException {
        return PeriodicalDao.getPeriodicalById(id);
    }

    public static ArrayList<Periodical> getAllPeriodicals() throws ServerException {
        return PeriodicalDao.getAllPeriodicals(null);
    }

    public static Periodical addPeriodical(Periodical periodical) throws ServerException {
        if (periodical == null){
            return null;
        }
        periodical.setId(Integer.toString(PeriodicalDao.addPeriodical(periodical)));
        return periodical;
    }

    public static Periodical editPeriodical(Periodical periodical) throws ServerException {
        if (periodical == null){
            return null;
        }

        return PeriodicalDao.editPeriodical(periodical);
    }

    public static void deletePeriodical(int id) throws ServerException {
        PeriodicalDao.deletePeriodical(id);
    }
}
