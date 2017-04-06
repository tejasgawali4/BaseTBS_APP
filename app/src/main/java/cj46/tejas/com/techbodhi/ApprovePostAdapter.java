package cj46.tejas.com.techbodhi;

/**
 * Created by Carl_johnson on 2/9/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ApprovePostAdapter extends BaseAdapter{

    private static LayoutInflater inflater = null;
    public Resources res;
    public String apr, rjt;

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;


    public ApprovePostAdapter(Activity a, ArrayList d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /********
     * What is the size of Passed Arraylist Size
     ************/
    public int getCount() {
//        System.out.println("In MyListAdapter ... getCount()..Size.. " + data.size());
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /******
     * Depends upon data size called for each row , Create each ListView row
     *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.view_postlistapprovals, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.pid = (TextView) vi.findViewById(R.id.p_id);
            holder.companyName = (TextView) vi.findViewById(R.id.companyName);
            holder.b1 = (ImageButton) vi.findViewById(R.id.btnApprovePost);
            holder.b2 = (ImageButton) vi.findViewById(R.id.btnRejectPost);
//            System.out.println("In MyListAdapter ... getView()....FirstName--> "+holder.firstname.getText() +" LastName -->" +holder.lastname.getText() );

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0)
        {
            holder.pid.setText("No Data");

        } else {
            /***** Get each Model object from Arraylist ********/

//            tempValues = ( ListModel ) data.get( position );
            final HashMap tempValues1 = data.get(position);

            /************  Set Model values in Holder elements ***********/

/*            holder.uid.setText( tempValues1.getUid() );
            holder.firstname.setText( tempValues.getFirstname() );
            holder.lastname.setText(tempValues.getLastname());*/
            holder.pid.setText(tempValues1.get("p_id").toString());
            holder.companyName.setText(tempValues1.get("p_companyName").toString());
            holder.b1 = (ImageButton) vi.findViewById(R.id.btnApprovePost);
            holder.b2 = (ImageButton) vi.findViewById(R.id.btnRejectPost);

            System.out.println("In MyListAdapter ... getView() in ELSE....pid--> " + holder.pid.getText() + " FirstName--> " + holder.companyName.getText());

            holder.b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String idd = (String) tempValues1.get("p_id");
                    // Toast.makeText(activity.getApplicationContext(), "Processing for Approve User" + idd, Toast.LENGTH_SHORT).show();
                    apr = idd;
                    Approve1();

                }
            });

            holder.b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String idd = (String) tempValues1.get("p_id");
                    // Toast.makeText(activity.getApplicationContext(), "Processing for Reject User" + idd, Toast.LENGTH_SHORT).show();
                    rjt = idd;
                    Reject1();

                }
            });

            vi.setTag(holder);

            /******** Set Item Click Listner for LayoutInflater for each row *******/
            System.out.println("In MyListAdapter ... Before On Item Click Listener...");
            vi.setOnClickListener(new OnItemClickListener(position));
            System.out.println("In MyListAdapter ... After On Item Click Listener...");

        }

        return vi;
    }


    private void Approve1()
    {

        final String Status = "1";

        class ApproveU extends AsyncTask<Void, Void, String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                String response = s;
                try {
                    JSONObject parentObject = new JSONObject(response);
                    String name = parentObject.getString("result");
                    System.out.println("after call responce()" + name);
                    //And then read attributes like

                    //Toast.makeText(activity.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    if (name.equals("success")) {
                        Toast.makeText(activity.getApplicationContext(), "User is Approved Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity.getApplicationContext(), ApprovePosts.class);
                        activity.startActivity(intent);
                    } else if (name.equals("error"))
                    {
                        Toast.makeText(activity.getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(activity.getApplicationContext(), "Check the Code", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... v)
            {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_POST_ID, apr);
                System.out.println("in id Approve" + apr);
                params.put(Config.KEY_POST_STATUS, Status);
                HttpConnection register = new HttpConnection();
                String res = register.sendPostRequest(Config.URL_APPROVE_post, params);
                System.out.println("after call return()" + res);
                return res;

            }
        }

        ApproveU obj = new ApproveU();
        obj.execute();
    }


    private void Reject1() {

        final String Status = "-1";

        class RejectU extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                String response = s;
                try {
                    JSONObject parentObject = new JSONObject(response);
                    String name = parentObject.getString("result");
                    System.out.println("after call responce()" + name);
                    //And then read attributes like

                    // Toast.makeText(activity.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    if (name.equals("success"))
                    {
                        Toast.makeText(activity.getApplicationContext(), "User is Rejected Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity.getApplicationContext(), ApprovePosts.class);
                        activity.startActivity(intent);

                    } else if (name.equals("error")) {
                        Toast.makeText(activity.getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity.getApplicationContext(), "Check the Code", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... v)
            {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_POST_ID, rjt);
                params.put(Config.KEY_POST_STATUS, Status);

                HttpConnection rh = new HttpConnection();
                String res = rh.sendPostRequest(Config.URL_APPROVE_post, params);
                System.out.println("after call return()" + res);
                return res;

            }
        }

        RejectU obj = new RejectU();
        obj.execute();
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder
    {
        public TextView pid;
        public TextView companyName;
        ImageButton b1;
        ImageButton b2;

    }

    /*********
     * Called when Item click in ListView
     ************/
    class OnItemClickListener implements OnClickListener {
        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            System.out.println("In MyListAdapter ... OnItemClickListener() I came here....");
            Toast.makeText(activity.getApplicationContext(), "Please Approve or Reject...", Toast.LENGTH_SHORT).show();

            ApprovePosts sct = (ApprovePosts) activity;

            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

            sct.onItemClick(mPosition);
        }
    }
}