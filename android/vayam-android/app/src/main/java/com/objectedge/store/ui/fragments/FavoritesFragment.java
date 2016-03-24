package com.objectedge.store.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.FavoritesManager;
import com.objectedge.store.data.manager.ModelObserver;
import com.objectedge.store.ui.adapters.FavoritesGridAdapter;
import com.objectedge.store.ui.views.FavoritesItemView;
import com.objectedge.store.utils.LayoutUtils;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class FavoritesFragment extends Fragment {

    public static final String TAG = "FavoritesFragment";

    private GridViewWithHeaderAndFooter gridView;
    private LinearLayout noItemsLayout;
    private Button continueShoppingBtn;

    private FavoritesGridAdapter gridAdapter;

    private FavoritesManager favoritesManager;


    private OnFragmentInteractionListener listener;

    public static FavoritesFragment newInstance(){
        return new FavoritesFragment();
    }

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        favoritesManager = ((StoreApplication)getActivity().getApplication()).getFavoritesManager();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        //init grid
        gridView = (GridViewWithHeaderAndFooter)view.findViewById(R.id.favorites_grid);
        gridView.setNumColumns(LayoutUtils.getFavoritesGridColumnCount(getActivity()));
        initInvisibleFooter();
        gridAdapter = new FavoritesGridAdapter(getActivity(), favoritesManager.getFavorites());
        gridView.setAdapter(gridAdapter);
        noItemsLayout = (LinearLayout)view.findViewById(R.id.favorites_no_items_layout);
        continueShoppingBtn = (Button)view.findViewById(R.id.favorites_continue_shopping);
        continueShoppingBtn.setOnClickListener(onContinueShoppingBtnClick);
        return view;
    }

    private void checkIfThereAreSomeItems(){
        if(favoritesManager.getFavorites().size() == 0){
            gridView.setVisibility(View.GONE);
            noItemsLayout.setVisibility(View.VISIBLE);
        } else {
            gridView.setVisibility(View.VISIBLE);
            noItemsLayout.setVisibility(View.GONE);
        }
    }

    private void initInvisibleFooter(){
        //fake view
        //This view is necessary to up progress bar of last item in grid view
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.list_view_progress_footer, null);
        gridView.addFooterView(view);
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        favoritesManager.addObserver(modelObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        //check if there are any items
        checkIfThereAreSomeItems();
    }

    @Override
    public void onStop() {
        super.onStop();
        favoritesManager.removeObserver(modelObserver);
    }

    private ModelObserver modelObserver = new ModelObserver() {
        @Override
        public void modelChanged(String message, String error) {
            gridAdapter.notifyDataSetChanged();
            checkIfThereAreSomeItems();
        }
    };

    private View.OnClickListener onContinueShoppingBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onContinueShopping();
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onContinueShopping();
    }
}
