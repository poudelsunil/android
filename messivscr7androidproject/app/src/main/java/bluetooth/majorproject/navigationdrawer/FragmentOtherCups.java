package bluetooth.majorproject.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.json.JSONException;

import bluetooth.majorproject.navigationdrawer.ExpandableListView.MainStatsExpandListAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOtherCups extends Fragment {

    private MainStatsExpandListAdapter adapter;
    private ExpandableListView expandList;

    public FragmentOtherCups() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_cups, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        expandList = (ExpandableListView) getActivity().findViewById(R.id.expLvOtherCups);

        try {
            adapter = new MainStatsExpandListAdapter(getActivity(),2); //0 index for la liga
        } catch (JSONException e) {
            e.printStackTrace();
        }
        expandList.setAdapter(adapter);
    }


}
