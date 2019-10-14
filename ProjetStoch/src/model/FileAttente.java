package model;

import java.util.List;

public class FileAttente {
    protected float lambda;     // Nombre de clients par unité de temps
    protected float mu;         // Nombre de services par unité de temps
    protected int S;            // Nombre de services (S)
    protected int K;            // Nombre maximum de clients dans le système (K)
    protected float rho;          // ratio lamdba/S*mu
    protected float L;            // nombre de clients dans le système
    protected float Lq;           // nombre de clients dans la file
    protected float W;            // temps d'attente moyen dans le système
    protected float Wq;           // temps d'attentee moyen dans la file 
    protected List<Float> q;      // P(Xt=i) : probabilité d'être à l'état i 

    public float getLambda() {
        return lambda;
    }

    public void setLambda(float l) {
        lambda = l;
    }

    public float getMu() {
        return mu;
    }

    public void setMu(float m) {
        mu = m;
    }

    public int getNbServer() {
        return S;
    }

    public void setNbServer(int nbServer) {
        S = nbServer;
    }

    public int getMaxCust() {
        return K;
    }

    public void setMaxCust(int maxCust) {
        K = maxCust;
    }

    public float getRho() {
        return rho;
    }

    public float getNbCustInSystem() {
        return L;
    }

    public float getNbCustInQueue() {
        return Lq;
    }

    public float getMeanTimeInSystem() {
        return W;
    }

    public float getMeanTimeInQueue() {
        return Wq;
    }

    public List<Float> getProbabilityOfStates() {
        return q;
    }
  
}