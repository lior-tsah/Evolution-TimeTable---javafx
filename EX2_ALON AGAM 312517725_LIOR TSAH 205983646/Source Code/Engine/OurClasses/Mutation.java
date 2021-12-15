package OurClasses;

import genreatedClasses.ETTMutation;

import java.util.Random;

public abstract class Mutation {
    protected ETTMutation m_ETTMutation;
    protected Random rnd = new Random();
    protected String Name;
    protected double probability;
    protected String configuration;

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    public ETTMutation getM_ETTMutation() {
        return m_ETTMutation;
    }

    public void setM_ETTMutation(ETTMutation m_ETTMutation) {
        this.m_ETTMutation = m_ETTMutation;
    }

    public abstract void executeMutation(Descriptor descriptor, Solution solution);

    public abstract void splitConfiguration(ETTMutation ettMutation);
}
