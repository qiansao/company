package com.cp.dao;

import com.cp.model.Salary;

import java.sql.SQLException;
import java.util.List;

/**
 *
 *@author  刘宇欣 12.20
 */
public interface SalaryDAO {
    /**
     * 根据流水号查询工资
     * @param staffNumber
     *return salary
     * @throws SQLException
     */
    Salary get(String staffNumber) throws SQLException;
    /**
     * 更新薪资信息
     * @param salary
     * @return int
     * @throws SQLException
     */
    int update(Salary salary) throws SQLException;
    /**
     * 新增一个薪资信息
     * @param salary
     * @return int
     * @throws SQLException
     */
    int insert(Salary salary)throws SQLException;
    /**
     * 批量新增薪资信息
     * @param list
     * @return int[]
     * @throws SQLException
     */
    int[] batchInsert(List<Salary> list) throws SQLException;
    /**
     * 批量删除薪资信息
     * @param ids
     * @return int[]
     * @throws SQLException
     */
    int[] batchDelete(List<String> ids) throws SQLException;
    /**
     * 获取所有薪资信息
     *
     * @return list
     * @throws SQLException
     */
    List<Salary> getAll() throws SQLException;
    /**
     * 按关键词模糊查询薪资信息
     * @return list
     * @throws SQLException
     */
    List<Salary> queryLike(String keywords) throws SQLException;
    /**
     * 按条件查询薪资信息
     * @param condition
     * @return list
     * @throws SQLException
     */
    List<Salary> queryBy(String condition) throws SQLException;
}
