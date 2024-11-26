package espotify.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = null;
    
    /**
     * Requiere que la ubicacion del archivo .jar del proyecto sea /home/{usuario}/Espotify,
     * y la ubicacion del archivo servidorcentral.properties sea /home/{usuario}/Espotify/servidorcentral.properties
     */
    public ConfigReader() {};

    public static Properties getProperties() {
        if (properties == null) {
            File configFile =  new File(ConfigReader.getConfigPath());
        
            if (configFile.exists()) {        
                try {
                    FileInputStream configFileInputStream = new FileInputStream(configFile);
                    properties = new Properties();
                    properties.load(configFileInputStream);
                    configFileInputStream.close();
                    
                } catch (IOException e) {
                    System.err.println("No se pudo leer el archivo de configuración.");
                    properties = null;
                }
            } else {
                System.err.println("No se encontró el archivo de configuración en la ruta " + ConfigReader.getConfigPath());
            }   
        }
        return properties;
    }
    
    public static String getAppPath() {
        String appPath = System.getProperty("user.home") + "/Espotify";
        return appPath;
    }
    
    public static String getAppResourcesPath() {
        String resourcesPath = ConfigReader.getAppPath() + "/Resource";
        return resourcesPath;
    }
    
    public static String getConfigPath() {
        String configPath = ConfigReader.getAppPath() + "/servidorcentral.properties";
        return configPath;
    }

    public static String getAppName() {
        return ConfigReader.getProperties().getProperty("appname");
    }
    
    public static String getWebServiceIP() {
        
        if (ConfigReader.getProperties() == null) {
            return "localhost";
        }
        
        return ConfigReader.getProperties().getProperty("webserviceip");
    }

    public static String getWebServicePort() {
        
        if (ConfigReader.getProperties() == null) {
            return "8089";
        }
        
        return ConfigReader.getProperties().getProperty("webserviceport");
    }
    
    public static String getWebServiceBaseURL() {
        return ("http://" + ConfigReader.getWebServiceIP() + ":" + ConfigReader.getWebServicePort() + "/");
    }
    
    public static String getSenderEmail() {
        
        if (ConfigReader.getProperties() == null) {
            return "example@mail.com";
        }
        
        return ConfigReader.getProperties().getProperty("emailsender");
    }
    
    public static String getSenderEmailPassword() {
        
        if (ConfigReader.getProperties() == null) {
            return "";
        }
        
        return ConfigReader.getProperties().getProperty("emailsenderpassword");
    }
    
    public static String getSMTPhost() {
        
        if (ConfigReader.getProperties() == null) {
            return "127.0.0.1";
        }
        
        return ConfigReader.getProperties().getProperty("smtphost");
    }
    
    public static int getSMTPport() {
        
        if (ConfigReader.getProperties() == null) {
            return 25; //puerto por defecto que utiliza DevNull
        }
        
        return Integer.parseInt(ConfigReader.getProperties().getProperty("smtpport"));
    }
}
