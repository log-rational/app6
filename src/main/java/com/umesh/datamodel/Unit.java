package com.umesh.datamodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

/**
 * Created by Umesh on 14/11/2016.
 */
public class Unit implements Comparable<Unit> {
    private SimpleStringProperty unitName = new SimpleStringProperty();
    private SimpleStringProperty unitSymbol = new SimpleStringProperty();
    private  int fontIndex;

    private LinkedList<SubUnit> subUnits = new LinkedList<>();

    private ObservableList<SubUnit> subUnitList = FXCollections.observableList(subUnits);
    
    public Unit(String name, String symbol, int fontIndex) {
        this.unitName.set(name);
        this.unitSymbol.set(symbol);
        this.fontIndex = fontIndex;
    }

    public Unit(String name, String symbol) {
        this.unitName.set(name);
        this.unitSymbol.set(symbol);
        this.fontIndex = 0;
    }

    public void addSubUnit(String title, String symbol) {
        subUnits.add(new SubUnit(title, symbol));
    }

    public void addSubUnit(String title, String symbol, int fontIndex) {
        subUnits.add(new SubUnit(title, symbol, fontIndex));
    }



    public String getUnitName() {
        return unitName.get();
    }

    public SimpleStringProperty unitNameProperty() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName.set(unitName);
    }

    public String getUnitSymbol() {
        return unitSymbol.get();
    }

    public SimpleStringProperty unitSymbolProperty() {
        return unitSymbol;
    }

    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol.set(unitSymbol);
    }

    public ObservableList<SubUnit> getSubUnitList() {
        ObservableList<SubUnit> list = FXCollections.unmodifiableObservableList(subUnitList);
        return list;
    }

    public void setSubUnitList(ObservableList<SubUnit> subUnitList) {
        this.subUnitList = subUnitList;
    }

    public int getFontIndex() {
        return fontIndex;
    }

    @Override
    public String toString() {
        return unitName.get();
    }


    @Override
    public int compareTo(Unit o) {
        return this.getUnitName().compareTo(o.getUnitName());
    }
}
