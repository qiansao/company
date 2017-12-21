package com.niit.sm.dao;

import com.cp.dao.SalaryDAO;
import com.cp.factory.DAOFactory;
import com.cp.model.Salary;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author  刘宇欣 12.20
 */
public class SalaryDAOTest {
    private SalaryDAO salaryDAO;
    @Before
    public void setUp() throws Exception {
        salaryDAO = DAOFactory.getSalaryDAOInstance();
    }

    @Test
    public void getAll() throws Exception {
        List<Salary> studentList = salaryDAO.getAll();
        studentList.forEach(salary -> System.out.println(salary));
    }
    @Test
    public void update() throws Exception {
        Salary salary = salaryDAO.get("20010");
        salary.setFinalSalary(5700);
        int n = salaryDAO.update(salary);
        assertEquals(1, n);
   }
    @Test
    public void get() throws Exception {
        Salary salary = salaryDAO.get("20010");
        System.out.println(salary);
    }
    @Test
    public void batchDelete() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add("222");
        ids.add("333");
        ids.add("444");
        int[] result = salaryDAO.batchDelete(ids);
        assertEquals(3, result.length);
    }
    @Test
    public void insert() throws Exception {
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Salary salary1=new Salary("20016", 6000, 0, 0, 0,0,0,5700,sqlDate);
        int n= salaryDAO.insert(salary1);
        assertEquals(1, n);
    }
    @Test
    public void batchInsert() throws Exception {
        List<Salary> salaryList = new ArrayList<>();
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        salaryList.add(new Salary("20017", 6000, 0, 0, 0,0,0,5700,sqlDate));
        salaryList.add(new Salary("20018", 8000, 0, 0, 0,0,0,7000,sqlDate));
        salaryList.add(new Salary("20019", 7000, 0, 0, 0,0,0,5700,sqlDate));
        int[] result = salaryDAO.batchInsert(salaryList);
        assertEquals(3, result.length);
    }
    @Test
    public void queryLike() throws Exception {
        List<Salary> salaryList = salaryDAO.queryLike("8000");
        salaryList.forEach(salary -> System.out.println(salary));
    }

    @Test
    public void queryBy() throws Exception {
        List<Salary> salaryList = salaryDAO.queryBy("WHERE  basicSalary= 8000 " +"AND finalSalary= 7700 ");
        salaryList.forEach(salary -> System.out.println(salary));
    }
}
