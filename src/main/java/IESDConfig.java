import java.util.Properties;

class IESDConfig
{
    private Properties configFile;
    IESDConfig()
    {
        configFile = new java.util.Properties();
        try {
            configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.cfg"));
        }catch(Exception eta){
            eta.printStackTrace();
        }
    }

    String getProperty(String key)
    {
        return this.configFile.getProperty(key);
    }
}