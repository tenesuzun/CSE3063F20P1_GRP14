package iteration2.controller;

import iteration2.models.ConfigModel;
import org.json.simple.JSONObject;
import java.io.FileReader;

public class ConfigReceiver extends Receiver<ConfigModel>{

    public ConfigReceiver(){
        System.out.println("Config Receiver object created.");
    }

    @Override
    public void getJsonObject(String ioLocation){
        this.ioLocation = ioLocation;
        try{
            System.out.println(ioLocation+".json read started.");
            Object obj = jsonParser.parse(new FileReader(ioLocation));
            JSONObject jsonObject = (JSONObject) obj;
            model = mapper.readValue(jsonObject.toJSONString(), ConfigModel.class);
            System.out.println(ioLocation+" read finished.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
