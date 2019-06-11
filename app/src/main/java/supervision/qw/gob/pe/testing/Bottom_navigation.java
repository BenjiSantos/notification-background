package supervision.qw.gob.pe.testing;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supervision.qw.gob.pe.testing.adapter.EmergenciesAdapter;
import supervision.qw.gob.pe.testing.api.FirefighterService;
import supervision.qw.gob.pe.testing.api.RetrofitClient;
import supervision.qw.gob.pe.testing.api.model.Emergencie;
import supervision.qw.gob.pe.testing.listeners.OnTabNavListener;
import supervision.qw.gob.pe.testing.service.NewMessageNotification;
import supervision.qw.gob.pe.testing.service.NotificationJobService;

public class Bottom_navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnTabNavListener {

    private TextView mTextMessage;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FirefighterService EmergenciesService;
    private RecyclerView mEmergencies;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Log.d("TAG", "MENU 1");
                    switchToFragment_emergencie();
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    Log.d("TAG", "MENU 2");
                    switchToFragment_radio();
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    Log.d("TAG", "MENU 3");
                    switchToFragment_credit();
                    return true;
            }
            return false;
        }
    };

    public void switchToFragment_emergencie() {
        Intent intent = new Intent(this, Bottom_navigation.class);
        startActivity(intent);
    }

    public void switchToFragment_radio() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.drawer_layout, new RadioFragment()).commit();
    }

    public void switchToFragment_credit() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.drawer_layout, new CreditFragment()).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        EmergenciesService = RetrofitClient.getRetrofit().create(FirefighterService.class);
        mEmergencies = (RecyclerView) findViewById(R.id.emergenciesRecyclerView);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorWhite);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorPrimary);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                syncEmergencies();
            }
        });

        syncEmergencies();
        scheduleJob();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        NewMessageNotification message = new NewMessageNotification();

      if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void syncEmergencies() {
        Call<Emergencie> loginCall = EmergenciesService.getEmergencies();
        loginCall.enqueue(new Callback<Emergencie>() {
            @Override
            public void onResponse(Call<Emergencie> call, Response<Emergencie> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(Bottom_navigation.this, "No se sincronizaron emergencias: 1", Toast.LENGTH_LONG).show();

                }
                else if (response.body() == null) {
                    Toast.makeText(Bottom_navigation.this, "No se sincronizaron emergencias: 2", Toast.LENGTH_LONG).show();

                }
                else if (response.errorBody() != null){
                    Toast.makeText(Bottom_navigation.this, "No se sincronizaron emergencias: 3", Toast.LENGTH_LONG).show();

                }
                else {
                    // Initialize contacts
                    // Create adapter passing in the sample user data
                    EmergenciesAdapter adapter = new EmergenciesAdapter(response.body().getEmergenciesTotal());

                    // Attach the adapter to the recyclerview to populate items
                    mEmergencies.setAdapter(adapter);

                    // Set layout manager to position the items
                    mEmergencies.setLayoutManager(new LinearLayoutManager(Bottom_navigation.this));

                    SharedPreferences sharedPref = getSharedPreferences("EmergenciesShared", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("NumeroParte", response.body().getEmergenciesTotal().get(0).getNumeroParte());
                    editor.commit();

                    swipeRefreshLayout.setRefreshing(false);

                    }
            }

            @Override
            public void onFailure(Call<Emergencie> call, Throwable t) {
                Log.d("ERROR: " , ""+ t.getMessage());
                Toast.makeText(Bottom_navigation.this, "No se sincronizaron emergencias: 4" + " (" + t.getMessage() + ")", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, NotificationJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d("TAG", "Job scheduling successful");
        } else {
            Log.d("TAG", "Job scheduling failed");
        }
    }

    public void cancelJob() {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d("TAG BOMBA", "Job cancelled");
    }
}
