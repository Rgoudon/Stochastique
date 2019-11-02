package model;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class FileAttente {
    protected FloatProperty lambda = new SimpleFloatProperty(0);          // Nombre de clients par unité de temps
    protected FloatProperty mu = new SimpleFloatProperty(0);              // Nombre de services par unité de temps
    protected IntegerProperty S = new SimpleIntegerProperty(0);           // Nombre de services (S)
    protected IntegerProperty K = new SimpleIntegerProperty(0);           // Nombre maximum de clients dans le système (K)
    protected FloatProperty rho = new SimpleFloatProperty(0);             // ratio lamdba/S*mu
    protected FloatProperty L = new SimpleFloatProperty(0);               // nombre de clients dans le système
    protected FloatProperty Lq = new SimpleFloatProperty(0);              // nombre de clients dans la file
    protected FloatProperty W = new SimpleFloatProperty(0);               // temps d'attente moyen dans le système
    protected FloatProperty Wq = new SimpleFloatProperty(0);              // temps d'attentee moyen dans la file
    protected List<FloatProperty> q = new ArrayList<>();                             // P(Xt=i) : probabilité qu'il y ai i clients
    protected StringProperty timeUnit = new SimpleStringProperty();                  // Unité de temps des calculs

    /*public void initTimeUnits() {
        timeUnits.put("ms", "Milliseconde");
        timeUnits.put("s", "Seconde");
        timeUnits.put("m", "Minute");
        timeUnits.put("h", "Heure");
    }*/

    public FloatProperty getLambda() {
        return lambda;
    }

    public void setLambda(FloatProperty l) {
        lambda = l;
    }

    public FloatProperty getMu() {
        return mu;
    }

    public void setMu(FloatProperty m) {
        mu = m;
    }

    public IntegerProperty getNbServer() {
        return S;
    }

    public void setNbServer(IntegerProperty nbServer) {
        S = nbServer;
    }

    public IntegerProperty getMaxCust() {
        return K;
    }

    public void setMaxCust(IntegerProperty maxCust) {
        K = maxCust;
    }

    public FloatProperty getRho() {
        return rho;
    }

    public FloatProperty getNbCustInSystem() {
        return L;
    }

    public FloatProperty getNbCustInQueue() {
        return Lq;
    }

    public StringProperty getMeanTimeInSystem() {
        return new SimpleStringProperty(Utils.format(W.getValue(), timeUnit.getValue()));
    }

    public StringProperty getMeanTimeInQueue() {
        return new SimpleStringProperty(Utils.format(Wq.getValue(), timeUnit.getValue()));
    }

    public List<FloatProperty> getProbabilityOfStates() {
        return q;
    }

    public StringProperty getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(StringProperty tu) {
        timeUnit = tu;
    }
}