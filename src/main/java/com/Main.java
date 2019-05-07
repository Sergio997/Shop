package com;

import com.cash.Money;
import com.catalog.Stuff;
import com.control.ControlMethod;
import org.json.JSONException;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, JSONException {

        ControlMethod controlMethood = new ControlMethod();
        controlMethood.purchase(new Stuff("T-Shirt", "11/13/11", 2, Money.EUR));
        controlMethood.purchase(new Stuff("Beer", "11/13/11", 2, Money.EUR));
        controlMethood.purchase(new Stuff("T-Shirt", "11/12/11", 2,Money.EUR));
//        controlMethood.all();
//        controlMethood.clear("11/13/11");
        controlMethood.report();
    }
}
