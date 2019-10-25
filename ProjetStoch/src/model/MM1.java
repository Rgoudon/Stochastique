package model;

import java.util.List;

public class MM1 extends FileAttente {

    public MM1() {
        super();
        S = 1;
    }

    public float computeRho() {
        return lambda/mu;
    }

    public float computeNbCustomerInSystem() {
        return lambda/(mu-lambda);
    }

    public float computeNbCustomerInQueue() {
        return (lambda*lambda)/(mu*(mu-lambda));
    }

    public float computeMeanTimeInSystem() {
        return 1/(mu-lambda);
    }

    public float computeMeanTimeInQueue() {
        return lambda/(mu*(mu-lambda));
    }

    /**
     *  Compute the probabilities of having i customer in the system, i being the index of the list
     * @return the list of probabilities
     */
    public List<Float> computeNbCustomerProbabilities(int max) {
        // Calcul de q
        for(int i = 0; i<max; i++) {
            q.set(i, (float) (Math.pow(rho, i)*(1-rho)));
        }
        return q;
    }

    /**
     * Compute the probability for a person to stay longer than a time t (in unit of time)
     * @param t
     * @return Float
     */
    public Float computeProbabilityOfStayingInSystem(int t) {
        return (float) Math.exp(-mu*(1 - rho)*t);
    }
}