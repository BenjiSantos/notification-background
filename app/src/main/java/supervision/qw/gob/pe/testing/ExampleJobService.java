package supervision.qw.gob.pe.testing;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supervision.qw.gob.pe.testing.api.FirefighterService;
import supervision.qw.gob.pe.testing.api.RetrofitClient;
import supervision.qw.gob.pe.testing.api.model.Emergencie;

public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private FirefighterService EmergenciesService;
    private static final String CHANNEL_ID = "default";
    private static final String CHANNEL_DESCRIPTION = "Your description...";
    private boolean jobCancelled = false;
    private Emergencie listEmergergencies;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
        EmergenciesService = RetrofitClient.getRetrofit().create(FirefighterService.class);
        doBackgroundWork(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }

    private void syncEmergencies() {
        Call<List<Emergencie>> loginCall = EmergenciesService.getEmergencies();
        loginCall.enqueue(new Callback<List<Emergencie>>() {
            @Override
            public void onResponse(Call<List<Emergencie>> call, Response<List<Emergencie>> response) {

                if (!response.isSuccessful()) {
                    //Toast.makeText(MainActivity.this, "No se sincronizaron emergencias: 1", Toast.LENGTH_LONG).show();

                }
                else if (response.body() == null) {
                    //Toast.makeText(MainActivity.this, "No se sincronizaron emergencias: 2", Toast.LENGTH_LONG).show();

                }
                else if (response.errorBody() != null){
                    //Toast.makeText(MainActivity.this, "No se sincronizaron emergencias: 3", Toast.LENGTH_LONG).show();

                }
                else {
                    listEmergergencies = response.body().get(0);
                }
            }

            @Override
            public void onFailure(Call<List<Emergencie>> call, Throwable t) {
                //Toast.makeText(MainActivity.this, "No se sincronizaron emergencias: " + " (" + t.getMessage() + ")", Toast.LENGTH_LONG).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_DESCRIPTION, NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            NewMessageNotification message = new NewMessageNotification();
            @Override
            public void run() {
                syncEmergencies();
                SharedPreferences preferences = getSharedPreferences("EmergenciesShared", MODE_PRIVATE);
                String numeroParte =  preferences.getString("NumeroParte", "-");
                Log.d("numerodeparte",  numeroParte);

                //@TODO compare with true value of REST service
                if(numeroParte.compareTo("2018064312") != 0) {
                    //@TODO define all text for notification
                    message.notify(getApplicationContext(), "" + numeroParte, 1, CHANNEL_ID );
                }

                if (jobCancelled) {
                    return;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                Log.d(TAG, "Job finished");
                jobFinished(params, true);
            }
        }).start();
    }
}