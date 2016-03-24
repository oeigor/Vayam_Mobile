package com.objectedge.store.data.parser.product;

import com.objectedge.store.data.model.product.ProductsListModel;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by "Michael Katkov" on 9/11/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, emulateSdk = 18)
public class ProductsListParserTest {

    private String jsonString;

    @Before
    public void setup(){
        jsonString = "{\n" +
                "  \"links\": [\n" +
                "    {\n" +
                "      \"rel\": \"self\",\n" +
                "      \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/products\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"rel\": \"nextPage\",\n" +
                "      \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/products?page=2&pageSize=10\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinacuawb01\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl1218012654.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Under Armour 3 Inch Performance Wristband\",\n" +
                "        \"long\": \"Keep sweat at bay and away from hardworking hands with the Under Armour 3 Inch Performance Wristband. The performance fibers wick away unwanted moisture so you can concentrate on the task at hand, while the soft blend of materials keeps you wrapped in comfort. The embroidered Under Armour logo adds to the athletic styling of this much needed active accessory.\\n \\n FEATURES:\\n \\n FABRIC: Polyester, nylon, and rubber\\n QUANTITY: 1 pair\\n CARE: Machine wash\\n IMPORTED\",\n" +
                "        \"web\": \"Keep sweat at bay and away from hardworking hands with the Under Armour 3 Inch Performance Wristband.\"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinfeapru03\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl1251862013.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Women's Under Armour HeatGear Alpha Shorty Shorts\",\n" +
                "        \"long\": \"Dominate your next training session in these sleek shorty shorts, made of advanced Alpha HeatGear material. Breathable, lightweight and with a stellar fit, these shorts look just as good as they perform.\\n\\nFeaturing a compression fit, these 3\\\" shorts offer support to hard-working muscles. Sitting right at your hips, these little cuties have the UA logo on the exposed waistband for a stylish look. They also boast 4-Way stretch fabrication for mobility and Signature Moisture Transport System to wick away sweat as you push your limits.\\n\\nOn their own or as a base layer, these standout UA shorts are superstars in the gym.\\n\\nFEATURES:\\n\\nFABRIC: Polyester/elastane\\nFIT: Compression, 3\\\" inseam\\nCARE: Machine wash\\nIMPORTED\",\n" +
                "        \"web\": \"Dominate your next training session in these sleek shorty shorts, made of advanced Alpha HeatGear material. Breathable, lightweight and with a stellar fit, these shorts look just as good as they perform.\"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinfeapru04\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl620422-560_m1.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Women's Nike 3 Inch Pro Core Compression Printed Shorts\",\n" +
                "        \"long\": \"You can't get in the best workout possible when you are busy worrying about your ill-fitting shorts. That's why the Nike 3\\\" Pro Core Compression Printed Women's Shorts will soon become your go-to gear for all types of workouts. Get the fit and feel you need to take your mind off your clothing and keep it in the game.\\n\\nThese Nike shorts feature a compression fit that won't ride up or get in your way, while also offering maximum support to your muscles. Dri-FIT fabric ensures you stay dry, while the lightweight material helps you to feel free to move as you need to. The mesh-lined gusset allows for serious breathability, as flat seam construction keeps chafing at bay. A wide range of colors and prints allows you to show off your vibrant personality at the gym.\\n\\nDitch those old, uncomfortable shorts for the advanced performance and feel of these sassy little shorts.\\n\\nFEATURES:\\n\\nFABRIC: 80% polyester, 20% spandex Dri-FIT\\nFIT: Compression, 3\\\" inseam (size medium)\\nCARE: Machine wash\\nIMPORTED\\nModel Stats\\n\\nOur model Audrey is wearing a size Small.\\n\\nAudrey is 5'7\\\" and her measurements are:\\n\\nWaist: 27\\\"\\nBust: 32\\\"\\nNOTE: Product sizing may vary from brand to brand.\",\n" +
                "        \"web\": \"You can't get in the best workout possible when you are busy worrying about your ill-fitting shorts. That's why the Nike 3\\\" Pro Core Compression Printed Women's Shorts will soon become your go-to gear for all types of workouts.\"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinfeshca02\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl599432613.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Women's Nike Roshe Run Print Casual Shoes\",\n" +
                "        \"long\": \"Lightweight running performance and a trendy aesthetic combine in the Nike Roshe Run Print Women's Casual Shoes. A new twist on the full mesh Roshe Run Shoes that were released in early 2012, the new model showcases a unique upper for a stylish look that stands out from the crowd. With a name inspired by that of a Zen Master, these sneakers were designed to be as simple as possible, giving you only what you need and none of what you don't. They utilize the basics: Heel support, quarter support and light cushioning, while leaving off the excess bulk. These basics, which include an ultra-lightweight Phylon outsole with waffle treads and a Solarsoft technology midsole for responsive cushioning, were added to a retro running silhouette to create the simple yet supportive Roshe Run Print.\\n\\nThe Nike Roshe Run Print Women's Casual Shoes are versatile enough to be worn on your next run and as your favorite everyday casual shoes.\\n\\nFEATURES:\\n\\nUPPER: Mesh\\nMIDSOLE: Solarsoft technology\\nOUTSOLE: Waffle Phylon\\nIMPORTED\",\n" +
                "        \"web\": \"Lightweight running performance and a trendy aesthetic combine in the Nike Roshe Run Print Women's Casual Shoes.\"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinmeapba02\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl657894687.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Men's Jordan Retro 7 Vault Tank\",\n" +
                "        \"long\": \"Boasting iconic Retro 7 inspired graphics, the Jordan Vault Tank celebrates the past with retro screen-printing with a silhouette that's decidedly modern. Get maximum mobility thanks to the tank top design, with ribbed armholes and neck for total comfort. Plus, soft, lightweight fabric lets you breathe easy all day long.\\n\\nFEATURES:\\n\\n    FABRIC: 100% cotton\\n    FIT: Relaxed\\n    CARE: Machine wash\\n    IMPORTED\",\n" +
                "        \"web\": \"Boasting iconic Retro 7 inspired graphics, the Jordan Vault Tank celebrates the past with retro screen-printing with a silhouette that's decidedly modern.\"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinmeapca01\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl632298100.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Men's Jordan AJ11 T-Shirt\",\n" +
                "        \"long\": \"Pay tribute to an icon in the Men's Jordan AJ11 T-Shirt. Bold graphics celebrate one of the greatest players of our time and a legendary shoe, while soft fabric keeps you comfortable on or off the court.\\n\\nFEATURES:\\n\\n    FABRIC: 100% cotton\\n    FIT: Relaxed\\n    CARE: Machine wash\\n    IMPORTED\",\n" +
                "        \"web\": \"Pay tribute to an icon in the Men's Jordan AJ11 T-Shirt. \"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinmeapru01\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl624515004.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Men's Nike Fly Camo Shorts\",\n" +
                "        \"long\": \"The last thing you need to deal with during a brutal workout is the distraction of ill-fitting, uncomfortable shorts. That's why the Nike Fly Camo Men's Training Shorts are designed to keep you comfortable with an ideal, adjustable fit. Keep your head in the game with these performance shorts.\\n\\nSweat soaked shorts are not only heavy; they are also incredibly irritating when you are trying to focus on your performance. With these shorts, you never have to worry about sweat issues, as the Dri-FIT fabric wicks away sweat. An athletic style and lined elastic waist with drawcord ensure a comfortable, personalized fit, while flat-seam construction minimizes dreaded chafing.\\n\\nTry these zero-distraction shorts from Nike for your next workout or game.\\n\\nFEATURES:\\n\\n    FABRIC: Dri-FIT 100% polyester\\n    FIT: Athletic fit is loose enough to allow you to move freely, yet form-fitting enough to not get in your way\\n    CARE: Machine wash\\n    IMPORTED \",\n" +
                "        \"web\": \"Try these zero-distraction shorts from Nike for your next workout or game.\"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinmeapru02\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl648918644.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Men's Nike 9 Inch Freedom Training Shorts\",\n" +
                "        \"long\": \"Free youself from restrictive workout gear with the Nike 9 Inch Freedom Shorts. Made to offer you a full range of motion so you can cover more ground with ease, these hardworking shorts are a must for your favorite workouts.\\n\\nDri-FIT fabric gets the stretch treatment, while the liner-free shorts allow you to layer over compression tights easily. An elastic waistband sits comfortably and offers a great fit, while the 9\\\" inseam provides plenty of coverage. Mesh panels and gusset offer superior ventilation, so you stay cooler and dryer on the run. Plus, multiple pockets keep your essentials safe while you train.\\n\\nFEATURES:\\n\\n    FABRIC: Polyester stretch Dri-FIT\\n    FIT: Athletic, 9\\\" inseam\\n    CARE: Machine wash\\n    IMPORTED\",\n" +
                "        \"web\": \"Free youself from restrictive workout gear with the Nike 9 Inch Freedom Shorts.\"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinmeapru04\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl667389477.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Men's Jordan S. Flight Sonic Print Training Shorts\",\n" +
                "        \"long\": \"Made of ultra-lightweight Dri-FIT fabric, these shorts wick sweat away so you stay dry and comfortable on and off the court. A stretch waist with interior drawcord delivers snug comfort and your ideal fit, every time. In a rush to get to the game? No problem. Throw your phone and keys into the convenient side pockets and hit the road. If you're not already sold on these shorts, the signature Jordan Jumpman logo and sick sonic print should do it.\\n\\nStay loose, stay comfy, and most importantly, stay dressed for success in the Jordan S. Flight Knit Basketball Shorts. Sold.\\n\\nFEATURES:\\n\\n    FABRIC: Dri-FIT polyester\\n    FIT: Relaxed\\n    CARE: Machine wash\\n    IMPORTED \",\n" +
                "        \"web\": \"Made of ultra-lightweight Dri-FIT fabric, these shorts wick sweat away so you stay dry and comfortable on and off the court.\"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"x_siteName\": [\n" +
                "        \"Object Edge Demo\"\n" +
                "      ],\n" +
                "      \"itemID\": \"pfinmeshba02\",\n" +
                "      \"x_thumbnailImageURL\": \"http://www.finishline.com/images/products/xl705277606.jpg\",\n" +
                "      \"description\": {\n" +
                "        \"short\": \"Men's Nike Kyrie 1 Basketball Shoes\",\n" +
                "        \"long\": \"Designed for the on-court quickness of superstar Kyrie Irving, the Nike Kyrie 1 Basketball Shoes keep you light on your feet with responsive cushioning and hardwood gripping traction.\\n\\nA hyperfuse upper starts things off, designed to offer a no-bulk fit, while a visibile synthetic frame offers lateral stability for high-intensity games. An ultra-light Zoom Air unit works with featherweight foam for just enough cushioning to keep you quick on your feet. All of this sits atop an enhanced rubber sole with advanced traction that wraps up the sidewalls, keeping you grounded as you bring the heat to the court.\\n\\nInspired by a a standout player, the speedy Nike Kyrie 1 Basketball Shoe is a classic in the making.\\n\\nFEATURES:\\n\\n    UPPER: Hyperfuse construction\\n    MIDSOLE: Zoom Air unit, lightweight foam\\n    OUTSOLE: Rubber with enhanced traction pattern\\n    IMPORTED\",\n" +
                "        \"web\": \"Designed for the on-court quickness of superstar Kyrie Irving, the Nike Kyrie 1 Basketball Shoes keep you light on your feet with responsive cushioning and hardwood gripping traction. \"\n" +
                "      },\n" +
                "      \"merchandiseHierarchy\": \"Sub-Category\",\n" +
                "      \"itemPrice\": {},\n" +
                "      \"x_siteId\": [\n" +
                "        \"storeSiteUS\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"paging\": {\n" +
                "    \"page\": 1,\n" +
                "    \"totalItemCount\": 56,\n" +
                "    \"totalPages\": 6,\n" +
                "    \"pageSize\": 10\n" +
                "  }\n" +
                "}";
    }

    @Test
    public void testParser() throws JSONException {
        ProductsListParser productsListParser = new ProductsListParser();
        ProductsListModel productsListModel = (ProductsListModel)productsListParser.parse(jsonString);
        assertNotNull(productsListModel.getItems());
        assertNotNull(productsListModel.getLinks());
        assertNotNull(productsListModel.getPaging());
        assertEquals(productsListModel.getLinks().get(0).getHref(), "http://commerceapi.objectedge.com:7003/api/atg/v1/products");
        assertEquals(productsListModel.getItems().get(0).getDescription().getShortValue(),"Under Armour 3 Inch Performance Wristband");
        assertEquals(productsListModel.getItems().get(0).getX_siteId().get(0),"storeSiteUS");
        assertEquals(productsListModel.getPaging().getPage(),"1");
    }
}
