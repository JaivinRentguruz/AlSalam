package com.rentguruz.app.b2b.alsalaam.apicall;

import java.util.HashMap;

/**
 * Created by ujjawal on 22-03-2018.
 */

public interface OnResponseListener {

    void onSuccess(String response, HashMap<String, String> headers);

    void onError(String error);

}
