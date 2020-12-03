package iteration1;

import java.util.ArrayList;

public class Dataset {
    private int datasetID;
    public String name;
    private ArrayList<Data> dataList;
    private ArrayList<Integer> possibleLabelList;

    public Dataset(int id, String name, ArrayList<Data> datalist, ArrayList<Integer> labellist) {
        this.datasetID = id;
        this.name = name;
        this.dataList = datalist;
        this.possibleLabelList = labellist;
    }

    public ArrayList<Data> getDataList() {
        return dataList;
    }

    public ArrayList<Integer> getPossibleLabelList() {
        return possibleLabelList;
    }
}
