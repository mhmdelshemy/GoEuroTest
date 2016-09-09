package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.CityData;

/**
 * Created by elshemma on 9/8/2016.
 */
public class TestParser {

   private static final String urlWithoutCity = "http://api.goeuro.com/api/v2/position/suggest/en/";

   public static void main(String args[]) {
      boolean parsed = true;

      if (args.length == 0) {
         System.out.println("No City Found , Please append City Name after 'java -jar GoEuroTest.jar' ");
         System.exit(1);
      }
      String input = args[0];
      List<CityData> cityDataList = null;
      try {
         cityDataList = JSONParserFromURL.getDataList(urlWithoutCity + input);
         if (cityDataList.size()==0){
            System.out.println("No Data Found with City Name "+input);
            System.exit(1);
         }
      } catch (Exception e) {
         System.out.println("Unable to Parse URL !");
         parsed = false;
      }

      if (parsed) {
         String csvFile = input + ".csv";
         try {
            FileWriter writer = new FileWriter(csvFile);

            for (CityData c : cityDataList) {
               List<String> list = new ArrayList<String>();

               list.add(c.get_id());
               list.add(c.getName());
               list.add(c.getType());
               list.add(c.getGeo_position().getLatitude());
               list.add(c.getGeo_position().getLongitude());
               CSVUtils.writeLine(writer, list);

            }
            writer.flush();
            writer.close();

         } catch (IOException e) {
            System.out.println("Unable to Write on CSV File !");
            e.printStackTrace();
         }

      }

   }
}
