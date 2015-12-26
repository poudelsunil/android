package app.sunil.swipeview;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class FragmentA extends Fragment {
    Button btnWifiInfo=null;
    ListView listViewWifiInfo=null;
    HashMap<String,String> hashMap=new HashMap<String,String>();

    WifiManager wifiMan=null;
    WifiInfo wifiInf=null;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_a, container, false);
            listViewWifiInfo=(ListView)view.findViewById(R.id.listViewWifiInfo);
            btnWifiInfo = (Button)view.findViewById(R.id.btnWifiInfo);
            btnWifiInfo.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    try{
                        if(wifiMan!=null) {
                            if (wifiMan.isWifiEnabled() == false) {
                                Toast.makeText(getActivity().getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
                                wifiMan.setWifiEnabled(true);
                            }
                            else {
                                Toast.makeText(getActivity().getApplicationContext(), "wifi is enabled", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }catch (Exception er){
                        Toast.makeText(getActivity().getApplicationContext(), er.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    showWifiInfo();

                }

                private void showWifiInfo() {
                    HashMap<String,String> hashMap=new HashMap<String,String>();
                    try{
                        wifiMan=(WifiManager)getActivity().getSystemService (Context.WIFI_SERVICE);
                        if(wifiMan!=null)
                            wifiInf=wifiMan.getConnectionInfo();
                    }
                    catch (Exception er){
                        Toast.makeText(getActivity().getApplicationContext(), er.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    if(wifiInf!=null) {
                        hashMap.put("SSID", wifiInf.getSSID().toString());
                        hashMap.put("BSSID", wifiInf.getBSSID().toString());
                        hashMap.put("MAC", wifiInf.getMacAddress().toString());
                        hashMap.put("Supplicant state", wifiInf.getSupplicantState().toString());
                        hashMap.put("RSSI", wifiInf.getRssi() + "");
                        hashMap.put("Link Speed", wifiInf.getLinkSpeed() + "");
                        //  hashMap.put("Frequancy",wifiInf.getFrequency()+""); //only for api level higher then 21
                        hashMap.put("Net ID", wifiInf.getNetworkId() + "");
                        hashMap.put("IP", wifiInf.getIpAddress() + "");

                        MyAdapterForViewList adapter = new MyAdapterForViewList(hashMap);
                        listViewWifiInfo.setAdapter(adapter);
// ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

                    }

                }

            });
            return view;
        }

}
