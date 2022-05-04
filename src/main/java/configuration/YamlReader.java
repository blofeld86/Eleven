package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import model.EnvironmentStructure;
import model.Pojo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Slf4j
public class YamlReader {


    public static Pojo getProperties(){
        final ObjectMapper objectMapper =new ObjectMapper(new YAMLFactory());
        try{
            return objectMapper.readValue(new File(System.getProperty("user.dir")+
                    "\\src\\main\\resources\\properties.yaml"), Pojo.class);
        }catch (IOException e){
            log.error("Can not read properties due to IOException");
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
                    log.info("Loaded environment property: {} = {}",entry.getKey().toString(),entry.getValue().toString());
                }
                log.info("Loaded environment properties total: {}", environmentProperties.size());
                break;
            }
        }
    }
}
