package com.cp.factory;

import com.cp.dao.SalaryDAO;
import com.cp.dao.impl.SalaryDAOImpl;

/**
 * @author  刘宇欣 12.20
 */
public class DAOFactory {
    public static SalaryDAO getSalaryDAOInstance() {
        return new SalaryDAOImpl();
    }
}
