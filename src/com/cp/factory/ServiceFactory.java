package com.cp.factory;

import com.cp.model.Salary;
import com.cp.service.SalaryService;
import com.cp.service.impl.SalaryServiceImpl;

/**
 * @author 刘宇欣 12.20
 */
public class ServiceFactory {
    public static SalaryService getSalaryService() {
        return new SalaryServiceImpl();
    }
}
