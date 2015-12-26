package bluetooth.majorproject.navigationdrawer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bluetooth.majorproject.navigationdrawer.ExpandableListView.Child;

/**
 * Created by sunil on 8/8/15.
 */

public class HonoursListAdapter extends BaseExpandableListAdapter {

    public static final String TAG_HONORS="honors";
    public static final String TAG_YEAR="year";
    public static final String TAG_PLAYER="player";
    public static final String TAG_NAME="name";
    public static final String TAG_TROPHY="trophy";
    public static final String TAG_MESSI="messi";
    public static final String TAG_RONALDO="ronaldo";

    ImageView imgVPlayer1Photo,imgVPlayer2Photo;
    TextView tvPlayer1Name,tvPlayer2Name;
    ImageView imgVTrophy1,imgVTrophy2,imgVTrophy3,imgVTrophy4,imgVTrophy5,imgVTrophy6;
    TextView tvTrophy1,tvTrophy2,tvTrophy3,tvTrophy4,tvTrophy5,tvTrophy6;

    ImageView imgVTrophy21,imgVTrophy22,imgVTrophy23,imgVTrophy24,imgVTrophy25,imgVTrophy26;
    TextView tvTrophy21,tvTrophy22,tvTrophy23,tvTrophy24,tvTrophy25,tvTrophy26;
    Context context;
    JSONObject jsonObjectMainHonors=null;
    JSONArray jsonArrayHonors=null;
    public HonoursListAdapter(Context context) {
        this.context = context;
        String tmp= ReadFile.readFromAsset(R.raw.honors, context); //this return json contents in string format
        try {
            jsonObjectMainHonors=new JSONObject(tmp);
            jsonArrayHonors=jsonObjectMainHonors.getJSONArray(TAG_HONORS); //array of data of each year
        } catch (JSONException e) {
            Log.e("json", "JSON object parshing exception : " + e.getMessage());
        }
    }

    @Override
    public int getGroupCount() {
        return jsonArrayHonors.length();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childLength = 1;
        /*try {
            childLength = jsonArrayHonors.getJSONObject(groupPosition).getJSONArray(TAG_PLAYER).length();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        return childLength;
    }

    @Override
    public Object getGroup(int groupPosition) {
        Object tmpYr=0;
        try {
            tmpYr=jsonArrayHonors.getJSONObject(groupPosition).get(TAG_YEAR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tmpYr;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override  //return view with year
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView  == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_item_year,null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.group_name);
        long tmpYear=Long.parseLong(getGroup(groupPosition).toString());
        if(tmpYear==2016)
            tv.setText(tmpYear-1+"/"+tmpYear+" loading...");
        else
            tv.setText(tmpYear-1+"/"+tmpYear);

        return convertView;

    }

    @Override //return view with title trophy
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //Child child = (Child) getChild(groupPosition, childPosition);

        JSONArray jsonArrayPlayer;
        JSONObject jsonObjectSinglePlayer;
        JSONArray jsonArrayTrophy;

        JSONObject jsonObjectMessi=null,jsonObjectRonaldo=null;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_honors,null);
            init(convertView);
            hideAllTrophyImg(0);
            hideAllTrophyImg(1);

        }

        try {
            hideAllTrophyImg(0);
            hideAllTrophyImg(1);
            jsonArrayPlayer = jsonArrayHonors.getJSONObject(groupPosition).getJSONArray(TAG_PLAYER);


            JSONObject tmp;
            for(int j=0;j<jsonArrayPlayer.length();j++)
            {
                if((tmp=jsonArrayPlayer.getJSONObject(j)).getString(TAG_NAME).equals(TAG_MESSI))
                {
                    jsonObjectMessi=tmp;
                }
                else if ((tmp=jsonArrayPlayer.getJSONObject(j)).getString(TAG_NAME).equals(TAG_RONALDO)) {
                    jsonObjectRonaldo = tmp;
                }
            }


            jsonArrayTrophy = jsonObjectMessi.getJSONArray(TAG_TROPHY);
            int trophyCount = jsonArrayTrophy.length();
            hideAllTrophyImg(1);
            for (int i = 0; i < trophyCount; i++) {
                showTrophy(i, jsonArrayTrophy.getString(i), 0);
            }
            jsonArrayTrophy = jsonObjectRonaldo.getJSONArray(TAG_TROPHY);
            trophyCount = jsonArrayTrophy.length();
            hideAllTrophyImg(1);
            for (int i = 0; i < trophyCount; i++) {
                showTrophy(i, jsonArrayTrophy.getString(i), 1);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }

    private void showTrophy(int trophyPosition, final String trophyName, int playerPosition) {
         ImageView tmpImage=null;
         TextView tmpTrophyName=null;
        switch (trophyPosition)
        {
            case 0:
                tmpImage=(playerPosition==0)?imgVTrophy1:imgVTrophy21;
                tmpTrophyName=(playerPosition==0)?tvTrophy1:tvTrophy21;
                break;
            case 1:
                tmpImage=(playerPosition==0)?imgVTrophy2:imgVTrophy22;
                tmpTrophyName=(playerPosition==0)?tvTrophy2:tvTrophy22;
                break;
            case 2:
                tmpImage=(playerPosition==0)?imgVTrophy3:imgVTrophy23;
                tmpTrophyName=(playerPosition==0)?tvTrophy3:tvTrophy23;
                break;
            case 3:
                tmpImage=(playerPosition==0)?imgVTrophy4:imgVTrophy24;
                tmpTrophyName=(playerPosition==0)?tvTrophy4:tvTrophy24;
                break;
            case 4:
                tmpImage=(playerPosition==0)?imgVTrophy5:imgVTrophy25;
                tmpTrophyName=(playerPosition==0)?tvTrophy5:tvTrophy25;
                break;
            case 5:
                tmpImage=(playerPosition==0)?imgVTrophy6:imgVTrophy26;
                tmpTrophyName=(playerPosition==0)?tvTrophy6:tvTrophy26;
                break;
        }

        int trophyImgId=0;
        switch (trophyName)
        {
            case "La Liga": trophyImgId=R.drawable.laliga;break;
            case "Copa Del Rey": trophyImgId=R.drawable.copadelrey;break;
            case "Champions League": trophyImgId=R.drawable.champsleague;break;
            case "European Golden Shoe": trophyImgId=R.drawable.goldenboot;break;
            case "Club World Cup": trophyImgId=R.drawable.clubworldcup;break;
            case "UEFA Super Cup": trophyImgId=R.drawable.eurosupercup;break;
            case "Ballon d'Or": trophyImgId=R.drawable.ballondor;break;
            case "Spanish Super Cup": trophyImgId=R.drawable.spanishsupercup;break;

        }
        tmpTrophyName.setText(trophyName);
        tmpTrophyName.setVisibility(View.VISIBLE);

        tmpImage.setImageResource(trophyImgId);
        tmpImage.setVisibility(View.VISIBLE);
        /*tmpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllTrophyName();
                tmpTrophyName.setText(trophyName);
                tmpTrophyName.setVisibility(View.VISIBLE);
            }
        });*/

    }

    private void hideAllTrophyName(int playerIndex) {
        if(playerIndex==0) // for messi
        {
            tvTrophy1.setVisibility(View.GONE);
            tvTrophy2.setVisibility(View.GONE);
            tvTrophy3.setVisibility(View.GONE);
            tvTrophy4.setVisibility(View.GONE);
            tvTrophy5.setVisibility(View.GONE);
            tvTrophy6.setVisibility(View.GONE);
        }
        else
        {
            tvTrophy21.setVisibility(View.GONE);
            tvTrophy22.setVisibility(View.GONE);
            tvTrophy23.setVisibility(View.GONE);
            tvTrophy24.setVisibility(View.GONE);
            tvTrophy25.setVisibility(View.GONE);
            tvTrophy26.setVisibility(View.GONE);
        }
    }

    private void hideAllTrophyImg(int playerIndex) {
        hideAllTrophyName(playerIndex);
        if(playerIndex==0)
        {
            imgVTrophy1.setVisibility(View.GONE);
            imgVTrophy2.setVisibility(View.GONE);
            imgVTrophy3.setVisibility(View.GONE);
            imgVTrophy4.setVisibility(View.GONE);
            imgVTrophy5.setVisibility(View.GONE);
            imgVTrophy6.setVisibility(View.GONE);
        }
        else
        {
            imgVTrophy21.setVisibility(View.GONE);
            imgVTrophy22.setVisibility(View.GONE);
            imgVTrophy23.setVisibility(View.GONE);
            imgVTrophy24.setVisibility(View.GONE);
            imgVTrophy25.setVisibility(View.GONE);
            imgVTrophy26.setVisibility(View.GONE);
        }
    }


    private void init(View convertView) {
        imgVPlayer1Photo= (ImageView) convertView.findViewById(R.id.imgVPlayer1Img);
        imgVPlayer2Photo= (ImageView) convertView.findViewById(R.id.imgVPlayer2Img);
        tvPlayer1Name = (TextView) convertView.findViewById(R.id.tvPlayer1Name);
        tvPlayer2Name = (TextView) convertView.findViewById(R.id.tvPlayer2Name);

        imgVTrophy1 = (ImageView) convertView.findViewById(R.id.imgVtrophy11);
        imgVTrophy2 = (ImageView) convertView.findViewById(R.id.imgVtrophy12);
        imgVTrophy3 = (ImageView) convertView.findViewById(R.id.imgVtrophy13);
        imgVTrophy4 = (ImageView) convertView.findViewById(R.id.imgVtrophy14);
        imgVTrophy5 = (ImageView) convertView.findViewById(R.id.imgVtrophy15);
        imgVTrophy6 = (ImageView) convertView.findViewById(R.id.imgVtrophy16);

        tvTrophy1 = (TextView) convertView.findViewById(R.id.tvTrophyName11);
        tvTrophy2 = (TextView) convertView.findViewById(R.id.tvTrophyName12);
        tvTrophy3 = (TextView) convertView.findViewById(R.id.tvTrophyName13);
        tvTrophy4 = (TextView) convertView.findViewById(R.id.tvTrophyName14);
        tvTrophy5 = (TextView) convertView.findViewById(R.id.tvTrophyName15);
        tvTrophy6 = (TextView) convertView.findViewById(R.id.tvTrophyName16);


        imgVTrophy21 = (ImageView) convertView.findViewById(R.id.imgVtrophy21);
        imgVTrophy22 = (ImageView) convertView.findViewById(R.id.imgVtrophy22);
        imgVTrophy23 = (ImageView) convertView.findViewById(R.id.imgVtrophy23);
        imgVTrophy24 = (ImageView) convertView.findViewById(R.id.imgVtrophy24);
        imgVTrophy25 = (ImageView) convertView.findViewById(R.id.imgVtrophy25);
        imgVTrophy26 = (ImageView) convertView.findViewById(R.id.imgVtrophy26);

        tvTrophy21 = (TextView) convertView.findViewById(R.id.tvTrophyName21);
        tvTrophy22 = (TextView) convertView.findViewById(R.id.tvTrophyName22);
        tvTrophy23 = (TextView) convertView.findViewById(R.id.tvTrophyName23);
        tvTrophy24 = (TextView) convertView.findViewById(R.id.tvTrophyName24);
        tvTrophy25 = (TextView) convertView.findViewById(R.id.tvTrophyName25);
        tvTrophy26 = (TextView) convertView.findViewById(R.id.tvTrophyName26);

    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


}
