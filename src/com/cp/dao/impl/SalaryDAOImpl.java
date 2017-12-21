package com.cp.dao.impl;

import com.cp.dao.SalaryDAO;
import com.cp.model.Salary;
import com.cp.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author  刘宇欣    12.20
 */
public class SalaryDAOImpl implements SalaryDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    /**
     * 根据工号查询薪资
     * @param staffNumber
     *return salary
     * @return
     * @throws SQLException
     */
    @Override
    public Salary get(String staffNumber) throws SQLException {
        String sql = "SELECT * FROM t_salary WHERE staffNumber = ? ";
        Map<String, Object> map = jdbcUtil.executeQuerySingle(sql, new Object[]{staffNumber});
        if (map.size() != 0) {
            Salary salary = new Salary(map.get("staffNumber").toString(),
                    (Integer) map.get("basicSalary"),
                    (Integer) map.get("bfSalary"),
                    (Integer) map.get("deductSalary"),
                    (Integer) map.get("personalTax"),
                    (Integer) map.get("socialSec"),
                    (Integer) map.get("reservedFunds"),
                    (Integer) map.get("finalSalary"),
                    (Date) map.get("time")
            );
            return salary;
        } else {
            return null;
        }
    }

    /**
     * 修改信息
     * @param salary
     * @return
     * @throws SQLException
     */
    @Override
    public int update(Salary salary) throws SQLException {
            String sql = "UPDATE t_salary SET basicSalary = ?,bfSalary = ?,deductSalary = ? ,personalTax = ?,socialSec = ?,reservedFunds = ?,finalSalary = ?,time = ? WHERE staffNumber = ? ";
            Object[] params = {salary.getBasicSalary(), salary.getBfSalary(), salary.getDeductSalary(), salary.getPersonalTax(), salary.getSocialSec(), salary.getReservedFunds(), salary.getFinalSalary(), salary.getTime(), salary.getStaffNumber()};
            int n = jdbcUtil.executeUpdate(sql, params);
            return n;
        }

    /**
     * 增加一个信息
     * @param salary
     * @return
     * @throws SQLException
     */
    @Override
    public int insert(Salary salary) throws SQLException {
        String sql = "INSERT INTO t_salary VALUES (?,?,?,?,?,?,?,?,?,?) ";
        Object[] params = { salary.getId(),salary.getStaffNumber(),salary.getBasicSalary(), salary.getBfSalary(), salary.getDeductSalary(), salary.getPersonalTax(), salary.getSocialSec(), salary.getReservedFunds(), salary.getFinalSalary(), salary.getTime()};
        int n = jdbcUtil.executeUpdate(sql, params);
        return n;
    }
    @Override
    public int[] batchInsert(List<Salary> list) throws SQLException {
        int[] result;
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_salary VALUES (?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (Salary salary: list) {
            ps.setInt(1,salary.getId());
            ps.setString(2, salary.getStaffNumber());
            ps.setInt(3, salary.getBasicSalary());
            ps.setInt(4, salary.getBfSalary());
            ps.setInt(5, salary.getDeductSalary());
            ps.setInt(6, salary.getPersonalTax());
            ps.setInt(7, salary.getSocialSec());
            ps.setInt(8, salary.getReservedFunds());
            ps.setInt(9, salary.getFinalSalary());
            ps.setDate(10, salary.getTime());
            ps.addBatch();
        }
        // 执行批量更新操作
        result = ps.executeBatch();
        jdbcUtil.closeAll();
        return result;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws SQLException
     */
        @Override
        public int[] batchDelete(List<String> ids) throws SQLException {
            int[] result;
            Connection connection = jdbcUtil.getConnection();
            String sql = "DELETE FROM t_salary WHERE staffNumber = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            for (String staffNumber : ids) {
                ps.setString(1, staffNumber);
                ps.addBatch();
            }
            // 执行批量更新操作
            result = ps.executeBatch();
            jdbcUtil.closeAll();
            return result;
        }


    @Override
    public List<Salary> getAll() throws SQLException {
        String sql = "SELECT * FROM t_salary ";
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        return getSalaryList(list);
    }

    @Override
    public List<Salary> queryLike(String keywords) throws SQLException {
        String sql = "SELECT * FROM t_salary WHERE CONCAT(staffNumber,basicSalary,bfSalary,deductSalary,personalTax,socialSec,reservedFunds,finalSalary,time) LIKE ? ";
        List<Object> list = jdbcUtil.excuteQuery(sql, new Object[]{"%" + keywords + "%"});
        return getSalaryList(list);
    }

    @Override
    public List<Salary> queryBy(String condition) throws SQLException {
        String sql = "SELECT * FROM t_salary " + condition;
        System.out.println(sql);
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        return getSalaryList(list);
    }

    /**
 * 封装一个本类的私有方法，用来把Object集合转换成Salary类型的集合
 * @param list
 * @return
 */
private List<Salary> getSalaryList(List<Object> list) {
    List<Salary> salaries = new ArrayList<>();
    for (Object object : list) {
        Map<String, Object> map = (Map<String, Object>) object;
        Salary salary = new Salary(map.get("staffNumber").toString(),
                (Integer) map.get("basicSalary"),
                (Integer) map.get("bfSalary"),
                (Integer) map.get("deductSalary"),
                (Integer) map.get("personalTax"),
                (Integer) map.get("socialSec"),
                (Integer) map.get("reservedFunds"),
                (Integer) map.get("finalSalary"),
                (Date) map.get("time"));
        salary.setId((Integer) map.get("id"));
        salaries.add(salary);
    }
    return salaries;
}

}