package com.rentguruz.app.b2b.alsalaam.model.insert;

import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;

import java.io.Serializable;

public class TollCharges extends BaseModel  implements Serializable {
    public int Id,ReservationId,VehicleId;
    public Double Surcharge,TicketCharges,TotalPayable;

    public String Agency,EntryDateTime,EntryPlaza,ExitDateTime,ExitPlaza,LicencePlate,Notes,TransactionDate;

    public Boolean ChargeCustomer,IsTrasferTicketToDriver;

}
