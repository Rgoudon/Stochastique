
public class FileAttente {
    protected float lambda;   // Nombre de clients par unité de temps
    protected float mu;       // Nombre de services par unité de temps
    protected int nbServer;   // Nombre de services (S)
    protected int maxCust;    // Nombre maximum de clients dans le système (K)

    public float getLambda() {
        return lambda;
    }

    public void setLambda(float lambda) {
        this.lambda = lambda;
    }

    public float getMu() {
        return mu;
    }

    public void setMu(float mu) {
        this.mu = mu;
    }

    public int getNbServer() {
        return nbServer;
    }

    public void setNbServer(int nbServer) {
        this.nbServer = nbServer;
    }

    public int getMaxCust() {
        return maxCust;
    }

    public void setMaxCust(int maxCust) {
        this.maxCust = maxCust;
    }
}