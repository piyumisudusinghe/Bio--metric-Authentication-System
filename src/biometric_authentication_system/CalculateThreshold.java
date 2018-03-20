/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometric_authentication_system;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Piumi
 */
public class CalculateThreshold {
    
    public double kdThreshold;
    public double ddklThreshold;
    public double udklThreshold;
     public double uuklThreshold;
      public double duklThreshold;
     public CalculateThreshold(){
         
     } 
    
    public void calculateKDThreshold(){
       int min = Biometric_Authentication_System.final_KD.get(0).size();
       ArrayList<Double> eculidean_dist = new ArrayList <>();
       for (ArrayList<Long> arr : Biometric_Authentication_System.final_KD){
           if(min>arr.size()){
             min =arr.size();
                    
            }
       }
       int size = Biometric_Authentication_System.final_KD.size();
       
       for (ArrayList<Long> list1 : Biometric_Authentication_System.final_KD){
            int ind_test = Biometric_Authentication_System.final_KD.indexOf(list1);
            ArrayList<Long> avg_sample = new ArrayList<>();
            for(int m=0;m<min;m++){
                  long total =0;
                  for(ArrayList<Long> list2 : Biometric_Authentication_System.final_KD){
                      int ind_sample = Biometric_Authentication_System.final_KD.indexOf(list2);
                      if(ind_sample != ind_test){
                         total = total + list2.get(m);
                      }
                       
                  }
                  avg_sample.add(total/size);
            
            }
            long distance = 0;
            double eculidean_distance=0;
            for(int m=0;m<min;m++){
                distance = distance+ ((list1.get(m) - avg_sample.get(m))*(list1.get(m) - avg_sample.get(m)));
               
            }
            
            eculidean_distance = (Math.sqrt(distance));
            eculidean_dist.add(eculidean_distance);
            
            
        }
       double tot =0;
       for(double num : eculidean_dist){
            tot = tot + num;
       }
       if(eculidean_dist.size()!=0){
          this.kdThreshold = tot/eculidean_dist.size();
       }
       
      
       
       
    }
    
     public void calculateDDKLThreshold(){
       int min = Biometric_Authentication_System.final_DDKL.get(0).size();
       ArrayList<Double> eculidean_dist = new ArrayList <>();
       for (ArrayList<Long> arr : Biometric_Authentication_System.final_DDKL){
           if(min>arr.size()){
             min =arr.size();
                    
            }
       }
     
       int size = Biometric_Authentication_System.final_DDKL.size();
       
       for (ArrayList<Long> list1 : Biometric_Authentication_System.final_DDKL){
          
            int ind_test = Biometric_Authentication_System.final_DDKL.indexOf(list1);
            ArrayList<Long> avg_sample = new ArrayList<>();
            for(int m=0;m<min;m++){
                  long total =0;
                  for(ArrayList<Long> list2 : Biometric_Authentication_System.final_DDKL){
                     
                      int ind_sample = Biometric_Authentication_System.final_DDKL.indexOf(list2);
                      if(ind_sample != ind_test){
                         total = total + list2.get(m);
                         
                      }
                       
                  }
                  avg_sample.add(total/size);
            
            }
            long distance = 0;
            double eculidean_distance=0;
            for(int m=0;m<min;m++){
                distance = distance+ ((list1.get(m) - avg_sample.get(m))*(list1.get(m) - avg_sample.get(m)));
                System.out.println("disatnce" + String.valueOf(distance));
            }
            eculidean_distance = (Math.sqrt(distance));
            eculidean_dist.add(eculidean_distance);
            System.out.println("eculididean distance" + String.valueOf(eculidean_distance));
            
            
        }
       double tot =0;
       for(double num : eculidean_dist){
           System.out.println("num" + String.valueOf(num));
            tot = tot + num;
            System.out.println("total" + String.valueOf(tot));
            System.out.println(tot);
       }
       if(eculidean_dist.size()!=0){
           this.ddklThreshold = tot/eculidean_dist.size();
       }
             
     
       
       
    }



 public void calculateUDKLThreshold(){
       int min = Biometric_Authentication_System.final_UDKL.get(0).size();
       ArrayList<Double> eculidean_dist = new ArrayList <>();
       for (ArrayList<Long> arr : Biometric_Authentication_System.final_UDKL){
           if(min>arr.size()){
             min =arr.size();
                    
            }
       }
       int size = Biometric_Authentication_System.final_UDKL.size();
       
       for (ArrayList<Long> list1 : Biometric_Authentication_System.final_UDKL){
            int ind_test = Biometric_Authentication_System.final_UDKL.indexOf(list1);
            ArrayList<Long> avg_sample = new ArrayList<>();
            for(int m=0;m<min;m++){
                  long total =0;
                  for(ArrayList<Long> list2 : Biometric_Authentication_System.final_UDKL){
                      int ind_sample = Biometric_Authentication_System.final_UDKL.indexOf(list2);
                      if(ind_sample != ind_test){
                         total = total + list2.get(m);
                      }
                       
                  }
                  avg_sample.add(total/size);
            
            }
            long distance = 0;
            double eculidean_distance=0;
            for(int m=0;m<min;m++){
                distance = distance+ ((list1.get(m) - avg_sample.get(m))*(list1.get(m) - avg_sample.get(m)));
               
            }
            eculidean_distance = (Math.sqrt(distance));
            eculidean_dist.add(eculidean_distance);
            
            
        }
       double tot =0;
       for(double num : eculidean_dist){
            tot = tot + num;
       }
       if(eculidean_dist.size()!=0){
         this.udklThreshold = tot/eculidean_dist.size();
       }
       
      
       
       
    }
    
     public void calculateUUKLThreshold(){
       int min = Biometric_Authentication_System.final_UUKL.get(0).size();
       ArrayList<Double> eculidean_dist = new ArrayList <>();
       for (ArrayList<Long> arr : Biometric_Authentication_System.final_UUKL){
           if(min>arr.size()){
             min =arr.size();
                    
            }
       }
       int size = Biometric_Authentication_System.final_UUKL.size();
       
       for (ArrayList<Long> list1 : Biometric_Authentication_System.final_UUKL){
            int ind_test = Biometric_Authentication_System.final_UUKL.indexOf(list1);
            ArrayList<Long> avg_sample = new ArrayList<>();
            for(int m=0;m<min;m++){
                  long total =0;
                  for(ArrayList<Long> list2 : Biometric_Authentication_System.final_UUKL){
                      int ind_sample = Biometric_Authentication_System.final_UUKL.indexOf(list2);
                      if(ind_sample != ind_test){
                         total = total + list2.get(m);
                      }
                       
                  }
                  avg_sample.add(total/size);
            
            }
            long distance = 0;
            double eculidean_distance=0;
            for(int m=0;m<min;m++){
                distance = distance+ ((list1.get(m) - avg_sample.get(m))*(list1.get(m) - avg_sample.get(m)));
               
            }
            eculidean_distance = (Math.sqrt(distance));
            eculidean_dist.add(eculidean_distance);
            
            
        }
       double tot =0;
       for(double num : eculidean_dist){
            tot = tot + num;
       }
       if(eculidean_dist.size()!=0){
          this.uuklThreshold = tot/eculidean_dist.size();
       }
      
       System.out.println("uuklthreshold" + String.valueOf(uuklThreshold));
       
       
}
 
    
     public void calculateDUKLThreshold(){
       int min = Biometric_Authentication_System.final_DUKL.get(0).size();
       ArrayList<Double> eculidean_dist = new ArrayList <>();
       for (ArrayList<Long> arr : Biometric_Authentication_System.final_DUKL){
           if(min>arr.size()){
             min =arr.size();
                    
            }
       }
       int size = Biometric_Authentication_System.final_DUKL.size();
       
       for (ArrayList<Long> list1 : Biometric_Authentication_System.final_DUKL){
            int ind_test = Biometric_Authentication_System.final_DUKL.indexOf(list1);
            ArrayList<Long> avg_sample = new ArrayList<>();
            for(int m=0;m<min;m++){
                  long total =0;
                  for(ArrayList<Long> list2 : Biometric_Authentication_System.final_DUKL){
                      int ind_sample = Biometric_Authentication_System.final_DUKL.indexOf(list2);
                      if(ind_sample != ind_test){
                         total = total + list2.get(m);
                      }
                       
                  }
                  avg_sample.add(total/size);
            
            }
            long distance = 0;
            double eculidean_distance=0;
            for(int m=0;m<min;m++){
                distance = distance+ ((list1.get(m) - avg_sample.get(m))*(list1.get(m) - avg_sample.get(m)));
               
            }
            eculidean_distance = (Math.sqrt(distance));
            eculidean_dist.add(eculidean_distance);
            
            
        }
       double tot =0;
       for(double num : eculidean_dist){
            tot = tot + num;
       }
       
       if(eculidean_dist.size()!=0){
            this.duklThreshold = tot/eculidean_dist.size();      
       }
       
      
       
       
    }
     
     public void saveThreshold(int id) throws ClassNotFoundException, SQLException{
         
           Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
           Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric_authentication?" + "user=root&password=123"); 
           PreparedStatement pst = (PreparedStatement) conn.prepareStatement("insert into eculidean_threshold(user_id,kd_threshold,ddkl_threshold,uukl_threshold,dukl_threshold,udkl_threshold) values(?,?,?,?,?,?)");
           String userid = String.valueOf(id);
           pst.setString(1,userid); 
           pst.setString(2, String.valueOf(kdThreshold));
           pst.setString(3, String.valueOf(ddklThreshold));
           pst.setString(4, String.valueOf(uuklThreshold));
           pst.setString(5, String.valueOf(duklThreshold));
           pst.setString(6, String.valueOf(udklThreshold));
           pst.executeUpdate(); 
           Biometric_Authentication_System.final_DDKL.clear();
           Biometric_Authentication_System.final_DUKL.clear();
           Biometric_Authentication_System.final_KD.clear();
           Biometric_Authentication_System.final_UDKL.clear();
           Biometric_Authentication_System.final_UUKL.clear();
     
     }
     
     public double compareThreshold(int id) throws ClassNotFoundException, SQLException{
           Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
           Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric_authentication?" + "user=root&password=123"); 
           PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select * from eculidean_threshold where user_id=?");
           String userid = String.valueOf(id);
           pst.setString(1,userid); 
           ResultSet rs = pst.executeQuery();
           double errorrate = 0;
           if(rs.next()){
               Double threshold_kd = rs.getDouble("kd_threshold");
               Double threshold_ddkl = rs.getDouble("ddkl_threshold");
               Double threshold_uukl = rs.getDouble("uukl_threshold");
               Double threshold_dukl = rs.getDouble("dukl_threshold");
               Double threshold_udkl = rs.getDouble("udkl_threshold");
               
               if(kdThreshold>threshold_kd){
                   errorrate = errorrate + (((kdThreshold - threshold_kd)/threshold_kd));
                   
               }else{
                   errorrate = errorrate + (((threshold_kd-kdThreshold)/threshold_kd));
                   
               }
               if(ddklThreshold>threshold_ddkl){
                   errorrate = errorrate + (((ddklThreshold - threshold_ddkl)/threshold_ddkl));
                  
               }else{
                   errorrate = errorrate + (((threshold_ddkl-ddklThreshold)/threshold_ddkl));
                  
               }
               if(duklThreshold>threshold_dukl){
                   errorrate = errorrate + (((duklThreshold - threshold_dukl)/threshold_dukl));
                  
               }else{
                   errorrate = errorrate + (((threshold_dukl-duklThreshold)/threshold_dukl));
                   
               }
               if(udklThreshold>threshold_udkl){
                   errorrate = errorrate + (((udklThreshold - threshold_udkl)/threshold_udkl));
                  
               }else{
                   errorrate = errorrate + (((threshold_udkl-udklThreshold)/threshold_udkl));
                  
               }
               if(uuklThreshold>threshold_uukl){
                   errorrate = errorrate + (((uuklThreshold - threshold_uukl)/threshold_uukl));
                  
               }else{
                   errorrate = errorrate + (((threshold_uukl-uuklThreshold)/threshold_uukl));
                   
               }

               
             
           }
           Biometric_Authentication_System.final_DDKL.clear();
           Biometric_Authentication_System.final_DUKL.clear();
           Biometric_Authentication_System.final_KD.clear();
           Biometric_Authentication_System.final_UDKL.clear();
           Biometric_Authentication_System.final_UUKL.clear();
           System.out.println("errorrate--------"+String.valueOf(errorrate));
           return errorrate;
     }
}

