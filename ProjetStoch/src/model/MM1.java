package model;

public class MM1 extends FileAttente {

    MM1() {
        super();
        nbServer = 1;
    }

    public float computeRho() {
        return this.lambda/this.mu;
    }

    public float computeNbCustomerInSystem() {
        return this.lambda/(this.mu-this.lambda);
    }

    public float computeNbCustomerInQueue() {
        return (this.lambda*this.lambda)/(this.mu*(this.mu-this.lambda));
    }

    public float computeMeanTimeInSystem() {
        return 1/(this.mu-this.lambda);
    }

    public float computeMeanTimeInQueue() {
        return this.lambda/(this.mu*(this.mu-this.lambda));
    }


}