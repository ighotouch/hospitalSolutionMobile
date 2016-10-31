package com.adroits.smartmedcare.dbmodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by imatthew on 10/18/2016.
 */

public class DataProvider {
    public static List<Facility> facilities = new ArrayList<>();
    public static List<Lga> lgas = new ArrayList<>();


    public static List<String> lgaNames = new ArrayList<>();
    public static List<String> registrationForms = new ArrayList<>();


    static {
        addLGA("Agege");
        addLGA("Ajeromi");
        addLGA("Alimosho");
        addLGA("Amuwo Odofin");
        addLGA("Apapa");
        addLGA("Badagry");
        addLGA("Epe");
        addLGA("Eti-Osa");
        addLGA("Ibeju-Lekki");
        addLGA("Ifako-Ijare");
        addLGA("Ikeja");
        addLGA("Ikorodu");
        addLGA("Kosofe");
        addLGA("Lagos Island");
        addLGA("Lagos Mainland");
        addLGA("Mushin");
        addLGA("Ojo");
        addLGA("Oshodi Isolo");
        addLGA("Somolu");
        addLGA("Surulere");

        addRegistrationForm("Private Hospital");
        addRegistrationForm("Private Clinic");
        addRegistrationForm("Maternity Centre");
        addRegistrationForm("Private Nursing/convalescent home");
        addRegistrationForm("Private Physiotherapy Clinic");





//        addGeneralHospital(
//                "Alimosho General Hospital",
//                "6.634268",
//                "3.3077937",
//                "LASU",
//                "Alimosho"
//        );
//
//        addGeneralHospital(
//                "Apapa General Hospital",
//                "6.634268",
//                "3.3077937",
//                "LASU",
//                "Apapa"
//        );
//
//        addGeneralHospital(
//                "Badagry General Hospital",
//                "6.634268",
//                "3.3077937",
//                "LASU",
//                "Badagry"
//        );
//
//        addGeneralHospital(
//                "Epe General Hospital",
//                "6.634268",
//                "3.3077937",
//                "LASU",
//                "Epe"
//        );
//        addGeneralHospital(
//                "General Hospital, Akodo ",
//                "6.4959486",
//                "3.5940166",
//                "Lagos Road, Akodo, Ibeju-Lekki.",
//                "Ibeju-Lekki"
//        );
//        addGeneralHospital(
//                "Ifako Ijaiye General Hospital ",
//                "6.6552028",
//                "3.3063922",
//                "14 College Road, Iju Road, Ifako, Ifako-Ijaye.",
//                "Ifako-Ijaye"
//        );
//        addGeneralHospital(
//                "General Hospital, Ikeja",
//                "6.5954434",
//                "3.3389523",
//                "1-5 Oba Akinjobi street , Ikeja",
//                "Ifako-Ijaye"
//        );
//        addGeneralHospital(
//                "Lagos University Teaching Hospital - LASUTH",
//                "6.5954434",
//                "3.3389523",
//                "1-5 Oba Akinjobi street , Ikeja",
//                "Ikeja"
//        );
//
//        addGeneralHospital(
//                "Ikorodu General Hospital",
//                "6.5954434",
//                "3.3389523",
//                "1-5 Oba Akinjobi street , Ikeja",
//                "Ikorodu"
//        );
//        addGeneralHospital(
//                "Ijede Health Centre ",
//                "6.5954434",
//                "3.3389523",
//                "Egbin Station Road, Ijede, Ikorodu.",
//                "Ikorodu"
//        );
//
//        addGeneralHospital(
//                "Ketu Ejinrin Health Centre",
//                "6.5954434",
//                "3.3389523",
//                "Ejinrin Road, Ketu",
//                "Kosofe"
//        );
//        addGeneralHospital(
//                "Agbowa General Hospital",
//                "6.5954434",
//                "3.3389523",
//                "26 Hospital Road, off Ikosi Road, Agbowa",
//                "Kosofe"
//        );
//        addGeneralHospital(
//                "Gbagada General Hospital",
//                "6.5954434",
//                "3.3389523",
//                "1 Hospital Road, Gbagada, Kosofe.",
//                "Kosofe"
//        );
//        addGeneralHospital(
//                "General Hospital Lagos",
//                "6.5954434",
//                "3.3389523",
//                "1/3 Broad Street, Lagos Island, Lagos Island.",
//                "Lagos Island"
//        );
//        addGeneralHospital(
//                "Harvey Road Health Centre ",
//                "6.5954434",
//                "3.3389523",
//                "1 Hospital Road, Gbagada, Kosofe.",
//                "Lagos Mainland"
//        );
//        addGeneralHospital(
//                "Mushin General Hospital",
//                "6.5954434",
//                "3.3389523",
//                "1 Hospital Road, Gbagada, Kosofe.",
//                "Mushin"
//        );
//
//
//
//        addPrivateHospital(
//                "Bankole Medical Centre",
//                "6.6417831",
//                "3.3276065",
//                "43, College Road, Ifako, Agege, Lagos",
//                "Agege"
//        );
//
//        addPrivateHospital(
//                "Rekky Hospital",
//                "6.6417831",
//                "3.3276065",
//                "43, College Road, Ifako, Agege, Lagos",
//                "Agege"
//        );
//
//        addPrivateHospital(
//                "Efan Hospital & Maternity Home",
//                "6.6261991",
//                "3.3089728",
//                "143 Ipaja Rd., Agege",
//                "Ajeromi"
//        );
//
//        addPharmaceuticalStores(
//                "Abek Pharmacy",
//                "6.6261991",
//                "3.3089728",
//                "143 Ipaja Rd., Agege",
//                "Agege"
//        );
//
//
//
//        addFacilityTypes(
//                "Private Hospital",
//                "1000",
//                "1000"
//        );
//
//        addPharmaceuticalStores(
//                "Boluke Pharmacy Ltd",
//                "6.6262257",
//                "3.3024067",
//                "16, Iju Rd., Pen Cinema,Agege",
//                "Agege"
//        );


    }

    private static void addLGA(String lga) {
        Lga lga1 = new Lga(lga);
        lgaNames.add(lga1.getTitle());
        lgas.add(lga1);

        switch (lga){
            case  "Agege" :
                addFacility(
                        "General Hospital, Orile-Agege",
                        "6.634268",
                        "3.3077937",
                        "Old Ota Road, Off Lagos/Abeokuta Agege",
                        lga1,
                        "general hospital"
                );

                addFacility(
                        "May Flower Clinics Hospital",
                        "6.6417831",
                        "3.3276065",
                        "43, College Road, Ifako, Agege, Lagos",
                        lga1,
                        "private hospital"
                );
                break;
            case  "Ajeromi" :
                addFacility(
                        "Ajeromi General Hospital",
                        "6.4552835",
                        "3.3306008",
                        "Old Ota Road, Off Lagos/Abeokuta Agege",
                        lga1,
                        "general hospital"
                );
                break;

            case  "Alimosho" :
                addFacility(
                        "General Hospital, Orile-Agege",
                        "6.634268",
                        "3.3077937",
                        "Old Ota Road, Off Lagos/Abeokuta Agege",
                        lga1,
                        "general hospital"
                );
                break;





        }
    }
//    private static void addRegistrationForm(String formName) {
//        FacilityRegistrationForm form = new FacilityRegistrationForm(formName);
//        registrationForms.add(form.getTitle());
//    }

    private static void addFacility(String title, String latitude, String longitude, String address, Lga lga, String type) {
        Facility facility = new Facility(title,latitude,longitude,type,address,lga);
        facilities.add(facility);
        //generalHospitalNames.add(generalHospital.getTitle());
        // generalHospitalHashMap.
    }


    private static void addRegistrationForm(String formName) {
        FacilityRegistrationForm form = new FacilityRegistrationForm(formName);
        registrationForms.add(form.getTitle());
    }
}
