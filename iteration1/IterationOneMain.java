package iteration1;

import iteration1.controller.InputReceiver;
import iteration1.controller.OutputGenerator;
import iteration1.models.AssignementModel;
import iteration1.models.InputModel;
import iteration1.models.UserModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IterationOneMain {

    private final static UserModel user1 = new UserModel(1,"UserModel","RandomBot");
    private final static UserModel user2 = new UserModel(2,"UserModel2","RandomBot");
    private final static UserModel user3 = new UserModel(3,"UserModel3","RandomBot");
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy',' HH:mm:ss");

    private final static AssignementModel assignement1 = new AssignementModel(1, new ArrayList<>() {{
        add(1);
        add(2);
    }},user1.getId(), dateFormat.format(new Date()));

    private final static AssignementModel assignement2 = new AssignementModel(2, new ArrayList<>() {{
        add(3);
        add(4);
        add(5);
        add(6);
        add(7);
        add(8);
    }},user2.getId(), dateFormat.format(new Date()));

    private final static AssignementModel assignement3 = new AssignementModel(3, new ArrayList<>() {{
        add(1);
    }},user3.getId(), dateFormat.format(new Date()));

    public static void main(String[] args) {
        InputReceiver inputReceiver = new InputReceiver();
        OutputGenerator outputGenerator = new OutputGenerator();
        inputReceiver.getJsonObject("1");
        InputModel inputModel = inputReceiver.getModel();

        outputGenerator.createOutputForUser(
                inputModel,
                new ArrayList<>(){{
                    add(assignement1);
                    add(assignement2);
                    add(assignement3);
                }},
                new ArrayList<>(){{
                    add(user1);
                    add(user3);
                }});

        outputGenerator.createOutputForUser(
                inputModel,
                new ArrayList<>(){{
                    add(assignement1);
                    add(assignement3);
                }},
                new ArrayList<>(){{
                    add(user2);
                }});

        outputGenerator.createOutputForUser(
                inputModel,
                new ArrayList<>(){{
                    add(assignement3);
                }},
                new ArrayList<>(){{
                    add(user3);
                }});
    }
}
