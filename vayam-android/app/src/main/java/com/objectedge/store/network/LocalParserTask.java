package com.objectedge.store.network;

import android.content.Context;
import android.os.AsyncTask;

import com.objectedge.store.data.model.ErrorModel;
import com.objectedge.store.data.parser.ErrorParser;
import com.objectedge.store.utils.AssetsUtils;
import com.objectedge.store.utils.ConstantsLoader;
import com.objectedge.store.utils.WLog;

import org.json.JSONException;

/**
 * Created by "Michael Katkov" on 10/3/2015.
 */
public class LocalParserTask extends AsyncTask<Void,Void,ResponseModel> {

    private static final String TAG = "LocalParserTask";
    private RequestModel requestModel;
    private String dataFile;
    private Context context;

    public LocalParserTask(Context context, String dataFile, RequestModel requestModel){
        this.requestModel = requestModel;
        this.dataFile = dataFile;
        this.context = context;
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
        String responseString = AssetsUtils.readFile(context,dataFile);
        try {
            responseModel.model = requestModel.getParser().parse(responseString);
        } catch (JSONException e) {
            WLog.d(TAG, "JSON Parser Exception");
            processJsonException(responseString, responseModel);
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
