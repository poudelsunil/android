package bluetooth.majorproject.navigationdrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NagivationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mdrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private static final String PREF_FILE_NAME="messivscr7Pref";
    private static final String KEY_USER_LEARNED_DRAWER="mUserLearnedDrawer";

    private View containerView;

    ListView lvNevDrwMenu;
    ArrayAdapter<CharSequence> adapter;//=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);

    public NagivationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(readFromPreference(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nagivation_drawer, container, false);
    }

    public void setUp(int fragmentId,  DrawerLayout drawerLayout,Toolbar toolbar){

        containerView=getActivity().findViewById(fragmentId);

        mdrawerLayout=drawerLayout;
        mDrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    savedToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + " ");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState){
            mdrawerLayout.openDrawer(containerView);
        }
        mdrawerLayout.setDrawerListener(mDrawerToggle);
        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static  void savedToPreferences(Context context,String preferencesName,String preferenceValue){
        SharedPreferences sharedPreferences= context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(preferencesName,preferenceValue);
        editor.apply();
    }

    public static String readFromPreference(Context context,String preferenceName,String defaultValue){
        SharedPreferences sharedPreferences= context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }


    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lvNevDrwMenu=(ListView)getActivity().findViewById(R.id.lvNevDrwMenu);

        adapter=ArrayAdapter.createFromResource(getActivity(),R.array.menu_item,android.R.layout.simple_list_item_1);
        lvNevDrwMenu.setAdapter(adapter);
        lvNevDrwMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mdrawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(getActivity(),"Position : "+position,Toast.LENGTH_LONG).show();
                ((communicator) getActivity()).biPassMessage(position);

            }
        });

    }
}