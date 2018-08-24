package com.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class SampleSkeleton {
    // Setting up environment
    private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            "AYSq3RDGsmBLJE-otTkBtM-jBRd1TCQwFf9RGfwddNXWz0uFU9ztymylOhRS",
            "EGnHDxD_qRPdaLdZz8iCr8N7_MzF-YHPTkjs6NKYQvQSBngp4PTTVWkPZRbL");
    // Setting up client for that order
    PayPalHttpClient client = new PayPalHttpClient(environment);

    /**
     * Method to get client object
     * @return PayPalHttpClient client
     */
    public PayPalHttpClient client() {
        return this.client;
    }

    /**
     * Method to pretty print a response
     * @param jo JSONObject
     * @param pre prefix (default="")
     * @return String pretty printed JSON
     */
    public String prettyPrint(JSONObject jo, String pre)
    {
        Iterator<?> keys = jo.keys();
        StringBuilder pretty = new StringBuilder();
        while( keys.hasNext() ) {
            String key = (String)keys.next();
            pretty.append(String.format("%s%s: ", pre, StringUtils.capitalize(key)));
            if (jo.get(key) instanceof JSONObject) {
                pretty.append(prettyPrint(jo.getJSONObject(key), pre + "\t"));
            }
            else if (jo.get(key) instanceof JSONArray){
                int sno = 1;
                for ( Object jsonObject: jo.getJSONArray(key)){
                    pretty.append(String.format("\n%s\t%d:\n", pre, sno++));
                    pretty.append(prettyPrint((JSONObject) jsonObject, pre + "\t\t"));
                }
            }
            else{
                pretty.append(String.format("%s\n", jo.getString(key)));
            }
        }
        return pretty.toString();
    }
}
