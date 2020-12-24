package iteration2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseIO<T> {

    protected ObjectMapper mapper = new ObjectMapper();
    protected T model;
    protected String ioLocation;

    public T getModel() {
        return model;
    }
}
