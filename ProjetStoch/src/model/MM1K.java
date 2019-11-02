package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;

import java.util.ArrayList;
import java.util.List;

public class MM1K extends FileAttente {
    

    public MM1K(int maxCustomer) {
        super();
        this.S.setValue(1);
        this.K.setValue(maxCustomer);
    }

    public FloatProperty computeRho() {
        rho.setValue(lambda.getValue()/mu.getValue());
        return rho;
    }

    public FloatProperty computeNbCustomerInSystem() {
        if (rho.getValue() == 1) {
            L.setValue(K.getValue()/2);
        }
        else {
            L.setValue((float) (rho.getValue()*(1 - (K.getValue()+1)*Math.pow(rho.getValue(), K.getValue()) + K.getValue()*Math.pow(rho.getValue(), (K.getValue()+1)))/
                        ((1 - rho.getValue())*(1 - Math.pow(rho.getValue(),(K.getValue()+1))))));
        }
        return this.L;
    }

    public FloatProperty computeNbCustomerInQueue() {
        Lq.setValue((float) computeNbCustomerInSystem().getValue() - (1 - computeNbCustomerProbabilities().get(0).getValue()));
        return Lq;
    }

    public FloatProperty computeMeanTimeInSystem() {

        W.setValue((float) computeNbCustomerInSystem().getValue()/lambda.getValue());
        return W;
    }

    public FloatProperty computeMeanTimeInQueue() {
        Wq.setValue((float) computeMeanTimeInSystem().getValue() + 1/mu.getValue());
        return Wq;
    }

    /**
     *  Compute the probabilities of having i customer in the system, i being the index of the list
     * @return the list of probabilities
     */
    public List<FloatProperty> computeNbCustomerProbabilities() {
        int max = 5 * (int) Math.ceil(lambda.getValue());
        q = new ArrayList<FloatProperty>();
        // Calcul de q
        if (rho.getValue() == 1) {
            for(int i=0;i<max+1;i++) {
                q.add(i, new SimpleFloatProperty((float) (1/(K.getValue()+1))));
            }
        }
        else {
            for(int i=0;i<max+1;i++) {
                q.add(i, new SimpleFloatProperty((float) ((float) ((1 - rho.getValue())*Math.pow(rho.getValue(), i))/(1 - Math.pow(rho.getValue(), K.getValue()+1)))));
            }
        }
        return q;
    }

    /**
     * Compute the probabilities for a person to stay longer than a time t (in unit of time)
     * @return List<FloatProperty>
     */
    public List<FloatProperty> computeWaitingTimeProbabilities() {
        int max = (int) Math.ceil(lambda.getValue() * 5);
        w.clear();
        for(int i=1; i<max; i++) {
            w.add(new SimpleFloatProperty((float) Math.exp(-mu.getValue()*(1 - rho.getValue())*i)));
        }
        return w;
    }
}