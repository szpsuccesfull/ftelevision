package com.example.ftelevision.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.ftelevision.R;
import com.example.ftelevision.models.TelevisionModel;
import java.util.ArrayList;

public class televisionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TelevisionModel>modelArrayList;

    public televisionAdapter(Context context, ArrayList<TelevisionModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public TelevisionModel getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(R.layout.television_list_item,viewGroup, false );
        }

        TextView tv_television_list_item_description = view.findViewById(R.id.tv_televisions_list_item_description);
        TextView tv_television_list_item_serial = view.findViewById(R.id.tv_televisions_list_item_serial);

        tv_television_list_item_description.setText(getItem(position).getDescription());
        tv_television_list_item_serial.setText(getItem(position).getSerial());
        return view;
    }
}

