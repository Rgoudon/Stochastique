package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class MM1 extends FileAttente {

    public MM1() {
        super();
        S = new SimpleIntegerProperty(1);
    }

    public FloatProperty computeRho() throws  ArithmeticException {
        rho.setValue(lambda.getValue()/mu.getValue());
        if (rho.getValue() > 1) {
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
    public List<FloatProperty> computeNbCustomerProbabilities(int max) {
        // Calcul de q
        for(int i = 0; i<max; i++) {
            q.add(i, new SimpleFloatProperty((float) Math.pow(rho.getValue(), i)*(1-rho.getValue())));
        }
        return q;
    }

    /**
     * Compute the probability for a person to stay longer than a time t (in unit of time)
     * @param t
     * @return Float
     */
    public Float computeProbabilityOfStayingInSystem(int t) {
        return (float) Math.exp(-mu.getValue()*(1 - rho.getValue())*t);
    }
}