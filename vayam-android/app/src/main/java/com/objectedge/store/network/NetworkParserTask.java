package com.objectedge.store.network;

import android.os.AsyncTask;

import com.objectedge.store.data.model.ErrorModel;
import com.objectedge.store.data.parser.ErrorParser;
import com.objectedge.store.utils.ConstantsLoader;
import com.objectedge.store.utils.WLog;
import com.squareup.okhttp.*;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class NetworkParserTask extends AsyncTask<Void,Void,ResponseModel> {

    private static final String TAG = "NetworkParserTask";
    private static final int CONNECTION_TIMEOUT = 15;
    private static final int READ_TIMEOUT = 10;
    private RequestModel requestModel;

    public NetworkParserTask(RequestModel requestModel){
        this.requestModel = requestModel;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(requestModel.getCallback() != null) {
            requestModel.getCallback().onLoadStarted();
        }
    }

    @Override
    protected ResponseModel doInBackground(Void... voids) {
        ResponseModel responseModel = new ResponseModel();
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        String responseString = null;
        try {
            Response response = client.newCall(requestModel.getRequest()).execute();
            responseString = response.body().string();
            if(response.isSuccessful()) {
                responseModel.model = requestModel.getParser().parse(responseString);
            } else {
                if(responseString == null) {
                    responseString = "Server Error";
                }
                throw new ServerException(responseString);
            }
        } catch (JSONException e) {
            WLog.d(TAG, "JSON Parser Exception");
            processJsonException(responseString, responseModel);
        } catch (IOException e) {
            WLog.d(TAG, "IOException");
            responseModel.exception = new Exception(ConstantsLoader.UKNOWN_EXCEPTION);
        } catch (ServerException e) {
            WLog.d(TAG, "ServerException");
            responseModel.exception = new Exception(ConstantsLoader.SERVER_EXCEPTION);
        }
        return responseModel;
    }

    @Override
    protected void onPostExecute(ResponseModel responseModel) {
        super.onPostExecute(responseModel);
        if(requestModel.getCallback() != null){
            requestModel.getCallback().onLoadFinished(responseModel);
        }
    }

    /**
     * If we receive a server error we try to determine what is a kind of this error.
     * First of all we try to parse information obtained from the server.-
     * If we are not able to do this, we receive a JSONException. When we catch a JSONException
     * we try to determine is it a server exception, and we can parse it, or it is some kind of
     * unknown to us exceptions.
     * @param responseString string received by request
     * @param responseModel
     */
    private void processJsonException(String responseString, ResponseModel responseModel){
        ErrorParser errorParser = new ErrorParser();
        try {
            responseModel.exception = new Exception(((ErrorModel)errorParser.parse(responseString)).getMessage());
        } catch (JSONException e) {
            responseModel.exception = new Exception(ConstantsLoader.UKNOWN_EXCEPTION);
        }
    }

}
