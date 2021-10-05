package menu;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class Menu {

  private static String town;
  public static String date;
  public static int int_date;
  public static double lat;
  public static double lng;


  public static void main(String[] args) throws Exception {

    town = "Syrgyt";
    lat = 39.78373;
    lng = -100.445882;
    date = "today";
    int_date = 0;


    Properties props = new Properties();
    String key = "null";
    try (InputStream in = new FileInputStream("src/main/resources/config.properties")) {
      props.load(in);
      in.close();
      key = (String) props.get("Key");
       }
    catch (Exception e)
    {
      System.out.println("Can't find key");

    }

    String key_town = "null";
    try (InputStream in = new FileInputStream("src/main/resources/config.properties")) {
      props.load(in);
      in.close();
      key_town = (String) props.get("Key_town");
    }
    catch (Exception e)
    {
      System.out.println("Can't find key");

    }

    System.out.println("Welcome to menu");






    boolean ExitValue=false;
    int cont_menu;
    while (!ExitValue) {

      System.out.println("1 - change town, 2 - choose date, 3 - run, 4 - exit");
      System.out.println("Current town =" + town + ", current date =" + date);

      Scanner in_c = new Scanner(System.in);

      try {cont_menu = in_c.nextInt();}
      catch(InputMismatchException exception) {
        cont_menu = 0;
      }

      switch (cont_menu) {
        case 1:
          Action.Town(key_town);
          break;
        case 4:
          ExitValue=true;
          break;
        case 2:
          // date=Action.Date();
          Action.Date();
          break;
        case 3:
          Action.Run(key);
          break;
        default:
          System.out.println("Try again!");
          break;

      }
    }




  }


  static class Action {
    public static void Town(String key_town) throws Exception {
      Scanner in_c = new Scanner(System.in);
      System.out.println("Enter town");
      town = in_c.nextLine();
      String JSON_Town =  new HttpClient().sendGetTown(key_town, town);
      // System.out.println(JSON_Town);


      JSONObject json = new JSONObject(JSON_Town);

      JSONArray results = json.getJSONArray("results");
      JSONObject f_0 = results.getJSONObject(0);
      JSONArray locations = f_0.getJSONArray("locations");
      JSONObject locations_0 = locations.getJSONObject(0);

      town = locations_0.getString("adminArea5");
      String country = locations_0.getString("adminArea1");

      JSONObject latLng = locations_0.getJSONObject("latLng");
      lat = latLng.getDouble("lat");
      lng = latLng.getDouble("lng");

      System.out.println("Selected city=" + town + " " + country);




    }

    public static void Date() {

      System.out.println("0 - today, 1 - tomorrow, 2 - day after tomorrow");
      Scanner in_c = new Scanner(System.in);
      // String date;
      int in;
      try {in = in_c.nextInt();}
      catch(InputMismatchException exception) {
        in = 0;
      }
      switch (in) {
        case 0:
          date = "today";
          int_date =  in;
          break;
        case 1:
          date = "tomorrow";
          int_date =  in;
          break;
        case 2:
          date = "day after tomorrow";
          int_date =  in;
          break;
        default:
          System.out.println("Error. Date set to today");
          date = "today";
          int_date =  0;
          break;}

      //return date;
    }

    public static void Run(String key) throws Exception {

      String JSON =  new HttpClient().sendGet(key, lat, lng);
       // System.out.println(JSON);
       // System.out.println(int_date);

      JSONObject json = new JSONObject(JSON);

      JSONArray forecasts = json.getJSONArray("forecasts");
      JSONObject f_0 = forecasts.getJSONObject(int_date);
      JSONObject parts = f_0.getJSONObject("parts");
      JSONObject day = parts.getJSONObject("day");
      double temp_avg = day.getDouble("temp_avg");
      System.out.println("temp_avg =" + temp_avg);

      double wind_speed = day.getDouble("wind_speed");
      System.out.println("wind speed =" + wind_speed);

      String condition = day.getString("condition");
      System.out.println("condition =" + condition);

    }



    public static boolean isAlpha(String name) {
      char[] chars = name.toCharArray();

      for (char c : chars) {
        if(!Character.isLetter(c)) {
          return false;
        }
      }

      return true;
    }



  }
}

