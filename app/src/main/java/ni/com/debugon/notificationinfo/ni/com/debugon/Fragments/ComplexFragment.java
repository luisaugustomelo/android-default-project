package ni.com.debugon.notificationinfo.ni.com.debugon.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ni.com.debugon.notificationinfo.R;

/**
 * Created by luisr on 18/03/2017.
 */

public class ComplexFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        return inflater.inflate(R.layout.recentes_activity, container, false);
    }
}
