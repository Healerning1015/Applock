package student.example.applock.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import student.example.applock.R;
import student.example.applock._interface.AppOnClickListener;

public class AppViewHolder extends RecyclerView.ViewHolder {

    public ImageView app_icon, app_lock;
    public TextView app_name;

    private AppOnClickListener listener;

    public void setListener(AppOnClickListener listener) {
        this.listener = listener;
    }

    public AppViewHolder(@NonNull View itemView) {
        super(itemView);

        app_icon = itemView.findViewById(R.id.app_icon);
        app_lock = itemView.findViewById(R.id.app_lock);
        app_name = itemView.findViewById(R.id.app_info_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.selectApp(getAdapterPosition());
            }
        });
    }
}