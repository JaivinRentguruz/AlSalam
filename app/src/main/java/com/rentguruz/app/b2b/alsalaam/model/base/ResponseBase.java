package com.rentguruz.app.b2b.alsalaam.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseBase<T>  implements Serializable {
    public Boolean Status;
    public String Message,DataVersion;
    public List <T>  Data =  new ArrayList<>();
}
