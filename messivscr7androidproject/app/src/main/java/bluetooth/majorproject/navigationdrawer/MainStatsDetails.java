package bluetooth.majorproject.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainStatsDetails extends Fragment {
    ViewPager viewPager=null;
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main_stats_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager=new ViewPager(getActivity().getApplicationContext());

        viewPager=(ViewPager)getActivity().findViewById(R.id.pager);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if(viewPager==null) Toast.makeText(getActivity(),"View pager is null",Toast.LENGTH_LONG).show();
        else if(fragmentManager==null) Toast.makeText(getActivity(),"fragment manager is null",Toast.LENGTH_LONG).show();
        else
                viewPager.setAdapter(new NavigationDrawerListAdapter(fragmentManager));

    }
}

class NavigationDrawerListAdapter extends FragmentStatePagerAdapter
{

    public NavigationDrawerListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment =null;
        /*if(i==0) fragment=new FragmentAllCompetitions();*/
        if(i==0) fragment=new FragmentLaLiga();
        if(i==1) fragment=new FragmentChampionsLeague();
        if(i==2) fragment=new FragmentOtherCups();
        return fragment;
    }

    @Override
    public int getCount() {
        // Log.d("msg","getCount is called ");
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=new String();
        /*if(position==0) return "All Competitions"; */
        if(position==0) return "La Liga";
        if(position==1) return "Champions League";
        if(position==2) return "Other Cups";

        return null;
    }
}
