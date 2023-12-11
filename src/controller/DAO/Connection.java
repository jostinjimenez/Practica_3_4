package controller.DAO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import lombok.Getter;

import java.io.File;

public class Connection {
    private static final String URL = "data" + File.separatorChar;
    private static XStream xStream;

    private Connection() {
        xStream = new XStream(new JettisonMappedXmlDriver());
    }

    public static String getURL() {
        return URL;
    }

    public static XStream getxStream() {
        if (xStream == null) {
            xStream = new XStream(new JettisonMappedXmlDriver());
            //xStream.setMode(XStream.NO_REFERENCES);
            xStream.addPermission(AnyTypePermission.ANY);
        }
        return xStream;
    }

}
