package ni.com.debugon.notificationinfo.ni.com.debugon.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ni.com.debugon.notificationinfo.ni.com.debugon.Utils.ListView;
import ni.com.debugon.notificationinfo.R;

/**
 * Created by luisr on 18/03/2017.
 */

public class SecondListFragment extends Fragment {
    private android.widget.ListView saveListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.content_what_you_need, container, false);

        saveListView = (android.widget.ListView) rootView.findViewById(R.id.listview);
        saveListView.setAdapter(null);

        populateList(container);
        return rootView;
    }

    private void populateList(ViewGroup container) {
        saveListView.setAdapter(
                new ListView(
                        container.getContext(),
                        new String[] { "data3", "data4"},
                        new String[] { "content3", "content4"}
                )
        );
    }
}
