package model;

import java.util.List;

public class MM1K extends FileAttente {
    

    MM1K(int maxCustomer) {
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
        return (float) 0.0;
    }

    public float computeMeanTimeInSystem() {
        return (float) 0.0;
    }

    public float computeMeanTimeInQueue() {
        return (float) 0.0;
    }
}