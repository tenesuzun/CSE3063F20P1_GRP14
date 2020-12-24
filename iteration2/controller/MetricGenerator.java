package iteration2.controller;

import iteration2.models.*;
import java.io.File;

public class MetricGenerator extends BaseIO<MetricModel> {

    public MetricGenerator() {
        ioLocation = MetricGenerator.class.getPackageName().split("\\.")[0]+"\\metrics\\metric.json";
        System.out.println("MetricGenerator object created.");
    }

    public void createMetricJson(MetricModel metricModel){
        System.out.println("Metric generator started.");
        try{
            mapper.writeValue(new File(ioLocation), metricModel);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
