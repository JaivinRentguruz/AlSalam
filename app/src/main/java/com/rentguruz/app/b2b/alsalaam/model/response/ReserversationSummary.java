package com.rentguruz.app.b2b.alsalaam.model.response;

import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;

import java.io.Serializable;

public class ReserversationSummary extends BaseModel implements Serializable {
    public String Title,Description,ShortDescription;

    public int ReservationSummaryDetailType;
    public Double Total,PricePerTime;
}
