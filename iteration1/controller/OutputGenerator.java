package iteration1.controller;

import iteration1.models.*;
import java.io.File;
import java.util.List;
import java.util.Random;

public class OutputGenerator extends BaseIO<OutputModel> {

    public OutputGenerator() {
        ioLocation = "outputs\\output";
    }

    public void createOutputForUser(InputModel inputModel, List<AssignementModel> assignements, List<UserModel> userModels){
        model = new OutputModel(inputModel, assignements,userModels);
        try{
            mapper.writeValue(new File(ioLocation+(new Random().nextInt(5))+".json"), model);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
