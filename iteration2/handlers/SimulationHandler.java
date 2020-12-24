package iteration2.handlers;

import iteration2.controller.ConfigReceiver;
import iteration2.controller.InputReceiver;
import iteration2.controller.OutputGenerator;
import iteration2.models.AssignmentModel;
import iteration2.models.InputModel;
import iteration2.models.PathModel;
import iteration2.models.UserModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationHandler {

    private final List<InputModel> inputModels = new ArrayList<>();

    private final ConfigReceiver configReceiver = new ConfigReceiver();
    private final InputReceiver inputReceiver = new InputReceiver();
    private final OutputGenerator outputGenerator = new OutputGenerator();
    private final String CONFIG_LOCATION = ConfigReceiver.class.getPackageName().split("\\.")[0]+"\\inputs\\config.json";
    private final List<AssignmentModel> labelAssignements = new ArrayList<>();

    public void startSimulation(){
        configReceiver.getJsonObject(CONFIG_LOCATION);
        for (PathModel pathModel : configReceiver.getModel().getPaths()){
            inputReceiver.getJsonObject(pathModel.getPath());
            inputModels.add(inputReceiver.getModel());
            System.out.println("Model "+inputReceiver.getModel().getId()+" added to "+
                    pathModel.getPath()+" path.");
        }

        LabelAssignementHandler assignementHandler = new LabelAssignementHandler();

        //Users that will label the selected dataset.
        List<UserModel> usersForDataset = matchDatasetWithUsers(configReceiver.getModel().getCurrentDatasetID(), configReceiver.getModel().getUsers());
        //Dataset to be labeled
        InputModel selectedDataset = inputModels.stream().filter(inputModel ->
                inputModel.getId() == configReceiver.getModel().getCurrentDatasetID()
        ).collect(Collectors.toList()).get(0);

        for (UserModel userModel: usersForDataset){
            assignementHandler.labelDataset(userModel, selectedDataset);
        }
        System.out.println("Dataset with ID: "+configReceiver.getModel().getCurrentDatasetID()+" labeling finished.");

        labelAssignements.addAll(assignementHandler.getLabelAssignements());

        /*for (PathModel pathModel: configReceiver.getModel().getPaths()){
            LabelAssignementHandler assignementHandler = new LabelAssignementHandler();

            //Users that will label the selected dataset.
            List<UserModel> usersForDataset = matchDatasetWithUsers(pathModel.getDatasetID(), configReceiver.getModel().getUsers());
            //Dataset to be labeled
            InputModel selectedDataset = inputModels.stream().filter(inputModel ->
                    inputModel.getId() == pathModel.getDatasetID()
            ).collect(Collectors.toList()).get(0);

            for (UserModel userModel: usersForDataset){
                assignementHandler.labelDataset(userModel, selectedDataset);
            }
            System.out.println("Dataset with ID: "+pathModel.getDatasetID()+" labeling finished.");

            labelAssignements.addAll(assignementHandler.getLabelAssignements());
        }*/

        outputGenerator.createOutputForUser(
                inputModels,
                labelAssignements,
                configReceiver.getModel().getUsers()
        );
    }

    private List<UserModel> matchDatasetWithUsers(int datasetID, List<UserModel> users){
        List<UserModel> tempUsers = new ArrayList<>();
        for (UserModel userModel: users){
            if (userModel.getDatasets().contains(datasetID))
                tempUsers.add(userModel);
        }
        return tempUsers;
    }
}
