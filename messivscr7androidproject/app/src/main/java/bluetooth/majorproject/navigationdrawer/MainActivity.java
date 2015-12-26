package bluetooth.majorproject.navigationdrawer;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements communicator{

    Toolbar toolbar;
    LinearLayout starting_view,main_stats_layout,honours_layout,abilities_layout,calendar_year_status_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NagivationDrawerFragment drawerFragment=(NagivationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.nagivationDrawer);
        drawerFragment.setUp(R.id.nagivationDrawer, (DrawerLayout)findViewById(R.id.drawer_layout),toolbar);

    }

    private void init() {
        starting_view=(LinearLayout)findViewById(R.id.starting_view);
        main_stats_layout=(LinearLayout)findViewById(R.id.main_stats_details);
        honours_layout=(LinearLayout)findViewById(R.id.honours_details);
        abilities_layout=(LinearLayout)findViewById(R.id.abilities_details);
        calendar_year_status_layout=(LinearLayout)findViewById(R.id.calendar_year_status_details);

    }

    @Override
    public void biPassMessage(int position) {
        //Toast.makeText(getApplicationContext(),position+ "was clicked !",Toast.LENGTH_LONG).show();

        main_stats_layout=(LinearLayout)findViewById(R.id.main_stats_details);
        honours_layout=(LinearLayout)findViewById(R.id.honours_details);
        abilities_layout=(LinearLayout)findViewById(R.id.abilities_details);
        calendar_year_status_layout=(LinearLayout)findViewById(R.id.calendar_year_status_details);

        switch (position){
            case 0:
                showMainStatsDetails();
                break;
            case 1:
                showHonoursDetails();
                break;
            case 2:
                showAbilitiesDetails();
                break;
            case 3:
                showcalendar_year_statusDetails();
                break;
            default:
                break;
        }


    }

    private void showMainStatsDetails() {
        hideAll();
        main_stats_layout.setVisibility(View.VISIBLE);

        ViewPager viewPager=(ViewPager)findViewById(R.id.pager);
        if( viewPager.getAdapter()==null)  //is this first time. Or viewPager already has adapter if so don't add again
        {
            viewPager.setAdapter(new NavigationDrawerListAdapter(getSupportFragmentManager()));
        }

    }
    private void showHonoursDetails() {
        hideAll();

        ExpandableListView expandableListViewHonour = (ExpandableListView) findViewById(R.id.expLvHonours);

        expandableListViewHonour.setAdapter( new HonoursListAdapter(this));
        honours_layout.setVisibility(View.VISIBLE);
    }

    private void showAbilitiesDetails() {
        hideAll();
        abilities_layout.setVisibility(View.VISIBLE);
    }
    private void showcalendar_year_statusDetails() {
        hideAll();
        calendar_year_status_layout.setVisibility(View.VISIBLE);
    }
    private void hideAll() {
        starting_view.setVisibility(View.GONE);
        main_stats_layout.setVisibility(View.GONE);
        honours_layout.setVisibility(View.GONE);
        abilities_layout.setVisibility(View.GONE);
        calendar_year_status_layout.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){

            case R.id.aboutus:

                Toast.makeText(this,"this is about us",Toast.LENGTH_LONG).show();
                break;

            case R.id.contactus:

                Toast.makeText(this,"this is contact us",Toast.LENGTH_LONG).show();
                break;
            case R.id.menu:

                Toast.makeText(this,"this is menu",Toast.LENGTH_LONG).show();
                break;
            case R.id.navigate :
                Toast.makeText(this,"this is Navigate menu",Toast.LENGTH_LONG).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}

interface communicator {
    void biPassMessage(int position);
}

