package com.cp.service.impl;

import com.cp.dao.SalaryDAO;
import com.cp.factory.DAOFactory;
import com.cp.model.Salary;
import com.cp.service.SalaryService;

import java.sql.SQLException;

/**
 * @author 刘宇欣 12.20
 */
public class SalaryServiceImpl implements SalaryService {
private SalaryDAO salaryDAO= DAOFactory.getSalaryDAOInstance();
    @Override
    public Salary getSalary(String staffNumber) {
        Salary salary=null;
        try{
            salary=salaryDAO.get(staffNumber);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return salary;
    }

    @Override
    public boolean updateSalary(Salary salary) {
        boolean flag=false;
        int n=0;
        try{
            n=salaryDAO.update(salary);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(n==1){
            flag=true;
        }
        return flag;
    }
}
