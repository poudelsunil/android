package poudel.sunil.explicitintent;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by sunil on 6/22/15.
 */
public class FireMissilesDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You Want to see your password ??")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        T.Show(getActivity(),"you pressed Ok");
                        SecondActivity.flagToHide=false;
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        T.Show(getActivity(),"you pressed Cancel");
                        SecondActivity.flagToHide=true;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


}