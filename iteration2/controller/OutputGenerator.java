package iteration2.controller;

import iteration2.models.AssignmentModel;
import iteration2.models.InputModel;
import iteration2.models.UserModel;
import iteration2.models.OutputModel;
import java.io.File;
import java.util.List;
import java.util.Random;

public class OutputGenerator extends BaseIO<OutputModel> {

    public OutputGenerator() {
        ioLocation = OutputGenerator.class.getPackageName().split("\\.")[0]+"\\outputs\\output";
        System.out.println("OutputGenerator object created.");
    }

    public void createOutputForUser(List<InputModel> inputModel, List<AssignmentModel> assignements, List<UserModel> userModels){
        System.out.println("Output generator started.");
        model = new OutputModel(inputModel, assignements, userModels);
        try{
            mapper.writeValue(new File(ioLocation+(new Random().nextInt(5))+".json"), model);
            System.out.println("Output written to location: "+ioLocation);
            System.out.println("Output:\n"+model.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
