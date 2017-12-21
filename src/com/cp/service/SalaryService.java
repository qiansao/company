package com.cp.service;

import com.cp.model.Salary;

/**
 * @author 刘宇欣 12.20
 */
public interface SalaryService {
    Salary getSalary(String staffNumber);

    /**
     * 修改学生信息
     * @param salary
     * @return boolean
     */
    boolean updateSalary(Salary salary);

}
