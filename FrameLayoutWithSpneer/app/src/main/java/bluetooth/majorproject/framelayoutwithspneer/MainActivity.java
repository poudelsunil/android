package bluetooth.majorproject.framelayoutwithspneer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {

    LinearLayout ll1,ll2;
    Spinner spinner;
    String data[]={"A","B","C"};
    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(String tmp:data)
            adapter.add(tmp);

        spinner=(Spinner)findViewById(R.id.spineer);
        spinner.setAdapter(adapter);

    }



}
