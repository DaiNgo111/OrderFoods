package com.example.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.DTO.NhanVienDTO;
import com.example.orderfood.Database.CreateDatabase;

public class NhanVienDAO {

    SQLiteDatabase database;

    public NhanVienDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
        
    }
    // ham them nhan vien, khi thuc thi thanh cong thi tra ve id cua nhan vien
    public long ThemNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_NHANVIEN_TEDN, nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TB_NHANVIEN_CMND, nhanVienDTO.getCMND());
        contentValues.put(CreateDatabase.TB_NHANVIEN_GIOITINH, nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MATKHAU, nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TB_NHANVIEN_NGAYSINH, nhanVienDTO.getNGAYSINH());

        long kiemtra = database.insert(CreateDatabase.TB_NHANVIEN,null,contentValues);
        return kiemtra;
    }

    public boolean KiemTraNhanVien(){
        String truyVan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN;

        // database.raw sẽ trả về con trỏ lưu ở cursor
        Cursor cursor = database.rawQuery(truyVan, null);
        if(cursor.getCount()!=0){
            return true;
        } else{
            return false;
        }
    }

    public boolean KiemTraDangNhap(String tendangnhap, String matkhua){
        String truyVan = "SELECT * FROM "+ CreateDatabase.TB_NHANVIEN + " WHERE " + CreateDatabase.TB_NHANVIEN_TEDN +" = '" +tendangnhap
                +"' AND "+ CreateDatabase.TB_NHANVIEN_MATKHAU+" = '" + matkhua+ "'";

        Cursor cursor = database.rawQuery(truyVan, null);
        if(cursor.getCount()!=0){
            return true;
        } else{
            return false;
        }

    }
}
