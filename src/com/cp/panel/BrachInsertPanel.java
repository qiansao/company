package com.cp.panel;

import com.cp.dao.SalaryDAO;
import com.cp.factory.DAOFactory;
import com.cp.model.Salary;
import com.cp.utils.ExcelUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;

/**
 * @author 刘宇欣 12.21
 */
public class BrachInsertPanel extends JPanel{
    private JScrollPane scrollPane;
    private JTable table;
    private JButton brachInsertButton;
    private JPanel  insertPanel;
    private DefaultTableModel dtm;
    private DefaultTableCellRenderer renderer,renderer1;
    private java.util.List<Salary> salaries;
    Iterator<Salary> iterater = null;
    private SalaryDAO salaryDAO = DAOFactory.getSalaryDAOInstance();

    public BrachInsertPanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        table = new JTable();
        scrollPane = new JScrollPane();
        insertPanel = new JPanel();
        dtm = new DefaultTableModel();
        brachInsertButton = new JButton("批量增加");
        renderer = new DefaultTableCellRenderer();
        renderer1 = new DefaultTableCellRenderer();
        scrollPane.setViewportView(table);
        insertPanel.add(brachInsertButton);
        init();
        add(scrollPane, CENTER);
        add(insertPanel,BorderLayout.SOUTH);
       Font font = new Font("微软雅黑", Font.BOLD, 16);

        brachInsertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
//                int n = chooser.showOpenDialog(AdminFrame.this);
//                if (n == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        InputStream is = new FileInputStream(file);
                        //调用excel工具类，得到List<Student>数据集合
                        java.util.List<Salary> studentList = new ExcelUtil().readExcelContent(is);
                        //调用批量插入方法
                        int[] result = salaryDAO.batchInsert(studentList);
                        //至少一行记录受影响
                        if (result.length != 0) {
                            JOptionPane.showMessageDialog(null, "批量导入成功！");
                            salaryTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "批量导入失败！");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
            }
        });

    }
    private void init(){
        salaryTable();
    }

    private void salaryTable() {
        dtm = new DefaultTableModel();
        String[] titles = {"编号","工号","基础工资","补发工资","应扣工资","个人所得税",
                "社会保险","公积金","最终工资","时间"};
        dtm.setColumnIdentifiers(titles);
        table.setModel(dtm);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(renderer1);
        String[] content = new String[10];
        try {
            salaries = salaryDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        iterater = salaries.iterator();
        while (iterater.hasNext()){
            Salary salary = iterater.next();
            content[0] = String.valueOf(salary.getId());
            content[1] = salary.getStaffNumber();
            content[2] = String.valueOf(salary.getBasicSalary());
            content[3] = String.valueOf(salary.getBfSalary());
            content[4] = String.valueOf(salary.getDeductSalary());
            content[5] = String.valueOf(salary.getPersonalTax());
            content[6] = String.valueOf(salary.getSocialSec());
            content[7] = String.valueOf(salary.getReservedFunds());
            content[8] = String.valueOf(salary.getFinalSalary());
            content[9] = salary.getTime().toString();
            dtm.addRow(content);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("测试");
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.add(new SalaryPanel());
        frame.setVisible(true);
    }


}
