package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;

import java.util.ArrayList;
import java.util.List;

public class MMS extends FileAttente {
    private List<Float> r;      // Tableau utilisé pour calculer q0

    public MMS(Integer nbServer) {
        super();
        if (nbServer == 1) {
            throw new IllegalArgumentException("The number of servers must be > 1. Use MM1 instead.");
        }
        S.setValue(nbServer);
        computeR(nbServer);
    }

    public FloatProperty computeRho() {
        rho.setValue(lambda.getValue()/(S.getValue()*mu.getValue()));
        if (rho.getValue() >= 1) {
            throw new  ArithmeticException("Lambda is > 1") ;
        }
        return rho;
    }



    /**
     * Compute the mean number of customers in the queue
     * @return float the mean number of customer in the queue
     */
    public FloatProperty computeNbCustomerInQueue() {
        Lq.setValue((float) (computeNbCustomerProbabilities().get(0).getValue() * (Math.pow(rho.getValue() * S.getValue(), S.getValue()) * rho.getValue()) / (Utils.fact(S.getValue()) * Math.pow(1 - rho.getValue(), 2))));
        return Lq;
    }

    /**
     * Compute the mean time spent by a customer in the queue.
     * The formula is Wq = Lq/lambda.
     * @return float the mean time spent by a customer in the queue
     */
    public FloatProperty computeMeanTimeInQueue() {
        Wq.setValue((float) ((float) (computeNbCustomerProbabilities().get(0).getValue()/lambda.getValue()) * (Math.pow(rho.getValue() * S.getValue(), S.getValue()) * rho.getValue()) / (Utils.fact(S.getValue()) * Math.pow(1 - rho.getValue(), 2))));
        return Wq;
    }

    /**
     * Compute the mean time spent by a customer in the system.
     * The formula is W = Wq + 1/mu.
     * @return float the mean time spent by a customer in the system
     */
    public FloatProperty computeMeanTimeInSystem() {
        W.setValue((float) ((computeNbCustomerProbabilities().get(0).getValue()/lambda.getValue()) * (Math.pow(rho.getValue() * S.getValue(), S.getValue()) * rho.getValue()) / (Utils.fact(S.getValue()) * Math.pow(1 - rho.getValue(), 2))) + 1/mu.getValue());
        return W;
    }

    /**
     * Compute the mean number of customers in the system.
     * The formula is L = lambda*W.
     * @return float the mean number of customers in the system
     */
    public FloatProperty computeNbCustomerInSystem() {
        L.setValue((float) (computeNbCustomerProbabilities().get(0).getValue()/lambda.getValue()) * (Math.pow(rho.getValue() * S.getValue(), S.getValue()) * rho.getValue()) / (Utils.fact(S.getValue()) * Math.pow(1 - rho.getValue(), 2)) + 1/mu.getValue()*lambda.getValue());
        return L;
    }

    /**
     *  Compute the probabilities of having i customer in the system, i being the index of the list
     * @return the list of probabilities
     */
    public List<FloatProperty> computeNbCustomerProbabilities() {
        int max = 5 * (int) Math.ceil(lambda.getValue());
        q = new ArrayList<FloatProperty>();
        // Calcul de q0
        Float q0 = (float) (1 / (getSumR() + Math.pow(rho.getValue()*S.getValue(), S.getValue())/(Utils.fact(S.getValue())*(1-rho.getValue()))));
        q.add(0, new SimpleFloatProperty(q0));

        // Calcul de qj, j<S
        for (int j = 1; j<S.getValue(); j++) {
            q.add(j, new SimpleFloatProperty((float) ((Math.pow(rho.getValue() * S.getValue(), j) / Utils.fact(j)) * q0)));
        }

        // Calcul de qS, j>=S
        for (int j = S.getValue(); j<max; j++) {
            q.add(j, new SimpleFloatProperty((float) ((Math.pow(S.getValue(), S.getValue())*Math.pow(rho.getValue(), j))/Utils.fact(S.getValue()))*q0));
        }

        return q;
    }

    /**
     * Compute a list of r (from j = 0 to S-1) used for q0 such that q0 = 1/(R + ((rho*S)^S)/S!(1-rho) where
     * R = \sum_{j=0}^{S-1} ((rho*S)^j)/j! = \sum_{j=0}^{S-1} r
     * @return
     */
    public List<Float> computeR(int S) {
        r = new ArrayList<Float>();
        for(int i = 0; i<=S-1; i++) {
            r.add((float) (Math.pow(rho.getValue() * S, i) / Utils.fact(i)));
        }
        return r;
    }

    private float getSumR() {
        float sumR = (float) 0.0;
        for (int i=0; i<=S.getValue()-1; i++) {
            sumR += r.get(i);
        }
        return sumR;
    }

    /**
     * Compute the probabilities for a person to stay longer than a time t (in unit of time)
     * @return List<FloatProperty>
     */
    public List<FloatProperty> computeWaitingTimeProbabilities(String timeunit) {
        double t;
        double incr = 0;
        switch (timeunit) {
            case "Milliseconde":
                incr = 10;
                break;
            case "Seconde":
                incr = 0.01;
                break;
            case "Minute":
                incr = 0.01;
                break;
            case "Heure":
                incr = 0.1;
                break;
            default :
                incr = 1;
        }
        float max = (float) Math.ceil(lambda.getValue() * 2);
        w.clear();
        for(t=0; t<max; t=t+incr) {
            w.add(new SimpleFloatProperty((float) (Math.exp(-mu.getValue() * t)
                                                    * (1 + (q.get(0).getValue()*(Math.pow(rho.getValue()*S.getValue(), S.getValue())))
                                                            /(Utils.fact(S.getValue())*(1-rho.getValue()))
                                                    * (1 - Math.exp(-mu.getValue()*t*(S.getValue()-1-rho.getValue()*S.getValue())))
                                                            /(S.getValue() - 1 - rho.getValue()*S.getValue())))));
        }
        return w;
    }
}