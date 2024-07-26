/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.entity.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.model.HoaDon;

/**
 *
 * @author Duy Nin
 */
public class HoaDonDao {
    public static ResultSet rs = null; // Trả về kết quả truy vấn
    public static String INSERT_SQL = "INSERT INTO HoaDon (MaHoaDon,NgayBan,TinhTrangDonHang,TinhTrangThanhToan,IDNhanVien,IDKhachHang) VALUES (?,?,?,?,?,?)";
    public static String UPDATE_SQL = "UPDATE HoaDon SET NgayBan=?,TinhTrangDonHang=?,TinhTrangThanhToan=?,IDNhanVien=?,IDKhachHang=? WHERE MaHoaDon=?";
    public static String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon=?";
    public static String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon=?";
    
    public void insert(HoaDon entity) {
        XJDBC.update(INSERT_SQL,
                entity.getMaHoaDon(),
                entity.getNgayBan(),
                entity.getTinhTrangDonHang(),
                entity.getTinhTrangThanhToan(),
                entity.getIDKhachHang(),
                entity.getIDNhanVien());
    }

    public void update(HoaDon entity) {
        XJDBC.update(UPDATE_SQL,
                entity.getNgayBan(),
                entity.getTinhTrangDonHang(),
                entity.getTinhTrangThanhToan(),
                entity.getIDKhachHang(),
                entity.getIDNhanVien(),
                entity.getMaHoaDon());
    }

    public void delete(String key) {
        XJDBC.update(DELETE_SQL, key);
    }

    public List<HoaDon> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public HoaDon selectById(String key) {
        List<HoaDon> list = selectBySql(SELECT_BY_ID_SQL, key);
        return !list.isEmpty() ? list.get(0) : null;
    }

    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            rs = XJDBC.query(sql, args);
            while (rs.next()) { 
                HoaDon entity = new HoaDon();
                entity.setMaHoaDon(rs.getString("MaHoaDon"));
                entity.setNgayBan(rs.getString("NgayBan"));
                entity.setIDKhachHang(rs.getString("IDKhachHang"));
                entity.setIDNhanVien(rs.getString("IDNhanVien"));
                entity.setTinhTrangDonHang(rs.getString("TinhTrangDonHang"));
                entity.setTinhTrangThanhToan(rs.getString("TinhTrangThanhToan"));

                list.add(entity);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
