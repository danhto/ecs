package com.ibm.cfc.godsplan.sandbox;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import com.google.gson.GsonBuilder;
import com.google.maps.errors.ApiException;
import com.ibm.cfc.godsplan.mapbox.model.DirectionInformation;
import com.ibm.cfc.godsplan.maps.LocationMapper;
import com.ibm.cfc.godsplan.maps.model.GoogleAddressInformation;
import com.mapbox.geojson.Point;

public class Example
{
   // Find your Account Sid and Token at twilio.com/user/account
   public static final String ACCOUNT_SID = "*";
   public static final String AUTH_TOKEN = "*";
   public static final String CLIENT_PHONE_NUMBER = "+*";
   public static final String SERVER_PHONE_NUMBER = "+*";

   public static void main(String[] args) throws ApiException, InterruptedException, IOException, URISyntaxException
   {
      // Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      //
      // WatsonAssistantBot watson = new WatsonAssistantBot();
      // watson.sendAssistantMessage(Optional.empty(), Optional.empty());

      // Message message = Message
      // .creator(new PhoneNumber(CLIENT_PHONE_NUMBER), new
      // PhoneNumber(SERVER_PHONE_NUMBER), resp).create();
      //
      // System.out.println(message.getSid());

      String rawAddress = "8200 Warden Ave";
      LocationMapper mapper = new LocationMapper();
      List<GoogleAddressInformation> addresses = mapper.getFormattedAddress(rawAddress);
      for (GoogleAddressInformation address : addresses)
      {
         System.out.println(address);
      }
      Point coords1 = mapper.getGeocodingCoordinates(rawAddress);
      rawAddress = "First Markham Place";
      Point coords2 = mapper.getGeocodingCoordinates(rawAddress);

      DirectionInformation info = mapper.getDirectionInformation(coords1, coords2, Optional.empty(), Optional.empty());
      File file = new File("./src/main/resources/directions.jpg");
      System.out.println(info.getDistance());
      System.out.println(info.getPolyline());

      System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(info.getRouteDetails()));
      mapper.getGoogleImage(rawAddress, "600x400", file, Optional.of(info));

      // CloudantPersistence p = new CloudantPersistence();
      // p.persistHasSpace(CLIENT_PHONE_NUMBER, true);

      //		 CloudantPersistence p = new CloudantPersistence();
      //		 p.removePhoneNumber(CLIENT_PHONE_NUMBER);
      //		 p.persistAddress(CLIENT_PHONE_NUMBER, addresses.get(0));
      //		 Optional<LocationContext> addressInfo =
      //		 p.retrieveAddress(CLIENT_PHONE_NUMBER);
      //		 System.out.println(addressInfo.get().getAddress().toString());
      //		
      //		 p.persistAddressConfirmation(CLIENT_PHONE_NUMBER, true);
      //		 p.persistMustEvacuate(CLIENT_PHONE_NUMBER, true);
      //		 p.persistHasSpace(CLIENT_PHONE_NUMBER, true);
      //		 p.persistHasVehicle(CLIENT_PHONE_NUMBER, true);
      //		 Optional<SurveyContext> context =
      //		 p.retrieveSurveyContext(CLIENT_PHONE_NUMBER);
      //		 System.out.println(context.get().toString());
      //		 
      //		 p.persistFireLocation("1", new Coordinates(1,2));
      //		 p.persistShelterLocation("1", addresses.get(0));
      //		 p.shutdown();

      // String imageURI =
      // mapper.getGoogleImageURI(addresses.get(0).getFormattedAddress());
      // String body = "Here is a map of your location";
      // Message.creator(new PhoneNumber(CLIENT_PHONE_NUMBER), new
      // PhoneNumber(SERVER_PHONE_NUMBER), body)
      // .setMediaUrl(Promoter.listOfOne(URI.create(imageURI))).create();

   }
}
