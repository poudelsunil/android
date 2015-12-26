package bluetooth.majorproject.navigationdrawer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sunil on 8/7/15.
 */
public class MainStatsJsonOperation {
    public static final String TAG_STATS="stats";
    public static final String TAG_TITLE="title";
    public static final String TAG_TITLE_LA_LIGA="la_liga";
    public static final String TAG_TITLE_CHAMPINONS_LEAGUE="champinons_league";
    public static final String TAG_TITLE_OTHER_CUPS="other_cups";

    Context context;

    public MainStatsJsonOperation(Context context) {
        this.context = context;
    }

    public JSONObject getJsonObjByMainStatsIndex(int index)
    {
        JSONObject tmpObj=null;
        JSONObject jsonObjectLaLiga=null,jsonObjectChampinonsLeague=null,jsonObjectOtherCups=null;

        JSONObject jsonObjectMain=null,jsonObjectPlayerInfo=null;
        JSONArray jsonArrayStats=null,jsonArrayDetails,jsonArrayPlayer;


        String tmp=readFromAsset(R.raw.mainstats,context);

        try {
            jsonObjectMain=new JSONObject(tmp);
        } catch (JSONException e) {
            Log.e("json", "JSON object parshing exception : " + e.getMessage());
        }
        if(jsonObjectMain != null){
            try {
                String tmpTitle,tmpPlayerName;
                long tmpYear,tmpMinutesPalyed;
                int tmpGoal,tmpPenalties,tmpHatTricks,tmpAssists,tmpApps,tmpMinutesForAGoal;
                double tmpGoalRatio;
                jsonArrayStats=jsonObjectMain.getJSONArray(TAG_STATS);


                for(int i=0;i<jsonArrayStats.length();i++){
                    tmpTitle=jsonArrayStats.getJSONObject(i).getString(TAG_TITLE);

                    //Toast.makeText(context,"Array size :" + jsonArrayStats.length()+"\nIndex : "+i+" Title : "+tmpTitle,Toast.LENGTH_LONG).show();

                    if(tmpTitle==TAG_TITLE_LA_LIGA || i==0)
                    {
                        jsonObjectLaLiga=jsonArrayStats.getJSONObject(i);
                    }
                    else if(tmpTitle==TAG_TITLE_CHAMPINONS_LEAGUE || i==1)
                    {
                        jsonObjectChampinonsLeague=jsonArrayStats.getJSONObject(i);
                    }
                    else if(tmpTitle==TAG_TITLE_OTHER_CUPS || i==2)
                    {
                        jsonObjectOtherCups=jsonArrayStats.getJSONObject(i);
                    }

                    /*
                    jsonArrayDetails=jsonArrayStats.getJSONObject(i).getJSONArray(TAG_DETAILS);
                    for(int j=0;j<jsonArrayDetails.length();j++) {

                        tmpYear = jsonArrayDetails.getJSONObject(j).getLong(TAG_YEAR);

                        jsonArrayPlayer = jsonArrayDetails.getJSONObject(j).getJSONArray(TAG_PLAYER);
                        for(int k=0;k<jsonArrayPlayer.length();k++)
                        {
                            tmpPlayerName=jsonArrayPlayer.getJSONObject(k).getString(TAG_NAME);
                            jsonObjectPlayerInfo=jsonArrayPlayer.getJSONObject(k).getJSONObject(TAG_INFO);

                            tmpGoal = jsonObjectPlayerInfo.getInt(TAG_GOALS);
                            tmpPenalties = jsonObjectPlayerInfo.getInt(TAG_PENALTIES);
                            tmpHatTricks = jsonObjectPlayerInfo.getInt(TAG_HAT_TRICKS);
                            tmpGoalRatio = jsonObjectPlayerInfo.getDouble(TAG_GOAL_RATIO);
                            tmpAssists = jsonObjectPlayerInfo.getInt(TAG_ASSISTS);
                            tmpApps = jsonObjectPlayerInfo.getInt(TAG_APPS);
                            tmpMinutesPalyed = jsonObjectPlayerInfo.getLong(TAG_MINUTES_PLAYED);
                            tmpMinutesForAGoal = jsonObjectPlayerInfo.getInt(TAG_MINUTES_FOR_A_GOAL);


                        }

                    }
                    */

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



            switch (index) {
                case 0:
                    tmpObj = jsonObjectLaLiga;
                    break;
                case 1:
                    tmpObj = jsonObjectChampinonsLeague;
                    break;
                case 2:
                    tmpObj = jsonObjectOtherCups;
                    break;

            }

        }
        if(tmpObj == null)
        {
            try {
                tmpObj = new JSONObject("{ \"a\":\"aa\"}");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return tmpObj;
    }

    public String readFromAsset(int file, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().openRawResource(file);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            //Log.d("json", "BufferedReader : "+input);
            String line = "";
            while ((line = input.readLine()) != null) {
                //Log.d("json", "Line : " + line);
                //Toast.makeText(context,"reading data ->"+line,Toast.LENGTH_LONG).show();
                returnString.append(line);
            }
        } catch (Exception e) {
            Log.e("json", " io Exception : " + e.getMessage());
            Toast.makeText(context, "error : " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                Log.e("json", " fileClose Exception : " + e2.getMessage());
            }
        }
        //Toast.makeText(context,"sending data "+returnString,Toast.LENGTH_LONG).show();
        return returnString.toString();
    }

}
