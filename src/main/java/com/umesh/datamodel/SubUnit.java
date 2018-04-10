package com.umesh.datamodel;

/**
 * Created by Umesh on 14/11/2016.
 */
public class SubUnit extends Unit {
    public SubUnit(String name, String symbol, int fontIndex) {
        super(name, symbol, fontIndex);
    }

    public SubUnit(String name, String symbol) {
        super(name, symbol);
    }

//    public SubUnit(String name, String symbol, int fontIndex) {
//        super(name, symbol, fontIndex);
//    }
//    public SubUnit(String name, String symbol) {
//        super(name, symbol);
//    }

    public String getName() {
        return super.getUnitName();
    }

    @Override
    public String getUnitSymbol() {
        return super.getUnitSymbol();
    }
}
