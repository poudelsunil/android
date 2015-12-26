package poudel.sunil.explicitintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button lunchMap,lunchStore,sendMail,gotoNextActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        lunchMap.setOnClickListener(this);
        lunchStore.setOnClickListener(this);
        sendMail.setOnClickListener(this);
        gotoNextActivity.setOnClickListener(this);

    }

    private void init() {
        lunchMap=(Button)findViewById(R.id.btnLunchMap);
        lunchStore=(Button)findViewById(R.id.btnLunchStore);
        sendMail=(Button)findViewById(R.id.btnSendEmail);
        gotoNextActivity=(Button)findViewById(R.id.btnNextActivity);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null, chooser=null;
        if(v.getId()==R.id.btnLunchMap){
        /*    intent=new Intent(android.content.Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:20,030,7 2,8898"));
            chooser=Intent.createChooser(intent,"Lunch Map");
            startActivity(chooser);
        */
            Intent a=getIntent();
            Log.d("a", a.toString());
            Toast toast=Toast.makeText(this,a.toString(),Toast.LENGTH_LONG);
            toast.show();

        }
        if(v.getId()==R.id.btnLunchStore){
            intent=new Intent(android.content.Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=dolphin.developer.com"));
            chooser=Intent.createChooser(intent,"Lunch Play Store");
            startActivity(chooser);
        }
        if(v.getId()==R.id.btnSendEmail){
            intent=new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to={"sunil15poudel@gmail.com","sunil43_poudel@hotmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hi");
            intent.putExtra(Intent.EXTRA_TEXT,"Hi this is first email from android program");
            intent.setType("message/rfc822");
            chooser=Intent.createChooser(intent,"Send Email");
            startActivity(chooser);
        }
        if(v.getId()==R.id.btnNextActivity){
            intent=new Intent(this,SecondActivity.class);

            EditText etUserId=(EditText)findViewById(R.id.etUserId);
            EditText etPassword=(EditText)findViewById(R.id.etPassword);

           // intent.putExtra("name", "sunil");
            //intent.putExtra("pou", "poudel");

            Bundle bundle=new Bundle();
            bundle.putString("name",etUserId.getText().toString()+"");
            bundle.putString("pou",etPassword.getText().toString()+"");

            intent.putExtra("myInfo",bundle);


            startActivity(intent);
        }


    }
}
