
package biometric_authentication_system;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class KeyEvent {
    
    private long initialTime;
    private long ReleasedTime;
    private long pressedTime;
    private long avginter_keytime;
    private HashMap <Integer,Long> pressedKeys;
    private  HashMap <Integer, ArrayList<Long>> avgFlightTime;
    private HashMap <Integer,Long> avgPressedTime;
    public Set<Integer> keys; 
    private ArrayList<Long> interkeytime;
    
    /**
     * Creates a new KeyEvent.
     */
    public KeyEvent(){
       pressedKeys = new HashMap();
       avgFlightTime = new HashMap();  
       avgPressedTime = new HashMap<>();
       interkeytime = new ArrayList<>();
    }
    
    public void getPressedKeys(java.awt.event.KeyEvent key){
      this.initialTime = System.currentTimeMillis();
      interkeytime.add(initialTime);
      int code =  key.getKeyCode();
        if(!(pressedKeys.containsKey(code))){
             pressedKeys.put(code, initialTime);
        }
      
    }
    
    public void getReleasedKeys(java.awt.event.KeyEvent key){
        this.ReleasedTime = System.currentTimeMillis();
        int code =  key.getKeyCode();
        long time = pressedKeys.get(code);
        this.pressedTime = (ReleasedTime - time);
        if(avgFlightTime.containsKey(code)){
          avgFlightTime.get(code).add(time);
        }else{
          ArrayList<Long> timearray = new ArrayList<>();
          timearray.add(pressedTime);
          avgFlightTime.put(code, timearray);
        }
        
        pressedKeys.remove(code);
        //System.out.println(avgFlightTime.get(code));
    }
    
    public boolean getAvgPressedTime(int id) throws ClassNotFoundException, SQLException{
       JOptionPane.showMessageDialog(null, "I am here 3");
       keys = avgFlightTime.keySet();
       for (Integer s : keys) {
          ArrayList<Long> timearray = avgFlightTime.get(s);
          long total=0;
          int len = timearray.size();
          for(long b : timearray) {
              total = total + b;
          }
          long avg = (total/len);
          System.out.println(avg);
          System.out.println(s);
          avgPressedTime.put(s,avg);
       }
      
       if(Thread.currentThread().getStackTrace()[2].getClassName()== "biometric_authentication_system.RegisterNew"){
          // JOptionPane.showMessageDialog(null,"i am inside it");
          JOptionPane.showMessageDialog(null, "I am here 4"); 
          storeAvgTime(id);
          getInterkeyTime(id,"Registrationfrm");
           return true;
       }else if (Thread.currentThread().getStackTrace()[2].getClassName()== "biometric_authentication_system.LoginNew"){
           long diff1 = getInterkeyTime(id,"LoginInterface");
           long diff =  checkPressTimes(id);
           return ((diff+ diff1)<=50);
       }else{
          return false;
       }
       /*for (Integer s : keys) {
          
           System.out.println("code" + s + "Array - " + avgPressedTime.get(s) );
          }*/
       
          
}
    
    public void storeAvgTime(int id) throws ClassNotFoundException, SQLException{
       keys = avgPressedTime.keySet();
       for (Integer s : keys) {
           long avg = avgPressedTime.get(s);
           Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
           Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric_authentication?" + "user=root&password=123"); 
           PreparedStatement pst = (PreparedStatement) conn.prepareStatement("insert into presstime_details (user_id,key_code,average_time) values (?,?,?)");
           String userid = String.valueOf(id);
           pst.setString(1,userid); 
           String keycode = String.valueOf(s);
           pst.setString(2, keycode);
           String avg_time = String.valueOf(avg);
           pst.setString(3,avg_time);
           pst.executeUpdate(); 
       }
    }
    
    public long checkPressTimes(int id) throws ClassNotFoundException, SQLException{
        keys = avgPressedTime.keySet();
        long totDiff = 0;
        for (Integer s : keys) {
           long avg = avgPressedTime.get(s);
           Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
           Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric_authentication?" + "user=root&password=123"); 
           PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select average_time from presstime_details where user_id=? and key_code=?");
           String userid = String.valueOf(id);
           pst.setString(1,userid); 
           String keycode = String.valueOf(s);
           pst.setString(2, keycode);
           ResultSet rsnew  = pst.executeQuery(); 
           if(rsnew.next()){
               long avgtime = rsnew.getLong("average_time");
               long difference = 0;
               if(avgtime > avg){
                   difference = (((avgtime - avg)/avgtime)*100);
               }else{
                   difference = (((avg - avgtime)/avgtime)*100);
               }
               totDiff = totDiff + difference;
              
           }
       }
        return totDiff;
    }
    
    public long getInterkeyTime(int id ,String classname) throws SQLException{
      long total = 0;
      int count = 0;
      for(long time : interkeytime){
           int ind = interkeytime.indexOf(time);
           if(ind != (interkeytime.size()-1)){
               long nxttime = interkeytime.get(ind+1);
               long interkeytime = (nxttime - ind);
               total = total + interkeytime;
               count = count + 1;
           }
           
      } 
        if (count!=0){
        this.avginter_keytime = (total/count);}
        
        try {
            Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KeyEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric_authentication?" + "user=root&password=123"); 
        JOptionPane.showMessageDialog(null,"i am in the gett inter key time before store");
        if(classname == "RegisterNew"){
           PreparedStatement pst = (PreparedStatement) conn.prepareStatement("insert into avg_interkeytime(user_id,avg_time) values (?,?)");
           String userid = String.valueOf(id);
           pst.setString(1,userid); 
           String intertime = String.valueOf(avginter_keytime);
           pst.setString(2, intertime);
           pst.executeUpdate();
           JOptionPane.showMessageDialog(null,"i am in the gett inter key time after store");
           return avginter_keytime;
       }else if (classname== "LoginNew"){
           PreparedStatement pst = (PreparedStatement) conn.prepareStatement("selecct avg_time from avg_interkeytime where user_id=?");
           String userid = String.valueOf(id);
           pst.setString(1,userid); 
           ResultSet rs = pst.executeQuery();
           long diff = 0;
           if(rs.next()){
             long time = rs.getLong("avg_time");
             
             if(time>avginter_keytime){
               diff = ((time-avginter_keytime)/time)*100;
             }else{
               diff = ((avginter_keytime -time)/time)*100;
             }
           }
           return diff;
           
           
       }else{
         return 0;
       }
      
    }
       
    } 
    
    

