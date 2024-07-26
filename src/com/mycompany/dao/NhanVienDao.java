/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.entity.XJDBC;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.model.NhanVien;
import java.sql.ResultSet;

/**
 *
 * @author Duy Nin
 */
public class NhanVienDao {
    public static ResultSet rs = null; // Trả về kết quả truy vấn
    public static String INSERT_SQL = "INSERT INTO NhanVien (ID,HoVaTen,Email,DiaChi,SDT) VALUES (?,?,?,?,?)";
    public static String UPDATE_SQL = "UPDATE NhanVien SET HoVaTen=?,Email=?,DiaChi=?,SDT=? WHERE ID=?";
    public static String DELETE_SQL = "DELETE FROM NhanVien WHERE ID=?";
    public static String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE ID=?";
    
    public void insert(NhanVien entity) {
        XJDBC.update(INSERT_SQL,
                entity.getID(),
                entity.getHoVaTen(),
                entity.getEmail(),
                entity.getDiaChi(),
                entity.getSDT());
    }

    public void update(NhanVien entity) {
        XJDBC.update(UPDATE_SQL,
                entity.getHoVaTen(),
                entity.getEmail(),
                entity.getDiaChi(),      
                entity.getSDT(),
                entity.getID());
    }

    public void delete(String key) {
        XJDBC.update(DELETE_SQL, key);
    }

    public List<NhanVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public NhanVien selectById(String key) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, key);
        return !list.isEmpty() ? list.get(0) : null;
    }

    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            rs = XJDBC.query(sql, args);
            while (rs.next()) { 
                NhanVien entity = new NhanVien();
                entity.setID(rs.getString("ID"));
                entity.setHoVaTen(rs.getString("HoVaTen"));
                entity.setEmail(rs.getString("Email"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setSDT(rs.getString("SDT"));
                list.add(entity);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
