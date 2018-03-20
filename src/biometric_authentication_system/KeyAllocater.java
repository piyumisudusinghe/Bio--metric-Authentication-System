package biometric_authentication_system;


import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Piumi
 */
public class KeyAllocater {
    
    private long down_time;
    private long up_time;
    private ArrayList<Long> pressTime;
    private ArrayList<Long> releaseTime;
    private ArrayList<Long> kd_time;
    private ArrayList<Long> ddkl_time;
    private ArrayList<Long> udkl_time;
    private ArrayList<Long> uukl_time;
    private ArrayList<Long> dukl_time;
    
    public KeyAllocater(){
        pressTime= new ArrayList<>();
        releaseTime= new ArrayList<>();
        kd_time = new ArrayList<>();
        ddkl_time = new ArrayList<>();
        udkl_time = new ArrayList<>();
        uukl_time = new ArrayList<>();
        dukl_time = new ArrayList<>();
    }
    
    public void getPressTime(java.awt.event.KeyEvent evt){
        this.down_time = System.currentTimeMillis();
        int code = 10;
        if(code != evt.getKeyCode()){
            pressTime.add(down_time);
        }
        
        
      
    }
    
    public void getReleaseTime(java.awt.event.KeyEvent evt){
        this.up_time = System.currentTimeMillis();
        int code = 10;
        if(code != evt.getKeyCode()){
          releaseTime.add(up_time);
        }
        
        
      
    }
    
    public void calculate_KD(){
        int minnew = 0;
        int pSize = pressTime.size();
        int rSize = releaseTime.size();
        if(pSize>rSize){
            minnew=rSize;
        }else{
           minnew=pSize;
        }
        for(int i=0; i<minnew;i++ ){
           
           if(releaseTime.get(i) != null &&  pressTime.get(i) != null){
               long kd = releaseTime.get(i) -  pressTime.get(i);
               kd_time.add(kd);
           }
           
        }
        Biometric_Authentication_System.final_KD.add(kd_time);
        
        
    
    }
    
    public void calculate_DDKL(){
       
        for(long time : pressTime){
           
           int ind = pressTime.indexOf(time);
           if(ind != (pressTime.size()-1)){
               long ddkl = (pressTime.get(ind+1)-time);
               ddkl_time.add(ddkl);
              
               
           }
          
        }
        Biometric_Authentication_System.final_DDKL.add(ddkl_time);
        
        
    
    }
    
    public void calculate_UDKL(){
        for(int i=0; i<releaseTime.size();i++ ){
            if(i!= (releaseTime.size()-1)){
              long udkl = pressTime.get(i+1)-releaseTime.get(i);
              udkl_time.add(udkl);
            }
           
        }
        Biometric_Authentication_System.final_UDKL.add(udkl_time);
        
    
    }
    public void calculate_UUKL(){
        for(long time : releaseTime){
           int ind = releaseTime.indexOf(time);
           if(ind != (releaseTime.size()-1)){
               long uukl = (releaseTime.get(ind+1)- time);
               uukl_time.add(uukl);
               
           }
        }
        Biometric_Authentication_System.final_UUKL.add(uukl_time);
    
    }
    public void calculate_DUKL(){
        for(int i=1; i<releaseTime.size();i++ ){
           long dukl = releaseTime.get(i) -pressTime.get(i-1);
              dukl_time.add(dukl);
            
           
        }
        Biometric_Authentication_System.final_DUKL.add(dukl_time);
    
    }
    
}