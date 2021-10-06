package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter02.pojo;

import java.util.Properties;

public class PhoneBook {
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "properties=" + properties +
                '}';
    }
}
