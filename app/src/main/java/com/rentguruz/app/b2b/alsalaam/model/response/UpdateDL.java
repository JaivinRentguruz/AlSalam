package com.rentguruz.app.b2b.alsalaam.model.response;

import com.rentguruz.app.b2b.alsalaam.model.AddressesModel;
import com.rentguruz.app.b2b.alsalaam.model.AttachmentsModel;
import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;
import com.rentguruz.app.b2b.alsalaam.model.DrivingLicenceModel;

import java.io.Serializable;

public class UpdateDL extends BaseModel implements Serializable {
    public int Id;
    public int DetailId,CustomerId;
    //public Boolean IsActive;
    public String FName,LName,DOB, PhoneNo,Email,MobileNo;
    public String Fname,Lname,FullName;

    public DrivingLicenceModel DrivingLicenceModel = new DrivingLicenceModel();
    public Boolean GetWithDrivingLicence;

    public AddressesModel AddressesModel = new AddressesModel();

    public AttachmentsModel AttachmentsModel = new AttachmentsModel();

    public int RelationId;
    public String DisplayIssuedBy,RelationDesc;

}
