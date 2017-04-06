package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_LONG;

public class UserPanelViewPost extends AppCompatActivity
{
    private ProgressDialog pDialog;
    ListView postListView;
    public UserPanelViewPost CustomViewPost = null;
    public ArrayList<HashMap<String,String>> CustomListPostViewArray;
    UserPanelViewPostAdapter adapter;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userpanelviewpost);

        CustomViewPost = this;

        pDialog = new ProgressDialog(UserPanelViewPost.this);

        CustomListPostViewArray = new ArrayList<>();

        res = getResources();

        postListView= (ListView) findViewById(R.id.UserPanelViewPost);

        new ViewPost().execute();
    }

    public void onItemClick(int mPostion)
    {
         Toast.makeText(getApplicationContext(),"in UserPanelViewPost mPostion" +mPostion, LENGTH_LONG).show();
    }


    /**
     * Async task class to get json by making HTTP call
     */

    protected class ViewPost extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(UserPanelViewPost.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        public Void doInBackground(Void... arg0) {
            HttpConnection sh = new HttpConnection();

            // Making a request to url and getting response
            String jsonStr = sh.sendGetRequest(Config.URL_VIEW_POSTS);

            // Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray result = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        String companyId =jsonResponce.getString("p_id");
                        String comapanyName = jsonResponce.getString("p_companyName");
                        String Note = jsonResponce.getString("p_note");
                        String Deadline = jsonResponce.getString("p_deadline");
                        String companyProfile = jsonResponce.getString("p_companyProfile");
                        String companyCode = jsonResponce.getString("p_companyCode");
                        String JobDiscription = jsonResponce.getString("p_jobDescription");
                        String OtherSkills = jsonResponce.getString("p_otherSkills");
                        String Responsibility = jsonResponce.getString("p_responsibility");
                        String SkillsRequired = jsonResponce.getString("p_skillsRequired");
                        String Percentage = jsonResponce.getString("p_percentageCriteria");
                        String SalaryRange = jsonResponce.getString("p_salaryRange");
                        String InterviewProcess = jsonResponce.getString("p_interviewProcess");
                        String JobLocation = jsonResponce.getString("p_jobLocation");
                        String TestLocation = jsonResponce.getString("p_testLocation");



                        System.out.println(comapanyName);
                        System.out.println(companyProfile);

                        // tmp hash map for single contact
                        HashMap<String, String> Post = new HashMap<>();

                        // adding each child node to HashMap key => value
                        Post.put(Config.KEY_POST_ID,companyId);
                        Post.put(Config.KEY_COMPANYNAME, comapanyName);
                        Post.put(Config.KEY_NOTE,Note);
                        Post.put(Config.KEY_DEADLINE,Deadline);
                        Post.put(Config.KEY_COMPANYPROFILE,companyProfile);
                        Post.put(Config.KEY_COMPANYCODE,companyCode);
                        Post.put(Config.KEY_JOBDISCRIPTION,JobDiscription);
                        Post.put(Config.KEY_OTHERSKILLS,OtherSkills);
                        Post.put(Config.KEY_RESPONSIBILITY,Responsibility);
                        Post.put(Config.KEY_SKILLREQUIRES,SkillsRequired);
                        Post.put(Config.KEY_PERCENTAGE,Percentage);
                        Post.put(Config.KEY_SALARYRANGE,SalaryRange);
                        Post.put(Config.KEY_INTERVIEWPROCESS,InterviewProcess);
                        Post.put(Config.KEY_JOBLOCATION,JobLocation);
                        Post.put(Config.KEY_TESTLOCATION,TestLocation);

                        /*Post.put(Config.KEY_CONTENT, content);*/

                        // adding contact to contact list
                        CustomListPostViewArray.add(Post);
                    }
                } catch (final JSONException e) {
                    // Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                //Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        Toast.makeText(getApplicationContext(),"Couldn't get json from server. Check LogCat for possible errors!", LENGTH_LONG).show();
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

            adapter = new UserPanelViewPostAdapter(CustomViewPost, CustomListPostViewArray, res);

            postListView.setAdapter(adapter);


        }
    }
}
