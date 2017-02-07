package com.blackbirdcompany.jose.master_detail_jason.dummy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.blackbirdcompany.jose.master_detail_jason.ItemListActivity;
import com.blackbirdcompany.jose.master_detail_jason.dummy.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    private static String url = "http://iesayala.ddns.net/jose/Peliculas.php";
    public static ProgressDialog pDialog ;

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

   // private static final int COUNT = 25;
    static
    {
        new GetContacts().execute();
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
/*
    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position),"12","12","12");
    }
*/
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public  String nom;
        public  String ress;
        public  String info;
        public  String url;
        public  String url2;
        public  String fecest;

        public DummyItem(String id,String nom, String ress,String inf, String url, String url2, String fecest) {
            this.id=id;
            this.nom = nom;
            this.ress = ress;
            this.info=inf;
            this.url = url;
            this.url2 = url2;
            this.fecest = fecest;
        }

        @Override
        public String toString() {
            return nom;
        }
    }
    private static class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            /*pDialog= new ProgressDialog();
            pDialog.setMessage("Espere un momento...");
            pDialog.setCancelable(false);
            pDialog.show();*/
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);
                    Log.e(TAG, "0");
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    Log.e(TAG, "1");

                    // Getting JSON Array node
                    // JSONArray contacts = jsonObj.getJSONArray("contacts");
                    JSONArray peliculas = jsonObj.getJSONArray("Peliculas");

                    Log.e(TAG, "2");

                    // looping through All Contacts
                    for (int i = 0; i < peliculas.length(); i++) {
                        JSONObject c = peliculas.getJSONObject(i);
                        String id=c.getString("id");
                        String nombre = c.getString("Nombre");
                        String resumen = c.getString("Resumen");
                        String Info = c.getString("Informacion");
                        String URL=c.getString("URL");
                        String URL2=c.getString("URL2");
                        String fechaEstreno=c.getString("FechaEstreno");
                        // tmp hash map for single contact

                        addItem(new DummyItem(id,nombre,resumen,Info,URL,URL2,fechaEstreno));
                        //Log.e(TAG,id+nombre+resumen+Info+URL+URL2+fechaEstreno);

                        // adding each child node to HashMap key => value

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");

            }

            /*addItem(new DummyItem("1","Marshall", "JMP 800","http://www.drtube.com/schematics/marshall/2959-pic1.jpg","","",""));
            addItem(new DummyItem("2","Vox", "AC30","http://c1.zzounds.com/media/fit,2018by3200/quality,85/AC30_2x12_front-85b8b718f02627d9c08d712573fbccf6.jpg","","",""));
            addItem(new DummyItem("3","Fender", "Bassman","http://www.fmicassets.com/Damroot/ZoomJpg/10001/2171000010_amp_frt_001_nr.jpg","","",""));*/


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            /*if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
        }

    }
}
