package com.rentguruz.app.b2b.alsalaam.flexiicar.booking2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rentguruz.app.b2b.alsalaam.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.androidnetworking.AndroidNetworking;
import com.rentguruz.app.b2b.alsalaam.model.CreditCardModel;
import com.rentguruz.app.b2b.alsalaam.model.response.CustomerProfile;
import com.rentguruz.app.b2b.alsalaam.adapters.CustomToast;
import com.rentguruz.app.b2b.alsalaam.adapters.LoginRes;
import com.rentguruz.app.b2b.alsalaam.flexiicar.commonfragment.Fragment_Term_And_Condition;
import com.rentguruz.app.b2b.alsalaam.apicall.OnResponseListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_CreditCards_List extends Fragment
{
    Handler handler = new Handler();
    public static Context context;
    public String id = "", transactionId;
    ImageView back;
    TextView txt_Addcards;
    Bundle BookingBundle,VehicleBundle;
    Bundle returnLocationBundle, locationBundle;
    Boolean locationType, initialSelect;
    LoginRes loginRes;
    CustomerProfile customerProfile;
    CreditCardModel cardModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        loginRes = new LoginRes(getContext());
        customerProfile = new CustomerProfile();
        customerProfile = loginRes.callFriend("CustomerProfile", CustomerProfile.class);
        cardModel = new CreditCardModel();
        return inflater.inflate(R.layout.fragment_creditcards_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        try {
            super.onViewCreated(view, savedInstanceState);

            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

            txt_Addcards = view.findViewById(R.id.discard);
            txt_Addcards.setText("Credit Card List");
            BookingBundle = getArguments().getBundle("BookingBundle");
            VehicleBundle = getArguments().getBundle("VehicleBundle");
            returnLocationBundle = getArguments().getBundle("returnLocation");
            locationBundle = getArguments().getBundle("location");
            locationType = getArguments().getBoolean("locationType");
            initialSelect = getArguments().getBoolean("initialSelect");
            transactionId = getArguments().getString("transactionId");

            cardModel = (CreditCardModel) getArguments().getSerializable("CreditCard");
            Log.d("Mungara", "onViewCreated: " + cardModel.Number);

            showCard(cardModel);

            txt_Addcards.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("transactionId", transactionId);
                   // bundle.putDouble("total", total);
                    bundle.putBundle("VehicleBundle", VehicleBundle);
                    bundle.putBundle("BookingBundle", BookingBundle);
                    bundle.putBundle("location", locationBundle);
                    bundle.putBoolean("locationType", locationType);
                    bundle.putBoolean("initialSelect", initialSelect);
                    bundle.putBundle("returnLocation", returnLocationBundle);
                    bundle.putString("transactionId", transactionId);
                    NavHostFragment.findNavController(Fragment_CreditCards_List.this)
                            .navigate(R.id.action_CardsOnAccount_to_AddCreditCard, bundle);
                }
            });

            back = view.findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Bundle bundle = new Bundle();
                    bundle.putBundle("BookingBundle", BookingBundle);
                    bundle.putBundle("VehicleBundle", VehicleBundle);
                    bundle.putBundle("returnLocation", returnLocationBundle);
                    bundle.putBundle("location", locationBundle);
                    bundle.putBoolean("locationType", locationType);
                    bundle.putBoolean("initialSelect", initialSelect);
                    bundle.putBundle("returnLocation", returnLocationBundle);
                    bundle.putBoolean("isDefaultCard", true);
                    BookingBundle.putInt("BookingStep", 5);
                    NavHostFragment.findNavController(Fragment_CreditCards_List.this)
                            .navigate(R.id.action_CardsOnAccount_to_Payment_checkout, bundle);
                }
            });

            SharedPreferences sp = getActivity().getSharedPreferences("FlexiiCar", MODE_PRIVATE);
            id = sp.getString(getString(R.string.id), "");

            String bodyParam = "";

            try {
                  bodyParam += "customerId=" + id;
                  System.out.println(bodyParam);
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            AndroidNetworking.initialize(getActivity());
            Fragment_Term_And_Condition.context = getActivity();

 /*           ApiService ApiService = new ApiService(GetCreditcardList, RequestType.GET,
                    GETDCREDITCARDLIST, BASE_URL_BOOKING, new HashMap<String, String>(), bodyParam);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    OnResponseListener GetCreditcardList = new OnResponseListener()
    {
        @Override
        public void onSuccess(final String response, HashMap<String, String> headers)
        {
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    try {
                        System.out.println("Success");
                        System.out.println(response);

                        JSONObject responseJSON = new JSONObject(response);
                        Boolean status = responseJSON.getBoolean("status");

                        if (status)
                        {
                            try
                            {
                                JSONObject resultSet = responseJSON.getJSONObject("resultSet");
                                final JSONArray getInsuranceDEtails = resultSet.getJSONArray("t0050_Customer_Card_Details");

                                final RelativeLayout rlCreditcard = getActivity().findViewById(R.id.rl_cards);

                                int len;
                                len = getInsuranceDEtails.length();

                                for (int j = 0; j < len; j++)
                                {
                                    final JSONObject test = (JSONObject) getInsuranceDEtails.get(j);

                                    final int card_ID = test.getInt("card_ID");
                                    final String card_No=test.getString("card_No");
                                    final String card_Type_ID=test.getString("card_Type_ID");
                                    final String card_Name=test.getString("card_Name");
                                    final String address=test.getString("address");
                                    final String expiry_Date=test.getString("expiry_Date");
                                    final String cvS_Code=test.getString("cvS_Code");
                                    final int isDefault=test.getInt("isDefault");
                                    final String created_Date=test.getString("created_Date");
                                    final String card_PStreet=test.getString("card_PStreet");
                                    final String card_PCity=test.getString("card_PCity");
                                    final String card_PUnitNo=test.getString("card_PUnitNo");
                                    final int card_PCountry=test.getInt("card_PCountry");
                                    final int card_PState=test.getInt("card_PState");
                                    final String card_SStreet=test.getString("card_SStreet");
                                    final String card_SUnitNo=test.getString("card_SUnitNo");
                                    final String card_SCity=test.getString("card_SCity");
                                    final int card_SCountry=test.getInt("card_SCountry");
                                    final int card_SState=test.getInt("card_SState");
                                    final String card_SZipCode=test.getString("card_SZipCode");


                                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                    lp.addRule(RelativeLayout.BELOW, (200 + j - 1));
                                    lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                                    lp.setMargins(0, 0, 0, 0);

                                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
                                    LinearLayout view = (LinearLayout) inflater.inflate(R.layout.list_credit_card, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
                                    view.setId(200 + j);
                                    view.setLayoutParams(lp);

                                    LinearLayout creditcardLayout=(LinearLayout)view.findViewById(R.id.creditcard_layout);
                                    LinearLayout LLDefaultCard=(LinearLayout)view.findViewById(R.id.LLDefaultCard);

                                    TextView txtCardNumber= (TextView)view.findViewById(R.id.txtCardNumber1);
                                    TextView editcard=(TextView)view.findViewById(R.id.editcard1);
                                    TextView txt_CardName=(TextView)view.findViewById(R.id.txt_CardName1);
                                    TextView txt_ExpiryDate=(TextView)view.findViewById(R.id.txt_ExpiryDate1);
                                    txtCardNumber.setText("**** ***** ***** "+card_No.substring(card_No.length()-4));
                                    txt_CardName.setText(card_Name);
                                    txt_ExpiryDate.setText(expiry_Date);

                                    if(isDefault==1)
                                    {
                                        LLDefaultCard.setVisibility(View.VISIBLE);
                                    }
                                    editcard.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View view)
                                        {
                                            Bundle CardBundle = new Bundle();
                                            CardBundle.putInt("card_ID", card_ID);
                                            CardBundle.putString("card_No", card_No);
                                            CardBundle.putString("card_Type_ID", card_Type_ID);
                                            CardBundle.putString("card_Name", card_Name);
                                            CardBundle.putString("expiry_Date", expiry_Date);
                                            CardBundle.putString("cvS_Code", cvS_Code);
                                            CardBundle.putInt("isDefault", isDefault);
                                            CardBundle.putString("created_Date", created_Date);
                                            CardBundle.putString("card_PStreet", card_PStreet);
                                            CardBundle.putString("card_PCity", card_PCity);
                                            CardBundle.putString("card_PUnitNo", card_PUnitNo);
                                            CardBundle.putInt("card_PCountry", card_PCountry);
                                            CardBundle.putInt("card_PState", card_PState);
                                            CardBundle.putString("card_SStreet", card_SStreet);
                                            CardBundle.putString("card_SUnitNo", card_SUnitNo);
                                            CardBundle.putString("card_SCity", card_SCity);
                                            CardBundle.putInt("card_SCountry", card_SCountry);
                                            CardBundle.putInt("card_SState", card_SState);
                                            CardBundle.putString("card_SZipCode", card_SZipCode);

                                            Bundle CreditCardBundle = new Bundle();
                                            CreditCardBundle.putBundle("CardBundle", CardBundle);
                                            CreditCardBundle.putBundle("VehicleBundle", VehicleBundle);
                                            CreditCardBundle.putBundle("BookingBundle", BookingBundle);
                                            CreditCardBundle.putBundle("returnLocation", returnLocationBundle);
                                            CreditCardBundle.putBundle("location", locationBundle);
                                            CreditCardBundle.putBoolean("locationType", locationType);
                                            CreditCardBundle.putBoolean("initialSelect", initialSelect);
                                            CreditCardBundle.putString("transactionId", transactionId);
                                          //  CreditCardBundle.putDouble("total", total);
                                            System.out.println(CreditCardBundle);
                                            NavHostFragment.findNavController(Fragment_CreditCards_List.this)
                                                    .navigate(R.id.action_CardsOnAccount_to_EditCreditCard,CreditCardBundle);
                                        }
                                    });

                                    view.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View view)
                                        {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("transactionId",transactionId);
                                            bundle.putString("creditcard", test.toString());
                                            bundle.putBundle("VehicleBundle",VehicleBundle);
                                            bundle.putBundle("BookingBundle",BookingBundle);
                                            bundle.putBundle("returnLocation", returnLocationBundle);
                                            bundle.putBundle("location", locationBundle);
                                            bundle.putBoolean("locationType", locationType);
                                            bundle.putBoolean("initialSelect", initialSelect);
                                            bundle.putBoolean("isDefaultCard", false);
                                            NavHostFragment.findNavController(Fragment_CreditCards_List.this)
                                                    .navigate(R.id.action_CardsOnAccount_to_Payment_checkout, bundle);
                                        }
                                    });
                                    rlCreditcard.addView(view);
                                }

                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }

                        else
                        {
                            String errorString = responseJSON.getString("message");
                            CustomToast.showToast(getActivity(),errorString,1);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        @Override
        public void onError(String error)
        {
            System.out.println("Error-" + error);
        }
    };

    public void showCard(CreditCardModel card){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, (200));
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.setMargins(0, 0, 0, 0);

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.list_credit_card, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        //view.setId(200);
        view.setLayoutParams(lp);

        LinearLayout creditcardLayout=(LinearLayout)view.findViewById(R.id.creditcard_layout);
        LinearLayout LLDefaultCard=(LinearLayout)view.findViewById(R.id.LLDefaultCard);

        TextView txtCardNumber= (TextView)view.findViewById(R.id.txtCardNumber1);
        TextView editcard=(TextView)view.findViewById(R.id.editcard1);
        TextView txt_CardName=(TextView)view.findViewById(R.id.txt_CardName1);
        TextView txt_ExpiryDate=(TextView)view.findViewById(R.id.txt_ExpiryDate1);
        txtCardNumber.setText("**** ***** ***** "+card.Number.substring(card.Number.length()-4));
        txt_CardName.setText(cardModel.NameOn);
        txt_ExpiryDate.setText(card.ExpiryMonth + "/" + card.ExpiryYear);
    }

}