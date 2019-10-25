package model;

import java.util.List;

public class MM1 extends FileAttente {

    public MM1() {
        super();
        S = 1;
    }

    public float computeRho() throws  ArithmeticException {
        rho = lambda/mu;
        if (rho > 1) {
            throw new  ArithmeticException("Lambda is > 1") ;
        }
        return rho;
    }

    public float computeNbCustomerInSystem() {
        L = lambda/(mu-lambda);
        return L;
    }

    public float computeNbCustomerInQueue() {
        Lq = (lambda*lambda)/(mu*(mu-lambda));
        return Lq;
    }

    public float computeMeanTimeInSystem() {
        W = 1/(mu-lambda);
        return W;
    }

    public float computeMeanTimeInQueue() {
        Wq = lambda/(mu*(mu-lambda));
        return Wq;
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