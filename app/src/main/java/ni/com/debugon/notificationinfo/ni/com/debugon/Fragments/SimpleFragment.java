package ni.com.debugon.notificationinfo.ni.com.debugon.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ni.com.debugon.notificationinfo.ni.com.debugon.Intents.CreateKeyword;
import ni.com.debugon.notificationinfo.R;

/**
 * Created by luisr on 18/03/2017.
 */

public class SimpleFragment extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.content_layout, container, false);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        fab.setOnClickListener(this);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CreateKeyword.class));
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                Snackbar.make(view, "Ainda não é possível adicionar novas consultas", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });*/

        return rootView;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),CreateKeyword.class));
        //getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }
}
