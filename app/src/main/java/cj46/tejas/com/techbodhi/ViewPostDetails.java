package cj46.tejas.com.techbodhi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Carl_johnson on 2/2/2017.
 */

public class ViewPostDetails extends AppCompatActivity implements View.OnClickListener
{

    public EditText pid , pcompanyName ,pNote , pDeadline ,
            pCompanyProfile, pCompanyCode ,pJobDiscription,
            pOtherSkills, pResponsibilty , pSkilledRequired ,
            pPercentageCriteria , pSalaryRange , pInterviewProcess ,
            pjoblocation , PTestlocation;

    public Button btnUpdatePost;

    String id;

/*
    public String id,companyId , companyName , companyNote , companyDeadline , companyProfile, companyCode , jobDescription , otherSkills ,
    responsibility , skillsRequired , percentageCriteria , salaryRange , interviewProcess , jobLocation , testLocation;
*/


    ArrayList<HashMap<String,String>> ViewPostArray;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpostdetails);

        ViewPostArray = new ArrayList<>();

        pid = (EditText) findViewById(R.id.p_id);
        pcompanyName = (EditText) findViewById(R.id.companyName);
        pNote = (EditText) findViewById(R.id.Note);
        pDeadline = (EditText) findViewById(R.id.Deadline);
        pCompanyProfile = (EditText) findViewById(R.id.CompanyProfile);
        pCompanyCode = (EditText) findViewById(R.id.CompanyCode);
        pJobDiscription = (EditText) findViewById(R.id.jobDiscription);
        pOtherSkills = (EditText) findViewById(R.id.OtherSkills);
        pResponsibilty = (EditText) findViewById(R.id.Responsibility);
        pSkilledRequired = (EditText) findViewById(R.id.SkillsRequire);
        pPercentageCriteria = (EditText) findViewById(R.id.Percentage);
        pSalaryRange = (EditText) findViewById(R.id.salaryRange);
        pInterviewProcess = (EditText) findViewById(R.id.InterviewProcess);
        pjoblocation = (EditText) findViewById(R.id.JobLoction);
        PTestlocation = (EditText) findViewById(R.id.testLocation);

        btnUpdatePost = (Button) findViewById(R.id.btnUpdatePost);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("pid");
        //System.out.println("id : - " + id);

        Toast.makeText(getApplicationContext(), "id : - " + id , Toast.LENGTH_SHORT).show();

        new DetailViewPost().execute();

        btnUpdatePost.setOnClickListener(this);

/*        hash.put("key1", editText1.getText().toString());*/
    }

    @Override
    public void onClick(View v) {
        if (btnUpdatePost == v)
        {
            updatePost();
        }
    }

    /*  03 - 02 - 2017  Tejas : -  Creating new AsyncTask Class*/


    class DetailViewPost extends AsyncTask<Void, Void , Void >
    {
        String companyId , companyName , companyNote , companyDeadline , companyProfile, companyCode , jobDescription , otherSkills ,
                responsibility , skillsRequired , percentageCriteria , salaryRange , interviewProcess , jobLocation , testLocation;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            //Showing Progress Dialog
            pDialog = new ProgressDialog(ViewPostDetails.this);
            pDialog.setMessage("Please Wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params)
        {
            HttpConnection sh = new HttpConnection();
            //System.out.println("id verification :- " + id);
            String jsonStr = sh.sendGetRequest(Config.URL_VIEW_POST_BYID + id );
            System.out.println("URL :- " + Config.URL_VIEW_POST_BYID + id);
            if (jsonStr != null)
            {
                try
                {
                    JSONObject jsonObj= new JSONObject(jsonStr);

                    JSONArray result= jsonObj.getJSONArray("result");

                    for (int i=0 ; i<result.length();i++)
                    {
                        JSONObject jsonResponce = result.getJSONObject(i);

                        companyId = jsonResponce.getString("p_id");
                        companyName = jsonResponce.getString("p_companyName");
                        companyNote = jsonResponce.getString("p_note");
                        companyDeadline = jsonResponce.getString("p_deadline");
                        companyProfile = jsonResponce.getString("p_companyProfile");
                        companyCode = jsonResponce.getString("p_companyCode");
                        jobDescription = jsonResponce.getString("p_jobDescription");
                        otherSkills = jsonResponce.getString("p_otherSkills");
                        responsibility = jsonResponce.getString("p_responsibility");
                        skillsRequired = jsonResponce.getString("p_skillsRequired");
                        percentageCriteria = jsonResponce.getString("p_percentageCriteria");
                        salaryRange = jsonResponce.getString("p_salaryRange");
                        interviewProcess = jsonResponce.getString("p_interviewProcess");
                        jobLocation = jsonResponce.getString("p_jobLocation");
                        testLocation = jsonResponce.getString("p_testLocation");

                        System.out.println("companyId:"+ companyId + "companyName:" + companyName + "companyNote:" + companyNote +
                                "companyDeadline:" +companyDeadline + "companyProfile:"+companyProfile + "companyCode:"+companyCode+
                                "jobDescription:"+jobDescription+ "otherSkills:"+otherSkills+ "responsibility:" +responsibility+
                                "skillsRequired:"+skillsRequired+"percentageCriteria:"+percentageCriteria+"salaryRange:"+salaryRange+
                                "interviewProcess:"+interviewProcess+"jobLocation:"+jobLocation+"testLocation:"+testLocation);

/*                        HashMap<String , String> Post = new HashMap<>();
                        Post.put(Config.KEY_POST_ID,companyId);
                        Post.put(Config.KEY_COMPANYNAME,companyName);
                        Post.put(Config.KEY_NOTE, companyNote);
                        Post.put(Config.KEY_DEADLINE,companyDeadline);
                        Post.put(Config.KEY_COMPANYPROFILE,companyProfile);
                        Post.put(Config.KEY_COMPANYCODE,companyCode);
                        Post.put(Config.KEY_JOBDISCRIPTION,jobDescription);
                        Post.put(Config.KEY_OTHERSKILLS,otherSkills);
                        Post.put(Config.KEY_RESPONSIBILITY,responsibility);
                        Post.put(Config.KEY_SKILLREQUIRES,skillsRequired);
                        Post.put(Config.KEY_PERCENTAGE,percentageCriteria);
                        Post.put(Config.KEY_SALARYRANGE,salaryRange);
                        Post.put(Config.KEY_INTERVIEWPROCESS,interviewProcess);
                        Post.put(Config.KEY_JOBLOCATION,jobLocation);
                        Post.put(Config.KEY_TESTLOCATION,testLocation);
                        ViewPostArray.add(Post);*/

                    }
                }
                catch (final JSONException e)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"JSON Parsing error" + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            else
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"couldn't get JSON From Server",Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing())
                pDialog.dismiss();

            pid.setText(companyId);
            pcompanyName.setText(companyName);
            pNote.setText(companyNote);
            pDeadline.setText(companyDeadline);
            pCompanyProfile.setText(companyProfile);
            pCompanyCode.setText(companyCode);
            pJobDiscription.setText(jobDescription);
            pOtherSkills.setText(otherSkills);
            pResponsibilty.setText(responsibility);
            pSkilledRequired.setText(skillsRequired);
            pPercentageCriteria.setText(percentageCriteria);
            pSalaryRange.setText(salaryRange);
            pInterviewProcess.setText(interviewProcess);
            pjoblocation.setText(jobLocation);
            PTestlocation.setText(testLocation);

        }

    }

    public void updatePost()
    {
        final String Post_id = pid.getText().toString().trim();
        final String CompanyName1 = pcompanyName.getText().toString().trim();
        final String Note1 = pNote.getText().toString().trim();
        final String Deadline1 = pDeadline.getText().toString().trim();
        final String CompanyProfile1 = pCompanyProfile.getText().toString().trim();
        final String CompanyCode1 = pCompanyCode.getText().toString().trim();
        final String JobDiscription1 = pJobDiscription.getText().toString().trim();
        final String otherSkills1 = pOtherSkills.getText().toString().trim();
        final String responsibility1 = pResponsibilty.getText().toString().trim();
        final String SkilledRequired1 = pSkilledRequired.getText().toString().trim();
        final String PercentageCriteria1 = pPercentageCriteria.getText().toString().trim();
        final String salaryRange1 = pSalaryRange.getText().toString().trim();
        final String InterviewProcess1 = pInterviewProcess.getText().toString().trim();
        final String JobLocation1 = pjoblocation.getText().toString().trim();
        final String testLocation1 = PTestlocation.getText().toString().trim();

        class UpdateJobPost extends AsyncTask<Void , Void , String>
        {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewPostDetails.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewPostDetails.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_POST_ID,Post_id);
                params.put(Config.KEY_COMPANYNAME, CompanyName1);
                params.put(Config.KEY_NOTE, Note1);
                params.put(Config.KEY_DEADLINE, Deadline1);
                params.put(Config.KEY_COMPANYPROFILE, CompanyProfile1);
                params.put(Config.KEY_COMPANYCODE, CompanyCode1);
                params.put(Config.KEY_JOBDISCRIPTION, JobDiscription1);
                params.put(Config.KEY_OTHERSKILLS, otherSkills1);
                params.put(Config.KEY_RESPONSIBILITY, responsibility1);
                params.put(Config.KEY_SKILLREQUIRES, SkilledRequired1);
                params.put(Config.KEY_PERCENTAGE, PercentageCriteria1);
                params.put(Config.KEY_SALARYRANGE, salaryRange1);
                params.put(Config.KEY_INTERVIEWPROCESS, InterviewProcess1);
                params.put(Config.KEY_JOBLOCATION, JobLocation1);
                params.put(Config.KEY_TESTLOCATION, testLocation1);

                HttpConnection rh = new HttpConnection();
                String res = rh.sendPostRequest(Config.URL_UPDATE_JOBPOST, params);
                return res;
            }
        }

        UpdateJobPost obj = new UpdateJobPost();
        obj.execute();
    }
}