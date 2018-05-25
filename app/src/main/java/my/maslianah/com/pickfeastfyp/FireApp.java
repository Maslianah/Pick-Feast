/*
package my.maslianah.com.pickfeastfyp;

import android.app.Activity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;

import my.maslianah.com.pickfeastfyp.model.Caterer;

public class FireApp {

    DatabaseReference db;
    Boolean saved;
    ArrayList<Caterer> aCaterer = new ArrayList<>();

    public FireApp(){}
    public FireApp (DatabaseReference db){
        this.db = db;

    }

    public boolean saved (Caterer caterer ){
        if (caterer == null){
            saved = false;
        }else{
            try{
                db.child("Caterer").push().setValue(caterer);
            }catch (DatabaseException e){
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }

    private void fetchData(DataSnapshot dataSnapshot){
        aCaterer.clear();

        for (DataSnapshot ds: dataSnapshot.getChildren()){
            Caterer caterer = ds.getValue(Caterer.class);
            aCaterer.add(caterer);
        }
    }

    public ArrayList<Caterer> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return aCaterer;
    }
}
*/



/* CATERERDETAIL TABLE

   //Insert caterer into caterers table
    Caterer CAT001 = new Caterer("CAT001","Tuk Tuk Kitchen","tuktuk@gmail.com","PJ, Selangor","0127987011","2215-04/2013" ,"123456-Z");
    Caterer CAT002 = new Caterer("CAT002","WYK Catering and Canopy Services Sdn Bhd","wykcatering@hotmail.com","Kuala Lumpur","1700815199","Non-Halal" ,"1018570-D");
    Caterer CAT003 = new Caterer("CAT003","Big Onion Food Caterer Sdn Bhd","event@bigonioncaterer.com.my","Raja Chulan, Kuala Lumpur","0340414488","2049-04/2013" ,"920380-k");
    Caterer CAT004 = new Caterer("CAT004","The Big Rajah Food Caterers Sdn Bhd","admin@thebigrajah.com","Taman Sea, Selangor","0378802266","2137-02/20133" ,"486596-P");
    Caterer CAT005 = new Caterer("CAT005","One Catering Malaysia","info@onecateringmalaysia.com","Puchong, Selangor","0176505768","1234-05/2019" ,"234567-Z");
    Caterer CAT006 = new Caterer("CAT006","Alanna Kitchen","alana@gmail.com","Cheras, Kuala Lumpur","0125518227","2139-01/2017" ,"345678-Z");
    Caterer CAT007 = new Caterer("CAT007","Best Chef Catering Services","contact@bestchef.com.biz","PJ, Selangor","0378874263","2022-08/2010" ,"456789-Z");
    Caterer CAT008 = new Caterer("CAT008","Felda DSaji Sdn Bhd","dsaji@felda.net.my","Kuala Lumpur","0326988302","2030-12/2009" ,"567891-Z");
    Caterer CAT009 = new Caterer("CAT009","Classic Caterers Sdn Bhd","info@classic-caterers.com","Bandar Menjalara, Kuala Lumpur","0362736394","2215-08/2008" ,"221309-T");
    Caterer CAT010 = new Caterer("CAT010","Kenny Roagers Roasters","qa2@krr.com.my","Berjaya Times Square, Kuala Lumpur","0321199888","2002-03/2004" ,"278605-A");

        mDatabase.child("FullCaterersDetails").child("CAT001").setValue(CAT001);
                mDatabase.child("FullCaterersDetails").child("CAT002").setValue(CAT002);
                mDatabase.child("FullCaterersDetails").child("CAT003").setValue(CAT003);
                mDatabase.child("FullCaterersDetails").child("CAT004").setValue(CAT004);
                mDatabase.child("FullCaterersDetails").child("CAT005").setValue(CAT005);
                mDatabase.child("FullCaterersDetails").child("CAT006").setValue(CAT006);
                mDatabase.child("FullCaterersDetails").child("CAT007").setValue(CAT007);
                mDatabase.child("FullCaterersDetails").child("CAT008").setValue(CAT008);
                mDatabase.child("FullCaterersDetails").child("CAT009").setValue(CAT009);
                mDatabase.child("FullCaterersDetails").child("CAT010").setValue(CAT010);


                // Insert packages into Package table
                Packages P001 = new Packages("Package A","Chinese",32.80,FALSE, TRUE ,"You get 15 Dishes.\n 1)Signature Curry Chicken\n 2)Crispy Fried Chicken\n 3)Spring Roll\n 4)Fried Dumplings\n 5)Mushroom & Braised Beancurd\n 6)Thai Style Fish Fillet\n 7)Fried Mixed Vegetables\n 8)Deep Fried Squid\n 9)Fried Meehoon\n 10)Cantonese Chicken Fried Rice\n  11)Local Fruits\n 12)Orange Cordial\n 13)Agar-agar\n 14)Nyonya Kuih\n 15)Sesame Ball\n\n This package is available for buffet\n\n MIN 150 pax \n\n Price per person \n\n Free Transportation, Canopy Service, Chairs, Round Tables and Waiters \n\n This package is suitable for Private events and Wedding");
                Packages P002 = new Packages("Sit Down Menu Set A","Chinese",688.00,FALSE,TRUE ,"You get 9 Dishes:\n 1)Special Four Seasons Platter\n 2)Braised Shark Fin Soup with Fish Maw and Conpoy (Individual Serving)\n 3)Steamed Fresh Grouper With Enoki Mushroom & Black Fungi\n 4)Crispy Fried Chicken\n 5)Scalded Fresh Tiger Prawns\n 6)Braised Abalone Slice with Fish Maw & Mushroom\n 7)Fried Rice with Anchovies & Salted Fish\n 8)Two Varieties of Chinese Pastries\n 9)Longan & Sea Coconut With White Fungus\n\n This package is available for Dome\n\n MIN 150 pax\n\n Price per table \n\n Free Transportation, Canopy Service, Chairs, Round Tables and Waiters\n\n This package is suitable for Private events and Wedding");
                Packages P003 = new Packages("Sit Down Menu Set B ",  "Chinese", 888.00 , FALSE, TRUE, "You get 9 Dishes:\n 1)Deluxe Happiness Five Combinations Platter\n 2)Double – Boiled Chicken Soup With Assorted Dried Seafood (Individual Serving)\n 3)Steamed King Tiger Grouper with Superior Soy Sauce\n 4)Crispy Chicken & Duck Platter\n 5)Baked Tiger Prawns With Salted Egg Yolk\n 6)Braised Mushroom With Sea Cucumber & Seasonal Vegetable\n 7)Hong Kong Style Steamed Glutinous Rice\n 8)Pastry Desserts in Two Varieties\n 9)Boiled Lotus Seeds with Longan & Lily Bulbs\n\n This package is available for Dome\n\n MIN 150 pax\n\n Price per table \n\n Free Transportation , Canopy Service, Chairs, Round Tables and Waiters \n\n This package is suitable for Private events and Wedding" );
                Packages P004 = new Packages("Arabic Food Set A", "Others", 19.90 , TRUE, TRUE, "You will get Arabic Lamb Mandy with rice \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Meeting,Private events and Open House\n\n This package is available for buffet or packed\n\n" );
                Packages P005 = new Packages("Arabic Food Set B",  "Others", 13.50 , TRUE, TRUE, "You will get Arabic Chicken Mandy with rice \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Meeting,Private events and Open House\n\n This package is available for buffet or packed\n\n " );
                Packages P006 = new Packages("Asian Delight Set A",  "Malay", 8.90 , TRUE, TRUE, "You will get Nasi Lemak Special \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Private events and Meeting\n\n This package is available for buffet or packed\n\n ");
                Packages P007 = new Packages( "Asian Delight Set B",  "Malay", 11.90 , TRUE, TRUE, "You will get Grilled Sambal Fish(Kembung) with rice \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Private events and Meeting\n\n This package is available for buffet or packed\n\n ");
                Packages P008 = new Packages("Western Set A",  "Western", 11.90 , TRUE, TRUE, "You will get Spaghetti Bolognese \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Private events,Party,Meeting and Open House\n\n This package is available for buffet or packed\n\n ");
                Packages P009 = new Packages("Western Set B",  "Western", 12.90 , TRUE, TRUE, "You will get Spaghetti Carbonara \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Private events,Party,Meeting and Open House\n\n This package is available for buffet or packed\n\n ");
                Packages P010 = new Packages("The Wedding Banquet",  "Indian", 49.00 , FALSE, TRUE, "You get 8 Dishes:\n 1)Plain Pulao Briyani\n 2)Chicken Kurma\n 3)Malay Mutton Rendang\n 4)Prawn or Fish-Aloo Cutlet\n 5)Sauteed Mixed Vegetables\n 6)Meal Maker Peratel\n 7)Prathaman\n 8)Orange Cordial\n\n This package is available for Banquet or Buffet\n\n Price per pax \n\n This package is suitable for Wedding, Corporate or Private event" );
                Packages P011 = new Packages("Live Malaysian Stalls",  "Malay", 35.00 , FALSE, TRUE, "You get 9 Dishes:\n 1)Superior Grilled Satay (OTS) *\n 2)Roti Jala with Chicken Curry \n 3)Penang Char Kway Teow (OTS) \n 4)Mini Murtabak with Condiments (OTS) \n 5)Rainbow Ice-Kacang (OTS) \n 6)Prawn Mee Hoon\n 7)Potato Apple Salad\n 8)Buttered Oyster Mushroom\n 9)OrangeCordial\n *served with cucumber, onions and peanut sauce\n\n This package is available for live station\n\n Price per pax \n\n This package is suitable for Wedding, Open House or Private event" );
                Packages P012 = new Packages("The Golden Barbecue",  "Western", 65.00 , FALSE, TRUE, "You get 12 Dishes:\n 1)Tender-Roast Lamb Chops (OTS) *\n 2)Grilled Honey Chicken Wings (OTS)\n 3)Chicken Burgers (OTS)\n 4)Buttered Garlic Rice\n 5)Corn on the Cob\n 6)Chicken Sausages\n 7)Garlic Bread\n 8)Bread Rolls\n 9)Fresh Garden Salad\n 10)Ice-Cream in Fruit Cocktail\n 11)Tropical Fruit Punch\n 12)Black Coffee\n \n* Whole Roast Lamb (grilled to perfection at site with Rajah’s special marinade and mint sauce)\n\n This package is available for Buffet and Live Station\n\n Price per pax \n\n This package is suitable for Wedding, Party or Open House");
                Packages P013 = new Packages( "The Grand Garden", "Others", 20.00 , FALSE, TRUE, "You get 11 Dishes:\n 1)Assorted Sandwich\n 2)Meat Puffs\n 3)Chicken Capitan\n 4)Golden Spring Rolls\n 5)Chocolate Eclairs\n 6)Finger Pizza\n 7)Spicy Meatballs\n 8)Cocktail Sausages\n 9)Kuih Lapis\n 10)Kuih Seri Muka\n 11)Tea or Coffee \n\n This package is available for Buffet and Packed\n\n Price per pax \n\n This package is suitable for Wedding, Party, Meeting,Private event or Open House" );
                Packages P014 = new Packages("Selection of Hot Spot", "Malay", 8.00 , TRUE, TRUE, "You get 3 Dishes: 1)Rainbow Ice-Kacang\n 2)Chendol Delight\n 3)Mamak Teh Tarik\n\n This package is available for Live Station\n\n Price per pax \n\n This package is suitable for Wedding, Party, Meeting,Private event or Open House ");
                Packages P015 = new Packages("Mini Buffet Set A", "Malay", 20.90 , TRUE, FALSE, "You get 7 Dishes: 1)Nasi Putih\n 2)Daging Masak Lemak Cili Api\n 3)Sotong Sambal\n 4)Ayam Goreng Kunyit\n 5)Sayur Goreng Brokoli dengan Tahu Jepun\n 6)Ulam-Ulaman & Sambal Belacan\n 7)Puding Caramel\n\n \n\n This package is available for Buffet\n\n Price per pax \n\n This package is suitable for Party, Meeting or Private event/n/n FREE delivery\n\n MIN 12 pax\n\n");
                Packages P016 = new Packages("Mini Buffet Set B", "Malay", 22.50 , TRUE, FALSE, "You get 8 Dishes: 1)Nasi Beriyani\n 2)Daging Masak Hitam\n 3)Ayam Masak Merah\n 4)Isi Ikan Harum Wangi Bunga Kantan\n 5)Acar Buah\n 6)Sayur Campur\n 7)Papedom 8)Puding Caramel\n\n \n\n This package is available for Buffet\n\n Price per pax \n\n This package is suitable for Party, Meeting or Private event/n/n FREE delivery\n\n MIN 12 pax\n\n" );
                Packages P017 = new Packages("Buffet Menu Set A", "Malay", 20.00 , FALSE, TRUE, "You get 12 Dishes: 1)Ayam Masak Merah\n 2)Beef Rendang\n 3)Kerabu Thai Sotong\n 4)Fried Shrimp Bomb\n 5)Dalca Vegetables\n 6)Fried Long Bean With Sambal Belacan\n 7)Acar Rampai\n 8)Fried Mee Mamak\n 9)Beryani Rice\n 10)Mix Malaysian Fruits\n 11)Ice Kacang\n 12)Syrup Cordial\n\n (Minimum 50 Person) \n\n This package is available for Buffet\n\n Price per pax \n\n This package is suitable for Wedding, Meeting or Open House /n/n FREE delivery\n\n " );
                Packages P018 = new Packages("Stall Menu Set A", "Others", 20.00 , FALSE, TRUE, "You get 11 Dishes: 1)Satay (Mix of ChickenBeef / Mutton)\n 2)Cucumber, Onion, Peanut Sauce\n 3)Fried Chicken Wings\n 4)Tasty Butter Fillet Of Fish\n 5)Fried Sotong Ball & Fried Shrimp Bomb\n 6)Grilled Murtabak\n 7)Fried Kway Teow With Prawns & Cockles\n 8)Fried Rice\n 9)Assorted Nyonya Kuih\n 10)Mix Malaysian Fruits\n 11)Orange Squash (Minimum 100 Person) \n\n This package is available for Buffet or Live Station\n\n Price per pax \n\n This package is suitable for Private Event, Party, or Open House /n/n FREE delivery\n\n ");
                Packages P019 = new Packages("Western Menu Set A", "Western", 30.00 , FALSE, TRUE, "You get 13 Dishes: 1)Satay (Mix of ChickenBeef / Mutton) \n 2)Cucumber, Onion, Peanut Sauce\n 3)Roast Chicken With Rosemary\n 4)Grilled Lamb Loin\n 5)Grilled Fillet Of Fish\n 6)Grilled Chicken Sausages\n 7)Mix Vegetables Salad\n 8)Coleslaw Salad\n 9)French Fries\n 10)Spaghetti With Chicken Bolognase Sauce\n 11)Mix Malaysian Fruits\n 12)Coffee & Tea\n 13)Orange Cordial (Minimum 50 Person) \n\n This package is available for Banquet or Live Station\n\n Price per pax \n\n This package is suitable for Private Event, Party, or Open House \n\n FREE delivery\n\n");
                Packages P020 = new Packages("Tea Menu Set A", "Others", 10.00 , FALSE, TRUE, "You get 7 Dishes: 1)Mini Fruit Tarts\n 2)Brownies Cake\n 3)Pulut Panggang\n 4)Kuih Kole Kacang\n 5)Kuih Sri Muka\n 6)Egg Sandwiches\n 7)Coffee & Tea  (Minimum 30 Person) \n\n This package is available for Buffet\n\n Price per pax \n\n This package is suitable for Private Event, Party, Meeting, Wedding or Open House /n/n FREE delivery\n\n" );
                Packages P021 = new Packages("Classic Choice Meal", "Western", 13.90 , TRUE, TRUE, "You get Rotisserie-Roasted Quarter Chicken with Coleslaw and Aromatic Rice\n\n Price per pax.\n\n This package is available for Packed or Buffet\n\n This package is suitable for Private Event, Party, Meeting, Wedding or Open House /n/n"  );
                Packages P022 = new Packages("Kenny's Quarter Meal", "Western", 18.90 , TRUE, TRUE, "You get Rotisserie-Roasted 1/4 Chicken with 3 side dishes of the day and Kennys homemade muffin \n\n Price per pax.\n\n This package is available for Packed or Buffet\n\n This package is suitable for Private Event, Party, Meeting, Wedding or Open House /n/n");
                Packages P023 = new Packages("Kenny's Half Meal", "Western", 28.80 , TRUE, TRUE, "You get Rotisserie-Roasted 1/2 Chicken with 3 side dishes of the day and Kennys homemade muffin \n\n Price per pax.\n\n This package is available for Packed or Buffet\n\n This package is suitable for Private Event, Party, Meeting, Wedding or Open House /n/n" );
                Packages P024 = new Packages("Value Package", "Malay", 18.00 , FALSE, TRUE, "You get: \n* Fried Rice/ Steam Rice (30%)\n  * Asian Fried Meehoon\n * Signature Curry Chicken with Potato\n * Lemon Fish Fillet\n * Healthy Mixed Vegetables\n * Fried Fish Ball\n * Tasty Sausage\n * Jelly\n * Local Fruits\n * Orange Cordial\n * Foam plate, cup, fork, spoon & napkin (Minimum 50 Person) \n\n This package is available for Buffet or Packed\n\n Price per pax \n\n This package is suitable for Meeting, Wedding or Open House /n/n FREE Swiss roll cake for above 100pax \n " );
                Packages P025 = new Packages( "BBQ ", "Western", 45.00 ,FALSE, TRUE, "You get: \n * CK Fried Rice\n * Tuna & Egg Sandwich\n * Signature Curry Chicken with Potato\n * Salted Egg Prawn\n * Potato Salad\n * BBQ Fish Fillet (CK Special Recipe)\n * BBQ Boneless Chicken Chop\n * BBQ Lamb Chop\n * BBQ Honey Glazed Chicken Sausage\n * BBQ Corn\n (mint & blackpepper sauce)\n * Satay 500 sticks chicken (100pax)\n * Local Fruits\n * Soya Bean / Orange Cordial\n * Foam plate, cup, fork, spoon & napkin (Minimum 50 Person) \n\n This package is available for Buffet \n\n Price per pax \n\n This package is suitable for Meeting, Wedding, Private Event or Open House \n\n FREE Fried Chicken Wing for above 100pax \n ");
                Packages P026 = new Packages( "Non-Vegetarian Box", "Chinese", 14.00 , TRUE, TRUE, "You get: 1)Yong chow fried rice\n 2)Singapore meehoon\n 3)Sweet & sour fish\n 4)Seafood Wonton\n\n (Minimum 50 Person) \n\n This package is available for Packed \n\n Price per pax \n\n This package is suitable for Meeting, Party or Private Event \n\n FREE Delivery \n" );
                Packages P027 = new Packages("Vegetarian Box", "Chinese", 14.00 , TRUE, TRUE,"You get: 1)Vegetable Meehoon.\n 2)Pandan vegeterian sesame chicken\n 3)Vegetarian Spring roll. (Minimum 50 Person) \n\n This package is available for Packed \n\n Price per pax \n\n This package is suitable for Meeting, Party or Private Event \n\n FREE Delivery \n" );
                Packages P028 = new Packages("Party Package", "Others", 60.00 , FALSE, TRUE, "You get: * Yong Chow Fried Rice\n * Asian Fried Rice\n * Spaghetti Bolognese\n * Signature Curry Chicken with Potato\n * Assorted Mini Sandwiches\n * Breaded Fish Fingers\n * Chicken Nugget\n * Fried Fish Ball\n * Mini Sausage\n * Assorted Swiss Roll Cake\n * Local Fruits\n * Ribena Lemon\n * Party Plate Bundle (30 sets)\n * Plastic plate, cup, fork, spoon (Minimum 50 Person) \n\n This package is available for Buffet \n\n Price per pax \n\n This package is suitable for Party, Private Event or Open House /n/n FREE Clown Performance, Party Planner Deco and Delivery\n" );
                Packages P029 = new Packages("Dome Package", "Chinese", 86.00 , FALSE, TRUE, "You get: *Chinese Fried Rice\n * Signature Curry Chicken with Potato\n * Sweet & Sour Fish Fillet\n * Salted Egg Sotong\n * Stir Fry Mix Vegetables\n * Xing Kong Tofu\n * Assorted Bite Size Cakes\n * Orange Cordial (Minimum 50 Person) \n\n This package is available for Dome \n\n Price per pax \n\n This package is suitable for Dome Setup, Complete Cutleries, Mineral Water per guest and Delivery\n" );
                Packages P030 = new Packages("Western Package", "Western", 40.00 , FALSE, TRUE, "You get: *Chicken Mushroom Soup\n * Potato Salad\n * Spaghetti Bolognes (chicken)\n * CK Fried Rice\n * Lamb Chop (blackpepper sauce)\n * Chicken Chop (mushroom sauce)\n * Mayo Fish Fillet\n * Assorted Mini Sandwiches\n * Fried Spring Roll\n * Fried Curry Samosa\n * Mango Pudding\n * Assorted Swiss Roll Cake\n * Iced Lemon Tea (Minimum 50 Person) \n\n This package is available for Buffet \n\n Price per pax \n\n This package is suitable for Party, Private Event, Wedding or Open House /n/n FREE 400 sticks chicken satay (above 100 pax) and Delivery\n");

                mDatabase.child("FullCaterersDetails").child("CAT002").child("P001").setValue(P001);
                mDatabase.child("FullCaterersDetails").child("CAT002").child("P002").setValue(P002);
                mDatabase.child("FullCaterersDetails").child("CAT002").child("P003").setValue(P003);
                mDatabase.child("FullCaterersDetails").child("CAT006").child("P004").setValue(P004);
                mDatabase.child("FullCaterersDetails").child("CAT006").child("P005").setValue(P005);
                mDatabase.child("FullCaterersDetails").child("CAT006").child("P006").setValue(P006);
                mDatabase.child("FullCaterersDetails").child("CAT006").child("P007").setValue(P007);
                mDatabase.child("FullCaterersDetails").child("CAT006").child("P008").setValue(P008);
                mDatabase.child("FullCaterersDetails").child("CAT006").child("P009").setValue(P009);
                mDatabase.child("FullCaterersDetails").child("CAT004").child("P010").setValue(P010);
                mDatabase.child("FullCaterersDetails").child("CAT004").child("P011").setValue(P011);
                mDatabase.child("FullCaterersDetails").child("CAT004").child("P012").setValue(P012);
                mDatabase.child("FullCaterersDetails").child("CAT004").child("P013").setValue(P013);
                mDatabase.child("FullCaterersDetails").child("CAT004").child("P014").setValue(P014);
                mDatabase.child("FullCaterersDetails").child("CAT008").child("P015").setValue(P015);
                mDatabase.child("FullCaterersDetails").child("CAT008").child("P016").setValue(P016);
                mDatabase.child("FullCaterersDetails").child("CAT009").child("P017").setValue(P017);
                mDatabase.child("FullCaterersDetails").child("CAT009").child("P018").setValue(P018);
                mDatabase.child("FullCaterersDetails").child("CAT009").child("P019").setValue(P019);
                mDatabase.child("FullCaterersDetails").child("CAT009").child("P020").setValue(P020);
                mDatabase.child("FullCaterersDetails").child("CAT010").child("P021").setValue(P021);
                mDatabase.child("FullCaterersDetails").child("CAT010").child("P022").setValue(P022);
                mDatabase.child("FullCaterersDetails").child("CAT010").child("P023").setValue(P023);
                mDatabase.child("FullCaterersDetails").child("CAT005").child("P024").setValue(P024);
                mDatabase.child("FullCaterersDetails").child("CAT005").child("P025").setValue(P025);
                mDatabase.child("FullCaterersDetails").child("CAT007").child("P026").setValue(P026);
                mDatabase.child("FullCaterersDetails").child("CAT007").child("P027").setValue(P027);
                mDatabase.child("FullCaterersDetails").child("CAT003").child("P028").setValue(P028);
                mDatabase.child("FullCaterersDetails").child("CAT003").child("P029").setValue(P029);
                mDatabase.child("FullCaterersDetails").child("CAT003").child("P030").setValue(P030);


                //Insert caterer addOn into CatererAddOn table
                AddOn cAddOn001 = new AddOn("Furnitre Set", 550.00, "Price per set of 100 guests which includes tables, chairs, table clothes, fans, lighting and canopies.\n If you ordered more than 1 set, then you are entitled for free red carpet and stage set-up. ");
                AddOn cAddOn002 = new AddOn("Canopy", 150.00, "Price per canopy. ");
                AddOn cAddOn003 = new AddOn("Disposal Cutlery and Plate", 10.00, "Price per set of 12. ");
                AddOn cAddOn004 = new AddOn("Entertainment Set", 450.00, "Price per set.\n The set consists of Clown, DJ, Game master and Puppet Show. ");
                AddOn cAddOn005 = new AddOn("Furniture Set", 250.00, "Price per set.\n It consists of Canopy,Light and Fans. ");
                AddOn cAddOn006 = new AddOn("Table Set", 250.00, "Price per set of 100 guests which includes tables, chairs and table clothes. ");
                AddOn cAddOn007 = new AddOn("Stage Set", 300.00, "Price per set.\n It consists of Stage, Backdrop and PA System. ");
                AddOn cAddOn008 = new AddOn("Canopy Set", 350.00, "Price per set.\n It consists of Canopy, 5 units of Round Table(8 pax), 40 units of Chair, Fan and Lighting. ");
                AddOn cAddOn009 = new AddOn("Transportation", 100.00, "Please add this option for every order. ");
                AddOn cAddOn010 = new AddOn("Buffet Table", 100.00, "Includes cloth & skirt. ");
                AddOn cAddOn011 = new AddOn("Furniture Set", 300.00, "Price per set.\n It consists of Buffet Table,7 units of Round Table(8 pax)and 50 units of Chair. ");
                AddOn cAddOn012 = new AddOn("Stage Setup", 450.00, "Price per set.\n It consists of Red Carpet and 8x8 inch Stage. ");

                mDatabase.child("FullCaterersDetails").child("CAT004").child("cAddOn001").setValue(cAddOn001);
                mDatabase.child("FullCaterersDetails").child("CAT004").child("cAddOn002").setValue(cAddOn002);
                mDatabase.child("FullCaterersDetails").child("CAT008").child("cAddOn003").setValue(cAddOn003);
                mDatabase.child("FullCaterersDetails").child("CAT009").child("cAddOn004").setValue(cAddOn004);
                mDatabase.child("FullCaterersDetails").child("CAT009").child("cAddOn005").setValue(cAddOn005);
                mDatabase.child("FullCaterersDetails").child("CAT009").child("cAddOn006").setValue(cAddOn006);
                mDatabase.child("FullCaterersDetails").child("CAT009").child("cAddOn007").setValue(cAddOn007);
                mDatabase.child("FullCaterersDetails").child("CAT005").child("cAddOn008").setValue(cAddOn008);
                mDatabase.child("FullCaterersDetails").child("CAT005").child("cAddOn009").setValue(cAddOn009);
                mDatabase.child("FullCaterersDetails").child("CAT007").child("cAddOn010").setValue(cAddOn010);
                mDatabase.child("FullCaterersDetails").child("CAT003").child("cAddOn011").setValue(cAddOn011);
                mDatabase.child("FullCaterersDetails").child("CAT003").child("cAddOn012").setValue(cAddOn012);*/



  /*       mDatabase = FirebaseDatabase.getInstance().getReference();
            //Insert charity house into charity table
            Charity CHARITY001 = new Charity("CHARITY001","Rumah Kasih Hospital Kuala Lumpur","Foong Pong Lam","0123009913","No 773, Lot 222, Jln Umbun 2, Taman Setapak, 53000 , KL", "Kuala Lumpur","Free hospice services to provide palliative care that fulfil the needs of the elderly and unfortunate patients that are abandoned in government hospitals.");
            Charity CHARITY002 = new Charity("CHARITY002","Rumah Aman Children Home","Abdul Rehman Bakri","0360381430","No 51, Jln Seroja 1/1, Sg Buloh Country Resort, Saujana Utama, 47000 Sungai Buloh, Selangor", "Selangor","Rumah Aman which means peaceful home in Malay is an orphanage providing caring and loving home for children that are orphaned or from very poor or impoverished families.");
            Charity CHARITY003 = new Charity("CHARITY003","Pusat Transit Gelandangan Kuala Lumpur","DBKL","null","20 Jalan Pahang, Titiwangsa Central, 53000 Kuala Lumpur ", "Kuala Lumpur","A one stop centre for homeless where it can fit around 250 at a time.");
            Charity CHARITY004 = new Charity("CHARITY004","Pusat Penjagaan Kanak-Kanak Cacat Taman Megah","R.Uma Devi","0378061143","Lot 13488, Jalan Jenjarung, Off Jalan ss23/1, Taman Sea, Selangor", "Selangor","The founder of this charity house was himself physically handicapped therefore, he was determined to provide shelter for special children.Currently it accommodates about 100 disabled children with various disability such as down syndrome and autism.");
            Charity CHARITY005 = new Charity("CHARITY005","Pertubuhan Kebajikan Darul Wardah Selangor","Hazlina","0351661273"," No 1 , Jalan Tanjung Pahang 30/102A, Lorong Haji Taib, Seksyen 30, 40460 Shah Alam, Selangor", "Selangor","A centre for rehabilitation of morals among Muslim women.");

            mDatabase.child("Charity").child("CHARITY001").setValue(CHARITY001);
            mDatabase.child("Charity").child("CHARITY002").setValue(CHARITY002);
            mDatabase.child("Charity").child("CHARITY003").setValue(CHARITY003);
            mDatabase.child("Charity").child("CHARITY004").setValue(CHARITY004);
            mDatabase.child("Charity").child("CHARITY005").setValue(CHARITY005);
*/
 /*
        //Insert caterer into caterers table
        Caterer CAT001 = new Caterer("CAT001","Tuk Tuk Kitchen","tuktuk@gmail.com","PJ, Selangor","0127987011","2215-04/2013" ,"123456-Z");
        Caterer CAT002 = new Caterer("CAT002","WYK Catering and Canopy Services Sdn Bhd","wykcatering@hotmail.com","Kuala Lumpur","1700815199","Non-Halal" ,"1018570-D");
        Caterer CAT003 = new Caterer("CAT003","Big Onion Food Caterer Sdn Bhd","event@bigonioncaterer.com.my","Raja Chulan, Kuala Lumpur","0340414488","2049-04/2013" ,"920380-k");
        Caterer CAT004 = new Caterer("CAT004","The Big Rajah Food Caterers Sdn Bhd","admin@thebigrajah.com","Taman Sea, Selangor","0378802266","2137-02/20133" ,"486596-P");
        Caterer CAT005 = new Caterer("CAT005","One Catering Malaysia","info@onecateringmalaysia.com","Puchong, Selangor","0176505768","1234-05/2019" ,"234567-Z");
        Caterer CAT006 = new Caterer("CAT006","Alanna Kitchen","alana@gmail.com","Cheras, Kuala Lumpur","0125518227","2139-01/2017" ,"345678-Z");
        Caterer CAT007 = new Caterer("CAT007","Best Chef Catering Services","contact@bestchef.com.biz","PJ, Selangor","0378874263","2022-08/2010" ,"456789-Z");
        Caterer CAT008 = new Caterer("CAT008","Felda DSaji Sdn Bhd","dsaji@felda.net.my","Kuala Lumpur","0326988302","2030-12/2009" ,"567891-Z");
        Caterer CAT009 = new Caterer("CAT009","Classic Caterers Sdn Bhd","info@classic-caterers.com","Bandar Menjalara, Kuala Lumpur","0362736394","2215-08/2008" ,"221309-T");
        Caterer CAT010 = new Caterer("CAT010","Kenny Roagers Roasters","qa2@krr.com.my","Berjaya Times Square, Kuala Lumpur","0321199888","2002-03/2004" ,"278605-A");

        mDatabase.child("Caterers").child("CAT001").setValue(CAT001);
        mDatabase.child("Caterers").child("CAT002").setValue(CAT002);
        mDatabase.child("Caterers").child("CAT003").setValue(CAT003);
        mDatabase.child("Caterers").child("CAT004").setValue(CAT004);
        mDatabase.child("Caterers").child("CAT005").setValue(CAT005);
        mDatabase.child("Caterers").child("CAT006").setValue(CAT006);
        mDatabase.child("Caterers").child("CAT007").setValue(CAT007);
        mDatabase.child("Caterers").child("CAT008").setValue(CAT008);
        mDatabase.child("Caterers").child("CAT009").setValue(CAT009);
        mDatabase.child("Caterers").child("CAT010").setValue(CAT010);*/

 /*     //Insert caterer addOn into CatererAddOn table
        AddOn cAddOn001 = new AddOn("Furnitre Set", 550.00, "Price per set of 100 guests which includes tables, chairs, table clothes, fans, lighting and canopies.\n If you ordered more than 1 set, then you are entitled for free red carpet and stage set-up. ");
        AddOn cAddOn002 = new AddOn("Canopy", 150.00, "Price per canopy. ");
        AddOn cAddOn003 = new AddOn("Disposal Cutlery and Plate", 10.00, "Price per set of 12. ");
        AddOn cAddOn004 = new AddOn("Entertainment Set", 450.00, "Price per set.\n The set consists of Clown, DJ, Game master and Puppet Show. ");
        AddOn cAddOn005 = new AddOn("Furniture Set", 250.00, "Price per set.\n It consists of Canopy,Light and Fans. ");
        AddOn cAddOn006 = new AddOn("Table Set", 250.00, "Price per set of 100 guests which includes tables, chairs and table clothes. ");
        AddOn cAddOn007 = new AddOn("Stage Set", 300.00, "Price per set.\n It consists of Stage, Backdrop and PA System. ");
        AddOn cAddOn008 = new AddOn("Canopy Set", 350.00, "Price per set.\n It consists of Canopy, 5 units of Round Table(8 pax), 40 units of Chair, Fan and Lighting. ");
        AddOn cAddOn009 = new AddOn("Transportation", 100.00, "Please add this option for every order. ");
        AddOn cAddOn010 = new AddOn("Buffet Table", 100.00, "Includes cloth & skirt. ");
        AddOn cAddOn011 = new AddOn("Furniture Set", 300.00, "Price per set.\n It consists of Buffet Table,7 units of Round Table(8 pax)and 50 units of Chair. ");
        AddOn cAddOn012 = new AddOn("Stage Setup", 450.00, "Price per set.\n It consists of Red Carpet and 8x8 inch Stage. ");

        mDatabase.child("CatererAddOn").child("CAT004").child("cAddOn001").setValue(cAddOn001);
        mDatabase.child("CatererAddOn").child("CAT004").child("cAddOn002").setValue(cAddOn002);
        mDatabase.child("CatererAddOn").child("CAT008").child("cAddOn003").setValue(cAddOn003);
        mDatabase.child("CatererAddOn").child("CAT009").child("cAddOn004").setValue(cAddOn004);
        mDatabase.child("CatererAddOn").child("CAT009").child("cAddOn005").setValue(cAddOn005);
        mDatabase.child("CatererAddOn").child("CAT009").child("cAddOn006").setValue(cAddOn006);
        mDatabase.child("CatererAddOn").child("CAT009").child("cAddOn007").setValue(cAddOn007);
        mDatabase.child("CatererAddOn").child("CAT005").child("cAddOn008").setValue(cAddOn008);
        mDatabase.child("CatererAddOn").child("CAT005").child("cAddOn009").setValue(cAddOn009);
        mDatabase.child("CatererAddOn").child("CAT007").child("cAddOn010").setValue(cAddOn010);
        mDatabase.child("CatererAddOn").child("CAT003").child("cAddOn011").setValue(cAddOn011);
        mDatabase.child("CatererAddOn").child("CAT003").child("cAddOn012").setValue(cAddOn012);*/

 /* // Insert packages into Package table
        // Insert packages into Package table
        Packages P001 = new Packages("Package A","Chinese",32.80,FALSE, TRUE ,"You get 15 Dishes.\n 1)Signature Curry Chicken\n 2)Crispy Fried Chicken\n 3)Spring Roll\n 4)Fried Dumplings\n 5)Mushroom & Braised Beancurd\n 6)Thai Style Fish Fillet\n 7)Fried Mixed Vegetables\n 8)Deep Fried Squid\n 9)Fried Meehoon\n 10)Cantonese Chicken Fried Rice\n  11)Local Fruits\n 12)Orange Cordial\n 13)Agar-agar\n 14)Nyonya Kuih\n 15)Sesame Ball\n\n This package is available for buffet\n\n MIN 150 pax \n\n Price per person \n\n Free Transportation, Canopy Service, Chairs, Round Tables and Waiters \n\n This package is suitable for Private events and Wedding");
        Packages P002 = new Packages("Sit Down Menu Set A","Chinese",688.00,FALSE,TRUE ,"You get 9 Dishes:\n 1)Special Four Seasons Platter\n 2)Braised Shark Fin Soup with Fish Maw and Conpoy (Individual Serving)\n 3)Steamed Fresh Grouper With Enoki Mushroom & Black Fungi\n 4)Crispy Fried Chicken\n 5)Scalded Fresh Tiger Prawns\n 6)Braised Abalone Slice with Fish Maw & Mushroom\n 7)Fried Rice with Anchovies & Salted Fish\n 8)Two Varieties of Chinese Pastries\n 9)Longan & Sea Coconut With White Fungus\n\n This package is available for Dome\n\n MIN 150 pax\n\n Price per table \n\n Free Transportation, Canopy Service, Chairs, Round Tables and Waiters\n\n This package is suitable for Private events and Wedding");
        Packages P003 = new Packages("Sit Down Menu Set B ",  "Chinese", 888.00 , FALSE, TRUE, "You get 9 Dishes:\n 1)Deluxe Happiness Five Combinations Platter\n 2)Double – Boiled Chicken Soup With Assorted Dried Seafood (Individual Serving)\n 3)Steamed King Tiger Grouper with Superior Soy Sauce\n 4)Crispy Chicken & Duck Platter\n 5)Baked Tiger Prawns With Salted Egg Yolk\n 6)Braised Mushroom With Sea Cucumber & Seasonal Vegetable\n 7)Hong Kong Style Steamed Glutinous Rice\n 8)Pastry Desserts in Two Varieties\n 9)Boiled Lotus Seeds with Longan & Lily Bulbs\n\n This package is available for Dome\n\n MIN 150 pax\n\n Price per table \n\n Free Transportation , Canopy Service, Chairs, Round Tables and Waiters \n\n This package is suitable for Private events and Wedding" );
        Packages P004 = new Packages("Arabic Food Set A", "Others", 19.90 , TRUE, TRUE, "You will get Arabic Lamb Mandy with rice \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Meeting,Private events and Open House\n\n This package is available for buffet or packed\n\n" );
        Packages P005 = new Packages("Arabic Food Set B",  "Others", 13.50 , TRUE, TRUE, "You will get Arabic Chicken Mandy with rice \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Meeting,Private events and Open House\n\n This package is available for buffet or packed\n\n " );
        Packages P006 = new Packages("Asian Delight Set A",  "Malay", 8.90 , TRUE, TRUE, "You will get Nasi Lemak Special \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Private events and Meeting\n\n This package is available for buffet or packed\n\n ");
        Packages P007 = new Packages( "Asian Delight Set B",  "Malay", 11.90 , TRUE, TRUE, "You will get Grilled Sambal Fish(Kembng) with rice \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Private events and Meeting\n\n This package is available for buffet or packed\n\n ");
        Packages P008 = new Packages("Western Set A",  "Western", 11.90 , TRUE, TRUE, "You will get Sphagetti Bolognese \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Private events,Party,Meeting and Open House\n\n This package is available for buffet or packed\n\n ");
        Packages P009 = new Packages("Western Set B",  "Western", 12.90 , TRUE, TRUE, "You will get Sphagetti Carbonara \n\n Comes with a FREE drink \n\n FREE Delivery\n\n Price per pax \n\n We cater upto 60 pax\n\n This package is suitable for Private events,Party,Meeting and Open House\n\n This package is available for buffet or packed\n\n ");
        Packages P010 = new Packages("The Wedding Banquet",  "Indian", 49.00 , FALSE, TRUE, "You get 8 Dishes:\n 1)Plain Pulao Briyani\n 2)Chicken Kurma\n 3)Malay Mutton Rendang\n 4)Prawn or Fish-Aloo Cutlet\n 5)Sauteed Mixed Vegetables\n 6)Meal Maker Peratel\n 7)Prathaman\n 8)Orange Cordial\n\n This package is available for Banquet or Buffet\n\n Price per pax \n\n This package is suitable for Wedding, Corporate or Private event" );
        Packages P011 = new Packages("Live Malaysian Stalls",  "Malay", 35.00 , FALSE, TRUE, "You get 9 Dishes:\n 1)Superior Grilled Satay (OTS) *\n 2)Roti Jala with Chicken Curry \n 3)Penang Char Kway Teow (OTS) \n 4)Mini Murtabak with Condiments (OTS) \n 5)Rainbow Ice-Kacang (OTS) \n 6)Prawn Mee Hoon\n 7)Potato Apple Salad\n 8)Buttered Oyster Mushroom\n 9)OrangeCordial\n *served with cucumber, onions and peanut sauce\n\n This package is available for live station\n\n Price per pax \n\n This package is suitable for Wedding, Open House or Private event" );
        Packages P012 = new Packages("The Golden Barbeque",  "Western", 65.00 , FALSE, TRUE, "You get 12 Dishes:\n 1)Tender-Roast Lamb Chops (OTS) *\n 2)Grilled Honey Chicken Wings (OTS)\n 3)Chicken Burgers (OTS)\n 4)Buttered Garlic Rice\n 5)Corn on the Cob\n 6)Chicken Sausages\n 7)Garlic Bread\n 8)Bread Rolls\n 9)Fresh Garden Salad\n 10)Ice-Cream in Fruit Cocktail\n 11)Tropical Fruit Punch\n 12)Black Coffee\n \n* Whole Roast Lamb (grilled to perfection at site with Rajah’s special marinade and mint sauce)\n\n This package is available for Buffet and Live Station\n\n Price per pax \n\n This package is suitable for Wedding, Party or Open House");
        Packages P013 = new Packages( "The Grand Garden", "Others", 20.00 , FALSE, TRUE, "You get 11 Dishes:\n 1)Assorted Sandwich\n 2)Meat Puffs\n 3)Chicken Capitan\n 4)Golden Spring Rolls\n 5)Chocolate Eclairs\n 6)Finger Pizza\n 7)Spicy Meatballs\n 8)Cocktail Sausages\n 9)Kuih Lapis\n 10)Kuih Seri Muka\n 11)Tea or Coffee \n\n This package is available for Buffet and Packed\n\n Price per pax \n\n This package is suitable for Wedding, Party, Meeting,Private event or Open House" );
        Packages P014 = new Packages("Selection of Hot Spot", "Malay", 8.00 , TRUE, TRUE, "You get 3 Dishes: 1)Rainbow Ice-Kacang\n 2)Chendol Delight\n 3)Mamak Teh Tarik\n\n This package is available for Live Station\n\n Price per pax \n\n This package is suitable for Wedding, Party, Meeting,Private event or Open House ");
        Packages P015 = new Packages("Mini Buffet Set A", "Malay", 20.90 , TRUE, FALSE, "You get 7 Dishes: 1)Nasi Putih\n 2)Daging Masak Lemak Cili Api\n 3)Sotong Sambal\n 4)Ayam Goreng Kunyit\n 5)Sayur Goreng Brokoli dengan Tahu Jepun\n 6)Ulam-Ulaman & Sambal Belacan\n 7)Puding Caramel\n\n \n\n This package is available for Buffet\n\n Price per pax \n\n This package is suitable for Party, Meeting or Private event/n/n FREE delivery\n\n MIN 12 pax\n\n");
        Packages P016 = new Packages("Mini Buffet Set B", "Malay", 22.50 , TRUE, FALSE, "You get 8 Dishes: 1)Nasi Beriyani\n 2)Daging Masak Hitam\n 3)Ayam Masak Merah\n 4)Isi Ikan Harum Wangi Bunga Kantan\n 5)Acar Buah\n 6)Sayur Campur\n 7)Papedom 8)Puding Caramel\n\n \n\n This package is available for Buffet\n\n Price per pax \n\n This package is suitable for Party, Meeting or Private event/n/n FREE delivery\n\n MIN 12 pax\n\n" );
        Packages P017 = new Packages("Buffet Menu Set A", "Malay", 20.00 , FALSE, TRUE, "You get 12 Dishes: 1)Ayam Masak Merah\n 2)Beef Rendang\n 3)Kerabu Thai Sotong\n 4)Fried Shrimp Bomb\n 5)Dalca Vegetables\n 6)Fried Long Bean With Sambal Belacan\n 7)Acar Rampai\n 8)Fried Mee Mamak\n 9)Beryani Rice\n 10)Mix Malaysian Fruits\n 11)Ice Kacang\n 12)Syrup Cordial\n\n (Minimum 50 Person) \n\n This package is available for Buffet\n\n Price per pax \n\n This package is suitable for Wedding, Meeting or Open House /n/n FREE delivery\n\n " );
        Packages P018 = new Packages("Stall Menu Set A", "Others", 20.00 , FALSE, TRUE, "You get 11 Dishes: 1)Satay (Mix of ChickenBeef / Mutton)\n 2)Cucumber, Onion, Peanut Sauce\n 3)Fried Chicken Wings\n 4)Tasty Butter Fillet Of Fish\n 5)Fried Sotong Ball & Fried Shrimp Bomb\n 6)Grilled Murtabak\n 7)Fried Kway Teow With Prawns & Cockles\n 8)Fried Rice\n 9)Assorted Nyonya Kuih\n 10)Mix Malaysian Fruits\n 11)Orange Squash (Minimum 100 Person) \n\n This package is available for Buffet or Live Station\n\n Price per pax \n\n This package is suitable for Private Event, Party, or Open House /n/n FREE delivery\n\n ");
        Packages P019 = new Packages("Western Menu Set A", "Western", 30.00 , FALSE, TRUE, "You get 13 Dishes: 1)Satay (Mix of ChickenBeef / Mutton) \n 2)Cucumber, Onion, Peanut Sauce\n 3)Roast Chicken With Rosemary\n 4)Grilled Lamb Loin\n 5)Grilled Fillet Of Fish\n 6)Grilled Chicken Sausages\n 7)Mix Vegetables Salad\n 8)Coleslaw Salad\n 9)French Fries\n 10)Spaghetti With Chicken Bolognase Sauce\n 11)Mix Malaysian Fruits\n 12)Coffee & Tea\n 13)Orange Cordial (Minimum 50 Person) \n\n This package is available for Banquet or Live Station\n\n Price per pax \n\n This package is suitable for Private Event, Party, or Open House \n\n FREE delivery\n\n");
        Packages P020 = new Packages("Tea Menu Set A", "Others", 10.00 , FALSE, TRUE, "You get 7 Dishes: 1)Mini Fruit Tarts\n 2)Brownies Cake\n 3)Pulut Panggang\n 4)Kuih Kole Kacang\n 5)Kuih Sri Muka\n 6)Egg Sandwiches\n 7)Coffee & Tea  (Minimum 30 Person) \n\n This package is available for Buffet\n\n Price per pax \n\n This package is suitable for Private Event, Party, Meeting, Wedding or Open House /n/n FREE delivery\n\n" );
        Packages P021 = new Packages("Classic Choice Meal", "Western", 13.90 , TRUE, TRUE, "You get Rotisserie-Roasted Quarter Chicken with Coleslaw and Aromatic Rice\n\n Price per pax.\n\n This package is available for Packed or Buffet\n\n This package is suitable for Private Event, Party, Meeting, Wedding or Open House /n/n"  );
        Packages P022 = new Packages("Kennys Quarter Meal", "Western", 18.90 , TRUE, TRUE, "You get Rotisserie-Roasted 1/4 Chicken with 3 side dishes of the day and Kennys homemade muffin \n\n Price per pax.\n\n This package is available for Packed or Buffet\n\n This package is suitable for Private Event, Party, Meeting, Wedding or Open House /n/n");
        Packages P023 = new Packages("Kennys Half Meal", "Western", 28.80 , TRUE, TRUE, "You get Rotisserie-Roasted 1/2 Chicken with 3 side dishes of the day and Kennys homemade muffin \n\n Price per pax.\n\n This package is available for Packed or Buffet\n\n This package is suitable for Private Event, Party, Meeting, Wedding or Open House /n/n" );
        Packages P024 = new Packages("Value Package", "Malay", 18.00 , FALSE, TRUE, "You get: \n* Fried Rice/ Steam Rice (30%)\n  * Asian Fried Meehoon\n * Signature Curry Chicken with Potato\n * Lemon Fish Fillet\n * Healthy Mixed Vegetables\n * Fried Fish Ball\n * Tasty Sausage\n * Jelly\n * Local Fruits\n * Orange Cordial\n * Foam plate, cup, fork, spoon & napkin (Minimum 50 Person) \n\n This package is available for Buffet or Packed\n\n Price per pax \n\n This package is suitable for Meeting, Wedding or Open House /n/n FREE Swiss roll cake for above 100pax \n " );
        Packages P025 = new Packages( "BBQ ", "Western", 45.00 ,FALSE, TRUE, "You get: \n * CK Fried Rice\n * Tuna & Egg Sandwich\n * Signature Curry Chicken with Potato\n * Salted Egg Prawn\n * Potato Salad\n * BBQ Fish Fillet (CK Special Recipe)\n * BBQ Boneless Chicken Chop\n * BBQ Lamb Chop\n * BBQ Honey Glazed Chicken Sausage\n * BBQ Corn\n (mint & blackpepper sauce)\n * Satay 500 sticks chicken (100pax)\n * Local Fruits\n * Soya Bean / Orange Cordial\n * Foam plate, cup, fork, spoon & napkin (Minimum 50 Person) \n\n This package is available for Buffet \n\n Price per pax \n\n This package is suitable for Meeting, Wedding, Private Event or Open House \n\n FREE Fried Chicken Wing for above 100pax \n ");
        Packages P026 = new Packages( "Non-Vegetarian Box", "Chinese", 14.00 , TRUE, TRUE, "You get: 1)Yong chow fried rice\n 2)Singapore meehoon\n 3)Sweet & sour fish\n 4)Seafood Wonton\n\n (Minimum 50 Person) \n\n This package is available for Packed \n\n Price per pax \n\n This package is suitable for Meeting, Party or Private Event \n\n FREE Delivery \n" );
        Packages P027 = new Packages("Vegetarian Box", "Chinese", 14.00 , TRUE, TRUE,"You get: 1)Vegetable Meehoon.\n 2)Pandan vegeterian sesame chicken\n 3)Vegetarian Spring roll. (Minimum 50 Person) \n\n This package is available for Packed \n\n Price per pax \n\n This package is suitable for Meeting, Party or Private Event \n\n FREE Delivery \n" );
        Packages P028 = new Packages("Party Package", "Others", 60.00 , FALSE, TRUE, "You get: * Yong Chow Fried Rice\n * Asian Fried Rice\n * Spaghetti Bolognese\n * Signature Curry Chicken with Potato\n * Assorted Mini Sandwiches\n * Breaded Fish Fingers\n * Chicken Nugget\n * Fried Fish Ball\n * Mini Sausage\n * Assorted Swiss Roll Cake\n * Local Fruits\n * Ribena Lemon\n * Party Plate Bundle (30 sets)\n * Plastic plate, cup, fork, spoon (Minimum 50 Person) \n\n This package is available for Buffet \n\n Price per pax \n\n This package is suitable for Party, Private Event or Open House /n/n FREE Clown Performance, Party Planner Deco and Delivery\n" );
        Packages P029 = new Packages("Dome Package", "Chinese", 86.00 , FALSE, TRUE, "You get: *Chinese Fried Rice\n * Signature Curry Chicken with Potato\n * Sweet & Sour Fish Fillet\n * Salted Egg Sotong\n * Stir Fry Mix Vegetables\n * Xing Kong Tofu\n * Assorted Bite Size Cakes\n * Orange Cordial (Minimum 50 Person) \n\n This package is available for Dome \n\n Price per pax \n\n This package is suitable for Dome Setup, Complete Cutleries, Mineral Water per guest and Delivery\n" );
        Packages P030 = new Packages("Western Package", "Western", 40.00 , FALSE, TRUE, "You get: *Chicken Mushroom Soup\n * Potato Salad\n * Spaghetti Bolognes (chicken)\n * CK Fried Rice\n * Lamb Chop (blackpepper sauce)\n * Chicken Chop (mushroom sauce)\n * Mayo Fish Fillet\n * Assorted Mini Sandwiches\n * Fried Spring Roll\n * Fried Curry Samosa\n * Mango Pudding\n * Assorted Swiss Roll Cake\n * Iced Lemon Tea (Minimum 50 Person) \n\n This package is available for Buffet \n\n Price per pax \n\n This package is suitable for Party, Private Event, Wedding or Open House /n/n FREE 400 sticks chicken satay (above 100 pax) and Delivery\n");


        mDatabase.child("Packages").child("CAT002").child("P001").setValue(P001);
        mDatabase.child("Packages").child("CAT002").child("P002").setValue(P002);
        mDatabase.child("Packages").child("CAT002").child("P003").setValue(P003);
        mDatabase.child("Packages").child("CAT006").child("P004").setValue(P004);
        mDatabase.child("Packages").child("CAT006").child("P005").setValue(P005);
        mDatabase.child("Packages").child("CAT006").child("P006").setValue(P006);
        mDatabase.child("Packages").child("CAT006").child("P007").setValue(P007);
        mDatabase.child("Packages").child("CAT006").child("P008").setValue(P008);
        mDatabase.child("Packages").child("CAT006").child("P009").setValue(P009);
        mDatabase.child("Packages").child("CAT004").child("P010").setValue(P010);
        mDatabase.child("Packages").child("CAT004").child("P011").setValue(P011);
        mDatabase.child("Packages").child("CAT004").child("P012").setValue(P012);
        mDatabase.child("Packages").child("CAT004").child("P013").setValue(P013);
        mDatabase.child("Packages").child("CAT004").child("P014").setValue(P014);
        mDatabase.child("Packages").child("CAT008").child("P015").setValue(P015);
        mDatabase.child("Packages").child("CAT008").child("P016").setValue(P016);
        mDatabase.child("Packages").child("CAT009").child("P017").setValue(P017);
        mDatabase.child("Packages").child("CAT009").child("P018").setValue(P018);
        mDatabase.child("Packages").child("CAT009").child("P019").setValue(P019);
        mDatabase.child("Packages").child("CAT009").child("P020").setValue(P020);
        mDatabase.child("Packages").child("CAT010").child("P021").setValue(P021);
        mDatabase.child("Packages").child("CAT010").child("P022").setValue(P022);
        mDatabase.child("Packages").child("CAT010").child("P023").setValue(P023);
        mDatabase.child("Packages").child("CAT005").child("P024").setValue(P024);
        mDatabase.child("Packages").child("CAT005").child("P025").setValue(P025);
        mDatabase.child("Packages").child("CAT007").child("P026").setValue(P026);
        mDatabase.child("Packages").child("CAT007").child("P027").setValue(P027);
        mDatabase.child("Packages").child("CAT003").child("P028").setValue(P028);
        mDatabase.child("Packages").child("CAT003").child("P029").setValue(P029);
        mDatabase.child("Packages").child("CAT003").child("P030").setValue(P030);*/
/* OR THIS WAY
          mDatabase = FirebaseDatabase.getInstance().getReference();
            // Insert packages into Package table
            Packages P001 = new Packages("Package A","Chinese",32.80,FALSE, TRUE ,"\n Price per person \n\n You get 15 Dishes.\n 1)Signature Curry Chicken\n 2)Crispy Fried Chicken\n 3)Spring Roll\n 4)Fried Dumplings\n 5)Mushroom & Braised Beancurd\n 6)Thai Style Fish Fillet\n 7)Fried Mixed Vegetables\n 8)Deep Fried Squid\n 9)Fried Meehoon\n 10)Cantonese Chicken Fried Rice\n  11)Local Fruits\n 12)Orange Cordial\n 13)Agar-agar\n 14)Nyonya Kuih\n 15)Sesame Ball \n\n (MIN 150 pax) \n\n Free Transportation, Canopy Service, Chairs, Round Tables and Waiters \n\n This package is available for Buffet \n\n  This package is suitable for Private Events and Wedding","CAT002","Kuala Lumpur","Kuala Lumpur",FALSE);
            Packages P002 = new Packages("Sit Down Menu Set A","Chinese",688.00,FALSE,TRUE ,"\n Price per table \n\n You get 9 Dishes:\n 1)Special Four Seasons Platter\n 2)Braised Shark Fin Soup with Fish Maw and Conpoy (Individual Serving)\n 3)Steamed Fresh Grouper With Enoki Mushroom & Black Fungi\n 4)Crispy Fried Chicken\n 5)Scalded Fresh Tiger Prawns\n 6)Braised Abalone Slice with Fish Maw & Mushroom\n 7)Fried Rice with Anchovies & Salted Fish\n 8)Two Varieties of Chinese Pastries\n 9)Longan & Sea Coconut With White Fungus \n\n (MIN 150 pax) \n\n Free Transportation, Canopy Service, Chairs, Round Tables and Waiters \n\n This package is available for Dome \n\n This package is suitable for Private Events and Wedding","CAT002","Kuala Lumpur","Kuala Lumpur",FALSE);
            Packages P003 = new Packages("Sit Down Menu Set B ",  "Chinese", 888.00 , FALSE, TRUE, "\n Price per table \n\n You get 9 Dishes:\n 1)Deluxe Happiness Five Combinations Platter\n 2)Double – Boiled Chicken Soup With Assorted Dried Seafood (Individual Serving)\n 3)Steamed King Tiger Grouper with Superior Soy Sauce\n 4)Crispy Chicken & Duck Platter\n 5)Baked Tiger Prawns With Salted Egg Yolk\n 6)Braised Mushroom With Sea Cucumber & Seasonal Vegetable\n 7)Hong Kong Style Steamed Glutinous Rice\n 8)Pastry Desserts in Two Varieties\n 9)Boiled Lotus Seeds with Longan & Lily Bulbs\n\n (MIN 150 pax) \n\n Free Transportation , Canopy Service, Chairs, Round Tables and Waiters \n\n This package is available for Dome\n\n This package is suitable for Private Events and Wedding","CAT002" ,"Kuala Lumpur","Kuala Lumpur",FALSE);
            Packages P004 = new Packages("Arabic Food Set A", "Others", 19.90 , TRUE, TRUE, "\n Price per pax \n\n You will get Arabic Lamb Mandy with rice \n\n Comes with a FREE drink \n\n FREE Delivery \n\n We cater upto 60 pax \n\n This package is suitable for Meeting,Private Events and Open House \n\n This package is available for Buffet or Packed" ,"CAT006","Kuala Lumpur","Cheras, Kuala Lumpur",TRUE);
            Packages P005 = new Packages("Arabic Food Set B",  "Others", 13.50 , TRUE, TRUE, "\n Price per pax \n\n You will get Arabic Chicken Mandy with rice \n\n Comes with a FREE drink \n\n FREE Delivery \n\n We cater upto 60 pax \n\n This package is suitable for Meeting,Private Events and Open House \n\n This package is available for Buffet or Packed","CAT006","Kuala Lumpur","Cheras, Kuala Lumpur",TRUE );
            Packages P006 = new Packages("Asian Delight Set A",  "Malay", 8.90 , TRUE, TRUE, "\n Price per pax \n\n You will get Nasi Lemak Special \n\n Comes with a FREE drink \n\n FREE Delivery \n\n We cater up to 60 pax \n\n This package is suitable for Private Events and Meeting\n\n This package is available for Buffet or Packed","CAT006","Kuala Lumpur","Cheras, Kuala Lumpur",TRUE);
            Packages P007 = new Packages( "Asian Delight Set B",  "Malay", 11.90 , TRUE, TRUE, "\n Price per pax \n\n You will get Grilled Sambal Fish(Kembung) with rice \n\n Comes with a FREE drink \n\n FREE Delivery \n\n We cater upto 60 pax \n\n This package is suitable for Private Events and Meeting \n\n This package is available for Buffet or Packed","CAT006","Kuala Lumpur","Cheras, Kuala Lumpur",TRUE);
            Packages P008 = new Packages("Western Set A",  "Western", 11.90 , TRUE, TRUE, "\n Price per pax \n\n You will get Sphagetti Bolognese \n\n Comes with a FREE drink \n\n FREE Delivery \n\n Price per pax \n\n We cater upto 60 pax \n\n This package is suitable for Private Events,Party,Meeting and Open House \n\n This package is available for Buffet or Packed ","CAT006","Kuala Lumpur","Cheras, Kuala Lumpur",TRUE);
            Packages P009 = new Packages("Western Set B",  "Western", 12.90 , TRUE, TRUE, "\n Price per pax \n\n You will get Sphagetti Carbonara \n\n Comes with a FREE drink \n\n FREE Delivery \n\n Price per pax \n\n We cater upto 60 pax \n\n This package is suitable for Private Events,Party,Meeting and Open House \n\n This package is available for Buffet or Packed ","CAT006","Kuala Lumpur","Cheras, Kuala Lumpur",TRUE);
            Packages P010 = new Packages("The Wedding Banquet",  "Indian", 49.00 , FALSE, TRUE, "\n Price per pax \n\n You get 8 Dishes:\n 1)Plain Pulao Briyani\n 2)Chicken Kurma\n 3)Malay Mutton Rendang\n 4)Prawn or Fish-Aloo Cutlet\n 5)Sauteed Mixed Vegetables\n 6)Meal Maker Peratel\n 7)Prathaman\n 8)Orange Cordial \n\n This package is available for Banquet or Buffet \n\n This package is suitable for Wedding, Corporate or Private Events" ,"CAT004","Selangor","Taman Sea, Selangor",TRUE);
            Packages P011 = new Packages("Live Malaysian Stalls",  "Malay", 35.00 , FALSE, TRUE, "\n Price per pax \n\n You get 9 Dishes:\n 1)Superior Grilled Satay (OTS)\n 2)Roti Jala with Chicken Curry\n 3)Penang Char Kway Teow (OTS)\n 4)Mini Murtabak with Condiments (OTS)\n 5)Rainbow Ice-Kacang (OTS)\n 6)Prawn Mee Hoon\n 7)Potato Apple Salad\n 8)Buttered Oyster Mushroom\n 9)OrangeCordial\n *served with cucumber, onions and peanut sauce \n\n This package is available for Live Station \n\n This package is suitable for Wedding, Open House or Private Events","CAT004","Selangor","Taman Sea, Selangor",TRUE );
            Packages P012 = new Packages("The Golden Barbeque",  "Western", 65.00 , FALSE, TRUE, "\n Price per pax \n\n You get 12 Dishes:\n 1)Tender-Roast Lamb Chops (OTS)\n 2)Grilled Honey Chicken Wings (OTS)\n 3)Chicken Burgers (OTS)\n 4)Buttered Garlic Rice\n 5)Corn on the Cob\n 6)Chicken Sausages\n 7)Garlic Bread\n 8)Bread Rolls\n 9)Fresh Garden Salad\n 10)Ice-Cream in Fruit Cocktail\n 11)Tropical Fruit Punch\n 12)Black Coffee\n * Whole Roast Lamb (grilled to perfection at site with Rajah’s special marinade and mint sauce) \n\n This package is available for Buffet and Live Station \n\n This package is suitable for Wedding, Party or Open House","CAT004","Selangor","Taman Sea, Selangor",TRUE);
            Packages P013 = new Packages( "The Grand Garden", "Others", 20.00 , FALSE, TRUE, "\n Price per pax \n\n You get 11 Dishes:\n 1)Assorted Sandwich\n 2)Meat Puffs\n 3)Chicken Capitan\n 4)Golden Spring Rolls\n 5)Chocolate Eclairs\n 6)Finger Pizza\n 7)Spicy Meatballs\n 8)Cocktail Sausages\n 9)Kuih Lapis\n 10)Kuih Seri Muka\n 11)Tea or Coffee \n\n This package is available for Buffet and Packed \n\n This package is suitable for Wedding, Party, Meeting,Private Events or Open House" ,"CAT004","Selangor","Taman Sea, Selangor",TRUE);
            Packages P014 = new Packages("Selection of Hot Spot", "Malay", 8.00 , TRUE, TRUE, "\n Price per pax \n\n You get 3 Dishes:\n 1)Rainbow Ice-Kacang\n 2)Chendol Delight\n 3)Mamak Teh Tarik \n\n This package is available for Live Station \n\n This package is suitable for Wedding, Party, Meeting,Private Events or Open House ","CAT004","Selangor","Taman Sea, Selangor",TRUE);
            Packages P015 = new Packages("Mini Buffet Set A", "Malay", 20.90 , TRUE, FALSE, "\n Price per pax \n\n You get 7 Dishes:\n 1)Nasi Putih\n 2)Daging Masak Lemak Cili Api\n 3)Sotong Sambal\n 4)Ayam Goreng Kunyit\n 5)Sayur Goreng Brokoli dengan Tahu Jepun\n 6)Ulam-Ulaman & Sambal Belacan\n 7)Puding Caramel \n\n This package is available for Buffet \n\n This package is suitable for Party, Meeting or Private Events \n\n FREE delivery\n\n MIN 12 pax","CAT008","Kuala Lumpur","Kuala Lumpur",TRUE);
            Packages P016 = new Packages("Mini Buffet Set B", "Malay", 22.50 , TRUE, FALSE, "\n Price per pax \n\n You get 8 Dishes:\n 1)Nasi Beriyani\n 2)Daging Masak Hitam\n 3)Ayam Masak Merah\n 4)Isi Ikan Harum Wangi Bunga Kantan\n 5)Acar Buah\n 6)Sayur Campur\n 7)Papedom\n 8)Puding Caramel \n\n This package is available for Buffet \n\n This package is suitable for Party, Meeting or Private Events \n\n FREE delivery \n\n MIN 12 pax","CAT008" ,"Kuala Lumpur","Kuala Lumpur",TRUE);
            Packages P017 = new Packages("Buffet Menu Set A", "Malay", 20.00 , FALSE, TRUE, "\n Price per pax \n\n You get 12 Dishes:\n 1)Ayam Masak Merah\n 2)Beef Rendang\n 3)Kerabu Thai Sotong\n 4)Fried Shrimp Bomb\n 5)Dalca Vegetables\n 6)Fried Long Bean With Sambal Belacan\n 7)Acar Rampai\n 8)Fried Mee Mamak\n 9)Beryani Rice\n 10)Mix Malaysian Fruits\n 11)Ice Kacang\n 12)Syrup Cordial \n\n (Minimum 50 Person) \n\n This package is available for Buffet \n\n This package is suitable for Wedding, Meeting or Open House \n\n FREE delivery" ,"CAT009","Kuala Lumpur","Bandar Menjalara, Kuala Lumpur",TRUE);
            Packages P018 = new Packages("Stall Menu Set A", "Others", 20.00 , FALSE, TRUE, "\n Price per pax \n\n You get 11 Dishes:\n 1)Satay (Mix of ChickenBeef / Mutton)\n 2)Cucumber, Onion, Peanut Sauce\n 3)Fried Chicken Wings\n 4)Tasty Butter Fillet Of Fish\n 5)Fried Sotong Ball & Fried Shrimp Bomb\n 6)Grilled Murtabak\n 7)Fried Kway Teow With Prawns & Cockles\n 8)Fried Rice\n 9)Assorted Nyonya Kuih\n 10)Mix Malaysian Fruits\n 11)Orange Squash (Minimum 100 Person) \n\n This package is available for Buffet or Live Station \n\n This package is suitable for Private Events, Party, or Open House \n\n FREE delivery","CAT009","Kuala Lumpur","Bandar Menjalara, Kuala Lumpur",TRUE);
            Packages P019 = new Packages("Western Menu Set A", "Western", 30.00 , FALSE, TRUE, "\n Price per pax \n\n You get 13 Dishes:\n 1)Satay (Mix of ChickenBeef / Mutton)\n 2)Cucumber, Onion, Peanut Sauce\n 3)Roast Chicken With Rosemary\n 4)Grilled Lamb Loin\n 5)Grilled Fillet Of Fish\n 6)Grilled Chicken Sausages\n 7)Mix Vegetables Salad\n 8)Coleslaw Salad\n 9)French Fries\n 10)Spaghetti With Chicken Bolognese Sauce\n 11)Mix Malaysian Fruits\n 12)Coffee & Tea\n 13)Orange Cordial (Minimum 50 Person) \n\n This package is available for Banquet or Live Station\n\n This package is suitable for Private Events, Party, or Open House \n\n FREE delivery","CAT009","Kuala Lumpur","Bandar Menjalara, Kuala Lumpur",TRUE);
            Packages P020 = new Packages("Tea Menu Set A", "Others", 10.00 , FALSE, TRUE, "\n Price per pax \n\n You get 7 Dishes:\n 1)Mini Fruit Tarts\n 2)Brownies Cake\n 3)Pulut Panggang\n 4)Kuih Kole Kacang\n 5)Kuih Sri Muka\n 6)Egg Sandwiches\n 7)Coffee & Tea\n (Minimum 30 Person) \n\n This package is available for Buffet \n\n This package is suitable for Private Events, Party, Meeting, Wedding or Open House \n\n FREE delivery","CAT009","Kuala Lumpur","Bandar Menjalara, Kuala Lumpur",TRUE );
            Packages P021 = new Packages("Classic Choice Meal", "Western", 13.90 , TRUE, TRUE, "\n Price per pax \n\n You get Rotisserie-Roasted Quarter Chicken with Coleslaw and Aromatic Rice \n\n This package is available for Packed or Buffet \n\n This package is suitable for Private Events, Party, Meeting, Wedding or Open House" ,"CAT010","Kuala Lumpur","Berjaya Times Square, Kuala Lumpur",TRUE );
            Packages P022 = new Packages("Kenny's Quarter Meal", "Western", 18.90 , TRUE, TRUE, "\n Price per pax \n\n You get Rotisserie-Roasted 1/4 Chicken with 3 side dishes of the day and Kenny's homemade muffin \n\n This package is available for Packed or Buffet \n\n This package is suitable for Private Events, Party, Meeting, Wedding or Open House","CAT010","Kuala Lumpur","Berjaya Times Square, Kuala Lumpur",TRUE );
            Packages P023 = new Packages("Kenny's Half Meal", "Western", 28.80 , TRUE, TRUE, "\n Price per pax \n\n You get Rotisserie-Roasted 1/2 Chicken with 3 side dishes of the day and Kenny's homemade muffin \n\n This package is available for Packed or Buffet \n\n This package is suitable for Private Events, Party, Meeting, Wedding or Open House" ,"CAT010","Kuala Lumpur","Berjaya Times Square, Kuala Lumpur",TRUE );
            Packages P024 = new Packages("Value Package", "Malay", 18.00 , FALSE, TRUE, "\n Price per pax \n\n You get:\n 1)Fried Rice/ Steam Rice (30%)\n 2)Asian Fried Meehoon\n 3)Signature Curry Chicken with Potato\n 4)Lemon Fish Fillet\n 5)Healthy Mixed Vegetables\n 6)Fried Fish Ball\n 7)Tasty Sausage\n 8)Jelly\n 9)Local Fruits\n 10)Orange Cordial\n * Foam plate, cup, fork, spoon & napkin (Minimum 50 Person) \n\n This package is available for Buffet or Packed \n\n This package is suitable for Meeting, Wedding or Open House \n\n FREE Swiss roll cake for above 100pax ","CAT005" ,"Selangor","Puchong, Selangor",TRUE );
            Packages P025 = new Packages( "BBQ ", "Western", 45.00 ,FALSE, TRUE, "\n Price per pax \n\n You get:\n 1)CK Fried Rice\n 2)Tuna & Egg Sandwich\n 3)Signature Curry Chicken with Potato\n 4)Salted Egg Prawn\n 5)Potato Salad\n 6)BBQ Fish Fillet (CK Special Recipe)\n 7)BBQ Boneless Chicken Chop\n 8)BBQ Lamb Chop\n 9)BBQ Honey Glazed Chicken Sausage\n 10)BBQ Corn\n (mint & blackpepper sauce)\n * Satay 500 sticks chicken (100pax)\n * Local Fruits\n * Soya Bean / Orange Cordial\n * Foam plate, cup, fork, spoon & napkin (Minimum 50 Person) \n\n This package is available for Buffet \n\n This package is suitable for Meeting, Wedding, Private Events or Open House \n\n FREE Fried Chicken Wing for above 100pax ","CAT005","Selangor","Puchong, Selangor",TRUE );
            Packages P026 = new Packages( "Non-Vegetarian Box", "Chinese", 14.00 , TRUE, TRUE, "\n Price per pax \n\n You get:\n 1)Yong chow fried rice\n 2)Singapore meehoon\n 3)Sweet & sour fish\n 4)Seafood Wonton \n\n (Minimum 50 Person) \n\n This package is available for Packed \n\n This package is suitable for Meeting, Party or Private Events \n\n FREE Delivery","CAT007" ,"Selangor","PJ, Selangor",TRUE );
            Packages P027 = new Packages("Vegetarian Box", "Chinese", 14.00 , TRUE, TRUE,"\n Price per pax \n\n You get:\n 1)Vegetable Meehoon.\n 2)Pandan vegeterian sesame chicken\n 3)Vegetarian Spring roll. (Minimum 50 Person) \n\n This package is available for Packed \n\n This package is suitable for Meeting, Party or Private Events \n\n FREE Delivery" ,"CAT007","Selangor","PJ, Selangor",TRUE );
            Packages P028 = new Packages("Party Package", "Others", 60.00 , FALSE, TRUE, "\n Price per pax \n\n You get:\n 1)Yong Chow Fried Rice\n 2)Asian Fried Rice\n 3)Spaghetti Bolognese\n 4)Signature Curry Chicken with Potato\n 5)Assorted Mini Sandwiches\n 6)Breaded Fish Fingers\n 7)Chicken Nugget\n 8)Fried Fish Ball\n 9)Mini Sausage\n 10)Assorted Swiss Roll Cake\n 11)Local Fruits\n 12)Ribena Lemon\n 13)Party Plate Bundle (30 sets)\n * Plastic plate, cup, fork, spoon (Minimum 50 Person) \n\n This package is available for Buffet \n\n This package is suitable for Party, Private Events or Open House \n\n FREE Clown Performance, Party Planner Deco and Delivery","CAT003" ,"Kuala Lumpur","Raja Chulan, Kuala Lumour",TRUE );
            Packages P029 = new Packages("Dome Package", "Chinese", 86.00 , FALSE, TRUE, "\n Price per pax \n\n You get:\n 1)Chinese Fried Rice\n 2)Signature Curry Chicken with Potato\n 3)Sweet & Sour Fish Fillet\n 4)Salted Egg Sotong\n 5)Stir Fry Mix Vegetables\n 6)Xing Kong Tofu\n 7)Assorted Bite Size Cakes\n 8)Orange Cordial\n (Minimum 50 Person) \n\n This package is available for Dome \n\n This package is suitable for Wedding \n\n FREE Setup, Complete Cutleries, Mineral Water per guest and Delivery" ,"CAT003","Kuala Lumpur","Raja Chulan, Kuala Lumour",TRUE  );
            Packages P030 = new Packages("Western Package", "Western", 40.00 , FALSE, TRUE, "\n Price per pax \n\n You get:\n 1)Chicken Mushroom Soup\n 2)Potato Salad\n 3)Spaghetti Bolognes (chicken)\n 4)CK Fried Rice\n 5)Lamb Chop (blackpepper sauce)\n 6)Chicken Chop (mushroom sauce)\n 7)Mayo Fish Fillet\n 8)Assorted Mini Sandwiches\n 9)Fried Spring Roll\n 10)Fried Curry Samosa\n 11)Mango Pudding\n 12)Assorted Swiss Roll Cake\n 13)Iced Lemon Tea (Minimum 50 Person) \n\n This package is available for Buffet \n\n This package is suitable for Party, Private Events, Wedding or Open House \n\n FREE 400 sticks chicken satay (above 100 pax) and Delivery","CAT003" ,"Kuala Lumpur","Raja Chulan, Kuala Lumour",TRUE );


            mDatabase.child("Packages").child("P001").setValue(P001);
            mDatabase.child("Packages").child("P002").setValue(P002);
            mDatabase.child("Packages").child("P003").setValue(P003);
            mDatabase.child("Packages").child("P004").setValue(P004);
            mDatabase.child("Packages").child("P005").setValue(P005);
            mDatabase.child("Packages").child("P006").setValue(P006);
            mDatabase.child("Packages").child("P007").setValue(P007);
            mDatabase.child("Packages").child("P008").setValue(P008);
            mDatabase.child("Packages").child("P009").setValue(P009);
            mDatabase.child("Packages").child("P010").setValue(P010);
            mDatabase.child("Packages").child("P011").setValue(P011);
            mDatabase.child("Packages").child("P012").setValue(P012);
            mDatabase.child("Packages").child("P013").setValue(P013);
            mDatabase.child("Packages").child("P014").setValue(P014);
            mDatabase.child("Packages").child("P015").setValue(P015);
            mDatabase.child("Packages").child("P016").setValue(P016);
            mDatabase.child("Packages").child("P017").setValue(P017);
            mDatabase.child("Packages").child("P018").setValue(P018);
            mDatabase.child("Packages").child("P019").setValue(P019);
            mDatabase.child("Packages").child("P020").setValue(P020);
            mDatabase.child("Packages").child("P021").setValue(P021);
            mDatabase.child("Packages").child("P022").setValue(P022);
            mDatabase.child("Packages").child("P023").setValue(P023);
            mDatabase.child("Packages").child("P024").setValue(P024);
            mDatabase.child("Packages").child("P025").setValue(P025);
            mDatabase.child("Packages").child("P026").setValue(P026);
            mDatabase.child("Packages").child("P027").setValue(P027);
            mDatabase.child("Packages").child("P028").setValue(P028);
            mDatabase.child("Packages").child("P029").setValue(P029);
            mDatabase.child("Packages").child("P030").setValue(P030);
/*
//Insert caterer addOn into CatererAddOn table
        AddOn cAddOn001 = new AddOn("Furnitre Set", 550.00, "Price per set of 100 guests which includes tables, chairs, table clothes, fans, lighting and canopies.\n If you ordered more than 1 set, then you are entitled for free red carpet and stage set-up. ","CAT004");
                AddOn cAddOn002 = new AddOn("Canopy", 150.00, "Price per canopy. ","CAT004");
                AddOn cAddOn003 = new AddOn("Disposal Cutlery and Plate", 10.00, "Price per set of 12. ","CAT008");
                AddOn cAddOn004 = new AddOn("Entertainment Set", 450.00, "Price per set.\n The set consists of Clown, DJ, Game master and Puppet Show. ","CAT009");
                AddOn cAddOn005 = new AddOn("Furniture Set", 250.00, "Price per set.\n It consists of Canopy,Light and Fans. ","CAT009");
                AddOn cAddOn006 = new AddOn("Table Set", 250.00, "Price per set of 100 guests which includes tables, chairs and table clothes. ","CAT009");
                AddOn cAddOn007 = new AddOn("Stage Set", 300.00, "Price per set.\n It consists of Stage, Backdrop and PA System. ","CAT009");
                AddOn cAddOn008 = new AddOn("Canopy Set", 350.00, "Price per set.\n It consists of Canopy, 5 units of Round Table(8 pax), 40 units of Chair, Fan and Lighting. ","CAT005");
                AddOn cAddOn009 = new AddOn("Transportation", 100.00, "Please add this option for every order. ", "CAT005");
                AddOn cAddOn010 = new AddOn("Buffet Table", 100.00, "Includes cloth & skirt. ","CAT007");
                AddOn cAddOn011 = new AddOn("Furniture Set", 300.00, "Price per set.\n It consists of Buffet Table,7 units of Round Table(8 pax)and 50 units of Chair. ","CAT003");
                AddOn cAddOn012 = new AddOn("Stage Setup", 450.00, "Price per set.\n It consists of Red Carpet and 8x8 inch Stage. ","CAT003");

                mDatabase.child("CatererAddOn").child("cAddOn001").setValue(cAddOn001);
                mDatabase.child("CatererAddOn").child("cAddOn002").setValue(cAddOn002);
                mDatabase.child("CatererAddOn").child("cAddOn003").setValue(cAddOn003);
                mDatabase.child("CatererAddOn").child("cAddOn004").setValue(cAddOn004);
                mDatabase.child("CatererAddOn").child("cAddOn005").setValue(cAddOn005);
                mDatabase.child("CatererAddOn").child("cAddOn006").setValue(cAddOn006);
                mDatabase.child("CatererAddOn").child("cAddOn007").setValue(cAddOn007);
                mDatabase.child("CatererAddOn").child("cAddOn008").setValue(cAddOn008);
                mDatabase.child("CatererAddOn").child("cAddOn009").setValue(cAddOn009);
                mDatabase.child("CatererAddOn").child("cAddOn010").setValue(cAddOn010);
                mDatabase.child("CatererAddOn").child("cAddOn011").setValue(cAddOn011);
                mDatabase.child("CatererAddOn").child("cAddOn012").setValue(cAddOn012);*/


/*
    private ArrayAdapter<String> checkAddOn() {
        arrayList.clear();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Packages pack = dataSnapshot1.getValue(Packages.class);
                    if ((pack.getPackDescription()).contains("Indian")) {
                        list = pack.toStringFirst();
                        arrayAdapter.add(list);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    return arrayAdapter;
    }

    private ArrayAdapter<String> checkHalal() {
        arrayList.clear();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Packages pack = dataSnapshot1.getValue(Packages.class);
                    if ((pack.getPackDescription()).contains("Malay")){
                        list =  pack.toStringFirst();
                        arrayAdapter.add(list);
                    }
                }

                Log.i("success product count",dataSnapshot.getChildrenCount()+"");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
        return arrayAdapter;
    }*/
 /*if(cbIndian.isChecked()){
           // arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        */
     /*   if(cbChinese.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbWestern.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbOthers.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbBuffet.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbBanquet.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbDome.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbPack.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbLive.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbMeeting.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbWedding.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbParty.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbPrivate.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        } if(cbOpen.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbAddOn.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        if(cbHalal.isChecked()){
            arrayList.clear();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Packages pack = dataSnapshot1.getValue(Packages.class);
                        if ((pack.getPackCuisine()).contains("Malay")){
                            list =  pack.toStringFirst();
                            arrayAdapter.add(list);
                        }
                        listView.setAdapter(arrayAdapter);
                    }

                    Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }*/


      /*  if(cbBanquet.isChecked() && id == R.id.banquet){
            listView.setAdapter(checkBanquet());
        }
        if(cbBuffet.isChecked() && id == R.id.buffet){
            listView.setAdapter(checkBuffet());
        }
        if(id == R.id.livestaion && cbLive.isChecked()){
            listView.setAdapter(checkLive());
        }
        if(id == R.id.packed &&  cbPack.isChecked()){
            listView.setAdapter(checkPack());
        }
        if(id == R.id.dome && cbDome.isChecked()){
            listView.setAdapter(checkDome());
        }
        if(id == R.id.malay && cbMalay.isChecked()) {
            listView.setAdapter(checkMalay());
        }
        if(id == R.id.chinese && cbChinese.isChecked() ){
            listView.setAdapter(checkChinese());
        }
        if(id == R.id.indian && cbIndian.isChecked()){
            listView.setAdapter(checkIndian());
        }
        if( id == R.id.western &&cbWestern.isChecked()){
            listView.setAdapter(checkWestern());
        }
        if( id == R.id.others&&cbOthers.isChecked() ){
            listView.setAdapter(checkOthers());
        }
*/

     /*   switch (id){
            case  R.id.banquet: checkBanquet();
            case  R.id.buffet: checkBuffet();
            case  R.id.livestaion: checkLive();
            case  R.id.packed: checkPack();
            case  R.id.dome: checkDome();
            case  R.id.malay: checkMalay();
            case  R.id.chinese: checkChinese();
            case  R.id.indian: checkIndian();
            case  R.id.western: checkWestern();
            case  R.id.others: checkOthers();
        }
*/

     /*
//SIGN UP

 private DatabaseReference mDatabase;
    private ListView listView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    String list = "";
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private EditText txtMail,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        //listView = (ListView)findViewById(R.id.lv);
        mAuth = FirebaseAuth.getInstance();

        // Views
        txtMail = (EditText) findViewById(R.id.textEmail);
        txtPassword = (EditText) findViewById(R.id.textPassword);

        mAuth.signOut();
        // check if user is signed in then start dashboardactivity
        if(mAuth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(MainActivity.this,create_account_2.class);
            startActivity(intent);
            finish();
        }
    }
    // to validate the form is user has filled the required fields
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(txtMail.getText().toString())) {
            txtMail.setError("Required");
            result = false;
        } else {
            txtMail.setError(null);
        }
        if (TextUtils.isEmpty(txtPassword.getText().toString())) {
            txtPassword.setError("Required");
            result = false;
        } else {
            txtPassword.setError(null);
        }
        return result;
    }

    public void next(View view) {

        Log.d("MainActivity", "signIn");
        if (!validateForm()) {
            return;
        }
        showProgressDialog();
        String email = txtMail.getText().toString();
        String password = txtPassword.getText().toString();
        // to sign in the user at firebase
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("MainActivity", "signIn:onComplete:" + task.isSuccessful());
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(MainActivity.this,create_account_2.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Sign In Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }
        mProgressDialog.show();
    }
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    public void signout(View view) {

        mAuth.signOut();
        Toast.makeText(MainActivity.this,"Signed Out",Toast.LENGTH_SHORT).show();

        }

    public void signUp(View view) {
        Intent i = new Intent(MainActivity.this,create_account_1.class);
        startActivity(i);

    }


//LOG IN

  private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private EditText txtMail,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        //listView = (ListView)findViewById(R.id.lv);
        mAuth = FirebaseAuth.getInstance();

        // Views
        txtMail = (EditText) findViewById(R.id.textEmail);
        txtPassword = (EditText) findViewById(R.id.textPassword);

        // check if user is signed in then start dashboardactivity
        if(mAuth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(MainActivity.this,create_account_3.class);
            startActivity(intent);
            finish();
        }
    }
    // to validate the form is user has filled the required fields
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(txtMail.getText().toString())) {
            txtMail.setError("Required");
            result = false;
        } else {
            txtMail.setError(null);
        }
        if (TextUtils.isEmpty(txtPassword.getText().toString())) {
            txtPassword.setError("Required");
            result = false;
        } else {
            txtPassword.setError(null);
        }
        return result;
    }

    public void next(View view) {

        Log.d("MainActivity", "signIn");
        if (!validateForm()) {
            return;
        }
        showProgressDialog();
        String email = txtMail.getText().toString();
        String password = txtPassword.getText().toString();
// to sign in the user at firebase
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("MainActivity", "signIn:onComplete:" + task.isSuccessful());
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(MainActivity.this,create_account_1.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Sign In Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }
        mProgressDialog.show();
    }
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

//list packages in list view
mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                //Caterer caterer = postSnapshot.getValue(Caterer.class);
                //Charity charity = postSnapshot.getValue(Charity.class);
                //AddOn addOn = postSnapshot.getValue(AddOn.class);
                Packages pack = postSnapshot.getValue(Packages.class);
                list = list + pack.toString();
                arrayAdapter.add(list);



            }
            listView.setAdapter(arrayAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

            // [START_EXCLUDE]
            System.out.println("The read failed: " + databaseError.getMessage());
        }
    });


//sort by price but with 3 cond filter

 mCaterer = FirebaseDatabase.getInstance().getReference().child("Packages");

query = mCaterer.orderByChild("packPrice");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Packages pack = dataSnapshot1.getValue(Packages.class);
                    if ((pack.getPackCuisine()).contains("Western"))
                        if ((pack.getPackDescription()).contains("Packed")) {
                            if ((pack.getPackDescription()).contains("Wedding")) {
                                list = list + pack.toString();
                            }
                        }
                }

                Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                txt.setText(list.toString());
            }
             @Override
            public void onCancelled(DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });


//sort accord to price but search pack with packed n wedding
 mCaterer = FirebaseDatabase.getInstance().getReference().child("Packages");
mCaterer = FirebaseDatabase.getInstance().getReference().child("Packages");
        query = mCaterer.orderByChild("packPrice");

        txt = (TextView) findViewById(R.id.text);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Packages pack=dataSnapshot1.getValue(Packages.class);
                    if((pack.getPackDescription()).contains("Packed")) {
                        if ((pack.getPackDescription()).contains("Wedding")) {
                            list = list + pack.toString();
                        }
                    }
                }
                Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                txt.setText(list.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });


//dipsplay rows with more than 1 preference
 mCaterer = FirebaseDatabase.getInstance().getReference().child("Packages");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Packages pack=dataSnapshot1.getValue(Packages.class);
                    if((pack.getPackDescription()).contains("Packed")) {
                        if ((pack.getPackDescription()).contains("Wedding")) {
                            list = list + pack.toString();
                        }
                    }
                }
                Log.i("success product count",dataSnapshot.getChildrenCount()+"");
                txt.setText(list.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });


        //display all rows in the table
        mCaterer.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //Caterer caterer = postSnapshot.getValue(Caterer.class);
                        //Charity charity = postSnapshot.getValue(Charity.class);
                        //AddOn addOn = postSnapshot.getValue(AddOn.class);
                        Packages pack = postSnapshot.getValue(Packages.class);

                        list = list + pack.toString();

                    }
                    txt.setText(list.toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });


            //display all packages with preferred cuisine
        mCaterer = FirebaseDatabase.getInstance().getReference().child("Packages");
        query = mCaterer.orderByChild("packCuisine").equalTo("Chinese");

        txt = (TextView) findViewById(R.id.text);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("success product count",dataSnapshot.getChildrenCount()+"");

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Packages pack=dataSnapshot1.getValue(Packages.class);
                    list = list + pack.toString();

                }
                txt.setText(list.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });*/



   /*     //get data
        email = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.name);*/

       /* //set data
        Caterer caterer = new Caterer();
        caterer.setCatererID("CAT001");
        caterer.setCatererName("Tuk Tuk Kitchen");
        caterer.setCatererMail("tuktuk@gmail.com");
        caterer.setCatererAddress("PJ, Selangor");
        caterer.setCatererNumber("0127987011");
        caterer.setHalalReg("2215-04/2013");
        caterer.setCompanyReg("123456-Z");
*/


      /*  //validate
        if(name!= null && name.length()>0){
            if(helper.saved(caterer)){
                name.setText("");
                email.setText("");

                adapter = new CustomAdapter(MainActivity.this,helper.retrieve());
                lv.setAdapter(adapter);
            }
            else{
                Toast.makeText(MainActivity.this,"Name must not be empty",Toast.LENGTH_SHORT).show();
            }
        }
*/
/*FOR COUNT
 DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Packages").child("P001").child("count").setValue(0);
        mDatabase.child("Packages").child("P002").child("count").setValue(0);
        mDatabase.child("Packages").child("P003").child("count").setValue(0);
        mDatabase.child("Packages").child("P004").child("count").setValue(0);
        mDatabase.child("Packages").child("P005").child("count").setValue(0);
        mDatabase.child("Packages").child("P006").child("count").setValue(0);
        mDatabase.child("Packages").child("P007").child("count").setValue(0);
        mDatabase.child("Packages").child("P008").child("count").setValue(0);
        mDatabase.child("Packages").child("P009").child("count").setValue(0);
        mDatabase.child("Packages").child("P010").child("count").setValue(0);
        mDatabase.child("Packages").child("P011").child("count").setValue(0);
        mDatabase.child("Packages").child("P012").child("count").setValue(0);
        mDatabase.child("Packages").child("P013").child("count").setValue(0);
        mDatabase.child("Packages").child("P014").child("count").setValue(0);
        mDatabase.child("Packages").child("P015").child("count").setValue(0);
        mDatabase.child("Packages").child("P016").child("count").setValue(0);
        mDatabase.child("Packages").child("P017").child("count").setValue(0);
        mDatabase.child("Packages").child("P018").child("count").setValue(0);
        mDatabase.child("Packages").child("P019").child("count").setValue(0);
        mDatabase.child("Packages").child("P020").child("count").setValue(0);
        mDatabase.child("Packages").child("P021").child("count").setValue(0);
        mDatabase.child("Packages").child("P022").child("count").setValue(0);
        mDatabase.child("Packages").child("P023").child("count").setValue(0);
        mDatabase.child("Packages").child("P024").child("count").setValue(0);
        mDatabase.child("Packages").child("P025").child("count").setValue(0);
        mDatabase.child("Packages").child("P026").child("count").setValue(0);
        mDatabase.child("Packages").child("P027").child("count").setValue(0);
        mDatabase.child("Packages").child("P028").child("count").setValue(0);
        mDatabase.child("Packages").child("P029").child("count").setValue(0);;
        mDatabase.child("Packages").child("P030").child("count").setValue(0);

 */