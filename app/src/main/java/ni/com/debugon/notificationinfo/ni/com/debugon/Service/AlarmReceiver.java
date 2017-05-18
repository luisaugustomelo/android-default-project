package ni.com.debugon.notificationinfo.ni.com.debugon.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by luisr on 21/03/2017.
 * Referência para a configuração do Alarm: https://developer.android.com/training/scheduling/alarms.html
 */


public final class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, BackgroundService.class);
        context.startService(background);

    }
}