package com.example.orderfood.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.DTO.BanAnDTO;
import com.example.orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class BanAnDAO {
    SQLiteDatabase database;

    public BanAnDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();

    }

    public boolean ThemBanAn(String tenban){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN, tenban);
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG, "flase");
        
        long kiemtra = database.insert(CreateDatabase.TB_BANAN, null, contentValues);
        if (kiemtra!=0){
            return true;
        }else{
            return false;
        }
    }
    // hàm trả về danh sách bàn ăn
    @SuppressLint("Range")
    public List<BanAnDTO> LayTatCaBanAn(){
        List<BanAnDTO> banAnDTOList = new ArrayList<BanAnDTO>();
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_BANAN;
        // TRẢ VỀ CON TRỎ CURSOR
        Cursor cursor = database.rawQuery(truyvan, null);
        // TRẢ CON TRỎ VỀ VỊ TRÍ ĐẦU TIÊN
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            // lấy dữ liệu
            BanAnDTO banAnDTO = new BanAnDTO();
            banAnDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_MABAN)));
            banAnDTO.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TENBAN)));

            banAnDTOList.add(banAnDTO);
            // chuyển con trỏ xuống dưới
            cursor.moveToNext();
        }
        return banAnDTOList;
    }


}
