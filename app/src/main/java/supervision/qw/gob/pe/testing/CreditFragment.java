package supervision.qw.gob.pe.testing;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import supervision.qw.gob.pe.testing.listeners.OnTabNavListener;


public class CreditFragment extends Fragment implements OnTabNavListener {
    private OnTabNavListener mListener;

    public CreditFragment() {
        // Required empty public constructor
    }

    public static CreditFragment newInstance(Bundle args) {
        CreditFragment fragment = new CreditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credit, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTabNavListener) {
            mListener = (OnTabNavListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTabNavListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
