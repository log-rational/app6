package com.umesh.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Umesh on 11/11/2016.
 */
public class Formation {
    private static final Formation ourInstance = new Formation();

    public static Formation getInstance() {
        return ourInstance;
    }

    private Formation() {
    }

    private Map<String, String> tempMap = new LinkedHashMap<>();


    public void loadFormation() {
        tempMap.put(null, "None");
        tempMap.put("\uF031", "Fireteams");
        tempMap.put("\uF032", "Section *Not valid in the UK");
        tempMap.put("\uF033", "Platoon | Troop | Flight");
        tempMap.put("\uF034", "Coy | Sqn | Battery");
        tempMap.put("\uF035", "Battalion | Regiment | Battle group");
        tempMap.put("\uF036", "Regiment *Not valid in the UK");
        tempMap.put("\uF037", "Brigade");
        tempMap.put("\uF038", "Division");
        tempMap.put("\uF039", "Corps");
        tempMap.put("\uF02d", "Army");
        tempMap.put("\uF05C", "Army Group");

    }

    ObservableMap<String, String> formationMap = FXCollections.observableMap(tempMap);

    public ObservableMap<String, String> getFormationMap() {
        return formationMap;
    }



}
