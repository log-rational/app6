package com.umesh;

import java.io.InputStream;
import com.umesh.datamodel.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;


public class Controller {
    @FXML
    FlowPane textFlowPane;
    @FXML
    private ListView<Unit> unitListView;
    @FXML
    private ComboBox formationComboBox;
    @FXML
    private ToggleGroup forceToggleGp = new ToggleGroup();
    @FXML
    private RadioButton forceRadioBtn1;
    @FXML
    private RadioButton forceRadioBtn2;
    @FXML
    private RadioButton forceRadioBtn3;
    @FXML
    private RadioButton forceRadioBtn4;
    @FXML
    private ToggleGroup typeToggleGp = new ToggleGroup();
    @FXML
    private ToggleButton typeToggleBtn1;
    @FXML
    private ToggleButton typeToggleBtn2;
    @FXML
    private ToggleButton typeToggleBtn3;
    @FXML
    private ToggleButton typeToggleBtn4;
    @FXML
    private Label lblUnit;
    @FXML
    private Label lblHigherFormation;
    @FXML
    private TextArea fdUnit;
    @FXML
    HBox rightPane;

    @FXML
    private TextArea fdHigherFormation;


    @FXML
    javafx.scene.canvas.Canvas renderCanvas;

    @FXML
    BorderPane mainPane;

    @FXML
    public void saveFile() {
        FileChooser fc = new FileChooser();
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        snapshotParameters.setDepthBuffer(true);

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", ".png"));
        fc.setTitle("Save Map Symbol");
        File file = fc.showSaveDialog(mainPane.getScene().getWindow());
        if (file != null) {
            WritableImage wi = new WritableImage(((int) canvasWidth), ((int) canvasHeight));
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(renderCanvas.snapshot(snapshotParameters, wi), null), "png", file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void loadCanvas() {
        canvasWidth = renderCanvas.getWidth();
        canvasHeight = renderCanvas.getHeight();

    }


    private String forceFontIndex = "F";
    private int unitFontIndex = 0;
    @FXML
    private Text backGroundSym;
    @FXML
    private Text mainSymbol = new Text("\uF030");
    @FXML
    private Text formation = new Text(null);
    @FXML
    private Text typeSymbol;
    @FXML
    private ComboBox<SubUnit> subUnitComboBox;

    private double canvasWidth;
    private double canvasHeight;

    private Font baseFont;
    private Font symbolFont;
    private ObservableList<SubUnit> dummySubUnitList;
    private List<SubUnit> tempList = new LinkedList<SubUnit>();

    private ObservableList<SubUnit> getDummySubUnitList() {
        tempList.add(new SubUnit("------- N/A ------", ""));
        dummySubUnitList = FXCollections.observableList(tempList);
        return dummySubUnitList;
    }

    private Color customColor;
    ObservableMap<String, String> map = Formation.getInstance().getFormationMap();


    private Color getColor() {
        Color color = this.customColor;
        return color;
    }


    public void initialize() {

//        List<String> strings  = Arrays.asList("APP6A02", "APP6A16", "APP6A04", "APP6A18", "APP6A03", "APP6A17", "APP6A01", "APP6A15");
//        for (String s : strings) {
//            Font.loadFont(getClass().getResourceAsStream("/"+s + ".ttf"), 150);
//        }

        Fonts.getInstance().load();
        rightPane.setVisible(false);
        loadCanvas();
        loadUnitList();
        loadToggles();
        loadFormationList();
        bindUnitLabels();
        displayCredit();

        fdUnit.textProperty().addListener((observable, oldValue, newValue) -> updateSymbol());

        fdHigherFormation.textProperty().addListener((observable, oldValue, newValue) -> updateSymbol());


        // Listener for force Unknown | Friendly | Neutral | Hostile
        forceToggleGp.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (forceRadioBtn1.isSelected()) {
                forceFontIndex = "U";
                customColor = Color.YELLOW;
            } else if (forceRadioBtn2.isSelected()) {
                forceFontIndex = "F";
                customColor = Color.LIGHTSKYBLUE;
            } else if (forceRadioBtn3.isSelected()) {
                forceFontIndex = "N";
                customColor = Color.LIGHTGREEN;
            } else if (forceRadioBtn4.isSelected()) {
                forceFontIndex = "H";
                customColor = Color.PINK;
            }
            updateSymbol();
        });

        //Unit type event listener
        typeToggleGp.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (typeToggleBtn1.isSelected()) {
                typeSymbol.setText("\uF07E");
            } else if (typeToggleBtn2.isSelected()) {
                typeSymbol.setText("\uF05F");
            } else if (typeToggleBtn3.isSelected()) {
                typeSymbol.setText("\uF05E");
            } else if (typeToggleBtn4.isSelected()) {
                typeSymbol.setText("\uF0AC");
            } else {
                typeSymbol.setText(null);
            }
            if (newValue != null) {
                formationComboBox.getSelectionModel().selectFirst();
            }
            updateSymbol();
        });
        // Formation Combo box listener
        formationComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals(newValue)) {
                    formation.setText(entry.getKey());
                }
            }
            if (!newValue.equals("None")) {
                if (typeToggleGp.getSelectedToggle() != null) {
                    typeToggleGp.getSelectedToggle().setSelected(false);
                }
            }
            updateSymbol();
        });
        forceRadioBtn2.setSelected(true);

        // Unit List Listener. It should change font
        unitListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Unit selectedUnit = newValue;
            if (newValue != null) {
                ObservableList<SubUnit> innerObservableList = selectedUnit.getSubUnitList();
                unitFontIndex = selectedUnit.getFontIndex();
                mainSymbol.setText(selectedUnit.getUnitSymbol());

                if (selectedUnit.getSubUnitList().size() <= 0) {
                    if (!tempList.isEmpty()) {
                        for (int i = 0; i < tempList.size(); i++) {
                            tempList.remove(i);
                        }
                    }
                    subUnitComboBox.promptTextProperty().isNull();
                    subUnitComboBox.setItems(getDummySubUnitList());
                    subUnitComboBox.getSelectionModel().selectFirst();
                } else {
                    subUnitComboBox.setItems(innerObservableList);
                    subUnitComboBox.setPromptText("Select " + selectedUnit + " Subtype:");

                }
            }
            updateSymbol();
        });

        //Listener for subUnitComboBox;
        subUnitComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.getUnitSymbol().isEmpty()) {
                    mainSymbol.setText(newValue.getUnitSymbol());
                    unitFontIndex = newValue.getFontIndex();
                }
            } catch (NullPointerException e) {
                System.out.println("Subunit Couldn't be selected");
            }
            updateSymbol();
        });

        //Selects the first Item on the unit list
        unitListView.getSelectionModel().selectFirst();
    }

    private void loadFormationList() {
        Formation.getInstance().loadFormation();
        java.util.List<String> tempList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            tempList.add(entry.getValue());
        }
        ObservableList<String> comboList = FXCollections.observableList(tempList);
        formationComboBox.setItems(comboList);
    }

    private void loadUnitList() {
        unitListView.setItems(UnitData.getInstance().getUnitObservableList());
    }


    @FXML
    private void bindUnitLabels() {
        lblUnit.textProperty().bind(fdUnit.textProperty());
        lblHigherFormation.textProperty().bind(fdHigherFormation.textProperty());
    }


    // Updates textFlow with selected options. It is called in every events so that the symbol update with each event.
    public void updateSymbol() {
        double xPos = canvasWidth / 2 - 70;
        double yPos = canvasHeight / 2 + 70;

        updateBaseFont();
        updateSymbolFont();
        backGroundSym.setFill(getColor());

//        System.out.println(mainSymbol.getFont());
        GraphicsContext fGc = renderCanvas.getGraphicsContext2D();
        GraphicsContext labelGc = renderCanvas.getGraphicsContext2D();

//        bkgGc.clearRect(0, 0, renderCanvas.getWidth(), renderCanvas.getHeight());
        fGc.clearRect(0, 0, renderCanvas.getWidth(), renderCanvas.getHeight());

        //Sets the background color of the symbol
        fGc.setFill(customColor);
        fGc.setFont(baseFont);
        fGc.fillText(backGroundSym.getText(), xPos, yPos);

        fGc.setFill(Color.BLACK);
        fGc.setTextAlign(TextAlignment.LEFT);

        fGc.setFont(baseFont);
        fGc.fillText(formation.getText(), xPos, (yPos)); // Draws unit size symbol
        fGc.fillText(typeSymbol.getText(), xPos, yPos); // Draws the Type mark ie Installation, Task Force, Faint/Dummy, HQ

        // Sets the unit size and main symbol
        fGc.setFont(symbolFont);
        fGc.fillText(mainSymbol.getText(), xPos, yPos); // Draws the primary symbol

        // Set-up for symbol label
        Font arialFont = new Font("Arial bold", 24);
        fGc.setFont(arialFont);
        fGc.setTextAlign(TextAlignment.RIGHT);
        fGc.setTextBaseline(VPos.BOTTOM);
        fGc.fillText(fdUnit.getText(), (xPos - 1), yPos - 28);
        fGc.setTextAlign(TextAlignment.LEFT);
        fGc.fillText(fdHigherFormation.getText(), (xPos + 130), yPos - 28);
        System.out.println("Base font is :"+ baseFont);
        System.out.println("Symbol font is :"+ symbolFont);
    }

    private Font updateSymbolFont() {
        String fontCode = forceFontIndex + unitFontIndex;
        if (fontCode.equals("U0")) {
            symbolFont = new Font("MapSym-NK-Land-APP6a", 150);
        } else if (fontCode.equals("U1")) {
            symbolFont = new Font("MapSymNKLand1", 150);
        } else if (fontCode.equals("F0")) {
            symbolFont = new Font("MapSym-FR-Land-APP6a", 150);
        } else if (fontCode.equals("F1")) {
            symbolFont = new Font("MapSymFRLand1", 150);
        } else if (fontCode.equals("N0")) {
//            symbolFont = new Font("MapSym-NU-Land-APP6a", 150);
            symbolFont = Fonts.getInstance().FRL1;
        } else if (fontCode.equals("N1")) {
            symbolFont = new Font("MapSymNULand1", 150);
        } else if (fontCode.equals("H0")) {
            symbolFont = new Font("MapSym-EN-Land-APP6a", 150);
        } else if (fontCode.equals("H1")) {
            symbolFont = new Font("MapSymENLand1", 150);
        } else {
            symbolFont = baseFont;
            System.out.println("no fonts were selected.");
        }
        return symbolFont;
    }

    private void updateBaseFont() {
        if (forceFontIndex.equals("U")) {
            baseFont = new Font("MapSym-NK-Land-APP6a", 150);
        } else if (forceFontIndex.equals("F")) {
            baseFont = new Font("MapSym-FR-Land-APP6a", 150);
        } else if (forceFontIndex.equals("N")) {
            baseFont = new Font("MapSym-NU-Land-APP6a", 150);
        } else if (forceFontIndex.equals("H")) {
            baseFont = new Font("MapSym-EN-Land-APP6a", 150);
        }

    }

    //  Loads Forces and Type Toggle Groups
    private void loadToggles() {
        forceRadioBtn1.setToggleGroup(forceToggleGp);
        forceRadioBtn2.setToggleGroup(forceToggleGp);
        forceRadioBtn3.setToggleGroup(forceToggleGp);
        forceRadioBtn4.setToggleGroup(forceToggleGp);

        typeToggleBtn1.setToggleGroup(typeToggleGp);
        typeToggleBtn2.setToggleGroup(typeToggleGp);
        typeToggleBtn3.setToggleGroup(typeToggleGp);
        typeToggleBtn4.setToggleGroup(typeToggleGp);
    }

    @FXML
    TextFlow creditTextFlow;

    private void displayCredit() {
        Text fistLine = new Text("Developed by: Lcpl Umesh Rai | 42 Engineer Regiment (GEO) | ");
        Hyperlink hyperlink = new Hyperlink("umesrai@gmail.com");
        hyperlink.setOnAction(event -> {
            // Placeholder for Mail-to function
        });
        creditTextFlow.getChildren().addAll(fistLine, hyperlink);
    }

    public void clearCanvas() {
        GraphicsContext eraser = renderCanvas.getGraphicsContext2D();
        eraser.clearRect(0, 0, renderCanvas.getWidth(), renderCanvas.getHeight());
    }
}
