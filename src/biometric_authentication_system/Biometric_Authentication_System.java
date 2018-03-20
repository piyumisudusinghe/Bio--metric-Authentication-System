
package biometric_authentication_system;

import java.util.ArrayList;


public class Biometric_Authentication_System {

    public static ArrayList<ArrayList<Long>> final_KD;
    public static ArrayList<ArrayList<Long>> final_DDKL;
    public static ArrayList<ArrayList<Long>> final_UDKL;
    public static ArrayList<ArrayList<Long>> final_UUKL;
    public static ArrayList<ArrayList<Long>> final_DUKL;
    
    static{
        final_KD = new ArrayList<>();
        final_DDKL = new ArrayList<>();
        final_UDKL= new ArrayList<>();
        final_UUKL  = new ArrayList<>();
        final_DUKL= new ArrayList<>();
    }
    public static  int  v = 0;
    public static void main(String[] args) {
        HomeNew home = new HomeNew();
        home.setVisible(true);
        
        
    }
    
}
