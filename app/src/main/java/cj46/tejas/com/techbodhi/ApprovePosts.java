package cj46.tejas.com.techbodhi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


import static android.widget.Toast.LENGTH_LONG;

public class ApprovePosts extends Activity {

    ListView t;
    ProgressDialog pDialog;
    public ApprovePosts CustomListView = null;
    public ArrayList<HashMap<String,String>> CustomListViewValuesArr;
    ApprovePostAdapter adapter;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.approveposts);
        CustomListView = this;
        pDialog = new ProgressDialog(ApprovePosts.this);
        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        System.out.println("In MainActivity ... Before Setting List Data....");
        CustomListViewValuesArr = new ArrayList<>();

        System.out.println("In MainActivity ... After Setting List Data....");
        res = getResources();
        t = (ListView) findViewById(R.id.Approvepost);  // List defined in XML ( See Below )
        System.out.println("In onCreate...ApproveUSer class()...");
        new Main().execute();
        System.out.println("In onCreate...After Main Execute()...");
        /**************** Create Custom Adapter *********/


    }

    public void onItemClick(int mPosition)
    {
        System.out.println("In MainActivity...onItemClick()...");
/*        Toast.makeText(getApplicationContext(),"This is Main Activity OnItemCLick", LENGTH_LONG).show();*/
        System.out.println("In MainActivity...onItemClick()...");

    }


    /******
     * Function to set data in ArrayList
     *************/


    private class Main extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            // Showing progress dialog
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        public Void doInBackground(Void... arg0) {
            HttpConnection sh = new HttpConnection();
            if(sh==null)
            {
                System.out.println("In setListData ... Checking HttpConnectioon....");
            }else
            {
                System.out.println("In setListData in ELSE... Checking HttpConnectioon....");

            }

            // Making a request to url and getting response
            String jsonStr = sh.sendGetRequest(Config.URL_APPROVAL_POST);

            //System.out.println("json :- " +jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        String pid = jsonResponce.getString("p_id");
                        String companyName = jsonResponce.getString("p_companyName");

                        System.out.println("In MainActivity ... Setting List Data....pid ->" + pid + " p_companyName-->" +companyName);

                        HashMap<String, String> Post = new HashMap<>();

                        // adding each child node to HashMap key => value
                        Post.put("p_id",pid);
                        Post.put("p_companyName", companyName);

                         System.out.println("In MainActivity ... Setting List Data...." + i);


                        // adding contact to contact list
                        CustomListViewValuesArr.add(Post);
                    }
                } catch (final JSONException e)
                {
                    //     Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(getApplicationContext(),"Json parsing error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            System.out.println("In MainActivity ... Before MyListAdaper....");
            adapter = new ApprovePostAdapter(CustomListView, CustomListViewValuesArr, res);
            System.out.println("In MainActivity ... After MyListAdaper....");

            t.setAdapter(adapter);

        }
    }
    /*****************
     * This function used by adapter
     ****************/
}



