package pojo;

import java.util.List;

/**
 * Created by elshemma on 9/8/2016.
 */
public class CityData {

   private GeoPosition geo_position;

   private String _id;

   private String name;

   private String type;

   private String key;

   public GeoPosition getGeo_position() {
      return geo_position;
   }

   public void setGeo_position(GeoPosition geo_position) {
      this.geo_position = geo_position;
   }

   public String get_id() {
      return _id;
   }

   public void set_id(String _id) {
      this._id = _id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }
}
