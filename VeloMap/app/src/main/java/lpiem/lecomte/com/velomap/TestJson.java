package lpiem.lecomte.com.velomap;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import lpiem.lecomte.com.velomap.Model.Contract;

public class TestJson extends AppCompatActivity {
    ArrayList<Contract> listContract;
    private static String apikey="626f84cb00f2d7868af5b65d34317c54c2158acd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);
        if(Connectivity.isConnectedFast(getApplicationContext())) {
            setTitle("Choisissez votre ville");
            new Chargement().execute();
        }else{
            setTitle("Connexion Impossible");
            Toast.makeText(getApplicationContext(),"Votre connexion internet est Trop lente",Toast.LENGTH_LONG).show();
            Intent i=new Intent(getApplicationContext(),NoConnectionActitivy.class);
            startActivity(i);
            this.finish();
        }


    }

    private class JsonListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listContract.size();
        }

        @Override
        public Object getItem(int position) {
            return listContract.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.json_list_adapter,parent, false);
            }
            TextView tvName=(TextView)convertView.findViewById(R.id.tvName);
            TextView tvCommercialName=(TextView)convertView.findViewById(R.id.tvCommercialName);
            TextView tvCountryName=(TextView)convertView.findViewById(R.id.tvCountryName);
            tvName.setText(listContract.get(position).getName());
            tvCommercialName.setText(listContract.get(position).getCommercialName());
            tvCountryName.setText(listContract.get(position).getCountryCode());
            return convertView;
        }


    }
    private class Chargement extends AsyncTask{
        public Chargement() {
        }

        @Override
        protected Object doInBackground(Object[] params) {
            listContract=new ArrayList<>();

            String myurl= "https://api.jcdecaux.com/vls/v1/contracts?apiKey="+apikey;

            URL url = null;
            try {
                url = new URL(myurl);

                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));



                String result = in.readLine();

                JSONArray array = new JSONArray(result);

                for(int i=0;i<array.length();i++){

                    listContract.add(new Contract(new JSONObject(array.getString(i))));
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            ListView listView=(ListView)findViewById(R.id.listView);
            listView.setAdapter(new JsonListAdapter());

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(Connectivity.isConnectedFast(getApplicationContext())){
                        Intent i=new Intent(getApplicationContext(),MapsActivity.class);
                        Bundle b=new Bundle();
                        b.putString("contract",listContract.get(position).getName());
                        i.putExtras(b);

                        startActivity(i);
                    }else{
                        setTitle("Connexion Impossible");
                        Toast.makeText(getApplicationContext(),"Votre connexion internet est Trop lente",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(),NoConnectionActitivy.class);
                        startActivity(i);

                    }



                }
            });
            
        }
    }


}
