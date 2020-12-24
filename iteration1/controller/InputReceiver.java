package iteration1.controller;

import iteration1.models.InputModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class InputReceiver extends BaseIO<InputModel> {

    public InputReceiver() {
        ioLocation = "iteration1\\inputs\\input";
    }

    public void getJsonObject(String inputName){
        JSONParser parser = new JSONParser();
        try{
            System.out.println(ioLocation+inputName+".json read started.");
            Object obj = parser.parse(new FileReader(ioLocation+inputName+".json"));
            JSONObject jsonObject = (JSONObject) obj;
            model = mapper.readValue(jsonObject.toJSONString(), InputModel.class);
            System.out.println(ioLocation+inputName+".json read finished.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
