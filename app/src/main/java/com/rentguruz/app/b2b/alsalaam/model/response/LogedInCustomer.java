package com.rentguruz.app.b2b.alsalaam.model.response;

import com.rentguruz.app.b2b.alsalaam.model.AddressesModel;
import com.rentguruz.app.b2b.alsalaam.model.AttachmentsModel;

import java.io.Serializable;

public class LogedInCustomer implements Serializable {
    public String Email, FullName,MobileNo;
    public AddressesModel AddressesModel = new AddressesModel();
    public AttachmentsModel AttachmentsModel = new AttachmentsModel();
}
