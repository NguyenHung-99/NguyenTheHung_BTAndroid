package test.room.api.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.Viewholder> {

    private List<User> data;
    private Context context;

    public UserAdapter(List<User> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.iteam_layout, parent, false);

        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.Viewholder holder, int position) {
        holder.id.setText("ID : " + String.valueOf(data.get(position).getId()));
        holder.tuoi.setText("Tuoi : " +String.valueOf(data.get(position).getTuoi()));
        holder.ten.setText("Ten : " +data.get(position).getTen());
        holder.remvoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuliAPI.DeleteApi(MainActivity.url, String.valueOf(data.get(position).getId()),context);
                data.clear();
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class Viewholder extends RecyclerView.ViewHolder {
         TextView id;
       TextView ten;
         TextView tuoi;
        Button remvoe ;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            ten = itemView.findViewById(R.id.ten);
            tuoi = itemView.findViewById(R.id.tuoi);
            remvoe = itemView.findViewById(R.id.xoa);

        }
    }
}
