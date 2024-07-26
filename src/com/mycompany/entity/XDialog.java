/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Duy Nin
 */
public class XDialog {
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Cửa hàng linh kiện điện tử",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * Hiển thị thông báo và yêu cầu người dùng xác nhận
     */
    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Cửa hàng linh kiện điện tử",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    /*
     * Hiển thị thông báo yêu cầu nhập dữ liệu
     */
    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "Cửa hàng linh kiện điện tử",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
