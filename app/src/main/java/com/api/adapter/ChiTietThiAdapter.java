package com.api.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.api.R;
import com.api.dto.ChiTietThiDto;
import com.api.dto.MonHocDto;

import java.util.List;

public class ChiTietThiAdapter extends RecyclerView.Adapter<ChiTietThiAdapter.ViewHolder> {
    List<ChiTietThiDto> chiTietThiDtoList;
    ItemClickCTT _itemClickCTT;

    public ChiTietThiAdapter(List<ChiTietThiDto> chiTietThiDtoList,ItemClickCTT _itemClickCTT) {
        this.chiTietThiDtoList = chiTietThiDtoList;
        this._itemClickCTT = _itemClickCTT;
    }
    public void setFilterList(List<ChiTietThiDto> filter){
        this.chiTietThiDtoList = filter;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_item_history,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,_itemClickCTT);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ChiTietThiDto chiTietThi = chiTietThiDtoList.get(position);
        holder.tvNgayThi.setText(chiTietThi.getNgayThi());
        holder.tvMonThi.setText(chiTietThi.getTenMonHoc());
        holder.llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder._itemClickCTT.onItemClick(chiTietThi);
            }
        });
    }
    @Override
    public int getItemCount() {
        if(chiTietThiDtoList != null)
            return chiTietThiDtoList.size();
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemClickCTT _itemClickCTT;
        TextView tvNgayThi, tvMonThi;
        RelativeLayout llItemClick;

        public ViewHolder(@NonNull View itemView, ItemClickCTT itemClickCTT) {
            super(itemView);
            _itemClickCTT = itemClickCTT;
            tvMonThi = itemView.findViewById(R.id.tvMonThi);
            tvNgayThi = itemView.findViewById(R.id.tvNgayThi);
            llItemClick = itemView.findViewById(R.id.llItemClick);
        }
    }
    public interface ItemClickCTT{
        void onItemClick(ChiTietThiDto chiTietThiDto);
    }
}
