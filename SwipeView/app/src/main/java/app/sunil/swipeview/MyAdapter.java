package app.sunil.swipeview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dell on 6/8/2015.
 */
class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if(position==0){
            fragment=new FragmentA();
        }
        if(position==1){
            fragment=new FragmentB();
        }
        if(position==2){
            fragment=new FragmentC();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0){
            title="tab 0";
        }
        else if(position==1){
            title="tab 1";
        }
        else if(position==2){
            title="tab 2";
        }
        return title;

    }
}
