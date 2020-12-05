package iteration1.controller;

import iteration1.models.InputModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class InputReceiver extends BaseIO<InputModel> {

    public InputReceiver() {
        ioLocation = "inputs\\input";
    }

    public void getJsonObject(String inputName){
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(ioLocation+inputName+".json"));
            JSONObject jsonObject = (JSONObject) obj;
            model = mapper.readValue(jsonObject.toJSONString(), InputModel.class);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}