package com.rentguruz.app.b2b.alsalaam.model.checkinout;

import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;

import java.io.Serializable;

public class ReservationCheckInModel extends BaseModel implements Serializable {

    public int VehicleId,ReservationId,      CheckInOdo, IsNoExtraMiles ,ReturnFuelInPR ,ReturnFuelInUnit ,IsNoExtraFuel     ;
    public String ReturnDate ,ReturnLocation ;
}
