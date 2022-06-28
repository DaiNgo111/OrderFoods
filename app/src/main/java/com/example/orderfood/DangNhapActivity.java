package com.example.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfood.DAO.NhanVienDAO;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDongYDN,btnDangKyDN;
    EditText edTenDangNhapDN, edMatKhauDN;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDangKyDN= findViewById(R.id.btnDangKyDN);
        btnDongYDN = findViewById(R.id.btnDongYDN);
        edMatKhauDN = findViewById(R.id.edMatKhauDN);
        edTenDangNhapDN = findViewById(R.id.edTenDangNhapDN);

        nhanVienDAO = new NhanVienDAO(this);
        btnDongYDN.setOnClickListener(this);
        btnDangKyDN.setOnClickListener(this);
        HienThiButtonDangKyVaDongY();
    }

    private void HienThiButtonDangKyVaDongY(){
       boolean kiemTra = nhanVienDAO.KiemTraNhanVien();
       if(kiemTra){
           btnDangKyDN.setVisibility(View.GONE);
           btnDongYDN.setVisibility(View.VISIBLE);
       } else{
           btnDangKyDN.setVisibility(View.VISIBLE);
           btnDongYDN.setVisibility(View.GONE);
       }
    }

    private void btnDongY(){
        String sTenDangNhap = edTenDangNhapDN.getText().toString();
        String sMatKhau = edMatKhauDN.getText().toString();
        boolean kienTra = nhanVienDAO.KiemTraDangNhap(sTenDangNhap, sMatKhau);
        if(kienTra){
            Intent iTrangchu = new Intent(DangNhapActivity.this, TrangChuActivity.class);
            iTrangchu.putExtra("tendn", edTenDangNhapDN.getText().toString());
            startActivity(iTrangchu);

        }else{
            Toast.makeText(DangNhapActivity.this,"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
        }
    }

    private void btnDangKy(){
        // chuyen qua trang khac
        Intent iDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
        startActivity(iDangKy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiButtonDangKyVaDongY();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnDangKyDN:
                btnDangKy();
                break;
            case R.id.btnDongYDN:
                btnDongY();
                break;
        }
    }
}
