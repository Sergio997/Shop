package com.control;

import com.cash.Money;
import com.catalog.Stuff;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ControlMethod {
    private List<Stuff> stuffs;
    private Stuff stuff;

    public ControlMethod(Stuff stuff) {
        stuff = null;
        stuffs = new ArrayList<Stuff>();
    }

    public ControlMethod() {
        stuff = null;
        stuffs = new ArrayList<Stuff>();
    }

    public List<Stuff> getStuffs() {
        return stuffs;
    }

    public void setStuffs(ArrayList<Stuff> stuffs) {
        this.stuffs = stuffs;
    }

    public void purchase(Stuff stuff){
        stuffs.add(stuff);
    }

    public void clear(String date){

        Iterator iterator = stuffs.iterator();

        while (iterator.hasNext()){
            Stuff element = (Stuff) iterator.next();
            if(element.getDate().equals(date)){
                iterator.remove();
            }
        }
        System.out.println(stuffs.size());
        System.out.println("After remove element");
        outputList();

    }

    public void all(){
        System.out.println(stuffs.size());
        for (Stuff stuff1: stuffs) {
            System.out.println(stuff1.getDate() + "\n" + stuff1.getName() + "\n" + stuff1.getPrice());
        }

        Collections.sort(stuffs,((o1, o2) -> (o1.getDate().compareTo(o2.getDate()))));

        outputList();

    }

    public void report() throws IOException {
        JSONObject json = readJsonFromUrl("http://data.fixer.io/api/latest?access_key=74b0a37291fbe6f6df37288562bbb9c0");
        JSONObject moneyes = new JSONObject(json.getJSONObject("rates").toString());
        //JSONObject money = new JSONObject(moneyes.getJSONObject("AUD").get);
        System.out.println(json.toString());
        System.out.println();
        System.out.println(moneyes.getDouble("USD"));
        double sum = 0; //usd
        for (Stuff stuff1 : stuffs) {
            sum += (stuff1.getPrice() * moneyes.getDouble("UAH"));
        }
        System.out.println("Sum = " + sum);

        double balans = moneyes.getDouble(Money.USD.toString());
        System.out.println(balans);
    }

    private void outputList(){
        for (Stuff stuff1: stuffs) {
            System.out.println(stuff1.getDate() + "\n" + stuff1.getName() + "\n" + stuff1.getPrice()+" " +stuff1.getMoney().toString());
        }
    }

    private  String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public  JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
