package lpiem.lecomte.com.velomap.Model;

import android.annotation.TargetApi;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by iem2 on 14/10/15.
 */
public class Contract {
    private String name;
    private String commercialName;
    private String countryCode;
    private ArrayList<String> cities;

    public Contract(String name, String commercialName, String countryCode) {
        this.name = name;
        this.commercialName = commercialName;
        this.countryCode = countryCode;
    }

    public Contract(String name, String commercialName, String countryCode, ArrayList<String> cities) {
        this.name = name;
        this.commercialName = commercialName;
        this.countryCode = countryCode;
        this.cities = cities;
    }

    public Contract() {
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public Contract(JSONObject obj){
        try {
            this.name=obj.getString("name");
            this.commercialName=obj.getString("commercial_name");
            this.countryCode=obj.getString("country_code");
            JSONArray array=new JSONArray(obj.getJSONArray("cities"));
            for(int i=0;i<array.length();i++){
                addCities(array.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void addCities(String cities){
        this.cities.add(cities);
    }

    public String getCities(int i){
        return cities.get(i);
    }
}
