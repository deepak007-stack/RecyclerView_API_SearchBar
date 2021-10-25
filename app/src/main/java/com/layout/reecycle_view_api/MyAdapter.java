package com.layout.reecycle_view_api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.net.ProtocolFamily;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewholder> {

    Context context;
    private ArrayList<GetSet> arrayList;

    public MyAdapter(ArrayList<GetSet> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view, parent, false);

        return new viewholder(view);
    }

    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        GetSet data = arrayList.get(position);

        holder.name.setText(data.getName());
        holder.email.setText(data.getEmail());
        holder.age.setText(data.getAge());
        String url = "http://i.imgur.com/DvpvklR.png";
//        Glide.with(holder.img.getContext()).load("http://goo.gl/gEgYUd").into(holder.img);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.img);

        holder.all_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), User_profile.class);
                intent.putExtra("key_name", data.getName());
                intent.putExtra("key_email", data.getEmail());
                intent.putExtra("key_age", data.getAge());
                intent.putExtra("key_img", url);

                v.getContext().startActivity(intent);

            }
        });


    }

    public int getItemCount() {
        return arrayList.size();
    }

    public void filtered_list(ArrayList<GetSet> searchList) {
        arrayList = searchList;
        notifyDataSetChanged();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView email;
        private TextView age;
        private ImageView img;
        private RelativeLayout all_layout;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            age = (TextView) itemView.findViewById(R.id.age);
            img = (ImageView) itemView.findViewById(R.id.img);
            all_layout = (RelativeLayout) itemView.findViewById(R.id.all_layout);

        }
    }
}
