package com.example.orderfood.CustumAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.orderfood.DTO.BanAnDTO;
import com.example.orderfood.R;

import java.util.List;

public class AdapterHienThiBanAn extends BaseAdapter implements View.OnClickListener {

    Context context;
    int layout;
    List<BanAnDTO> banAnDTOList;
    ViewHolderBanAn viewHolderBanAn;


    public AdapterHienThiBanAn(Context context, int layout, List<BanAnDTO> banAnDTOList){

        this.context = context;
        this.banAnDTOList = banAnDTOList;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTOList.get(position).getMaBan();
    }




    public class ViewHolderBanAn{
        ImageView imBanAn, imGoiMOn, imThanhToan, imAnButton;
        TextView txtTenBanAn;
     }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderBanAn = new ViewHolderBanAn();
            view = inflater.inflate(R.layout.custom_layout_hienthibanan, parent, false);
            viewHolderBanAn.imBanAn = view.findViewById(R.id.imBanAn);
            viewHolderBanAn.imGoiMOn = view.findViewById(R.id.imGoiMon);
            viewHolderBanAn.imThanhToan = view.findViewById(R.id.imThanhToan);
            viewHolderBanAn.imAnButton = view.findViewById(R.id.imAnBUtton);
            viewHolderBanAn.txtTenBanAn = view.findViewById(R.id.txtTenBanAn);

            view.setTag(viewHolderBanAn);


        }else{
            viewHolderBanAn =(ViewHolderBanAn) view.getTag();
        }

        if (banAnDTOList.get(position).isDuocChon()){
            HienThiButton();
        } else{
            AnButton();
        }

        BanAnDTO banAnDTO = banAnDTOList.get(position);
        viewHolderBanAn.txtTenBanAn.setText(banAnDTO.getTenBan());
        viewHolderBanAn.imBanAn.setTag(position);
        viewHolderBanAn.imBanAn.setOnClickListener(this);
        return view;
    }

     private  void HienThiButton(){
         viewHolderBanAn.imGoiMOn.setVisibility(View.VISIBLE);
         viewHolderBanAn.imThanhToan.setVisibility(View.VISIBLE);
         viewHolderBanAn.imAnButton.setVisibility(View.VISIBLE);
     }
     private  void AnButton(){
         viewHolderBanAn.imGoiMOn.setVisibility(View.INVISIBLE);
         viewHolderBanAn.imThanhToan.setVisibility(View.INVISIBLE);
         viewHolderBanAn.imAnButton.setVisibility(View.INVISIBLE);
     }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        viewHolderBanAn =(ViewHolderBanAn) ((View)v.getParent()).getTag();
        switch (id){
            case R.id.imBanAn:
                String tenban = viewHolderBanAn.txtTenBanAn.getText().toString();
                //hien thi 3 button len
                int vitri =(int) v.getTag();
                banAnDTOList.get(vitri).setDuocChon(true);

                Toast.makeText(context,"Click !",Toast.LENGTH_SHORT).show();
                HienThiButton();
                break;

        }
    }
}
