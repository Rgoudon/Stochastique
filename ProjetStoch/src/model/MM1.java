package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class MM1 extends FileAttente {

    public MM1() {
        super();
        S = new SimpleIntegerProperty(1);
    }

    public FloatProperty computeRho() throws  ArithmeticException {
        rho.setValue(lambda.getValue()/mu.getValue());
        if (rho.getValue() >= 1) {
            throw new  ArithmeticException("Lambda is > 1") ;
        }
        return rho;
    }

    public FloatProperty computeNbCustomerInSystem() {
        L.setValue(lambda.getValue()/(mu.getValue()-lambda.getValue()));
        return L;
    }

    public FloatProperty computeNbCustomerInQueue() {
        Lq.setValue(lambda.getValue()*lambda.getValue()/(mu.getValue()*(mu.getValue()-lambda.getValue())));
        return Lq;
    }

    public FloatProperty computeMeanTimeInSystem() {
        W.setValue(1/(mu.getValue()-lambda.getValue()));
        return W;
    }

    public FloatProperty computeMeanTimeInQueue() {
        Wq.setValue(lambda.getValue()/(mu.getValue()*(mu.getValue()-lambda.getValue())));
        return Wq;
    }

    /**
     *  Compute the probabilities of having i customer in the system, i being the index of the list
     * @return the list of probabilities
     */
    public List<FloatProperty> computeNbCustomerProbabilities() {
        int max = (int) Math.ceil(lambda.getValue() * 3);
        // Clear q
        q.clear();
        // Calcul de q
        for(int i = 0; i<max; i++) {
            q.add(i, new SimpleFloatProperty((float) Math.pow(rho.getValue(), i)*(1-rho.getValue())));
        }
        return q;
    }

    /**
     * Compute the probabilities for a person to stay longer than a time t (in unit of time)
     * @return List<FloatProperty>
     */
    public TreeMap<Float, FloatProperty> computeWaitingTimeProbabilities() {
        int max = (int) Math.ceil(lambda.getValue() * 5);
        w.clear();
        for(int i=1; i<max; i++) {
            w.put((float) i, new SimpleFloatProperty((float) Math.exp(-mu.getValue()*(1 - rho.getValue())*i)));
        }
        return w;
    }
}