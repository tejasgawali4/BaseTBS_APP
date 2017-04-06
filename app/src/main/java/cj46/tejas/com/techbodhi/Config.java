package cj46.tejas.com.techbodhi;

import android.nfc.Tag;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Carl_johnson on 12/5/2016.
 */
public class Config
{
        //Address of our scripts of the CRUD
    /*

        public static final String URL_ADD_USER="http://192.168.1.120/Techbodhi/registerUser.php";
        public static final String URL_ADD_POST = "http://192.168.1.120/Techbodhi/addPost.php";
        public static final String URL_CHECK_USER ="http://192.168.1.120/Techbodhi/checkUser.php";
        public static final String URL_VIEW_USER_BYID="http://192.168.1.120/Techbodhi/ViewUserById.php?u_id=";
        public static final String URL_VIEW_USERS = "http://192.168.1.120/Techbodhi/viewUsers.php";
        public static final String URL_VIEW_POSTS = "http://192.168.1.120/Techbodhi/viewPost.php";
        public static final String URL_APPROVAL_USER = "http://192.168.1.120/Techbodhi/viewUserApprovals.php";
        public static final String URL_APPROVE = "http://192.168.1.120/Techbodhi/ApproveUser.php";
        public static final String URL_APPROVAL_POST = "http://192.168.1.120/Techbodhi/approvalPost.php?u_id=";

    */

    //http://platformx.co.in/tbAndroidApp/viewUsers.php

    public static final String URL_ADD_USER="http://192.168.1.120/TBS_PORTAL/API/Registration.php";
    public static final String URL_ADD_POST = "http://192.168.1.120/TBS_PORTAL/API/addPost.php";
    public static final String URL_CHECK_USER ="http://192.168.1.120/TBS_PORTAL/API/checkUser.php";
    public static final String URL_VIEW_POST_BYID="http://192.168.1.120/TBS_PORTAL/API/ViewPostById.php?jid=";
    public static final String URL_VIEW_USERS = "http://192.168.1.120/TBS_PORTAL/API/viewUsers.php";
    public static final String URL_VIEW_POSTS = "http://192.168.1.120/TBS_PORTAL/API/viewPost.php";
    public static final String URL_APPROVAL_USER = "http://192.168.1.120/TBS_PORTAL/API/viewUserApprovals.php";
    public static final String URL_APPROVE_user = "http://192.168.1.120/TBS_PORTAL/API/ApproveUser.php";
    public static final String URL_APPROVAL_POST = "http://192.168.1.120/TBS_PORTAL/API/viewPostApprovals.php";
    public static final String URL_APPROVE_post = "http://192.168.1.120/TBS_PORTAL/API/ApprovePost.php";
    public static final String URL_UPDATE_JOBPOST= "http://192.168.1.120/TBS_PORTAL/API/updateJobPost.php";
    public static final String URL_USER_PANEL_VIEWPOST= "http://192.168.1.120/TBS_PORTAL/API/UserPanelViewJobPost.php";
    public static final String URL_VIEWPOSTS_BY_ID="http://192.168.1.120/TBS_PORTAL/API/UserPanelViewJobPostBYId.php?pid=";
    //Keys that will be used to send the request to php scripts

    public static final String KEY_USER_FIRSTNAME = "u_fisrtname";
    public static final String KEY_USER_LASTNAME = "u_lastname";
    public static final String KEY_USERNAME ="u_username";
    public static final String KEY_PASSWORD ="u_password";
    public static final String KEY_EMAIL ="u_email";
    public static final String KEY_DOB ="u_dob";
    public static final String KEY_GENDER="u_gender";
    public static final String KEY_ADDRESS = "u_address";
    public static final String KEY_MOBILE = "u_contact";


    //job post veriable keys

    public static final String KEY_COMPANYNAME = "p_companyName";
    public static final String KEY_NOTE = "p_note";
    public static final String KEY_DEADLINE ="p_deadline";
    public static final String KEY_COMPANYPROFILE ="p_companyProfile";
    public static final String KEY_COMPANYCODE ="p_companyCode";
    public static final String KEY_JOBDISCRIPTION ="p_jobDescription";
    public static final String KEY_OTHERSKILLS="p_otherSkills";
    public static final String KEY_RESPONSIBILITY = "p_responsibility";
    public static final String KEY_SKILLREQUIRES = "p_skillsRequired";
    public static final String KEY_PERCENTAGE = "p_percentageCriteria";
    public static final String KEY_SALARYRANGE = "p_salaryRange";
    public static final String KEY_INTERVIEWPROCESS ="p_interviewProcess";
    public static final String KEY_JOBLOCATION ="p_jobLocation";
    public static final String KEY_TESTLOCATION ="p_testLocation";


    public static final String KEY_USER_STATUS = "status";
    public static final String KEY_POST_STATUS = "status";

    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "techbodhi";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "id";

    public static final String KEY_POST_ID ="p_id";

    public static final String JSON_ARRAY="result";
    //User id to pass with intent
    public static final String USER_ID = "u_id";
    public static final String TAG_USER_ID="2";

}
