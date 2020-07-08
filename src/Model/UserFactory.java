package Model;

import java.io.IOException;

public class UserFactory {
	
   public User getUser(String userType) throws IOException{
      if(userType == null){
         return null;
      }		
      if(userType == "Seller"){
         return new Seller();
         
      } else if( userType == "Manager") {
         return new Manager();         
      }
      return null;
   }
}