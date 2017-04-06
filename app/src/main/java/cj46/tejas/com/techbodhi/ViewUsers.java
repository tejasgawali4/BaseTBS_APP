package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carl_johnson on 12/5/2016.
 */

public class ViewUsers extends AppCompatActivity {

    private String TAG = ViewPosts.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView postListView;
    private Button btnViewUserDeatils;

    ArrayList<HashMap<String, String>> viewuserarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewusers);

        viewuserarray = new ArrayList<>();

        postListView = (ListView) findViewById(R.id.viewusers);

        new ViewUser().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */

    protected class ViewUser extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ViewUsers.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        public Void doInBackground(Void... arg0) {
            HttpConnection sh = new HttpConnection();

            // Making a request to url and getting response
            String jsonStr = sh.sendGetRequest(Config.URL_VIEW_USERS);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        String uid=jsonResponce.getString("u_id");
                        String firstname = jsonResponce.getString("u_fisrtname");
                        String lastname = jsonResponce.getString("u_lastname");
                        btnViewUserDeatils = (Button) findViewById(R.id.btnViewUserDeatils);

                        System.out.println(uid);
                        System.out.println(firstname);
                        System.out.println(lastname);

                        // tmp hash map for single contact
                        HashMap<String, String> User = new HashMap<>();

                        // adding each child node to HashMap key => value
                        User.put(Config.USER_ID,uid);
                        User.put(Config.KEY_USER_FIRSTNAME, firstname);
                        User.put(Config.KEY_USER_LASTNAME, lastname);

                        // adding contact to contact list
                        viewuserarray.add(User);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
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
            ListAdapter adapter = new SimpleAdapter(

                    ViewUsers.this, viewuserarray, R.layout.view_user_list,
                    new String[]{ "firstname", "lastname"}, new int[]{ R.id.u_firstname, R.id.u_lastname});

            postListView.setAdapter(adapter);

        }
    }
}
