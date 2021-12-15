package OurClasses;

import Exceptions.*;
import genreatedClasses.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CheckXmlFile {

    public void checkFile(File file, Descriptor descriptor) throws Exception {
        descriptor.setEttDescriptor(checkDataFromFile(file));
    }

    private ETTDescriptor checkDataFromFile(File file) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(ETTDescriptor.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ETTDescriptor temp = (ETTDescriptor) unmarshaller.unmarshal(file);
        checkNumbering(temp);//3.2
        checkRules(temp.getETTTimeTable().getETTRules().getETTRule());//3.3
        checkKnowledgeOfTeacher(temp);//3.4
        checkClassesSubjects(temp);//3.5
        checkValidHours(temp);//3.6
        checkElitismSize(temp);//3.7

        return temp;
    }

    private void checkElitismSize(ETTDescriptor temp) throws ElitismSizeException {
        if(temp.getETTEvolutionEngine().getETTSelection().getETTElitism()!=null)
        {
            int number = temp.getETTEvolutionEngine().getETTSelection().getETTElitism().intValue();
            if(number>temp.getETTEvolutionEngine().getETTInitialPopulation().getSize()){
                throw new ElitismSizeException();
            }
        }
    }

    private void checkValidHours(ETTDescriptor temp) throws Exception {
        int hours = temp.getETTTimeTable().getHours();
        int days = temp.getETTTimeTable().getDays();
        int maxHours = hours * days;
        List<ETTClass> listOfClasses = temp.getETTTimeTable().getETTClasses().getETTClass();

        for (ETTClass ettClass : listOfClasses) {
            ETTStudy[] arrayOfRequirements = ettClass.getETTRequirements().getETTStudy().toArray
                    (new ETTStudy[ettClass.getETTRequirements().getETTStudy().size()]);
            int currentClassHours = 0;
            for (int j = 0; j < arrayOfRequirements.length; j++) {
                currentClassHours += arrayOfRequirements[j].getHours();
            }
            if (currentClassHours > maxHours) {
                throw new MaximumHoursException(ettClass.getETTName());
            }
        }
    }

    private void checkClassesSubjects(ETTDescriptor temp) throws Exception {
        List<ETTClass> listOfClasses = temp.getETTTimeTable().getETTClasses().getETTClass();
        List<ETTSubject> listOfSubjects = temp.getETTTimeTable().getETTSubjects().getETTSubject();
        ETTClass[] arrayOfClasses = listOfClasses.toArray(new ETTClass[listOfClasses.size()]);
        ETTSubject[] arrayOfSubjects = listOfSubjects.toArray(new ETTSubject[listOfSubjects.size()]);
        boolean flag;

        for (int i = 0; i < arrayOfClasses.length; i++) {
            ETTStudy[] arrayOfRequirements = arrayOfClasses[i].getETTRequirements().getETTStudy().toArray
                    (new ETTStudy[arrayOfClasses[i].getETTRequirements().getETTStudy().size()]);
            for (int j = 0; j < arrayOfRequirements.length; j++) {
                flag = false;
                for (int k = 0; k < arrayOfSubjects.length; k++) {
                    if (arrayOfRequirements[j].getSubjectId() == arrayOfSubjects[k].getId()) {
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    throw new SubjectNotExistException(arrayOfClasses[i].getETTName());

                }
            }
        }
    }

    private void checkKnowledgeOfTeacher(ETTDescriptor temp) throws Exception {
        List<ETTTeacher> listOfTeachers = temp.getETTTimeTable().getETTTeachers().getETTTeacher();
        List<ETTSubject> listOfSubjects = temp.getETTTimeTable().getETTSubjects().getETTSubject();
        ETTTeacher[] arrayOfTeachers = listOfTeachers.toArray(new ETTTeacher[listOfTeachers.size()]);
        ETTSubject[] arrayOfSubjects = listOfSubjects.toArray(new ETTSubject[listOfSubjects.size()]);
        boolean flag;

        for (int i = 0; i < arrayOfTeachers.length; i++) {
            ETTTeaches[] arrayOfTeaching = arrayOfTeachers[i].getETTTeaching().getETTTeaches().toArray
                    (new ETTTeaches[arrayOfTeachers[i].getETTTeaching().getETTTeaches().size()]);
            for (int j = 0; j < arrayOfTeaching.length; j++) {
                flag = false;
                for (int k = 0; k < arrayOfSubjects.length; k++) {
                    if (arrayOfTeaching[j].getSubjectId() == arrayOfSubjects[k].getId()) {
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    throw new KnowledgeOfTeacherException(arrayOfTeachers[i].getETTName());
                }
            }
        }
    }

    private void checkRules(List<ETTRule> ettRules) throws Exception {
        if (ettRules.size() > 0) {
            Collections.sort(ettRules, Comparator.comparing(ETTRule::getETTRuleId));
        }
        ETTRule[] array = ettRules.toArray(new ETTRule[ettRules.size()]);

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].getETTRuleId().equals(array[i + 1].getETTRuleId())) {
                throw new RuleDuplicateException(array[i].getETTRuleId());
            }
        }
    }

    private void checkNumbering(ETTDescriptor temp) throws Exception {
        checkSubjectsIds(temp.getETTTimeTable().getETTSubjects().getETTSubject());
        checkClassedIds(temp.getETTTimeTable().getETTClasses().getETTClass());
        checkTeachersIds(temp.getETTTimeTable().getETTTeachers().getETTTeacher());
    }

    private void checkClassedIds(List<ETTClass> ettClass) throws Exception {
        Comparator<ETTClass> comparator = Comparator.comparingInt(ETTClass::getId);
        Collections.sort(ettClass, comparator);
        int i = 1;
        for (ETTClass currentClass : ettClass) {
            Comparator<ETTStudy> study = Comparator.comparingInt(ETTStudy::getSubjectId);
            Collections.sort(currentClass.getETTRequirements().getETTStudy(), study);
            if (currentClass.getId() != i) {
                throw new ClassesNumberingException();
            }
            i++;
        }
    }

    private void checkTeachersIds(List<ETTTeacher> ettTeacher) throws Exception {
        Comparator<ETTTeacher> comparator = Comparator.comparingInt(ETTTeacher::getId);
        Collections.sort(ettTeacher, comparator);
        int i = 1;
        for (ETTTeacher currentTeacher : ettTeacher) {
            if (currentTeacher.getId() != i) {
                throw new TeachersNumberingException();
            }
            i++;
        }
    }

    private void checkSubjectsIds(List<ETTSubject> ettSubject) throws Exception {
        Comparator<ETTSubject> comparator = Comparator.comparingInt(ETTSubject::getId);
        Collections.sort(ettSubject, comparator);
        int i = 1;
        for (ETTSubject currentSubject : ettSubject) {
            if (currentSubject.getId() != i) {
                throw new SubjectsNumberingException();
            }
            i++;
        }
    }

    private boolean checkFileExtension(String name) {
        return name.endsWith(".XML");
    }
}
