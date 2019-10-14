package model;

public class MM1 extends FileAttente {

    MM1() {
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


}