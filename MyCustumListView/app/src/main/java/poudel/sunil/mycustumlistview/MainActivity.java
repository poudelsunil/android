package poudel.sunil.mycustumlistview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.myListView);
        listView.setAdapter(new MyCustumAdapter(getApplicationContext()));

     //   listView.setOnItemLongClickListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // TextView textView= (TextView) view.findViewById(R.id.myText);
       // textView.setText(textView.getText()+" ->");
        Toast.makeText(this, "ItemClicked", Toast.LENGTH_SHORT).show();
    }
}
