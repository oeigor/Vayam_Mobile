package com.objectedge.store.data.parser.user;

import com.objectedge.store.data.model.user.UserModel;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by "Michael Katkov" on 9/10/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, emulateSdk = 18)
public class UserParserTest {

    private String jsonString;

    @Before
    public void setup(){
        jsonString = "{\n" +
                "  \"links\": [\n" +
                "    {\n" +
                "      \"rel\": \"self\",\n" +
                "      \"href\": \"http://commerceapi.objectedge.com:7003/api/atg/v1/users/200034\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"gender\": \"male\",\n" +
                "  \"birthMonthDay\": \"0815\",\n" +
                "  \"birthYear\": \"1889\",\n" +
                "  \"x_shippingAddress\": {\n" +
                "    \"postalCode\": \"132456\",\n" +
                "    \"state\": \"NY\",\n" +
                "    \"addressLines\": [\n" +
                "      {\n" +
                "        \"typeCode\": \"Street\",\n" +
                "        \"relativeOrder\": 1,\n" +
                "        \"addressLine\": \"abc.pvt.ltd\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"typeCode\": \"Street\",\n" +
                "        \"relativeOrder\": 2,\n" +
                "        \"addressLine\": \"building 1\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"country\": \"US\",\n" +
                "    \"city\": \"New York\"\n" +
                "  },\n" +
                "  \"x_secondaryAddresses\": [\n" +
                "    {\n" +
                "      \"postalCode\": \"132456\",\n" +
                "      \"state\": \"NY\",\n" +
                "      \"addressLines\": [\n" +
                "        {\n" +
                "          \"typeCode\": \"Street\",\n" +
                "          \"relativeOrder\": 1,\n" +
                "          \"addressLine\": \"abc.pvt.ltd\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"typeCode\": \"Street\",\n" +
                "          \"relativeOrder\": 2,\n" +
                "          \"addressLine\": \"building 1\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"country\": \"US\",\n" +
                "      \"city\": \"New York\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"contactInformation\": {\n" +
                "    \"email\": {\n" +
                "      \"emailAddress\": \"oetesting100rabh@gmail.com\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"names\": [\n" +
                "    {\n" +
                "      \"value\": \"Saurabh\",\n" +
                "      \"typeCode\": \"givenName\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"value\": \"Dhumal\",\n" +
                "      \"typeCode\": \"familyName\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"customerId\": \"oetesting100rabh@gmail.com\",\n" +
                "  \"dateTime\": \"2015-02-10 02:52:35.325\",\n" +
                "  \"entityInformation\": \"Individual\",\n" +
                "  \"personalPreferences\": {\n" +
                "    \"x_receivePromoEmail\": \"no\"\n" +
                "  }\n" +
                "}";
    }

    @Test
    public void testParser() throws JSONException {
        UserParser userParser = new UserParser();
        UserModel userModel = (UserModel)userParser.parse(jsonString);
        assertEquals(userModel.getLinks().get(0).getHref(),"http://commerceapi.objectedge.com:7003/api/atg/v1/users/200034");
        assertEquals(userModel.getContactInformation().getEmail().getEmailAddress(),"oetesting100rabh@gmail.com");
        assertEquals(userModel.getBirthMonthDay(),"0815");
    }

}
