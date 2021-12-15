package OurClasses;

import genreatedClasses.ETTMutation;
import genreatedClasses.ETTMutations;

import java.util.ArrayList;
import java.util.List;

public class Mutations {
    private ETTMutations m_ETTMutations;
    protected Mutation m_Mutation;
    protected List<Mutation> mutationList = new ArrayList<>();

    public Mutations(ETTMutations ettMutations) {
        this.m_ETTMutations = ettMutations;
        for (ETTMutation currentMutation : ettMutations.getETTMutation()) {
            switch (currentMutation.getName()) {
                case "Flipping":
                    m_Mutation = new Flipping(currentMutation);
                    mutationList.add(m_Mutation);
                    break;
                case "Sizer":
                    m_Mutation = new Sizer(currentMutation);
                    mutationList.add(m_Mutation);
                    break;
            }
        }
    }

    public List<Mutation> getMutationList() {
        return mutationList;
    }

    public void setMutationList(List<Mutation> mutationList) {
        this.mutationList = mutationList;
    }

    public ETTMutations getM_ETTMutations() {
        return m_ETTMutations;
    }

    public void setM_ETTMutations(ETTMutations m_ETTMutations) {
        this.m_ETTMutations = m_ETTMutations;
    }

    private void checkMutationType(ETTMutation currentMutation) {
        switch (currentMutation.getName()) {
            case "Flipping":
                m_Mutation = new Flipping(currentMutation);
                break;
            case "Sizer":
                m_Mutation = new Sizer(currentMutation);
                break;
        }
    }

    public void executeMutations(Descriptor descriptor, Solution solutionToChange) {
//            for (ETTMutation currentMutation : descriptor.getEttDescriptor().getETTEvolutionEngine().getETTMutations().getETTMutation()) {
//                checkMutationType(currentMutation);
//                m_Mutation.executeMutation(descriptor, solutionToChange);
//            }
        for (Mutation currentMutation : this.mutationList) {
            currentMutation.executeMutation(descriptor, solutionToChange);
        }
    }

    public Mutation getM_Mutation() {
        return m_Mutation;
    }

    public void setM_Mutation(Mutation m_Mutation) {
        this.m_Mutation = m_Mutation;
    }
}