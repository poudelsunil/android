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
public class FragmentChampionsLeague extends Fragment {

    private MainStatsExpandListAdapter adapter;
    private ExpandableListView expandList;
    TextView textView=null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_champions_league,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        expandList = (ExpandableListView) getActivity().findViewById(R.id.expLvChanpionsLeague);

        try {
            adapter = new MainStatsExpandListAdapter(getActivity(),1); //0 index for la liga
        } catch (JSONException e) {
            e.printStackTrace();
        }
        expandList.setAdapter(adapter);

        //JsonOperation jsonOperation=new JsonOperation(getActivity());
        //textView.setText(jsonOperation.getJsonObjByMainStatsIndex(1).toString() + "");
    }

}
