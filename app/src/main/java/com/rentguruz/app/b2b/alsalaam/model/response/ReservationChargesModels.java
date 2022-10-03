package com.rentguruz.app.b2b.alsalaam.model.response;

import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;

import java.io.Serializable;

public class ReservationChargesModels extends BaseModel implements Serializable {
    public int ChargeFor,Amount,AmountType,ChargeType;
    public Boolean IsTaxable;
}
