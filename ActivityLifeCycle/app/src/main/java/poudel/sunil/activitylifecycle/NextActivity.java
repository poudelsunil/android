package poudel.sunil.activitylifecycle;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class NextActivity extends ActionBarActivity {

    ListView lv=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lv=(ListView)findViewById(R.id.listView);
        String [] saharHaru={"Pokhara","Kathamandu","Lalitpur","Nepal"};
        ArrayAdapter <String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,saharHaru);

        lv.setAdapter(adapter);

        setContentView(R.layout.activity_next);
    }



}
