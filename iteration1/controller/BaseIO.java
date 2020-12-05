package iteration1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseIO<T> {

    protected ObjectMapper mapper = new ObjectMapper();
    protected T model;
    protected String ioLocation;

    public T getModel() {
        return model;
    }
}
