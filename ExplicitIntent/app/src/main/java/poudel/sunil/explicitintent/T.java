package poudel.sunil.explicitintent;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sunil on 6/22/15.
 */
public class T {
    public static void Show(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
