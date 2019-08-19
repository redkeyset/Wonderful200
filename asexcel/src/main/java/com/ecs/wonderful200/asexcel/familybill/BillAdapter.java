package com.ecs.wonderful200.asexcel.familybill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ecs.wonderful200.asexcel.R;
import com.ecs.wonderful200.asexcel.bean.BillObject;

import java.util.List;

/**
 * 作者：RedKeyset on 2019/8/19 11:11
 * 邮箱：redkeyset@aliyun.com
 */
public class BillAdapter extends BaseAdapter {

    private List<BillObject> list;
    private LayoutInflater inflater;

    public BillAdapter(Context context, List<BillObject> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            //添加单个布局
            convertView = inflater.inflate(R.layout.bill_items, null);
            //初始化控件
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.food = (TextView) convertView.findViewById(R.id.food);
            holder.clothes = (TextView) convertView.findViewById(R.id.clothes);
            holder.house = (TextView) convertView.findViewById(R.id.house);
            holder.vehicle = (TextView) convertView.findViewById(R.id.vehicle);
            //添加标记
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BillObject billObject = list.get(position);
        //设置内容
        holder.time.setText(billObject.getTime());
        holder.food.setText(billObject.getFood());
        holder.clothes.setText(billObject.getClothes());
        holder.house.setText(billObject.getHouse());
        holder.vehicle.setText(billObject.getVehicle());
        return convertView;
    }

    public class ViewHolder {
        TextView time,food,clothes,house,vehicle;
    }
}
