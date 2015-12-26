package bluetooth.majorproject.navigationdrawer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by alina on 8/2/15.
 */
public class FragmentAllCompetitions extends Fragment {

    TextView textView=null;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_competitions,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView= (TextView) getActivity().findViewById(R.id.tvAllComp);

        //JsonOperation jsonOperation=new JsonOperation(getActivity());

        textView.setText("Yesma sabai ko add garera dekhauni ho so k harira swipe garau na...");//jsonOperation.getJsonObjByMainStatsIndex(1).toString()+"");


    }
}

