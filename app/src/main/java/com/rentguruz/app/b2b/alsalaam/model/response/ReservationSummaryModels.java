package com.rentguruz.app.b2b.alsalaam.model.response;

import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;

import java.io.Serializable;

public class ReservationSummaryModels extends BaseModel implements Serializable {
    public int ReservationSummaryType,TotalDays;
    public String SummaryName;
    public Double TotalAmount;

    public ReserversationSummary[] ReservationSummaryDetailModels;
}
