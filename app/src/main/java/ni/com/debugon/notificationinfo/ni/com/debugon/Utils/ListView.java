package ni.com.debugon.notificationinfo.ni.com.debugon.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ni.com.debugon.notificationinfo.R;

/**
 * Created by luisr on 09/03/2017.

 ATENÇÃO: TENTAR UTILIZAR O LISTVIEW WATER
 https://android-arsenal.com/details/1/2078#!description

 */
public class ListView extends BaseAdapter {
    Context context;

    String[] data;
    String[] content;

    private static LayoutInflater inflater = null;

    public ListView(Context context, String[] data, String[] content) {
        // TODO Auto-generated constructor stub
        this.context = context;

        this.data = data;
        this.content = content;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row_listview, null);

        TextView header = (TextView) vi.findViewById(R.id.header);
        TextView text = (TextView) vi.findViewById(R.id.text);

        header.setText(content[position]);
        text.setText(data[position]);

        return vi;
    }
}
