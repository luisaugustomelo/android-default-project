package ni.com.debugon.notificationinfo.ni.com.debugon.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by luisr on 18/03/2017.
 * Uma das referências da criação de serviço: https://www.thepolyglotdeveloper.com/2014/10/use-broadcast-receiver-background-services-android/
 */

public class BackgroundService extends Service {

    private boolean isRunning;
    private Context context;
    private Thread backgroundThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        this.context = this;
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);
    }

    private Runnable myTask = new Runnable() {
        public void run() {
            //Faz alguma coisa.
            stopSelf();
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.context = context;

        if(!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        this.isRunning = false;
    }
}