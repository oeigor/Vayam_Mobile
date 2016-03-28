package com.objectedge.store.data.parser.user;

import com.objectedge.store.data.model.user.UsersListModel;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by "Michael Katkov" on 9/10/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, emulateSdk = 18)
public class UsersListParserTest {

    private String jsonString;

    @Before
    public void setup(){
        jsonString = "{\n" +
                "  \"links\": [\n" +
                "    {\n" +
                "      \"rel\": \"self\",\n" +
                "      \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users/\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"rel\": \"nextPage\",\n" +
                "      \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users/?page=2&pageSize=10\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//200034\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"male\",\n" +
                "      \"birthMonthDay\": \"0815\",\n" +
                "      \"birthYear\": \"1889\",\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"132456\",\n" +
                "        \"state\": \"NY\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"abc.pvt.ltd\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 2,\n" +
                "            \"addressLine\": \"building 1\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"New York\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"132456\",\n" +
                "          \"state\": \"NY\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"abc.pvt.ltd\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 2,\n" +
                "              \"addressLine\": \"building 1\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"New York\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"oetesting100rabh@gmail.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"Saurabh\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"Dhumal\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"customerId\": \"oetesting100rabh@gmail.com\",\n" +
                "      \"dateTime\": \"2015-02-10 02:52:35.325\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"no\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//200037\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"male\",\n" +
                "      \"birthMonthDay\": \"0623\",\n" +
                "      \"birthYear\": \"1889\",\n" +
                "      \"x_billingAddress\": {\n" +
                "        \"postalCode\": \"321321\",\n" +
                "        \"state\": \"AL\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"OEIS,MAHAPE\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 2,\n" +
                "            \"addressLine\": \"OEIS,MBP\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"thane\"\n" +
                "      },\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"321321\",\n" +
                "        \"state\": \"AL\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"OEIS,MAHAPE\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 2,\n" +
                "            \"addressLine\": \"OEIS,MBP\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"thane\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"321321\",\n" +
                "          \"state\": \"AL\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"OEIS,MAHAPE\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 2,\n" +
                "              \"addressLine\": \"OEIS,MBP\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"thane\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"ganesh.patil@objectedge.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"ganesh\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"patil\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"customerId\": \"ganesh.patil@objectedge.com\",\n" +
                "      \"dateTime\": \"2015-02-10 03:00:10.607\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"no\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//210006\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"male\",\n" +
                "      \"birthMonthDay\": \"0117\",\n" +
                "      \"birthYear\": \"1971\",\n" +
                "      \"x_billingAddress\": {\n" +
                "        \"postalCode\": \"94596\",\n" +
                "        \"state\": \"CA\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"224 Portola Dr\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Danville\"\n" +
                "      },\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"94596\",\n" +
                "        \"state\": \"CA\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"224 Portola Dr\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Danville\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"94596\",\n" +
                "          \"state\": \"CA\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"224 Portola Dr\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Danville\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"jags@objectedge.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"Jags\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"Krishnamurthy\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"customerId\": \"jags@objectedge.com\",\n" +
                "      \"dateTime\": \"2015-02-10 10:15:07.303\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"no\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//se-570030\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"male\",\n" +
                "      \"birthMonthDay\": \"0103\",\n" +
                "      \"birthYear\": \"1977\",\n" +
                "      \"x_billingAddress\": {\n" +
                "        \"postalCode\": \"03801\",\n" +
                "        \"state\": \"NH\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"105 Lake St\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Portsmouth\"\n" +
                "      },\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"03833\",\n" +
                "        \"state\": \"NH\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"457 Main St\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Exeter\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"03833\",\n" +
                "          \"state\": \"NH\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"457 Main St\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Exeter\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"postalCode\": \"03431\",\n" +
                "          \"state\": \"NH\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"12 Woodside Ave\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Keene\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"postalCode\": \"03801\",\n" +
                "          \"state\": \"NH\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"105 Lake St\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Portsmouth\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"stuart@example.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"Stuart\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"Schmidt\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"giftRegistration\": {\n" +
                "        \"occasion\": \"Birthday\",\n" +
                "        \"dateTime\": \"12082013\",\n" +
                "        \"x_giftList\": [\n" +
                "          \"xprod1001\",\n" +
                "          \"xprod1007\",\n" +
                "          \"xprod1014\",\n" +
                "          \"xprod1047\",\n" +
                "          \"xprod2032\",\n" +
                "          \"xprod2071\",\n" +
                "          \"xprod2116\",\n" +
                "          \"xprod2138\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"customerId\": \"stuart@example.com\",\n" +
                "      \"dateTime\": \"2015-01-27 14:12:12.367\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"yes\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//se-570031\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"female\",\n" +
                "      \"birthMonthDay\": \"0203\",\n" +
                "      \"birthYear\": \"1979\",\n" +
                "      \"x_billingAddress\": {\n" +
                "        \"postalCode\": \"14201\",\n" +
                "        \"state\": \"NY\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"451 Brooks Ave\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Buffalo\"\n" +
                "      },\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"13202\",\n" +
                "        \"state\": \"NY\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"21 Cedar Ave\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Syracuse\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"13202\",\n" +
                "          \"state\": \"NY\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"21 Cedar Ave\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Syracuse\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"postalCode\": \"13214\",\n" +
                "          \"state\": \"NY\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"41 Wexford Rd \"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Dewitt\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"postalCode\": \"14201\",\n" +
                "          \"state\": \"NY\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"451 Brooks Ave\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Buffalo\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"kim@example.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"Kim\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"Anderson\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"giftRegistration\": {\n" +
                "        \"occasion\": \"Wedding\",\n" +
                "        \"dateTime\": \"08202011\",\n" +
                "        \"x_giftList\": [\n" +
                "          \"xprod2095\",\n" +
                "          \"xprod2074\",\n" +
                "          \"xprod2126\",\n" +
                "          \"xprod2101\",\n" +
                "          \"xprod2007\",\n" +
                "          \"xprod2116\",\n" +
                "          \"xprod2096\",\n" +
                "          \"xprod2128\",\n" +
                "          \"xprod2005\",\n" +
                "          \"xprod2051\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"customerId\": \"kim@example.com\",\n" +
                "      \"dateTime\": \"2015-01-27 14:12:12.457\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"yes\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//se-570035\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"female\",\n" +
                "      \"birthMonthDay\": \"0507\",\n" +
                "      \"birthYear\": \"1982\",\n" +
                "      \"x_billingAddress\": {\n" +
                "        \"postalCode\": \"05401\",\n" +
                "        \"state\": \"VT\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"PO Box 105\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Burlington\"\n" +
                "      },\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"05401\",\n" +
                "        \"state\": \"VT\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"9010 Jackson Street\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Burlington\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"05401\",\n" +
                "          \"state\": \"VT\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"9010 Jackson Street\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Burlington\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"postalCode\": \"05401\",\n" +
                "          \"state\": \"VT\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"PO Box 105\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Burlington\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"erica@example.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"Erica\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"Clark\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"customerId\": \"erica@example.com\",\n" +
                "      \"dateTime\": \"2015-01-27 14:12:12.481\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"yes\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//se-570040\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"male\",\n" +
                "      \"birthMonthDay\": \"0601\",\n" +
                "      \"birthYear\": \"1952\",\n" +
                "      \"x_billingAddress\": {\n" +
                "        \"postalCode\": \"86336\",\n" +
                "        \"state\": \"AZ\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"2231 Iris Avenue\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Sedona\"\n" +
                "      },\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"86336\",\n" +
                "        \"state\": \"AZ\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"2231 Iris Avenue\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Sedona\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"86336\",\n" +
                "          \"state\": \"AZ\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"2231 Iris Avenue\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Sedona\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"andrew@example.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"Andrew\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"Wright\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"customerId\": \"andrew@example.com\",\n" +
                "      \"dateTime\": \"2015-01-27 14:12:12.509\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"yes\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//se-570045\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"female\",\n" +
                "      \"birthMonthDay\": \"1221\",\n" +
                "      \"birthYear\": \"1963\",\n" +
                "      \"x_billingAddress\": {\n" +
                "        \"postalCode\": \"84601\",\n" +
                "        \"state\": \"UT\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"531 Ridge Road\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Provo\"\n" +
                "      },\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"84601\",\n" +
                "        \"state\": \"UT\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"531 Ridge Road\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Provo\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"84601\",\n" +
                "          \"state\": \"UT\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"531 Ridge Road\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Provo\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"anna@example.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"Anna\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"Mitchell\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"customerId\": \"anna@example.com\",\n" +
                "      \"dateTime\": \"2015-01-27 14:12:12.53\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"yes\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//se-570050\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"male\",\n" +
                "      \"birthMonthDay\": \"1107\",\n" +
                "      \"birthYear\": \"1984\",\n" +
                "      \"x_billingAddress\": {\n" +
                "        \"postalCode\": \"35203\",\n" +
                "        \"state\": \"AL\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"PO Box 2255\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Birmingham\"\n" +
                "      },\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"35203\",\n" +
                "        \"state\": \"AL\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"1263 Spruce Street\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 2,\n" +
                "            \"addressLine\": \"Suite 100\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Birmingham\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"35203\",\n" +
                "          \"state\": \"AL\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"1263 Spruce Street\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 2,\n" +
                "              \"addressLine\": \"Suite 100\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Birmingham\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"postalCode\": \"35203\",\n" +
                "          \"state\": \"AL\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"PO Box 2255\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Birmingham\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"brandon@example.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"Brandon\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"Johnson\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"customerId\": \"brandon@example.com\",\n" +
                "      \"dateTime\": \"2015-01-27 14:12:12.556\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"yes\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"links\": [\n" +
                "        {\n" +
                "          \"rel\": \"self\",\n" +
                "          \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users//se-570055\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"gender\": \"female\",\n" +
                "      \"birthMonthDay\": \"0201\",\n" +
                "      \"birthYear\": \"1987\",\n" +
                "      \"x_billingAddress\": {\n" +
                "        \"postalCode\": \"02631\",\n" +
                "        \"state\": \"MA\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"1521 Marine Ave.\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Brewster\"\n" +
                "      },\n" +
                "      \"x_shippingAddress\": {\n" +
                "        \"postalCode\": \"02631\",\n" +
                "        \"state\": \"MA\",\n" +
                "        \"addressLines\": [\n" +
                "          {\n" +
                "            \"typeCode\": \"Street\",\n" +
                "            \"relativeOrder\": 1,\n" +
                "            \"addressLine\": \"1521 Marine Ave.\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"country\": \"US\",\n" +
                "        \"city\": \"Brewster\"\n" +
                "      },\n" +
                "      \"x_secondaryAddresses\": [\n" +
                "        {\n" +
                "          \"postalCode\": \"02631\",\n" +
                "          \"state\": \"MA\",\n" +
                "          \"addressLines\": [\n" +
                "            {\n" +
                "              \"typeCode\": \"Street\",\n" +
                "              \"relativeOrder\": 1,\n" +
                "              \"addressLine\": \"1521 Marine Ave.\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"country\": \"US\",\n" +
                "          \"city\": \"Brewster\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"contactInformation\": {\n" +
                "        \"email\": {\n" +
                "          \"emailAddress\": \"rachel@example.com\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"names\": [\n" +
                "        {\n" +
                "          \"value\": \"Rachel\",\n" +
                "          \"typeCode\": \"givenName\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"value\": \"Thomas\",\n" +
                "          \"typeCode\": \"familyName\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"customerId\": \"rachel@example.com\",\n" +
                "      \"dateTime\": \"2015-01-27 14:12:12.577\",\n" +
                "      \"entityInformation\": \"Individual\",\n" +
                "      \"personalPreferences\": {\n" +
                "        \"x_receivePromoEmail\": \"yes\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"paging\": {\n" +
                "    \"page\": 1,\n" +
                "    \"totalItemCount\": 31,\n" +
                "    \"totalPages\": 4,\n" +
                "    \"pageSize\": 10\n" +
                "  }\n" +
                "}";
    }

    @Test
    public void testParser() throws JSONException {
        UsersListParser userListParser = new UsersListParser();
        UsersListModel userListModel = (UsersListModel)userListParser.parse(jsonString);
        assertNotNull(userListModel.getItems());
        assertNotNull(userListModel.getLinks());
        assertNotNull(userListModel.getPaging());
        assertEquals(userListModel.getLinks().get(0).getHref(), "http://commerceapi.objectedge.com:7003/api/atg/v1/users/");
        assertEquals(userListModel.getItems().get(0).getLinks().get(0).getHref(), "http://commerceapi.objectedge.com:7003/api/atg/v1/users//200034");
        assertEquals(userListModel.getPaging().getPage(),"1");
    }
}
