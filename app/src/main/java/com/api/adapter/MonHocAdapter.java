package com.api.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.api.R;
import com.api.dto.MonHocDto;

import java.util.List;


public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.ViewHolder> {
    private ItemClickMH _itemClickMH;
    //định nghĩa từng item cho RecyclerView
    private List<MonHocDto> monHocDtoList;

    public MonHocAdapter(List<MonHocDto> monHocDtoList,ItemClickMH itemClickMH) {
        this.monHocDtoList = monHocDtoList;
        this._itemClickMH = itemClickMH;
    }
    public void setFilterList(List<MonHocDto> filter){
        this.monHocDtoList = filter;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    //định nghĩa các item layout và khởi tạo holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());//from(parent.getContext()
        //lấy dữ liệu của item thêm vào List
        View view = inflater.inflate(R.layout.activity_item_subject, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,_itemClickMH);

        return viewHolder;
    }

    @Override
    //thiết lập các thuộc tính của View và dữ liệu
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MonHocDto monHoc = monHocDtoList.get(position);
        holder.tvMaMH.setText(monHoc.getMaMonHoc());
        holder.tvTenMH.setText(monHoc.getTenMonHoc());
        holder.llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder._itemClickMH.onItemClick(monHoc);
            }
        });
    }

    @Override
    //số item của list data
    public int getItemCount() {
        if(monHocDtoList != null)
            return monHocDtoList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemClickMH _itemClickMH;
        TextView tvMaMH, tvTenMH;
        RelativeLayout llItemClick;

        public ViewHolder(@NonNull View itemView, ItemClickMH itemClickMH) {
            super(itemView);
            _itemClickMH = itemClickMH;
            tvMaMH = itemView.findViewById(R.id.tvMaMH);
            tvTenMH = itemView.findViewById(R.id.tvTenMH);
            llItemClick = itemView.findViewById(R.id.llItemClick);
        }
    }

    public interface ItemClickMH{
        void onItemClick(MonHocDto monHocDto);
    }
}
