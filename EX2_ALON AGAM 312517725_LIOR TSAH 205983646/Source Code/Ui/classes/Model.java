package classes;

import OurClasses.*;

public class Model {
    private CheckXmlFile checkXmlFile = new CheckXmlFile();
    private Descriptor descriptor = new Descriptor();

    public CheckXmlFile getCheckXmlFile() {
        return checkXmlFile;
    }

    public Descriptor getDescriptor() {
        return descriptor;
    }

}