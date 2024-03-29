package iteration1.controller;

import iteration1.models.*;
import java.io.File;
import java.util.List;
import java.util.Random;

public class OutputGenerator extends BaseIO<OutputModel> {

    public OutputGenerator() {
        ioLocation = "iteration1\\outputs\\output";
    }

    public void createOutputForUser(InputModel inputModel, List<AssignementModel> assignements, List<UserModel> userModels){
        System.out.println("Output generator started.");
        model = new OutputModel(inputModel, assignements,userModels);
        try{
            mapper.writeValue(new File(ioLocation+(new Random().nextInt(5))+".json"), model);
            System.out.println("Output written to location: "+ioLocation);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
