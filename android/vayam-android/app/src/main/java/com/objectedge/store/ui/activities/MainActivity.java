package com.objectedge.store.ui.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.model.fake.CategoryModel;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.ui.adapters.LeftMenuAdapter;
import com.objectedge.store.ui.fragments.FavoritesFragment;
import com.objectedge.store.ui.fragments.MainFragment;
import com.objectedge.store.ui.fragments.OrderFragment;
import com.objectedge.store.ui.fragments.ProductFragment;
import com.objectedge.store.ui.views.ActionBarView;
import com.objectedge.store.ui.views.LeftMenuHeaderView;
import com.objectedge.store.ui.views.LeftMenuListView;

import java.util.List;
import java.util.Stack;


public class MainActivity extends BaseActivity
        implements ActionBarView.ClickListener, MainFragment.OnFragmentInteractionListener,
        FavoritesFragment.OnFragmentInteractionListener, OrderFragment.OnFragmentInteractionListener {

    /*
     ProductsGridFragment.OnFragmentInteractionListener,
     ProductFragment.OnFragmentInteractionListener,
     BrandsListFragment.OnFragmentInteractionListener,
     MyProfileFragment.OnFragmentInteractionListener,
    MyAddressBookFragment.OnFragmentInteractionListener,
    EditAddressGridFragment.OnFragmentInteractionListener,
    MyPaymentInfoFragment.OnFragmentInteractionListener,
    EditPaymentListFragment.OnFragmentInteractionListener{
    */
    public static int SEARCH_ACTIVITY_REQUEST = 1;

    public static String CLEAR_FRAGMENTS_STACK = "clear_fragments_stack";

    public static MainActivity instance;

    private DrawerLayout     drawerLayout;
    private Toolbar          toolBar;
    private LeftMenuListView leftMenuListView;
    private StoreApplication application;
    private ActionBarView    actionBarView;
    //result from SearchActivity
    private String           searchName;
    //stack for tags of fragments
    private Stack<String>    fragmentsStackTags;


    /**
     * Start this activity and wishListLoaderService if startWishListLoad is set to true
     *
     * @param context                   application or activity context
     * @param startWishListAndOrderLoad if true than we start load and wishList too.
     */
    public static void start(Context context, boolean startWishListAndOrderLoad) {
        if (startWishListAndOrderLoad) {
            ((StoreApplication) context.getApplicationContext()).startOrderLoaderService();
        }
        Intent startMainActivityIntent = new Intent(context, MainActivity.class);
        context.startActivity(startMainActivityIntent);
    }


    public static void startFromTheBegin(Context context, boolean clearFragmentStack) {
        ((StoreApplication) context.getApplicationContext()).startOrderLoaderService();
        Intent startMainActivityIntent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CLEAR_FRAGMENTS_STACK, clearFragmentStack);
        startMainActivityIntent.putExtras(bundle);
        context.startActivity(startMainActivityIntent);
    }


    public MainActivity() {
        instance = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentsStackTags = new Stack<>();
        application = (StoreApplication) getApplication();
        setContentView(R.layout.activity_main);
        findViews();
        setSupportActionBar(toolBar);
        setUpActionBar();
        initLeftMenu();
        if (savedInstanceState == null) {
            showContent(MainFragment.newInstance(), MainFragment.TAG, false);
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            if (bundle.getBoolean(CLEAR_FRAGMENTS_STACK, false)) {
                clearContent();
                showContent(MainFragment.newInstance(), MainFragment.TAG, false);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        application.getFavoritesManager().addObserver(actionBarView);
        application.getOrdersManager().addObserver(actionBarView);
    }


    @Override
    protected void onPause() {
        super.onPause();
        application.getFavoritesManager().removeObserver(actionBarView);
        application.getOrdersManager().removeObserver(actionBarView);
    }


    private ActionBarView getActionBarView() {
        if (actionBarView == null) {
            actionBarView = new ActionBarView(this);
        }
        return actionBarView;
    }


    private void findViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftMenuListView = (LeftMenuListView) findViewById(R.id.left_menu);
        toolBar = (Toolbar) findViewById(R.id.actionBar);
    }


    private void initLeftMenu() {
        //add items if it is real profile
        //if(application.getAccessManager().getAccessModel().isRealUser()){
        //    application.getCategoriesManager().addChildrenToMyAccount();
        //}
        LeftMenuAdapter adapter = new LeftMenuAdapter(this, 0, application.getCategoriesManager().getCategoriesList());
        leftMenuListView.setAdapter(adapter);
        leftMenuListView.setDepth(3);
        leftMenuListView.setOnItemClickListener(onItemClickListener);
        leftMenuListView.setOnHeaderClickListener(onHeaderListener);
    }


    protected void showContent(Fragment fragment, String tag, boolean addToBackStack) {
        if (fragmentsStackTags.empty() || !fragmentsStackTags.peek().equals(tag)) {
            fragmentsStackTags.push(tag);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (addToBackStack) {
                transaction.addToBackStack(null);
            }
            transaction.replace(R.id.frame_container, fragment, tag).commit();
        }
    }


    protected void clearContent() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }


    /*
    private void showUserInfoFragment(String tabToShow){
        if(!fragmentsStackTags.peek().equals(UserInfoFragment.TAG)) {
            showContent(UserInfoFragment.newInstance(tabToShow), UserInfoFragment.TAG, true);
        } else {
            ((UserInfoFragment)getSupportFragmentManager().findFragmentByTag(UserInfoFragment.TAG)).selectMode(tabToShow);
        }
    }
    */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CategoryModel categoryModel = (CategoryModel) parent.getItemAtPosition(position);
            if (!categoryModel.getChildrenWithCount().isEmpty()) {
                LeftMenuAdapter adapter = new LeftMenuAdapter(MainActivity.this, 0,
                        categoryModel.getChildrenWithCount());
                boolean isHeaderAdded = leftMenuListView
                        .showNextHeader(categoryModel.getCategory_name(), (List<CategoryModel>) view.getTag(), adapter);
                if (!isHeaderAdded) {
                    Toast.makeText(MainActivity.this, "Will be implemented in the next version.", Toast.LENGTH_SHORT)
                            .show();
                    //drawerLayout.closeDrawer(GravityCompat.START);
                    //showContent(ProductsGridFragment.newInstance(categoryModel.getCategory_id(),categoryModel.getCategory_name()),
                    //        ProductsGridFragment.TAG + categoryModel.getCategory_id(), true);
                }
            } else {
                drawerLayout.closeDrawer(GravityCompat.START);
                if (application.getCategoriesManager().isBrand(categoryModel)) {
                    Toast.makeText(MainActivity.this, "Will be implemented in the next version.", Toast.LENGTH_SHORT)
                            .show();
                    //showContent(BrandsListFragment.newInstance(), BrandsListFragment.TAG, true);
                } else
                    if (application.getCategoriesManager().isFavorites(categoryModel)) {
                        showContent(FavoritesFragment.newInstance(), FavoritesFragment.TAG, true);
                    } else
                        if (application.getCategoriesManager().isFeedback(categoryModel)) {
                            Toast.makeText(MainActivity.this, "Will be implemented in the next version.",
                                    Toast.LENGTH_SHORT).show();
                            //showContent(WebViewFragment.newInstance(Urls.FAKE_FEEDBACK_URL), WebViewFragment.TAG, true);
                        } else
                            if (application.getCategoriesManager().isMyAccount(categoryModel)) {
                                Toast.makeText(MainActivity.this, "Will be implemented in the next version.",
                                        Toast.LENGTH_SHORT).show();
                                //LoginServerActivity.start(MainActivity.this, LoginServerActivity.QUIT_AFTER);
                            } else
                                if (application.getCategoriesManager().isHelp(categoryModel)) {
                                    Toast.makeText(MainActivity.this, "Will be implemented in the next version.",
                                            Toast.LENGTH_SHORT).show();
                                    //showContent(WebViewFragment.newInstance(Urls.FAKE_HELP_URL), WebViewFragment.TAG, true);
                                } else
                                    if (application.getCategoriesManager().isMyProfile(categoryModel)) {
                                        Toast.makeText(MainActivity.this, "Will be implemented in the next version.",
                                                Toast.LENGTH_SHORT).show();
                                        //showUserInfoFragment(UserInfoFragment.MY_PROFILE);
                                    } else
                                        if (application.getCategoriesManager().isAddressBook(categoryModel)) {
                                            Toast.makeText(MainActivity.this,
                                                    "Will be implemented in the next version.", Toast.LENGTH_SHORT)
                                                    .show();
                                            //showUserInfoFragment(UserInfoFragment.ADDRESS_BOOK);
                                        } else
                                            if (application.getCategoriesManager().isPaymentInfo(categoryModel)) {
                                                Toast.makeText(MainActivity.this,
                                                        "Will be implemented in the next version.", Toast.LENGTH_SHORT)
                                                        .show();
                                                //showUserInfoFragment(UserInfoFragment.PAYMENT_INFO);
                                            } else
                                                if (application.getCategoriesManager().isMyOrders(categoryModel)) {
                                                    Toast.makeText(MainActivity.this,
                                                            "Will be implemented in the next version.",
                                                            Toast.LENGTH_SHORT).show();
                                                    //showUserInfoFragment(UserInfoFragment.MY_ORDERS);
                                                } else
                                                    if (application.getCategoriesManager().isLogout(categoryModel)) {
                                                        Toast.makeText(MainActivity.this,
                                                                "Will be implemented in the next version.",
                                                                Toast.LENGTH_SHORT).show();
                                                        //restartApp();
                                                    } else {
                                                        Toast.makeText(MainActivity.this,
                                                                "Will be implemented in the next version.",
                                                                Toast.LENGTH_SHORT).show();
                                                        //showContent(ProductsGridFragment.newInstance(categoryModel.getCategory_id(),categoryModel.getCategory_name()),
                                                        //        ProductsGridFragment.TAG + categoryModel.getCategory_id(), true);
                                                    }
            }
        }
    };

    private View.OnClickListener onHeaderListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            LeftMenuHeaderView leftMenuHeaderView = (LeftMenuHeaderView) v;
            if (leftMenuHeaderView.getMode() != LeftMenuHeaderView.HIGHLIGHT_MODE) {
                int position = leftMenuListView.getPosition(leftMenuHeaderView);
                LeftMenuAdapter adapter = new LeftMenuAdapter(MainActivity.this, 0,
                        (List<CategoryModel>) leftMenuHeaderView.getTag());
                leftMenuListView.hideHeaders(position, adapter);
            }
        }
    };


    private void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBarView = getActionBarView();
        actionBarView.setClickListener(this);
        actionBar.setCustomView(actionBarView);
        actionBar.setDisplayShowCustomEnabled(true);
    }


    public void restartApp() {
        finish();
        //application.clearData();
        LaunchActivity.start(MainActivity.this);
    }


    @Override
    public void onSearch() {
        //close drawer if it is opened
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        //SearchActivity.startForResult(this, SEARCH_ACTIVITY_REQUEST);
    }


    @Override
    public void onLogo() {
        //close drawer if it is opened
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        showContent(MainFragment.newInstance(), MainFragment.TAG, false);
    }


    @Override
    public void onFavorites() {
        //close drawer if it is opened
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        showContent(FavoritesFragment.newInstance(), FavoritesFragment.TAG, true);
    }


    @Override
    public void onBag() {
        //close drawer if it is opened
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        showContent(OrderFragment.newInstance(), OrderFragment.TAG, true);
    }


    @Override
    public void onMenu() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onBackPressed() {
        if (!getSupportActionBar().isShowing()) {
            getSupportActionBar().show();
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (!fragmentsStackTags.isEmpty()) {
                fragmentsStackTags.pop();
            }
            super.onBackPressed();
        }
    }


    @Override
    public void onProductFeedItemClick(ProductModel productModel) {
        //method of MainFragment
        application.getProductsManager().setCurrentProduct(productModel);
        showContent(ProductFragment.newInstance(), ProductFragment.TAG, true);
    }


    @Override
    public void onContinueShopping() {
        //method from FavoritesFragment and OrderFragment
        showContent(MainFragment.newInstance(), MainFragment.TAG, true);
    }
    /*
    @Override
    public void onSocialNetworksButtonClick(String url) {
        //method of MainFragment
        WebViewFragment webViewFragment = WebViewFragment.newInstance(url);
        showContent(webViewFragment, WebViewFragment.TAG, true);
    }

    @Override
    public void onCarouselItemClick(String categoryId) {
        //method of MainFragment
        String categoryName = application.getCategoriesManager().getCategoryNameById(categoryId);
        showContent(ProductsGridFragment.newInstance(categoryId, categoryName), ProductsGridFragment.TAG + categoryId, true);
    }

    @Override
    public void onProductItemClickClick(String productId) {
        //method of ProductGridFragment
        showContent(ProductFragment.newInstance(productId), ProductFragment.TAG, true);
    }

    @Override
    public void onShowSizeGuide() {
        //TODO Need to add extension for this method
        //method of ProductFragment
        WebViewFragment webViewFragment = WebViewFragment.newInstance(Urls.FAKE_SIZE_GUIDE_URL);
        showContent(webViewFragment, WebViewFragment.TAG, true);
    }



    @Override
    public void onShowBrandProducts(String brandName) {
        showContent(ProductsGridFragment.newInstance(brandName), ProductsGridFragment.TAG + brandName, true);
    }

    //result from SearchActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SEARCH_ACTIVITY_REQUEST){
            if(resultCode == RESULT_OK){
                searchName = data.getStringExtra(SearchActivity.TAG);
            }
        }
    }

    //show fragment after search result
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if(searchName != null) {
            showContent(ProductsGridFragment.newInstance(searchName), ProductsGridFragment.TAG + searchName, true);
            searchName = null;
        }
    }

    @Override
    public void onEditProfileShow() {
        showContent(EditMyProfileFragment.newInstance(), EditMyProfileFragment.TAG, true);
    }

    @Override
    public void onChangePasswordShow() {
        ChangePasswordActivity.start(this);
    }

    @Override
    public void onEditAddressShow() {
        showContent(EditAddressGridFragment.newInstance(), EditAddressGridFragment.TAG, true);
    }

    @Override
    public void onAddNewAddressShow() {
        showContent(AddNewAddressFragment.newInstance(), AddNewAddressFragment.TAG, true);
    }

    @Override
    public void onAddNewAddressShow(String addressNickName) {
        showContent(AddNewAddressFragment.newInstance(addressNickName), AddNewAddressFragment.TAG, true);
    }

    @Override
    public void onEditPaymentShow() {
        showContent(EditPaymentListFragment.newInstance(), EditPaymentListFragment.TAG, true);
    }

    @Override
    public void onAddNewPaymentShow() {
        showContent(AddNewPaymentFragment.newInstance(), AddNewPaymentFragment.TAG, true);
    }

    @Override
    public void onAddNewPaymentShow(String paymentNickName) {
        showContent(AddNewPaymentFragment.newInstance(paymentNickName), AddNewPaymentFragment.TAG, true);
    }
    */
}
