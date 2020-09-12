package com.hegazy.ebtikar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hegazy.ebtikar.R;
import com.hegazy.ebtikar.model.DetailsResponse;
import com.hegazy.ebtikar.repo.remote.retrofit.ApiUrls;

import java.util.ArrayList;

public class CoverFlowAdapter extends BaseAdapter {

    private ArrayList<DetailsResponse.Profile> mData = new ArrayList<>(0);
    private Context mContext;

    public CoverFlowAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<DetailsResponse.Profile> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int pos) {
        return mData.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        DetailsResponse.Profile item = mData.get(position);


        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_people_image, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.image = rowView.findViewById(R.id.image);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();


        Glide.with(holder.image.getContext())
                .load(ApiUrls.BASE_IMAGE_PATH + item.getFile_path())
                .placeholder(R.drawable.will_smith)
                .into(holder.image);

        return rowView;
    }


    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }
}