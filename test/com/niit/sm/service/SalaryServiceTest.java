package com.niit.sm.service;

import com.cp.factory.ServiceFactory;
import com.cp.model.Salary;
import com.cp.service.SalaryService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author 刘宇欣12.20
 */
public class SalaryServiceTest {
    private SalaryService salaryService;
    @Before
    public void setUp() throws Exception {
        salaryService = ServiceFactory.getSalaryService();
    }
    @Test
    public void updateStudent() throws Exception {
        Salary salary= salaryService.getSalary("20017");
        salary.setBasicSalary(7000);
        boolean flag = salaryService.updateSalary(salary);
        assertEquals(true, flag);
    }
}
