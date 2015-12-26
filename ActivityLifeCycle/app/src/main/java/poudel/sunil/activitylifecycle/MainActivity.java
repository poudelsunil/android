package poudel.sunil.activitylifecycle;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("counter :" ,"onCreate Counter :"+counter);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("counter :", "onResume Counter :" + counter);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Counter", counter);
        Log.d("counter ","onSave Counter : "+counter+" Stored ");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter=savedInstanceState.getInt("Counter");
        Log.d("counter","onRestore Counter :"+counter+" Restored ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("counter :", "onPause Counter :" + counter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("counter :", "onStop Counter :" + counter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("counter :", "onStart Counter :" + counter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("counter :", "onDestroy Counter :" + counter);
    }

    public void process(View view){
        Intent intent=new Intent(this,NextActivity.class);
        FireMissilesDialogFragment dialogFragment=new FireMissilesDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"Hya ");
        //startActivity(intent);

    }
}
