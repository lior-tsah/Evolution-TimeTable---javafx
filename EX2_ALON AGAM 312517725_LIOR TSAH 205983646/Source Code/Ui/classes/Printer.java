package classes;

import OurClasses.Descriptor;
import genreatedClasses.*;
import javafx.scene.control.TextArea;

import java.util.List;

public class Printer {

    public static void printAll(Descriptor descriptor, TextArea textArea, StringBuilder fieldContent1) {
        printListOfSubjects(descriptor.getEttDescriptor().getETTTimeTable().getETTSubjects().getETTSubject(), fieldContent1);
        printListOfTeachers(descriptor.getEttDescriptor().getETTTimeTable().getETTTeachers().getETTTeacher(), descriptor.getEttDescriptor().getETTTimeTable().getETTSubjects().getETTSubject(), fieldContent1);
        printListOfClasses(descriptor.getEttDescriptor().getETTTimeTable().getETTClasses().getETTClass(), descriptor.getEttDescriptor().getETTTimeTable().getETTSubjects().getETTSubject(), fieldContent1);
        printListOfRules(descriptor.getEttDescriptor().getETTTimeTable().getETTRules().getETTRule(), fieldContent1);
        printEngineAlgorithm(descriptor.getEttDescriptor().getETTEvolutionEngine(), fieldContent1);
        textArea.appendText(String.valueOf(fieldContent1));
    }

    private static void printEngineAlgorithm(ETTEvolutionEngine ettEvolutionEngine, StringBuilder fieldContent1) {
        printPopulationSize(ettEvolutionEngine.getETTInitialPopulation().getSize(), fieldContent1);
        printSelection(ettEvolutionEngine.getETTSelection(), fieldContent1);
        printCrossover(ettEvolutionEngine.getETTCrossover(), fieldContent1);
        printMutations(ettEvolutionEngine.getETTMutations(), fieldContent1);
    }

    private static void printMutations(ETTMutations ettMutations, StringBuilder fieldContent1) {
        fieldContent1.append("Mutations: \n");
        for (ETTMutation currentMutation : ettMutations.getETTMutation()) {
            fieldContent1.append("Name: " + currentMutation.getName());
            fieldContent1.append(", probability = " + currentMutation.getProbability());
            fieldContent1.append(", configuration: " + currentMutation.getConfiguration() + "\n");
        }
    }

    private static void printCrossover(ETTCrossover ettCrossover, StringBuilder fieldContent1) {
        fieldContent1.append("Crossover:\n");
        fieldContent1.append("Name: ");
        fieldContent1.append(ettCrossover.getName());
        fieldContent1.append(", cutting points: " + ettCrossover.getCuttingPoints() + "\n\n");
    }

    private static void printSelection(ETTSelection ettSelection, StringBuilder fieldContent1) {
        fieldContent1.append("Selection technique: ");
        fieldContent1.append(ettSelection.getType());
        fieldContent1.append(", Configuration: ");
        fieldContent1.append(ettSelection.getConfiguration()!=null?ettSelection.getConfiguration()+"":"No Configuration" + "");
        fieldContent1.append(", Elitism=");
        fieldContent1.append(ettSelection.getETTElitism());
        fieldContent1.append("\n\n");
    }

    private static void printPopulationSize(int size, StringBuilder fieldContent1) {
        fieldContent1.append("\n");
        fieldContent1.append("Population size: ");
        fieldContent1.append(size + "\n\n");
    }

    private static void printListOfRules(List<ETTRule> ettRule, StringBuilder fieldContent1) {
        fieldContent1.append("Rules:\n");
        for (ETTRule ettrule : ettRule) {
            fieldContent1.append("Name: " + ettrule.getETTRuleId() + ", type: " + ettrule.getType() + "\n");
        }
    }

    private static void printListOfClasses(List<ETTClass> ettClass, List<ETTSubject> ettSubject, StringBuilder fieldContent1) {
        for (ETTClass currentClass : ettClass) {
            fieldContent1.append("The name of class #" + currentClass.getId() + " is " + currentClass.getETTName() + " and it studying:\n");
            for (ETTStudy currentSubject : currentClass.getETTRequirements().getETTStudy()) {
                fieldContent1.append("subject #" + currentSubject.getSubjectId() + " is ");
                printNameOfSubjectById(currentSubject.getSubjectId(), ettSubject, fieldContent1);
                fieldContent1.append("and it been studying " + currentSubject.getHours() + " hours\n");
            }
            fieldContent1.append("\n");
        }
    }

    private static void printNameOfSubjectById(int subjectId, List<ETTSubject> ettSubject, StringBuilder fieldContent1) {
        for (ETTSubject currentSubject : ettSubject) {
            if (subjectId == currentSubject.getId()) {
                fieldContent1.append(" " + currentSubject.getName() + ", ");
                return;
            }
        }
    }

    private static void printListOfTeachers(List<ETTTeacher> ettTeacher, List<ETTSubject> ettSubject, StringBuilder fieldContent1) {
        for (ETTTeacher teacher : ettTeacher) {
            fieldContent1.append("The name of teacher # " + teacher.getId() + " is " + teacher.getETTName() + " and teaching:");
            for (ETTTeaches teaches : teacher.getETTTeaching().getETTTeaches()) {
                printNameOfSubjectById(teaches.getSubjectId(), ettSubject, fieldContent1);
            }
            fieldContent1.append("\n");
        }
        fieldContent1.append("\n");
    }

    private static void printListOfSubjects(List<ETTSubject> ettSubject, StringBuilder fieldContent1) {
        for (ETTSubject subject : ettSubject) {
            fieldContent1.append("Subject #" + subject.getId() + " is " + subject.getName() + "\n");
        }
        fieldContent1.append("\n");
    }
}