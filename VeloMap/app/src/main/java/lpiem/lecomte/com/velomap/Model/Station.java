package lpiem.lecomte.com.velomap.Model;

import android.annotation.TargetApi;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by iem2 on 14/10/15.
 */
public class Station {
    private int number;
    private String contractName;
    private String name;
    private String adress;
    private double lat;
    private double lng;
    private boolean banking;
    private boolean bonus;
    private String status;
    private int bikeStand;
    private int availableBikeStands;
    private int availableBikes;
    private long lastUpdate;



    public Station() {
    }

    public Station(int number, String contractName, String name, String adress, double lat, double lng, boolean banking, boolean bonus, String status, int bikeStand, int availableBikeStands, int availableBikes, long lastUpdate) {
        this.number = number;
        this.contractName = contractName;
        this.name = name;
        this.adress = adress;
        this.lat = lat;
        this.lng = lng;
        this.banking = banking;
        this.bonus = bonus;
        this.status = status;
        this.bikeStand = bikeStand;
        this.availableBikeStands = availableBikeStands;
        this.availableBikes = availableBikes;
        this.lastUpdate = lastUpdate;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public Station(JSONObject jsonObject){
        try {
            this.number=jsonObject.getInt("number");
            this.contractName=jsonObject.getString("contract_name");
            this.name=jsonObject.getString("name");
            this.adress=jsonObject.getString("address");
            this.banking=jsonObject.getBoolean("banking");
            this.bonus=jsonObject.getBoolean("bonus");
            this.status=jsonObject.getString("status");
            this.bikeStand=jsonObject.getInt("bike_stands");
            this.availableBikeStands=jsonObject.getInt("available_bike_stands");
            this.availableBikes=jsonObject.getInt("available_bikes");
            this.lastUpdate=jsonObject.getLong("last_update");

            JSONObject obj=jsonObject.getJSONObject("position");

                this.lat=obj.getDouble("lat");
                this.lng=obj.getDouble("lng");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isBanking() {
        return banking;
    }

    public void setBanking(boolean banking) {
        this.banking = banking;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBikeStand() {
        return bikeStand;
    }

    public void setBikeStand(int bikeStand) {
        this.bikeStand = bikeStand;
    }

    public int getAvailableBikeStands() {
        return availableBikeStands;
    }

    public void setAvailableBikeStands(int availableBikeStands) {
        this.availableBikeStands = availableBikeStands;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
