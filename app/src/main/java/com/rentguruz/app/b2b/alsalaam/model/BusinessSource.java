package com.rentguruz.app.b2b.alsalaam.model;

import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;

import java.io.Serializable;

public class BusinessSource extends BaseModel implements Serializable {
    public int Id,TableType;
    public String Name;
    public Boolean IsDefault;
}
