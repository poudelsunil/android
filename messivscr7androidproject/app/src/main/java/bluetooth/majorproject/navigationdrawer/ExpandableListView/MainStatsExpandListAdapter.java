package bluetooth.majorproject.navigationdrawer.ExpandableListView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import bluetooth.majorproject.navigationdrawer.MainStatsJsonOperation;
import bluetooth.majorproject.navigationdrawer.R;

/**
 * Created by alina on 8/6/15.
 */


public class MainStatsExpandListAdapter extends BaseExpandableListAdapter {

    public static final String TAG_DETAILS="details";
    public static final String TAG_PLAYER="player";
    public static final String TAG_MESSI="messi";
    public static final String TAG_RONALDO="ronaldo";
    public static final String TAG_NAME="name";
    public static final String TAG_INFO="info";
    public static final String TAG_YEAR="year";
    public static final String TAG_GOALS="goals";
    public static final String TAG_PENALTIES="penalties";
    public static final String TAG_HAT_TRICKS="hat_tricks";
    public static final String TAG_GOAL_RATIO="goal_ratio";
    public static final String TAG_ASSISTS="assists";
    public static final String TAG_APPS="apps";
    public static final String TAG_MINUTES_PLAYED="minutes_played";
    public static final String TAG_MINUTES_FOR_A_GOAL="minutes_for_a_goal";

    private Context context;
    MainStatsJsonOperation jsonOperation=null;
    JSONObject jsonObjectLaLiga = null;
    JSONArray jsonArrayDetails=null;


    public MainStatsExpandListAdapter(Context context, int index) throws JSONException {   //index =0 for la_liga 1 for champ. 2 for other's cups
        this.context = context;
        jsonOperation=new MainStatsJsonOperation(context);
        jsonObjectLaLiga = jsonOperation.getJsonObjByMainStatsIndex(index);
        jsonArrayDetails=jsonObjectLaLiga.getJSONArray(TAG_DETAILS);

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Child child= null;
        try {
            child = getInfo(groupPosition, childPosition);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return child;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Child child = (Child) getChild(groupPosition, childPosition);
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_main_stats_info,null);
        }


        TextView messi_tv = (TextView) convertView.findViewById(R.id.messi_data);
        TextView childListName = (TextView) convertView.findViewById(R.id.child_list_name);
        TextView cr_tv = (TextView) convertView.findViewById(R.id.cr_data);

            messi_tv.setTextColor(Color.WHITE);
            cr_tv.setTextColor(Color.WHITE);
            childListName.setTextColor(Color.WHITE);


            messi_tv.setText(child.getMessiData().toString());
            String cTitle=child.getChildList().toString();
            childListName.setText(cTitle);
            cr_tv.setText(child.getCrData().toString());
           /* if(cTitle.contains("GOALS") || cTitle.contains("ASSIST") || cTitle.contains("APP"))
            {
                messi_tv.setTextColor(Color.RED);
                cr_tv.setTextColor(Color.RED);
                childListName.setTextColor(Color.RED);
            }*/

        return convertView;
    }


    @Override
    public int getGroupCount() {
        return jsonArrayDetails.length();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int tmpCount=8;
            //count garna lai ho , yesari namildo raixa.//
        try {
            JSONArray player=jsonArrayDetails.getJSONObject(groupPosition).getJSONArray(TAG_PLAYER);
            int messiInfoCount,cr7InfoCount;
            messiInfoCount=player.getJSONObject(0).getJSONObject(TAG_INFO).length();
            cr7InfoCount=player.getJSONObject(1).getJSONObject(TAG_INFO).length();
            tmpCount=(messiInfoCount==cr7InfoCount ? messiInfoCount : (messiInfoCount>cr7InfoCount ? messiInfoCount : cr7InfoCount));

            if(jsonArrayDetails.getJSONObject(groupPosition).getLong(TAG_YEAR)==2016)
                tmpCount=0;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return tmpCount;
    }

    @Override
    public Object getGroup(int groupPosition) {
        Object tmpYr=0;
        try {
            tmpYr = getYear(groupPosition);// groups.get(groupPosition);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmpYr;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView  == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_item_year,null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.group_name);
        try {
            long tmpYear=Long.parseLong(getYear(groupPosition).toString());
            if(tmpYear==2016)
                tv.setText(tmpYear-1+"/"+tmpYear+" loading...");
            else
                tv.setText(tmpYear-1+"/"+tmpYear);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public Object getYear(int  gPostition) throws JSONException {
        Object year=jsonArrayDetails.getJSONObject(gPostition).get(TAG_YEAR);
        return year;
    }
    public Child getInfo(int gPosition,int cPosition) throws JSONException {
        JSONObject jsonObject = jsonArrayDetails.getJSONObject(gPosition);
        Child tmpChild=new Child();
        JSONArray jsonArrayPlayer = jsonObject.getJSONArray(TAG_PLAYER);
        JSONObject jsonObjectMessiInfo=jsonArrayPlayer.getJSONObject(0).getJSONObject(TAG_INFO),jsonObjectRonaldoInfo= jsonArrayPlayer.getJSONObject(1).getJSONObject(TAG_INFO);

        Object tmpMessiData="-",tmpCr7Data="-";
        String tmpTag="";

        int i=0;
        Iterator<String> iter = jsonObjectMessiInfo.keys();

        while (iter.hasNext()) {
            tmpTag = iter.next();
            if(i==cPosition) {
                break;
            }
            i++;
        }


        if(jsonObjectMessiInfo.has(tmpTag))
            tmpMessiData = jsonObjectMessiInfo.get(tmpTag);
        if (jsonObjectRonaldoInfo.has(tmpTag))
            tmpCr7Data = jsonObjectRonaldoInfo.get(tmpTag);

        tmpChild.setMessiData(tmpMessiData);
        tmpChild.setCrData(tmpCr7Data);
        switch (tmpTag)
        {
            case TAG_GOALS : tmpTag="GOALS"; break;
            case TAG_APPS : tmpTag="APPEARANCE"; break;
            case TAG_ASSISTS: tmpTag="ASSISTS";break;
            case TAG_GOAL_RATIO: tmpTag="GOAL RATIO";break;
            case TAG_HAT_TRICKS: tmpTag="HAT TRICKS";break;
            case TAG_PENALTIES: tmpTag="PENALTIES";break;
            case TAG_MINUTES_PLAYED: tmpTag="MINUTES PLAYED";break;
            case TAG_MINUTES_FOR_A_GOAL: tmpTag="MINUTES FOR A GOAL";break;



        }
        tmpChild.setChildList(tmpTag);

        return tmpChild;
    }


}
