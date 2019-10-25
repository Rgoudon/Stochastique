package model;

import java.util.ArrayList;
import java.util.List;

public class MMS extends FileAttente {
    private List<Float> r;      // Tableau utilisé pour calculer q0

    public MMS(int nbServer) {
        super();
        S = nbServer;
        computeR();
    }

    public float computeRho() {
        rho = lambda/(S*mu);
        return rho;
    }

    /**
     *  Compute the probabilities of having i customer in the system, i being the index of the list
     * @return the list of probabilities
     */
    public List<Float> computeNbCustomerProbabilities(int max) {
        // Calcul de q0
        float q0 = (float) (1 / (getSumR() + Math.pow(rho*S, S)/(Utils.fact(S)*(1-rho))));
        q.add(q0);

        // Calcul de qj, j<S
        for (int j = 1; j<S; j++) {
            q.add(j, (float) ((Math.pow(rho * S, j) / Utils.fact(j)) * q0));
        }

        // Calcul de qS, j>=S
        for (int j = S; j<max; j++) {
            q.add(j, (float) ((Math.pow(S, S)*Math.pow(rho, j))/Utils.fact(S))*q0);
        }

        return q;
    }

    /**
     * Compute a list of r (from j = 0 to S-1) used for q0 such that q0 = 1/(R + ((rho*S)^S)/S!(1-rho) where 
     * R = \sum_{j=0}^{S-1} ((rho*S)^j)/j! = \sum_{j=0}^{S-1} r
     * @return
     */
    private List<Float> computeR() {
        r = new ArrayList<Float>();
        for(int i=0; i<=S-1; i++) {
            r.add((float) (Math.pow(rho * S, i) / Utils.fact(i)));
        }
        return r;
    }

    private float getSumR() {
        float sumR = (float) 0.0;
        for (int i=0; i<=S-1; i++) {
            sumR += r.get(i);
        }
        return sumR;
    }

    /**
     * Compute the mean number of customers in the queue
     * @return float the mean number of customer in the queue
     */
    public float computeNbCustomerInQueue() {
        Lq = (float) (q.get(0) * (Math.pow(rho * S, S) * rho) / (Utils.fact(S) * Math.pow(1 - rho, 2)));
        return Lq;
    }

    /**
     * Compute the mean time spent by a customer in the queue.
     * The formula is Wq = Lq/lambda, but here the computation doesn't use 
     * computeNbCustomerInQueue(). It recalculates it.
     * @return float the mean time spent by a customer in the queue
     */
    public float computeMeanTimeInQueue() {
        Wq = (float) ((float) (q.get(0)/lambda) * (Math.pow(rho * S, S) * rho) / (Utils.fact(S) * Math.pow(1 - rho, 2)));
        return Wq;
    }

    /**
     * Compute the mean time spent by a customer in the system.
     * The formula is W = Wq + 1/mu, but here the computation doesn't use 
     * computeMeanTimeInQueue(). It recalculates it.
     * @return float the mean time spent by a customer in the system
     */
    public float computeMeanTimeInSystem() {
        W = (float) ((float) (q.get(0)/lambda) * (Math.pow(rho * S, S) * rho) / (Utils.fact(S) * Math.pow(1 - rho, 2))) + 1/mu;
        return W;
    }

    /**
     * Compute the mean number of customers in the system.
     * The formula is L = lambda*W, but here the computation doesn't use 
     * computeMeanTimeInSystem(). It recalculates it.
     * @return float the mean number of customers in the system
     */
    public float computeNbCustomerInSystem() {
        L = ((float) ((float) (q.get(0)/lambda) * (Math.pow(rho * S, S) * rho) / (Utils.fact(S) * Math.pow(1 - rho, 2)) + 1/mu))*lambda;
        return L;
    }
}