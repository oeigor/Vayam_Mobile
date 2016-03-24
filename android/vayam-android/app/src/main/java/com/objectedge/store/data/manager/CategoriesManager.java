package com.objectedge.store.data.manager;

import android.content.Context;

import com.objectedge.store.R;
import com.objectedge.store.annotations.Fake;
import com.objectedge.store.data.model.fake.CategoryModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eloor_000 on 3/25/2015.
 */
public class CategoriesManager {

    private Context context;
    private List<CategoryModel> categoriesList;
    private HashMap<String,String> categoryIdToName;
    private HashMap<String,String> categoryNameToId;

    public CategoriesManager(Context context){
        categoryIdToName = new HashMap<>();
        categoryNameToId = new HashMap<>();
        categoriesList = new ArrayList<>();
        this.context = context;
    }

    public void setCategoriesList(List<CategoryModel> categoriesList){
        this.categoriesList.clear();
        this.categoriesList.addAll(categoriesList);
        prepareCategories();
    }

    public void clearData(){
        categoryIdToName = new HashMap<>();
        categoryNameToId = new HashMap<>();
        categoriesList = new ArrayList<>();
    }

    @Fake
    public void setFakeCategoriesList(){

        fillFakeCategories();

        //add other items to main menu
        String myAccount = context.getResources().getString(R.string.my_account);
        categoriesList.add(createSimpleCategory(myAccount));

        String brands = context.getResources().getString(R.string.brands);
        categoriesList.add(createSimpleCategory(brands));

        String favourites = context.getResources().getString(R.string.favourites);
        categoriesList.add(createSimpleCategory(favourites));

        String help = context.getResources().getString(R.string.help);
        categoriesList.add(createSimpleCategory(help));

        String giftCard = context.getResources().getString(R.string.gift_card);
        categoriesList.add(createSimpleCategory(giftCard));

        String feedback = context.getResources().getString(R.string.feedback);
        categoriesList.add(createSimpleCategory(feedback));

    }

    @Fake
    public void fillFakeCategories(){
        String categories = context.getResources().getString(R.string.categories);
        //1
        CategoryModel categoryCategory = createSimpleCategory(categories);
        //2
        CategoryModel accessories = createSimpleCategory("Accessories");
        CategoryModel clothing = createSimpleCategory("Clothing");
        CategoryModel shoes = createSimpleCategory("Shoes");
        CategoryModel baby = createSimpleCategory("Baby");
        //3

        //categories
        CategoryModel womensAccessories = createSimpleCategory("Women's accessories");
        CategoryModel mensAccessories = createSimpleCategory("Men's accessories");
        CategoryModel kidsAccessories = createSimpleCategory("Kid's accessories");
        //2
        accessories.addChildren(womensAccessories);
        accessories.addChildren(mensAccessories);
        accessories.addChildren(kidsAccessories);
        //1
        categoryCategory.addChildren(accessories);

        //clothing
        CategoryModel womensClothing = createSimpleCategory("Women's clothing");
        CategoryModel mensClothing = createSimpleCategory("Men's clothing");
        CategoryModel kidsClothing = createSimpleCategory("Kid's clothing");
        //2
        clothing.addChildren(womensClothing);
        clothing.addChildren(mensClothing);
        clothing.addChildren(kidsClothing);
        //1
        categoryCategory.addChildren(clothing);

        //shoes
        CategoryModel womensShoes = createSimpleCategory("Women's shoes");
        CategoryModel mensShoes = createSimpleCategory("Men's shoes");
        CategoryModel kidsShoes = createSimpleCategory("Kid's shoes");
        //2
        shoes.addChildren(womensShoes);
        shoes.addChildren(mensShoes);
        shoes.addChildren(kidsShoes);
        //1
        categoryCategory.addChildren(shoes);

        //baby
        CategoryModel carSeats = createSimpleCategory("Car seats");
        CategoryModel strollers = createSimpleCategory("Strollers");
        CategoryModel gear = createSimpleCategory("Gear");
        CategoryModel nursery = createSimpleCategory("Nursery");
        //2
        baby.addChildren(carSeats);
        baby.addChildren(strollers);
        baby.addChildren(gear);
        baby.addChildren(nursery);
        //1
        categoryCategory.addChildren(baby);
        categoriesList.add(categoryCategory);
    }

    @Fake
    public CategoryModel createSimpleCategory(String name){
        CategoryModel category = new CategoryModel();
        category.setCategory_name(name);
        category.setCount("1");
        return category;
    }

    public List<CategoryModel> getCategoriesList(){
        return categoriesList;
    }

    public String getCategoryNameById(String categoryId){
        return categoryIdToName.get(categoryId);
    }

    public String getCategoryIdByName(String categoryName){
        return categoryNameToId.get(categoryName);
    }

    private void fillCategoryIdToNameMap(CategoryModel categoryModel){
        categoryIdToName.put(categoryModel.getCategory_id(), categoryModel.getCategory_name());
        for(CategoryModel cModel: categoryModel.getChildren()){
            fillCategoryIdToNameMap(cModel);
        }
    }

    private void fillCategoryNameToIdMap(CategoryModel categoryModel){
        categoryNameToId.put(categoryModel.getCategory_name(), categoryModel.getCategory_id());
        for(CategoryModel cModel: categoryModel.getChildren()){
            fillCategoryNameToIdMap(cModel);
        }
    }

    private void prepareCategories(){
        categoriesList.remove(3);
        categoriesList.remove(2);
        categoriesList.remove(1);

        String categories = context.getResources().getString(R.string.categories);
        categoriesList.get(0).setCategory_name(categories);

        //fill categories ids to names map to use it for identifying categories names by id in future
        fillCategoryIdToNameMap(categoriesList.get(0));
        fillCategoryNameToIdMap(categoriesList.get(0));

        String myAccount = context.getResources().getString(R.string.my_account);
        CategoryModel myAccountCategory = new CategoryModel();
        myAccountCategory.setCategory_name(myAccount);
        myAccountCategory.setCount("1");
        categoriesList.add(myAccountCategory);

        //add other items to main menu
        String brands = context.getResources().getString(R.string.brands);
        CategoryModel brandsCategory = new CategoryModel();
        brandsCategory.setCategory_name(brands);
        brandsCategory.setCount("1");
        categoriesList.add(brandsCategory);

        String favourites = context.getResources().getString(R.string.favourites);
        CategoryModel favouritesCategory = new CategoryModel();
        favouritesCategory.setCategory_name(favourites);
        favouritesCategory.setCount("1");
        categoriesList.add(favouritesCategory);

        String help = context.getResources().getString(R.string.help);
        CategoryModel helpCategory = new CategoryModel();
        helpCategory.setCategory_name(help);
        helpCategory.setCount("1");
        categoriesList.add(helpCategory);

        /*
        String giftCard = context.getResources().getString(R.string.gift_card);
        CategoryModel giftCardCategory = new CategoryModel();
        giftCardCategory.setCategory_name(giftCard);
        categoriesList.add(giftCardCategory);
        */

        String feedback = context.getResources().getString(R.string.feedback);
        CategoryModel feedbackCategory = new CategoryModel();
        feedbackCategory.setCategory_name(feedback);
        feedbackCategory.setCount("1");
        categoriesList.add(feedbackCategory);
    }

    private CategoryModel findMyAccountCategory(){
        String myProfile = context.getResources().getString(R.string.my_account);
        CategoryModel result = null;
        for(CategoryModel categoryModel: categoriesList){
            if(categoryModel.getCategory_name().equals(myProfile)){
                result = categoryModel;
                break;
            }
        }
        return result;
    }

    public void addChildrenToMyAccount(){
        CategoryModel myAccountCategory = findMyAccountCategory();
        if(myAccountCategory.getChildren().size() > 0){
            //if My account has already elements, we don't have to add more elements
            return;
        }
        //add children to MyAccount
        String myProfile = context.getResources().getString(R.string.my_profile);
        CategoryModel myProfileCategory = new CategoryModel();
        myProfileCategory.setCategory_name(myProfile);
        myProfileCategory.setCount("1");
        myAccountCategory.addChildren(myProfileCategory);

        String addressBook = context.getResources().getString(R.string.address_book);
        CategoryModel addressBookCategory = new CategoryModel();
        addressBookCategory.setCategory_name(addressBook);
        addressBookCategory.setCount("1");
        myAccountCategory.addChildren(addressBookCategory);

        String paymentInfo = context.getResources().getString(R.string.payment_info);
        CategoryModel paymentInfoCategory = new CategoryModel();
        paymentInfoCategory.setCategory_name(paymentInfo);
        paymentInfoCategory.setCount("1");
        myAccountCategory.addChildren(paymentInfoCategory);

        String myOrders = context.getResources().getString(R.string.my_orders);
        CategoryModel myOrdersCategory = new CategoryModel();
        myOrdersCategory.setCategory_name(myOrders);
        myOrdersCategory.setCount("1");
        myAccountCategory.addChildren(myOrdersCategory);

        String logout = context.getResources().getString(R.string.logout);
        CategoryModel logoutCategory = new CategoryModel();
        logoutCategory.setCategory_name(logout);
        logoutCategory.setCount("1");
        myAccountCategory.addChildren(logoutCategory);
    }

    public boolean isBrand(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.brands));
    }

    public boolean isMyAccount(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.my_account));
    }

    public boolean isFavorites(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.favourites));
    }

    public boolean isHelp(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.help));
    }

    public boolean isFeedback(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.feedback));
    }

    public boolean isMyProfile(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.my_profile));
    }

    public boolean isAddressBook(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.address_book));
    }

    public boolean isPaymentInfo(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.payment_info));
    }

    public boolean isMyOrders(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.my_orders));
    }

    public boolean isLogout(CategoryModel categoryModel){
        return categoryModel.getCategory_name().equals(context.getResources().getString(R.string.logout));
    }

    public boolean isMyAccountSubCategoryButNotLogout(CategoryModel categoryModel){
        return isMyProfile(categoryModel) ||
                isAddressBook(categoryModel) ||
                isMyOrders(categoryModel) ||
                isPaymentInfo(categoryModel);
    }
}
