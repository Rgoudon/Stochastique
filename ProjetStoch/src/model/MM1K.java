package model;

import java.util.ArrayList;
import java.util.List;

public class MM1K extends FileAttente {
    

    public MM1K(int maxCustomer) {
        super();
        this.S = 1;
        this.K = maxCustomer;
    }

    public float computeRho() {
        rho = lambda/mu;
        return rho;
    }

    public float computeNbCustomerInSystem() {
        if (rho == 1) {
            L = K/2;
        }
        else {
            L = (float) (rho*(1 - (K+1)*Math.pow(rho, K) + K*Math.pow(rho, (K+1)))/
                        ((1 - rho)*(1 - Math.pow(rho,(K+1)))));
        }
        return this.L;
    }

    public float computeNbCustomerInQueue() {
        Lq = (float) computeNbCustomerInSystem() - (1 - computeNbCustomerProbabilities(0).get(0));
        return Lq;
    }

    public float computeMeanTimeInSystem() {
        return W = (float) computeNbCustomerInQueue()/lambda;
    }

    public float computeMeanTimeInQueue() {
        return Wq = (float) computeMeanTimeInSystem() - 1/mu;
    }

    /**
     *  Compute the probabilities of having i customer in the system, i being the index of the list
     * @return the list of probabilities
     */
    public List<Float> computeNbCustomerProbabilities(int max) {
        q = new ArrayList<>();
        // Calcul de q
        if (rho == 1) {
            for(int i=0;i<max;i++) {
                q.set(i, (float) (1/(K+1)));
            }
        }
        else {
            for(int i=0;i<max;i++) {
                q.set(i, (float) ((float) ((1 - rho)*Math.pow(rho, i))/(1 - Math.pow(rho, K+1))));
            }
        }
        return q;
    }
}