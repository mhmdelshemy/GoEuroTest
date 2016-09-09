package parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import pojo.CityData;
import pojo.GeoPosition;

/**
 * Created by elshemma on 9/8/2016.
 */
public class JSONParserFromURL {

   public static List<CityData> getDataList(String url) throws Exception {

      HttpClient httpClient = HttpClientBuilder.create().build();
      HttpGet httpGet = new HttpGet(url);
      httpGet.addHeader("accept", "application/json");
      HttpResponse httpResponse = httpClient.execute(httpGet);
      String data = readData(httpResponse);
      Gson gson = new Gson();
      Type type=new TypeToken<List<CityData>>(){}.getType();
      List<CityData> cityDataList=gson.fromJson(data,type);
      return cityDataList;
   }


   public static String readData(HttpResponse httpResponse) throws Exception {
      BufferedReader bufferedReader = null;
      try {
         bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
         StringBuffer stringBuffer = new StringBuffer();
         char[] dataLength = new char[1024];
         int read;
         while ((read = bufferedReader.read(dataLength)) != -1) {

            stringBuffer.append(dataLength, 0, read);
         }
         return stringBuffer.toString();
      } catch (Exception ex) {
         throw new Exception("Unable to Read Data "+ex.getMessage(),ex);
      } finally {
         if (bufferedReader != null) {
            bufferedReader.close();
         }
      }

   }

}
