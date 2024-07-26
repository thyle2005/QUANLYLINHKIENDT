    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import static com.mycompany.dao.NhanVienDao.SELECT_ALL_SQL;
import static com.mycompany.dao.NhanVienDao.SELECT_BY_ID_SQL;
import static com.mycompany.dao.NhanVienDao.rs;
import com.mycompany.entity.XJDBC;
import com.mycompany.model.SanPham;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duy Nin
 */
public class SanPhamDao {
    final String INSERT_SQL = "INSERT INTO SanPham (ID, TenSanPham, SoLuong, Gia, Loai, HinhAnh) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE SanPham SET TenSanPham=?, SoLuong=?, Gia=?, Loai=?, HinhAnh=? WHERE ID=?";
    final String DELETE_SQL = "delete from SanPham where ID = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    final String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE ID=?";
    
    public void insert(SanPham entity) {
        XJDBC.update(INSERT_SQL, 
                entity.getID(), 
                entity.getTenSanPham(), 
                entity.getSoLuong(), 
                entity.getLoai(), 
                entity.getGia(), 
                entity.getHinhAnh());
    }

    public void update(SanPham entity) {
        XJDBC.update(UPDATE_SQL, 
                entity.getTenSanPham(), 
                entity.getSoLuong(), 
                entity.getGia(), 
                entity.getLoai(), 
                entity.getHinhAnh(), 
                entity.getID());
    }


    public void delete(String id) {
        XJDBC.update(DELETE_SQL, id);
    }   
    public List<SanPham> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public SanPham selectById(String key) {
        List<SanPham> list = selectBySql(SELECT_BY_ID_SQL, key);
        return !list.isEmpty() ? list.get(0) : null;
    }

    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            rs = XJDBC.query(sql, args);
            while (rs.next()) { 
                SanPham entity = new SanPham();
                entity.setID(rs.getString("ID"));
                entity.setTenSanPham(rs.getString("TenSanPham"));
                entity.setSoLuong(rs.getString("SoLuong"));
                entity.setGia(rs.getString("Gia"));
                entity.setLoai(rs.getString("Loai"));
                entity.setHinhAnh(rs.getString("HinhAnh"));
                list.add(entity);
            } 
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
    