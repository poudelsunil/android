package bluetooth.majorproject.navigationdrawer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sunil on 8/8/15.
 */
public class ReadFile {
    public static String readFromAsset(int file, Context context) {
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
