package ni.com.debugon.notificationinfo.ni.com.debugon.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import ni.com.debugon.notificationinfo.ni.com.debugon.Utils.ListView;
import ni.com.debugon.notificationinfo.R;

/**
 * Created by luisr on 18/03/2017.
 */

public class FirstListFragment extends Fragment {
    private android.widget.ListView saveListView;
    private static String TAG = "Clique Listview";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.content_what_you_need, container, false);

        saveListView = (android.widget.ListView) rootView.findViewById(R.id.listview);
        saveListView.setAdapter(null);

        populateList(container);

        saveListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                Log.i(TAG, "Item da lista clicado!");

            }
        });

        return rootView;
    }

    private void populateList(ViewGroup container) {
        saveListView.setAdapter(
                new ListView(
                        container.getContext(),
                        new String[] { "data1", "data2"},
                        new String[] { "content1", "content2"}
                )
        );
    }
}
