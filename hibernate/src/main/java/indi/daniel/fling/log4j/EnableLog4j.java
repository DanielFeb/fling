package indi.daniel.fling.log4j;

/**
 * Created by daniel on 2018/8/5.
 */

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.net.URL;

public class EnableLog4j {
    static Logger logger = Logger.getLogger(EnableLog4j.class);
    static {
        BasicConfigurator.configure();
        logger.setLevel(Level.DEBUG);
        URL propUrl = EnableLog4j.class.getResource("/application.properties");

        if ( null != propUrl ){
            PropertyConfigurator.configure(propUrl.getFile());
        } else {
            logger.warn("Log4j properties is not defined, use default configuration");
        }
    }
}
