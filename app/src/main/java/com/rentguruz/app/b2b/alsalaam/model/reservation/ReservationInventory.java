package com.rentguruz.app.b2b.alsalaam.model.reservation;

import com.rentguruz.app.b2b.alsalaam.model.AttachmentsModel;
import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;

public class ReservationInventory extends BaseModel {
   public String Name,Code,Description,Size,ColorCode,Make,Model;
   public int TaxMasterId,DetailId,Id;
   public Boolean IsTaxable,isShowNoData;
   public Double Price;
   public AttachmentsModel AttachmentsModel;
}
