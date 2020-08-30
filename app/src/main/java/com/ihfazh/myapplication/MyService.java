package com.ihfazh.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.lang.ref.WeakReference;

public class MyService extends Service implements DummyAsyncCallback{
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand async mulai dijalankan");
        DummyAsync dummyAsync = new DummyAsync(this);
        dummyAsync.execute();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy....");
    }

    @Override
    public void preAsync() {
        Log.d(TAG, "Preasync Mulai....");

    }

    @Override
    public void postAsync() {
        Log.d(TAG, "postasync Mulai....");
        stopSelf();
    }

    private static class DummyAsync extends AsyncTask<Void, Void, Void> {
        private final WeakReference<DummyAsyncCallback> callback;

        DummyAsync(DummyAsyncCallback callback) {
            this.callback = new WeakReference<>(callback);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground...");
            try {
                Thread.sleep(3000);
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecuted....");
            this.callback.get().preAsync();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(TAG, "onPostExecuted....");
            this.callback.get().postAsync();
        }
    }
}
