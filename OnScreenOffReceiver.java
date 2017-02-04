package android.hmkcode.com.kiosk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.preference.PreferenceManager;

/**
 * Created by shreyas singh on 1/6/2017.
 */
public class OnScreenOffReceiver extends BroadcastReceiver {
    PowerManager pm ;
    private static final String PREF_KIOSK_MODE = "pref_kiosk_mode" ;
    @Override
    public void onReceive(Context context, Intent intent) {
        if ((Intent.ACTION_SCREEN_OFF.equals(intent.getAction()))){
            AppContext ctx = (AppContext) context.getApplicationContext();
            if(isKioskMoadeActive(ctx)){
                wakeUpDevice(ctx);
            }
        }
    }
    private void wakeUpDevice(AppContext context){
        PowerManager.WakeLock wakeLock = context.getWakeLock();
        if(wakeLock.isHeld()) {
            wakeLock.release();
        }
        wakeLock.acquire();
        wakeLock.release();
    }
    private boolean isKioskMoadeActive(final Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_KIOSK_MODE, false);
    }
}
