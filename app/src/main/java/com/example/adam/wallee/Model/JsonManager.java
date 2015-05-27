package com.example.adam.wallee.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adam on 5.3.2015.
 */
public class JsonManager {





    public JSONObject createJsonObjectLogin(String email, String password){

        JSONObject identity = new JSONObject();
        try {
            identity.put("email",email);
            identity.put("password", password);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return identity;
    }

}
