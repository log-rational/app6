package com.umesh.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Umesh on 15/11/2016.
 */
public class UnitData{
    private static UnitData ourInstance = new UnitData();

    public static UnitData getInstance() {
        return ourInstance;
    }

    private UnitData() {
    }

    private ArrayList<Unit> units = new ArrayList<>();
    private ObservableList<Unit> unitObservableList = FXCollections.observableList(this.initialize());

    public ObservableList<Unit> getUnitObservableList() {
        return unitObservableList;
    }

    public ArrayList<Unit> initialize() {

        Unit landUnit= new Unit(" Land Unit", "\uF030", 0);
        units.add(landUnit);

        Unit airDefence = new Unit("Air Defence", "\uF02E", 0);
            units.add(airDefence);
            airDefence.addSubUnit("AD - Missile", "\uF088", 1);

        Unit armd = new Unit("Armour (ARMD)", "\uF041", 0);
            units.add(armd);
            armd.addSubUnit("ARMD - Tracked Recovery", "\uF04A");
//            armd.addSubUnit("ARMD - Wheeled", "");
//            armd.addSubUnit("ARMD - Wheeled Recovery", "");

        Unit atk = new Unit("Anti-Tank", "\uF029", 0);
            units.add(atk);
//            atk.addSubUnit("ATK - ARMD Wheeled", "");
//            atk.addSubUnit("ATK - Motorized", "");

        Unit avn= new Unit("Aviation (AVN)", "\uF0DC", 1);
            units.add(avn);
            avn.addSubUnit("AVN - Rotary Wing", "\uF0DC", 1);
            avn.addSubUnit("AVN - Attack", "\uF0DA", 1);
            avn.addSubUnit("AVN - Reconnaissance", "\uF0DB",1);
            avn.addSubUnit("AVN - Utility", "\uF0DD", 1);
            avn.addSubUnit("AVN - Utility Light", "\uF0DE", 1);
            avn.addSubUnit("AVN - Utility Medium", "\uF0DF", 1);
            avn.addSubUnit("AVN - Utility Heavy", "\uF0E0", 1);
            avn.addSubUnit("AVN - C2", "\uF0E1", 1);
            avn.addSubUnit("AVN - MedEvac", "\uF0E2", 1);
            avn.addSubUnit("AVN - UAV ", "\uF09A", 1);
            avn.addSubUnit("AVN - UAV Fixed Wing", "\uF0A2", 1);
//            avn.addSubUnit("AVN - Search & Rescue", "");

        Unit inf= new Unit("Infantry", "\uF049", 0);
            units.add(inf);
            inf.addSubUnit("INF - Light", "\uF0ED",1);
            inf.addSubUnit("INF - Motorised", "\uF0AE");
            inf.addSubUnit("INF - Airborne", "\uF068");
            inf.addSubUnit("INF - Mechanised", "\uF04D");
            inf.addSubUnit("INF - Mechanised Wheeled", "\uF025");
            inf.addSubUnit("INF - Fighting Vehicle", "\uF049", 1);

        Unit engr= new Unit("Engineer", "\uF045", 0);
            units.add(engr);
        engr.addSubUnit("ENGR - ARMD", "\uF044");
        engr.addSubUnit("ENGR - Amphibious", "\uF0CC");
        engr.addSubUnit("ENGR - Bridge", "\uF0E3");
//        engr.addSubUnit("ENGR - Combat", "");
//        engr.addSubUnit("ENGR - Light", "");
//        engr.addSubUnit("ENGR - Motorised", "");
//        engr.addSubUnit("ENGR - Construction", "");

        Unit arty= new Unit("Artillery", "\uF023", 0);
            units.add(arty);
            arty.addSubUnit("ARTY - Self Propelled", "\uF028");
            arty.addSubUnit("ARTY - Field Light", "\uF0CF", 1);
            arty.addSubUnit("ARTY - Field Medium", "\uF0D0", 1);
            arty.addSubUnit("ARTY - Field Heavy", "\uF0D1", 1);
            arty.addSubUnit("ARTY - Target Acquisition (TA)", "\uF04C", 1);
            arty.addSubUnit("ARTY - TA RADAR", "\uF04D", 1);
            arty.addSubUnit("ARTY - TA Sound Ranging", "\uF0AA", 1);
            arty.addSubUnit("ARTY - Meteorological", "\uF0E0");
//            arty.addSubUnit("ARTY - Survey", "");


        Unit recce= new Unit("Reconnaissance", "\uF04C", 0);
            units.add(recce);
            recce.addSubUnit("RECCE - ARMD", "\uF052");
            recce.addSubUnit("RECCE - Motorised", "\uF0CC",1);
            recce.addSubUnit("RECCE - Airborne", "\uF025", 1);
//            recce.addSubUnit("RECCE - Light", "");

        Unit isf= new Unit("Internal Security Force","\uF0D4", 1);
            units.add(isf);

        Unit cs= new Unit("Combat Support", "\uF061", 1);
            units.add(cs);


        Unit nbc= new Unit("NBC", "\uF09B", 1);
        units.add(nbc);
        nbc.addSubUnit("NBC - Decon", "\uF0AD", 1);
//            nbc.addSubUnit("NBC - Chemical (CHEM)", "");
//            nbc.addSubUnit("NBC - CHEM-Smoke/ Decontamination", "");
//            nbc.addSubUnit("NBC - Nuclear", "");
//            nbc.addSubUnit("NBC - Biological", "");

        Unit milInt= new Unit("Military Intelligence", "\uF0BF");
        units.add(milInt);
        milInt.addSubUnit("INT - Signal", "\uF050", 1);
        milInt.addSubUnit("INT - EW Direction Finding", "\uF0EB", 1);
        milInt.addSubUnit("INT - EW Intercept", "\uF0EC", 1);
        milInt.addSubUnit("INT - EW Jamming", "\uF0EA", 1);
        milInt.addSubUnit("INT - Interrogation", "\uF0E4", 1);
        milInt.addSubUnit("INT - Joint Int Centre", "\uF0D8", 1);
//            milInt.addSubUnit("INT - Electronic Warfare(EW)", "\uF050",1);
//            milInt.addSubUnit("INT - Counter Intelligence", "");
//            milInt.addSubUnit("INT - Operations", "");


        Unit mp= new Unit("Law Enforcement - MP", "\uF0BC");
        units.add(mp);
        mp.addSubUnit("Civilian Law Enforcement", "\uF0D2", 1);

        Unit signal= new Unit("Signal", "\uF06D");
        units.add(signal);
//        signal.addSubUnit("Area Signal Unit (SIG)", "");
//        signal.addSubUnit("SIG - Operations", "");
//        signal.addSubUnit("SIG - FWD Communications", "");
//        signal.addSubUnit("SIG - Radio Tactical Satellite", "");

        Unit eod= new Unit("EOD", "\uF0F3");
        units.add(eod);

        Unit css= new Unit("Combat Service Support", "\uF062", 1);
        units.add(css);

        Unit admin= new Unit("Administrative (ADMIN)", "\uF0D9", 1);
        units.add(admin);

        Unit postal= new Unit("Postal", "\uF064");
        units.add(postal);

        Unit fin= new Unit("Finance (FIN)", "\uF065");
        units.add(fin);

        Unit ps= new Unit("Personal Service", "\uF0C9");
        units.add(ps);
        Unit mortuary= new Unit("Mortuary/ Grave Registry", "\uF0F4");
        units.add(mortuary);
        Unit pa= new Unit("Public Affair", "\uF043", 1);
        units.add(pa);
//        pa.addSubUnit("PA Joint Information Bureau (JIB)", "");

        Unit rhu= new Unit("Replacement Holding Unit (RHU)", "\uF0CA");
        units.add(rhu);

        Unit labour= new Unit("Labour Resources", "\uF07A");
        units.add(labour);

        Unit med= new Unit("Medical (MED)", "\uF046");
        units.add(med);
        med.addSubUnit("MED - Treatment Facility", "\uF076");
//        med.addSubUnit("MED - Dental", "");
//        med.addSubUnit("MED - Psychological", "");

        Unit supply= new Unit("Supply", "\uF053");
        units.add(supply);
        supply.addSubUnit("Supply - AMMO", "\uF0E5",1);
        supply.addSubUnit("Engineer Store", "\uF043");
        supply.addSubUnit("NBC Supply", "\uF0E7", 1);
        supply.addSubUnit("Ordnance Material", "\uF0E8", 1);
//        supply.addSubUnit("Canteen Supply", "");
//        supply.addSubUnit("Combat Supply - Rations", "");
//        supply.addSubUnit("Combat Supply - POL", "");
//        supply.addSubUnit("Combat Supply - Water", "");
//        supply.addSubUnit("Technical Store", "");
//        supply.addSubUnit("MED Store", "");
//        supply.addSubUnit("Laundry / Bath", "");
//        supply.addSubUnit("Water Purification", "");

        Unit tpt= new Unit("Transport", "\uF054");
        units.add(tpt);
        tpt.addSubUnit("Movement Control Centre (MCC)", "\uF046", 1);
        tpt.addSubUnit("Sea Point of Departure (SPOD)", "\uF045", 1);
        tpt.addSubUnit("Air Point of Departure (APOD)", "\uF0CB", 1);
//        tpt.addSubUnit("Rail Head", "");

        Unit maintenance= new Unit("Maintenance", "\uF057");
        units.add(maintenance);
//        maintenance.addSubUnit("MAINT - Recovery", "");
//        maintenance.addSubUnit("MAINT - Electro-Optical", "");
//        units.add(new Unit("Judge Advocate General (JAG)", ""));
//        units.add(new Unit("Religious Chaplain", ""));
//        units.add(new Unit("Morale, Welfare, Recreation", ""));

        Collections.sort(units);
        return units;
    }
}
