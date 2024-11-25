package espotify.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    
    public ConfigReader() {
        File configFile =  new File(ConfigReader.getConfigPath());
        
        if (configFile.exists()) {        
            try {
                FileInputStream configFileInputStream = new FileInputStream(configFile);
                properties = new Properties();
                properties.load(configFileInputStream);
                configFileInputStream.close();
                
                System.out.println(this.getAppName());
                System.out.println(this.getSMTPhost());
                System.out.println(this.getSMTPport());
                System.out.println(this.getSenderEmail());
                System.out.println(this.getSenderEmailPassword());
                System.out.println(this.getWebServiceIP());
                System.out.println(this.getWebServicePort());
                System.out.println(this.getWebServiceBaseURL());
                
            } catch (IOException e) {
                System.err.println("No se pudo leer el archivo de configuración.");
                e.printStackTrace();
            }
        } else {
            System.err.println("No se encontró el archivo de configuración en la ruta " + ConfigReader.getConfigPath());
        }    
    };

    public static String getAppPath() {
        String appPath = System.getProperty("user.home") + "/Espotify";
        return appPath;
    }
    
    public static String getAppResourcesPath() {
        String resourcesPath = ConfigReader.getAppPath() + "/Resource";
        return resourcesPath;
    }
    
    public static String getConfigPath() {
        String configPath = ConfigReader.getAppPath() + "/config.properties";
        return configPath;
    }
    
    public Properties getProperties() {
        return this.properties;
    }

    public String getAppName() {
        return this.getProperties().getProperty("appname");
    }
    
    public String getWebServiceIP() {
        return this.getProperties().getProperty("webserviceip");
    }
    
    public String getWebServicePort() {
        return this.getProperties().getProperty("webserviceport");
    }
    
    public String getWebServiceBaseURL() {
        return ("http://" + this.getWebServiceIP() + ":" + this.getWebServicePort() + "/");
    }
    
    public String getSenderEmail() {
        return this.getProperties().getProperty("emailsender");
    }
    
    public String getSenderEmailPassword() {
        return this.getProperties().getProperty("emailsenderpassword");
    }
    
    public String getSMTPhost() {
        return this.getProperties().getProperty("smtphost");
    }
    
    public int getSMTPport() {
        return Integer.parseInt(this.getProperties().getProperty("smtpport"));
    }
}
