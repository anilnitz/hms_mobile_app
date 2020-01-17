package com.example.aa.hospitalmanagementsystem;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AA on 29-Jun-19.
 */

public class AppintmentList_Adapter extends RecyclerView.Adapter<AppintmentList_Adapter.MyViewHolder> {

    ArrayList<Doctor_appointment_Model> doctor_appointment_lists;
    Context context;

    public AppintmentList_Adapter(Context context, ArrayList<Doctor_appointment_Model> doctor_appointment_lists) {
        this.context = context;
        this.doctor_appointment_lists = doctor_appointment_lists;
    }


    @Override
    public AppintmentList_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_appointment,parent,false);
        return new MyViewHolder(vv);
    }

    @Override
    public void onBindViewHolder(AppintmentList_Adapter.MyViewHolder holder, int position) {
      final   Doctor_appointment_Model appointment_list = doctor_appointment_lists.get(position);

          holder.tv_title.setText(appointment_list.getType());
        holder.tv_id.setText(appointment_list.getUserId());
        holder.tv_date.setText(appointment_list.getAppointmentDate());
    }

    @Override
    public int getItemCount() {
        return doctor_appointment_lists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title,tv_date,tv_id;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
