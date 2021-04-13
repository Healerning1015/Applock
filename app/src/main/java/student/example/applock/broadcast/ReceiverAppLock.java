package student.example.applock.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import student.example.applock.PatternLockAct;
import student.example.applock.utils.Utils;

public class ReceiverAppLock extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Utils utils = new Utils(context);
        String appRunning = utils.getLauncherTopApp();

        Log.i("appRunning: ",appRunning);
        Log.i("utils.getLastApp(): ",utils.getLastApp());
        //Log.i("isLock: ",utils.isLock(appRunning)+"");

        if(utils.isLock(appRunning)){
            if(!appRunning.equals(utils.getLastApp())){
                utils.clearLastApp();
                utils.setLastApp(appRunning);

                Intent i = new Intent(context, PatternLockAct.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("broadcast_receiver","broadcast_receiver");
                context.startActivity(i);
            }
        }
    }
}
