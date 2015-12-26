package poudel.sunil.explicitintent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SecondActivity extends ActionBarActivity  implements View.OnClickListener{

    static boolean flagToHide=true;
    LinearLayout ll;
    TextView tvUserId,tvPassword;
    Button btn;
    Bundle myInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        ll=new LinearLayout(this);
        tvUserId=new TextView(this);
        tvPassword=new TextView(this);
        btn=new Button(this);

        LinearLayout.LayoutParams linearDimension = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(linearDimension);

        LinearLayout.LayoutParams dimension = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvUserId.setLayoutParams(dimension);
        tvPassword.setLayoutParams(dimension);
        btn.setLayoutParams(dimension);

        ll.setOrientation(LinearLayout.VERTICAL);
        //tv.setText("this is text view ");
        btn.setText("Click Here");
        tvUserId.setHint("Your User Id : ");
        tvPassword.setHint("Your Password : ");

        ll.addView(tvUserId);
        ll.addView(tvPassword);
        ll.addView(btn);

        btn.setOnClickListener(this);

      //  setContentView(R.layout.activity_second);

        setContentView(ll);
        //String[] values=getIntent().getStringArrayExtra("myInfo");//getIntent().getStringArrayExtra("myInfo");


        //textView=(TextView)findViewById(R.id.tvResult);
       // textView.setText("Name  "+ getIntent().getStringExtra("name")+"\n"+"pou " + getIntent().getStringExtra("pou"));


    }

    @Override
    public void onClick(View v) {
        myInfo=getIntent().getBundleExtra("myInfo");
        //T.Show(this,"Data Recived from another activity");
        tvUserId.setText("User Id :" + myInfo.get("name"));
        if(flagToHide==true){
            tvPassword.setText("Password : "+"******");
        }
        else {
            tvPassword.setText("Password : "+myInfo.get("pou"));
        }
        FireMissilesDialogFragment dialogFragment= new FireMissilesDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"hi");



    }
}
