package student.example.applock.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import student.example.applock.R;
import student.example.applock.ViewHolder.AppViewHolder;
import student.example.applock._interface.AppOnClickListener;
import student.example.applock.model.AppItem;
import student.example.applock.utils.Utils;

public class AppAdapters extends RecyclerView.Adapter<AppViewHolder> {

    private Context mContext;
    private List<AppItem> appItemList;
    private Utils utils;

    public AppAdapters(Context mContext, List<AppItem> appItemList) {
        this.mContext = mContext;
        this.appItemList = appItemList;
        this.utils = new Utils(mContext);
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_apps, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        holder.app_name.setText(appItemList.get(position).getName());
        holder.app_icon.setImageDrawable(appItemList.get(position).getIcon());

        final String pk = appItemList.get(position).getPackageName();
        if(utils.isLock(pk)){
            holder.app_lock.setImageResource(R.drawable.ic_baseline_lock_24);
        } else {
            holder.app_lock.setImageResource(R.drawable.ic_baseline_lock_open_24);
        }

        holder.setListener(new AppOnClickListener() {
            @Override
            public void selectApp(int pos) {
                if(utils.isLock(pk)){
                    holder.app_lock.setImageResource(R.drawable.ic_baseline_lock_open_24);
                    utils.unlock(pk);
                    //Log.i("is Lock? ",utils.isLock(pk)+"");
                    Toast.makeText(mContext, "is Lock? "+utils.isLock(pk), Toast.LENGTH_SHORT).show();
                } else {
                    holder.app_lock.setImageResource(R.drawable.ic_baseline_lock_24);
                    utils.lock(pk);
                    //Log.i("is Lock? ",utils.isLock(pk)+"");
                    Toast.makeText(mContext, "is Lock? "+utils.isLock(pk), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}
