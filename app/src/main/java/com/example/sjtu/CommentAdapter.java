package com.example.sjtu;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class commentAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<CommentMessage> data;

    public commentAdapter(LayoutInflater inflater, ArrayList<CommentMessage> data) {
        this.inflater = inflater;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentMessage chatMessage=data.get(position);
        View view = null;
        ViewHolder viewHolder;
        if(convertView==null){
            view=inflater.inflate(R.layout.food_comment,null);
            viewHolder=new ViewHolder();
            viewHolder.comment=view.findViewById(R.id.comment);
            viewHolder.time=view.findViewById(R.id.time);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.comment.setText(data.get(position).getComment());
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time=format.format(new Date(chatMessage.getTime()));
        viewHolder.time.setText(time);
        return view;
    }

    class ViewHolder{
        TextView comment;
        TextView time;
    }
}

