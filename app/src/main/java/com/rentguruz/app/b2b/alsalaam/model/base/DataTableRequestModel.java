package com.rentguruz.app.b2b.alsalaam.model.base;

import java.io.Serializable;

public class DataTableRequestModel  implements Serializable {
    public  int pageSize,limit,offset;
    public String orderDir,orderBy,filter,filterObj;
}
