package iteration2.controller;

import org.json.simple.parser.JSONParser;

public abstract class Receiver<T> extends BaseIO<T> {

    protected JSONParser jsonParser = new JSONParser();

    public abstract void getJsonObject(String ioLocation);
}
