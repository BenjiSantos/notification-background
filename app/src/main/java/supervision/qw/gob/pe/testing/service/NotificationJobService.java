package supervision.qw.gob.pe.testing.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supervision.qw.gob.pe.testing.api.FirefighterService;
import supervision.qw.gob.pe.testing.api.RetrofitClient;
import supervision.qw.gob.pe.testing.api.model.Emergencie;

public class NotificationJobService extends JobService {
    private static final String TAG = "NotificationJobService";
    private FirefighterService EmergenciesService;
    private static final String CHANNEL_ID = "default";
    private static final String CHANNEL_DESCRIPTION = "Your description...";
    private boolean jobCancelled = false;
    private String listEmergergencies;

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
        Call<Emergencie> loginCall = EmergenciesService.getEmergencies();
        loginCall.enqueue(new Callback<Emergencie>() {
            @Override
            public void onResponse(Call<Emergencie> call, Response<Emergencie> response) {

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
                    SharedPreferences sharedPref = getSharedPreferences("EmergenciesShared", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("lastPartNumberEmergencie", response.body().getEmergenciesTotal().get(0).getNumeroParte());
                    editor.putString("typeEmergencie", response.body().getEmergenciesTotal().get(0).getTipoEmergenciaCompleto());
                    editor.putString("dateEmergencie", response.body().getEmergenciesTotal().get(0).getFechaParte());
                    editor.putString("addressEmergencie", response.body().getEmergenciesTotal().get(0).getDireccion() +
                            response.body().getEmergenciesTotal().get(0).getDistrito());
                    editor.putString("stateNotification", response.body().getEmergenciesTotal().get(0).getEstadoRegistro());
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<Emergencie> call, Throwable t) {
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
                String PartNumber =  preferences.getString("NumeroParte", "-");
                String lastPartNumber = preferences.getString("lastPartNumberEmergencie", "--");
                String dateEmergencie = preferences.getString("dateEmergencie", "---");
                String typeEmergencie = preferences.getString("typeEmergencie", "----");
                String addressEmergencie = preferences.getString("addressEmergencie", "-----");
                String stateNotification = preferences.getString("stateNotification", "------");

                Log.d("numerodeparte",  PartNumber);
                Log.d("lastEmergencie",  lastPartNumber);

                //PartNumber.compareTo(lastPartNumber)
                if(1 != 0) {
                    //@TODO define all text for notification
                    message.notify(getApplicationContext(), typeEmergencie, PartNumber, 1, dateEmergencie, addressEmergencie, stateNotification, CHANNEL_ID );
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