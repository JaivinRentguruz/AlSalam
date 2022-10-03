package com.rentguruz.app.b2b.alsalaam.model.checkinout;

import com.rentguruz.app.b2b.alsalaam.model.AttachmentsModel;
import com.rentguruz.app.b2b.alsalaam.model.base.BaseModel;

import java.util.ArrayList;

public class CheckoutSignatureDisplay extends BaseModel {
    public int ReservationId, SignType;

   public AttachmentsModel AttachmentsModel = new AttachmentsModel();
}
