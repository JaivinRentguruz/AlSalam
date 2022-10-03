package com.rentguruz.app.b2b.alsalaam.model.response;

import com.rentguruz.app.b2b.alsalaam.model.AddressesModel;

import java.io.Serializable;

public class User implements Serializable {
    public int CompanyId,UserType,UserFor,Id,LocationId;
    public String UserName,Email;
    public Boolean IsSuperAdmin,IsAdmin;
    public AddressesModel addressesModel = new AddressesModel();
    
    public String Telephone;
    public String LastName,FirtName;
}
