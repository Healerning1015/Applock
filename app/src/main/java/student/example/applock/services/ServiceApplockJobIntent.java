package student.example.applock.services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import student.example.applock.broadcast.ReceiverAppLock;

public class ServiceApplockJobIntent extends JobIntentService {
    private static final int JOB_ID = 15462;
    public static void enqueueWork(Context context, Intent work){
        enqueueWork(context, ServiceApplockJobIntent.class, JOB_ID, work);
    }
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        runAppLock();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        BackgroundManager.getInstance().init(this).startService();

        BackgroundManager.getInstance().init(this).startAlarmManager();
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onDestroy() {
        BackgroundManager.getInstance().init(this).startService();

        BackgroundManager.getInstance().init(this).startAlarmManager();
        super.onDestroy();
    }

    private void runAppLock(){
        long endTime = System.currentTimeMillis()+210;
        while(System.currentTimeMillis() < endTime){
            synchronized (this){
                try {
                    Intent intent = new Intent(this, ReceiverAppLock.class);
                    sendBroadcast(intent);
                    Log.e("wait time out:", (endTime - System.currentTimeMillis())+"");
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
