package com.objectedge.store.data.parser.product;

import com.objectedge.store.data.model.product.ProductModel;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by "Michael Katkov" on 9/11/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, emulateSdk = 18)
public class ProductParserTest {

    private String jsonString;

    @Before
    public void setup(){
        jsonString = "{\n" +
                "  \"links\": [\n" +
                "    {\n" +
                "      \"rel\": \"self\",\n" +
                "      \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/products/pfinacuawb01\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"x_siteName\": [\n" +
                "    \"Object Edge Demo\"\n" +
                "  ],\n" +
                "  \"itemID\": \"pfinacuawb01\",\n" +
                "  \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl1218012654.jpg\",\n" +
                "  \"description\": {\n" +
                "    \"short\": \"Under Armour 3 Inch Performance Wristband\",\n" +
                "    \"long\": \"Keep sweat at bay and away from hardworking hands with the Under Armour 3 Inch Performance Wristband. The performance fibers wick away unwanted moisture so you can concentrate on the task at hand, while the soft blend of materials keeps you wrapped in comfort. The embroidered Under Armour logo adds to the athletic styling of this much needed active accessory.\\n \\n FEATURES:\\n \\n FABRIC: Polyester, nylon, and rubber\\n QUANTITY: 1 pair\\n CARE: Machine wash\\n IMPORTED\",\n" +
                "    \"web\": \"Keep sweat at bay and away from hardworking hands with the Under Armour 3 Inch Performance Wristband.\"\n" +
                "  },\n" +
                "  \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "  \"itemPrice\": {},\n" +
                "  \"x_siteId\": [\n" +
                "    \"storeSiteUS\"\n" +
                "  ]\n" +
                "}";
    }

    @Test
    public void testParser() throws JSONException {
        ProductParser productParser = new ProductParser();
        ProductModel productModel = (ProductModel)productParser.parse(jsonString);
        assertEquals(productModel.getLinks().get(0).getHref(),"http://commerceapi.objectedge.com:7003/api/atg/v1/products/pfinacuawb01");
        assertEquals(productModel.getX_siteName().get(0),"Object Edge Demo");
        assertEquals(productModel.getX_thumbnailImageURL(),"http://www.finishline.com/images/products/xl1218012654.jpg");
        assertEquals(productModel.getX_siteId().get(0),"storeSiteUS");
    }
}
