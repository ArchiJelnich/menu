package menu;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class Menu {
  public static void main(String[] args) {

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


    System.out.println("Welcome to menu");


    String town = "Syrgyt";
    String date = "today";



    boolean ExitValue=false;
    int cont_menu = 0;
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
          town=Action.Town();
          break;
        case 4:
          ExitValue=true;
          break;
        case 2:
          date=Action.Date();
          break;
        case 3:
          System.out.println(Action.Run(town, date, key));
          break;
        default:
          System.out.println("Try again!");
          break;

      }
    }




  }


  static class Action {
    public static String Town() {
      Scanner in_c = new Scanner(System.in);
      System.out.println("Enter town");
      String town = in_c.nextLine();
      if (!Menu.Action.isAlpha(town)) {System.out.println("Wrong input");
        town = "Syrgyt"; }

      // Добавить проверку на пустую строчку
      // Убрать перезатирание при непарвильном ввводе

      return town;
    }

    public static String Date() {

      System.out.println("1 - today, 2 - tomorrow, 3 - day after tomorrow");
      Scanner in_c = new Scanner(System.in);
      String date;
      int in;
      try {in = in_c.nextInt();}
      catch(InputMismatchException exception) {
        in = 0;
      }
      switch (in) {
        case 1:
          date = "today";
          break;
        case 2:
          date = "tomorrow";
          break;
        case 3:
          date = "day after tomorrow";
          break;
        default:
          System.out.println("Error. Date set to today");
          date = "today";
          break;}

      return date;
    }

    public static String Run(String town, String date, String key) {
      String run = "Result of running= " + town + " + " + date + " key=" + key;
      return run;
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

