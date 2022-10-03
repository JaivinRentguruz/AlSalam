package com.rentguruz.app.b2b.alsalaam.model.reservation;

import com.rentguruz.app.b2b.alsalaam.model.AttachmentsModel;
import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;

public class ReservationEquipment extends BaseModel {
    public String Name,Description,SerialNo,Make,Model;
    public int Id,Quantity,TaxMasterId,LocationId,DetailId;
    public Boolean IsChargePerDay,UseMaxPrice,IsTaxable,GetForReservation;
    public Double Price,MaxPrice,ReplacementCost;

    public AttachmentsModel AttachmentsModel;
}
