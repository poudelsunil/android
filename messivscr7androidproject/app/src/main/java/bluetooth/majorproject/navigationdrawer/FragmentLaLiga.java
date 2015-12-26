package bluetooth.majorproject.navigationdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.json.JSONException;

import bluetooth.majorproject.navigationdrawer.ExpandableListView.MainStatsExpandListAdapter;

/**
 * Created by alina on 8/2/15.
 */
public class FragmentLaLiga extends Fragment {

    private MainStatsExpandListAdapter adapter;
    private ExpandableListView expandList;

    TextView textView=null;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_la_liga,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
       // textView= (TextView) getActivity().findViewById(R.id.tvLaLiga);
        super.onActivityCreated(savedInstanceState);
        expandList = (ExpandableListView) getActivity().findViewById(R.id.expLvLaLiga);

        try {
            adapter = new MainStatsExpandListAdapter(getActivity(),0); //0 index for la liga
        } catch (JSONException e) {
            e.printStackTrace();
        }
        expandList.setAdapter(adapter);

    }

}
