package app.sunil.swipeview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by dell on 6/8/2015.
 */
public class FragmentB extends Fragment implements View.OnClickListener{
    Button btnScan=null;

    WifiManager wifi;
    ListView lv;
    TextView textStatus;
    TextView scanResult;
    int size = 0;
   
    List<ScanResult> results;
    String ITEM_KEY = "key";
    ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=null;
        try {
            view = inflater.inflate(R.layout.fragment_a, container, false);
            btnScan = (Button) view.findViewById(R.id.btnScan);

            scanResult();
        }catch (Exception er){
            Toast.makeText(getActivity().getApplicationContext(), er.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return view;

    }

    private void scanResult() {
      /*  WifiManager wifiMan=null;
        WifiInfo wifiInf=null;
        try{
            wifiMan=(WifiManager)getActivity().getSystemService (Context.WIFI_SERVICE);
            wifiInf=wifiMan.getConnectionInfo();
        }
        catch (Exception er){
            Toast.makeText(getActivity().getApplicationContext(), er.getMessage(), Toast.LENGTH_SHORT).show();

        }
        if(wifiMan != null){
            Adapter adapter=(Adapter) wifiMan.getScanResults();
            listViewScanResult.setAdapter(adapter);
        }
        */

        textStatus = (TextView) getActivity().findViewById(R.id.textStatus);
        btnScan = (Button) getActivity().findViewById(R.id.btnScan);
        btnScan.setOnClickListener(this);
        lv = (ListView)getActivity().findViewById(R.id.listViewScanResult);

        wifi = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled() == false)
        {
            Toast.makeText(getActivity().getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }
       // this.adapter = new SimpleAdapter( getActivity(), arraylist, R.layout.row, new String[] {, "SSID","BSSID" }, new int[] { R.id.ssid , R.id.bssid} );
        MyAdapterForViewList adapter=new MyAdapterForViewList(new HashMap<String, String>());
        lv.setAdapter(this.adapter);

        getActivity().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                results = wifi.getScanResults();
                size = results.size();
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    public void onClick(View view)
    {
        arraylist.clear();
        wifi.startScan();

        Toast.makeText(getActivity().getApplicationContext(), "Scanning...." + size, Toast.LENGTH_SHORT).show();
        try
        {
            size = size - 1;
            while (size >= 0)
            {
                HashMap<String, String> item = new HashMap<String, String>();
                item.put(ITEM_KEY, results.get(size).SSID + "  " + results.get(size).capabilities);

                arraylist.add(item);
                size--;
                adapter.notifyDataSetChanged();
            }
        }
        catch (Exception e)
        { }
    }


}
