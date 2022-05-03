package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.EnvironmentStructure;
import model.Pojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YamlReader {

    private static YamlReader instance;;

    private YamlReader(){
        if(instance != null) {
            throw new RuntimeException("Not allowed. Please use getInstance() method");
        }
        setPropertiesFromYamlEnvironment();
    }

    public static YamlReader getInstance() {
        if( instance ==  null){
            return instance = new YamlReader();
        }
        return instance;
    }

    private static final Logger logger = LoggerFactory.getLogger("YamlReader.class");

    public static Pojo getProperties(){
        final ObjectMapper objectMapper =new ObjectMapper(new YAMLFactory());
        try{
            return objectMapper.readValue(new File(System.getProperty("user.dir")+
                    "\\src\\main\\resources\\properties.yaml"), Pojo.class);
        }catch (IOException e){
            logger.error("Can not read properties due to IOException");
        }
        return null;
    }

    public static void setPropertiesFromYamlEnvironment(){
        List<EnvironmentStructure> listOfEnvironments = getProperties().getEnvironments().listOfEnvironments();
        boolean foundActiveEnvironment = false;
        for (EnvironmentStructure environmentStructure : listOfEnvironments){
            if(environmentStructure.isActive()){
                foundActiveEnvironment = true;
                Map<String,Object>environmentProperties = environmentStructure.getStructure();
                for (Map.Entry entry : environmentProperties.entrySet()){
                    System.setProperty(entry.getKey().toString(),entry.getValue().toString());
                    logger.info("Loaded environment property: {} = {}",entry.getKey().toString(),entry.getValue().toString());
                }
                logger.info("Loaded environment properties total: {}", environmentProperties.size());
                break;
            }
        }
    }
}
