package com.kyletung.kylejuheweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <br>Created on 15-8-27.
 * <br>Email: dyh920827@gmail.com
 * <br>Website: <a href="http://www.kyletung.com">Kyle Tung</a>
 *
 * @author Kyle Tung
 * @version 0.3
 */

public class CityAdapter extends BaseAdapter {

    List<String> cityList;
    TextView textView;

    public CityAdapter() {
        cityList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.drawer_menu_list, null);
        textView = (TextView) view.findViewById(R.id.city_list_item);
        textView.setText(cityList.get(position));
        return view;
    }

    public void addCity(String cityName) {
        cityList.add(cityName);
        notifyDataSetChanged();
    }

    public void removeCity(int position) {
        cityList.remove(position);
        notifyDataSetChanged();
    }
}
