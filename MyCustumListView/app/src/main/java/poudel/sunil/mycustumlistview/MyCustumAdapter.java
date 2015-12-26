package poudel.sunil.mycustumlistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sunil on 6/24/15.
 */
public class MyCustumAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;

    ArrayList<MyDataItem> list;
    String myListText[];

    public MyCustumAdapter(Context context) {
        this.context = context;
        Resources resources= context.getResources();
        myListText=resources.getStringArray(R.array.myListData);
        int img[]={
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher};
        list=new ArrayList<MyDataItem>();
        for(int i=0;i<myListText.length;i++){
            list.add(new MyDataItem(myListText[i],img[i]));
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.my_item_layout, parent, false);

        TextView textView= (TextView) view.findViewById(R.id.myText);
        ImageView imageView=(ImageView) view.findViewById(R.id.myImage);
        Button button=(Button)view.findViewById(R.id.myBtn);

        textView.setText(list.get(position).getText().toString());
        imageView.setImageResource(list.get(position).getNum());

        button.setText("  " + list.get(position).getText().charAt(0) + "  ");

        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
       /* Button button= (Button) v.findViewById(R.id.myBtn);
            Toast.makeText(context,button.getText()+" Was Clicked",Toast.LENGTH_SHORT).show();
            if(button.getSolidColor()!=Color.GREEN){
                button.setTextColor(Color.GREEN);
            }else {
                button.setTextColor(Color.RED);
            }

        */
        }
}
