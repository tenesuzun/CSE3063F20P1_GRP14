package iteration2.controller;

import iteration2.models.InputModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class InputReceiver extends Receiver<InputModel> {

    public InputReceiver(){
        System.out.println("Input Receiver object created.");
    }

    @Override
    public void getJsonObject(String ioLocation){
        this.ioLocation = ioLocation;
        try{
            System.out.println(ioLocation+" read started.");
            Object obj = jsonParser.parse(new FileReader(ioLocation));
            JSONObject jsonObject = (JSONObject) obj;
            model = mapper.readValue(jsonObject.toJSONString(), InputModel.class);
            System.out.println(ioLocation+" read finished.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
