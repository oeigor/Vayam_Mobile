package com.objectedge.store.ui.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.ModelObserver;
import com.objectedge.store.data.manager.ProductsManager;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.ui.adapters.BannerFeedAdapter;


public class MainFragment extends Fragment implements ModelObserver {

    public static final String TAG = "MainFragment";

    private RecyclerView bannerFeedDeals;
    private RecyclerView bannerFeedNew;
    private RecyclerView bannerFeedRecommended;

    private BannerFeedAdapter dealsAdapter;
    private BannerFeedAdapter newAdapter;
    private BannerFeedAdapter recommendedAdapter;

    private OnFragmentInteractionListener listener;

    ProductsManager productsManager;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productsManager = ((StoreApplication) getActivity().getApplication()).getProductsManager();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_layout, container, false);
        initViews(view);
        return view;
    }


    private void initViews(View view) {
        bannerFeedDeals = (RecyclerView) view.findViewById(R.id.banner_deals);
        bannerFeedNew = (RecyclerView) view.findViewById(R.id.banner_new);
        bannerFeedRecommended = (RecyclerView) view.findViewById(R.id.banner_recommended);
        LinearLayoutManager dealsManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager newManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager recommendedManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                false);
        bannerFeedDeals.setLayoutManager(dealsManager);
        bannerFeedNew.setLayoutManager(newManager);
        bannerFeedRecommended.setLayoutManager(recommendedManager);

    }


    @Override
    public void onResume() {
        super.onResume();
        //if(productsManager.getProductModelList().isEmpty()) {
        productsManager.loadProducts();
        //}
    }


    @Override
    public void onStart() {
        super.onStart();
        productsManager.addObserver(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        productsManager.removeObserver(this);
    }


    @Override
    public void modelChanged(String message, String error) {
        int size = productsManager.getProductModelList().size();
        if (size > 0) {
            dealsAdapter = new BannerFeedAdapter(productsManager.getProductModelList().subList(1, size / 3), onItemClick);
            newAdapter = new BannerFeedAdapter(productsManager.getProductModelList().subList(size / 3 + 1, 2 * size / 3), onItemClick);
            recommendedAdapter = new BannerFeedAdapter(productsManager.getProductModelList().subList(2 * size / 3 + 1, size - 1),
                    onItemClick);
            bannerFeedDeals.setAdapter(dealsAdapter);
            bannerFeedNew.setAdapter(newAdapter);
            bannerFeedRecommended.setAdapter(recommendedAdapter);
        }
    }


    private View.OnClickListener onItemClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onProductFeedItemClick((ProductModel) v.getTag());
            }
        }
    };
    /*
    private View.OnClickListener onCarouselItemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onCarouselItemClick((String)v.getTag());
            }
        }
    };

    private void loadDataToShowMore(){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        String[] items = getResources().getStringArray(R.array.main_screen_items);
        String[] fakeItemsUrls = getResources().getStringArray(R.array.fake_main_screen_items_urls);
        String[] subItems = getResources().getStringArray(R.array.main_screen_subitems);
        String[] fakeSubItemsUrls = getResources().getStringArray(R.array.fake_main_screen_subitems_urls);
        for (int i = 0; i < items.length; i++){
            View view = inflater.inflate(R.layout.item_services_list, null);
            TextView textView = (TextView) view.findViewById(R.id.services_item_text);
            textView.setText(items[i]);
            if(i == 0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, ImageUtils.getDrawable(getActivity(), R.drawable.plus), null);
                textView.setOnClickListener(onExpand);
                showMoreLayout.addView(view);
                subMenuLayout = (LinearLayout)inflater.inflate(R.layout.linear_layout, null);
                for(int k = 0; k < items.length; k++){
                    View subView = inflater.inflate(R.layout.sub_item_service_list, null);
                    TextView textSubView = (TextView) subView.findViewById(R.id.services_sub_item_text);
                    textSubView.setText(subItems[k]);
                    textSubView.setTag(fakeSubItemsUrls[k]);
                    textSubView.setOnClickListener(onClick);
                    subMenuLayout.addView(subView);
                }
                subMenuLayout.setVisibility(View.GONE);
                showMoreLayout.addView(subMenuLayout);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, ImageUtils.getDrawable(getActivity(), R.drawable.arrow), null);
                textView.setTag(fakeItemsUrls[i - 1]);
                textView.setOnClickListener(onClick);
                showMoreLayout.addView(view);
            }
        }

    }

    private void scrollToEnd(){
        mainFragmentScroll.post(new Runnable() {
            @Override
            public void run() {
                mainFragmentScroll.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    private View.OnClickListener onShow = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(moreButton.getText().equals(getString(R.string.more))){
                moreButton.setText(getString(R.string.less));
                showMoreLayout.setVisibility(View.VISIBLE);
                scrollToEnd();
            } else {
                moreButton.setText(getString(R.string.more));
                showMoreLayout.setVisibility(View.GONE);
            }
        }
    };

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onSocialNetworksButtonClick((String) v.getTag());
            }
        }
    };

    private View.OnClickListener onExpand = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(subMenuLayout.getVisibility() == View.VISIBLE){
                subMenuLayout.setVisibility(View.GONE);
                ((TextView)v).setCompoundDrawablesWithIntrinsicBounds(null, null, ImageUtils.getDrawable(getActivity(), R.drawable.plus), null);
            } else {
                subMenuLayout.setVisibility(View.VISIBLE);
                ((TextView)v).setCompoundDrawablesWithIntrinsicBounds(null,null,ImageUtils.getDrawable(getActivity(),R.drawable.minus),null);
                scrollToEnd();
            }
        }
    };
    */


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
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

        //public void onSocialNetworksButtonClick(String url);
        public void onProductFeedItemClick(ProductModel productModel);
        //public void onCarouselItemClick(String categoryId);
    }

}
