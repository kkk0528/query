/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pufc;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author simon
 */
public class TestPaillier {
    
    public static void main(String[] args) {
     int a= 10;
      int b=5;
     int K=2;
       Ciphertext [] Di = null;      
       PaillierT       paillier = new PaillierT(1024, 64);              
       BigInteger B = new BigInteger(Integer.toString(  1));
        BigInteger B3 = new BigInteger(Integer.toString(  8));
       int a2 = 5;
       int a3 = 4;
        
       
       BigInteger []  ccc =  new    BigInteger  [paillier.beta];
       
          BigInteger      ab = new BigInteger(Integer.toString(16));
          BigInteger      bb = new BigInteger(Integer.toString(10));
                EGCD       paillier2 = new EGCD(); 
                BigInteger gcd[] = paillier2.ExtEuclidean(ab, bb);
                
                System.out.println(ab + "*" + gcd[0] + " + " + bb + "*" + gcd[1] + " = " + gcd[2]);
       System.out.println("2======================="+BigInteger.valueOf(2));
       BigInteger.valueOf(2);
       Ciphertext E1 = paillier.Encryption(B);
       CipherPub E10 = paillier.Encryption(B, paillier.H[1]);
       Ciphertext1 C10 = paillier.AddPDec1(E10);
       BigInteger C11 = paillier.AddPDec2(C10);
       for(int p =0 ; p <paillier.beta-1; p++)
       {
       ccc[p] = paillier.WPDecryption1(E10,paillier.X[p]);
       }
      
       CipherPub E20  = paillier.Encryption(B3,paillier.H[2]);
       
       
       System.out.println("NEW ALGO ==== " + C11);
       System.out.println("E20 ==== " + E20);
       System.out.println("E20=="+   paillier.SDecryption(E20));
       
        System.out.println("======SSBA/SLT=========");
        long TT1=0, TT2=0;
int CCC =0;
long CCCG1 = 0;
long T111 = System.currentTimeMillis(); 
      for(int iii=0; iii< 1; iii++){
 BigInteger C101 = paillier.WPDecryption2(E10,ccc,2,paillier.X[paillier.beta-1]);
      
      }
long T112 = System.currentTimeMillis(); 
System.out.println("======TT1========="+(T112-T111));
TT1=0;

       for(int iii=0; iii< 1; iii++){

            System.out.println("i==" + (iii)); 
   //  SSBA SK11 = new SSBA(E10 ,paillier);
          SLT SK11 = new SLT (E10, E20, paillier);
       long T1 = System.currentTimeMillis(); 
        SK11.StepOne();
         
      long T2 = System.currentTimeMillis(); 
         
        SK11.StepTwo ();
      
       long T3 = System.currentTimeMillis(); 
         
        SK11.StepThree();
      long T4 = System.currentTimeMillis(); 
      TT1 = TT1 + T4-T1 -(T3-T2)-SK11.TB ;
      TT2 = TT2 + T3-T2 +SK11.TB ;
      
        CCCG1 = CCCG1 + (long)SK11.CCC;
       }
        
         System.out.println("======TT1========="+TT1);
         System.out.println("======TT2========="+TT2);
         System.out.println("======CCCG1========="+CCCG1);
        
         
                   TT1=0; TT2=0;
  CCC =0;
  CCCG1 = 0;
       for(int iii=0; iii< 1; iii++){

            System.out.println("iEQ==" + (iii)); 
        // SSBA SK12 = new SSBA(E10 ,paillier);
     //     SEQ SK12 = new SEQ (E10, E20,   paillier);
          Sdiv SK12 = new Sdiv (E10,E20,paillier.Hsigma,10,paillier);
          //    SGCD SK12 = new SGCD (E10,E20,paillier.Hsigma,20,paillier);
       long T5 = System.currentTimeMillis(); 
        SK12.StepOne();
      long T6 = System.currentTimeMillis(); 
        
      TT1 = TT1 + T6-T5 - SK12.TB;
      TT2 = TT2 +  SK12.TB;
        CCCG1 = CCCG1 + (long)SK12.CCC;
       }
        
         System.out.println("======TT1========="+TT1);
         System.out.println("======TT2========="+TT2);
         System.out.println("======CCCG1========="+CCCG1);
         
         
        
   //    System.out.println("FIN=="+   paillier.SDecryption(SK11.FIN));
  //     System.out.println("FINS=="+   paillier.SDecryption(SK11.FINS));
   //    System.out.println("U=="+   paillier.SDecryption(SK11.U));
      // System.out.println("U=="+   paillier.SDecryption(SK11.a));
      // System.out.println("s=="+   paillier.SDecryption(SK11.s));
        System.out.println("======SSBA=========");
       
       
           System.out.println("======SLT=========");
       
                  SLT SK100 = new SLT(E10,E20 ,paillier);
   //      long T1 = System.currentTimeMillis(); 
        SK100.StepOne();
     //    long T2 = System.currentTimeMillis(); 
        SK100.StepTwo ();
      
       // long T3 = System.currentTimeMillis(); 
        SK100.StepThree();
       // long T4 = System.currentTimeMillis(); 
       System.out.println("FIN=="+   paillier.SDecryption(SK100.FIN));
       System.out.println("E10=="+   paillier.SDecryption(E10));
       System.out.println("E20=="+   paillier.SDecryption(E20));
//        System.out.println("UX=="+   paillier.SDecryption(SK100.UX));
      // System.out.println("s=="+   paillier.SDecryption(SK11.s));
        System.out.println("======SLT=========");   
       
       
      
   
        
        
                 System.out.println("======SMMS=========");
       
                  SMMS SK101 = new SMMS(E10,E20 ,paillier);
   //      long T1 = System.currentTimeMillis(); 
        SK101.StepOne();
     //    long T2 = System.currentTimeMillis(); 
        
       // long T4 = System.currentTimeMillis(); 
       System.out.println("FINA=="+   paillier.SDecryption(SK101.FINA));
       System.out.println("FINI=="+   paillier.SDecryption(SK101.FINI));
       System.out.println("E10=="+   paillier.SDecryption(E10));
       System.out.println("E20=="+   paillier.SDecryption(E20));
      // System.out.println("s=="+   paillier.SDecryption(SK11.s));
        System.out.println("======SMMS=========");   
        System.out.println("======Sdiv=========");   
        Sdiv SK103 = new Sdiv(E10,E20,10 ,paillier);
        SK103.StepOne();
        
         System.out.println("E10=="+   paillier.SDecryption(E10));
       System.out.println("E20=="+   paillier.SDecryption(E20));
              System.out.println("FQ=="+   paillier.SDecryption(SK103.FQ));
       System.out.println("FR=="+   paillier.SDecryption(SK103.FR));
                   System.out.println("Q=="+   paillier.SDecryption(SK103.Q));
       System.out.println("R=="+   paillier.SDecryption(SK103.R));   
        System.out.println("======Sdiv=========");   
        
        
        
        
       
       BigInteger C1 =  paillier.WDecryption(E1);
       BigInteger C2 =  paillier.SDecryption(E1);
       
       BigInteger[] TKK =  paillier.PSDecryption1(E1);
        BigInteger []    PPQ =  new    BigInteger [3];
       BigInteger FII = paillier.DDecryption1(TKK);
       
       
       Ciphertext1 ECC = paillier.WPDecryption1(E1);
       BigInteger CCK = paillier.WPDecryption2(ECC);
       
        Ciphertext E222  = paillier.Encryption(B3);
       
            RSM SK16 = new RSM(E10,E20,paillier);
         long T1 = System.currentTimeMillis(); 
        SK16.StepOne();
         long T2 = System.currentTimeMillis(); 
        SK16.StepTwo1();
        SK16.StepTwo2();
        long T3 = System.currentTimeMillis(); 
        SK16.StepThree();
        long T4 = System.currentTimeMillis(); 
        
        OddTest SK111 = new OddTest();
        System.out.println("ODD B3=="+ SK111.odd(B3));   
      
        System.out.println("ODD B=="+ SK111.odd(B));   
        
        
        ELSB SK12 = new ELSB(E10 ,paillier);
        
        SK12.StepOne();
         SK12.StepTwo ();
     
         SK12.StepThree();
         System.out.println("E20=="+   paillier.SDecryption(E20));
        System.out.println("SKELSB B=="+ paillier.SDecryption(SK12.FIN)); 
        
        SBD SK15 = new SBD (E20,10, paillier);
        CipherPub [] EBD = new CipherPub [10];
        EBD = SK15.StepOne();
        
        for (int jjj=0; jjj< 10; jjj++)
              {
              System.out.println("EBD=="+ paillier.SDecryption(EBD[jjj])); 
              }
        
        
                  
             CipherPub  AKKK = new CipherPub();
          
          
             BigInteger TWO = BigInteger.valueOf(2); 
            BigInteger  ZERO = BigInteger.valueOf(0);
           CipherPub  EZERO =  paillier.Encryption(ZERO, paillier.Hsigma);
               AKKK.T1 = EZERO.T1.multiply(EZERO.T1);
               AKKK.T2 = EZERO.T2.multiply(EZERO.T2);        
                AKKK.PUB = paillier.Hsigma;    
         for(int i = 9; i >  0; i--)
          
         {    
             
                AKKK.T1 = AKKK.T1.multiply(EBD[i].T1);
                AKKK.T2 = AKKK.T2.multiply(EBD[i].T1);
             
                AKKK.T1 =   AKKK.T1.modPow(TWO, paillier.nsquare);
                AKKK.T2 =   AKKK.T2.modPow(TWO, paillier.nsquare);
                

             System.out.println("AKKK=="+   paillier.SDecryption(AKKK));
 
           
         }    
               AKKK.T1 = AKKK.T1.multiply(EBD[0].T1);
               AKKK.T2 = AKKK.T2.multiply(EBD[0].T2); 
               AKKK.PUB = paillier.Hsigma;
          
          System.out.println("SBD Before=="+   paillier.SDecryption(E20));
          System.out.println("SBD recover=="+   paillier.SDecryption(AKKK));
        
        
        
        
        
        
        System.out.println("E20=="+   paillier.SDecryption(SK15.T1));
        SAD SK17 = new SAD(E10,E20,paillier);
         SK17.StepOne();
         SK17.StepTwo ();
     
         SK17.StepThree();
           System.out.println("E20=="+   paillier.SDecryption(E20));
      
       
          System.out.println("3 com 15=="+ B.compareTo(B3));
          System.out.println("15 com 3=="+ B3.compareTo(B));
        
        System.out.println("E20=="+   paillier.SDecryption(E20));
        SGT SK18 = new SGT (E20,E10,paillier.Hsigma,paillier);
          
          SK18.StepOne();
          SK18.StepTwo();
          SK18.StepThree();
           System.out.println("E20=="+   paillier.SDecryption(E20));
            System.out.println("E10=="+   paillier.SDecryption(E10));
          System.out.println("SGT!!!=="+   paillier.SDecryption(SK18.FIN));
          System.out.println("BITLENGTH=="+   paillier.n.bitLength()/2);
  
          SMAX SK30 = new SMAX (E10,E20,paillier.Hsigma,paillier);
          
          SK30.StepOne();
          SK30.StepTwo();
          SK30.StepThree();
          System.out.println("MAX=="+   paillier.SDecryption(SK30.FINMX));
          System.out.println("MIN=="+   paillier.SDecryption(SK30.FINMI));
         // System.out.println("BITLENGTH=="+   paillier.n.bitLength()/2);  
          
          
          
          
          
        SEQ SK19 = new SEQ (E10,E20,paillier.Hsigma,paillier);
          
          SK19.StepOne();
          
          System.out.println("SEQ u1=="+   paillier.SDecryption(SK19.u1));
          System.out.println("SEQ u2=="+   paillier.SDecryption(SK19.u2));
          System.out.println("SEQ=="+   paillier.SDecryption(SK19.FIN));
          
    //       Sdiv SK20 = new Sdiv (E10,E20,paillier.Hsigma,10,paillier);
  //         SK20.StepOne();
           
           
           
           
           
 //          System.out.println("Sdiv E10 orignial=="+   paillier.SDecryption(E10));
           System.out.println("Sdiv E20 orignial=="+   paillier.SDecryption(E20));
  //         System.out.println("Sdiv Q orignial=="+   paillier.SDecryption(SK20.Q));
   //       System.out.println("Sdiv remainder orignial=="+   paillier.SDecryption(SK20.R));
           System.out.println("Sdiv E20 orignial=="+   paillier.SDecryption(E20));
    //       System.out.println("Sdiv E10=="+   paillier.SDecryption(SK20.a12));
    //      System.out.println("Sdiv E20=="+   paillier.SDecryption(SK20.b12));
          
        System.out.println("================SGCD============");  
          
          
           SGCD SK210 = new SGCD (E10,E20,paillier.Hsigma,10,paillier);
           SK210.StepOne();

          System.out.println("SGCD=="+   paillier.SDecryption(SK210.FIN));
          
          
          
          
          
          
          
          
          
          
        System.out.println("RSM time A=="+ ((T2-T1)+(T4-T3))); 
        System.out.println("RSM time B=="+ (T3-T2));
         System.out.println("RSM REAULT=="+ SK16.FIN2);
        System.out.println("SAD REAULT=="+ SK17.FIN2);
         System.out.println("SECB Com=="+  SK16.CCC);
             
      
     //  System.out.println("SEC =="+SK14.Fin2); 
       System.out.println("======================================="); 
       System.out.println("C1=="+B); 
       System.out.println("C1=="+C1); 
       System.out.println("C2=="+C2); 
        System.out.println("CCk=="+CCK);
        System.out.println("TKK=="+TKK[0]);
        System.out.println("FII=="+FII);
       System.out.println("n=="+paillier.n); 
       System.out.println("=======================================");   
       
        BigInteger Bi = new BigInteger(Integer.toString(a2));
        BigInteger B2 = new BigInteger(Integer.toString(a3));
        Ciphertext E2 = paillier.Encryption(Bi);
       //      BigInteger Bi = new BigInteger(Integer.toString(15));
        BigInteger Ai = new BigInteger(Integer.toString(60));
        BigInteger Ci = new BigInteger(Integer.toString(70));
        BigInteger Pi = new BigInteger(Integer.toString(1));
          long i1=0,i2=0,i3=0,i4=0,i5=0,i6=0;
          int CCC1=0,CCC2 = 0,CCC3 =0;
          int KKKK =1;
  //     SECB SK = new SECB(E1,Bi,paillier);
   //    SK.StepOne();
   //    SK.StepTwo();
  //     SK.StepThree();
  //     BigInteger C3 =  paillier.SDecryption(SK.F);
     //  System.out.println("SECB=="+C3); 
     //  System.out.println("SECBCC1=="+SK.CCC); 
    //   System.out.println("SECBCC2=="+SK.CCC1);
    
          long Tk1 = System.currentTimeMillis(); 
          
          int cck =  3;
          for(int ii=0;ii<KKKK;ii++) 
      {  System.out.println("ii=="+(ii));
          
          
          Ciphertext E22 = paillier.Encryption(Bi);
          
      
       
  //      System.out.println("PSM=="+SK11.FIN2); 
     // System.out.println("PSM=="+SK11.CCC); 
       
        
               
          
     //  RSM SK16 = new RSM(E1,E2,paillier);
      //  long T1 = System.currentTimeMillis(); 
     //  SK16.StepOne();
     //   long T2 = System.currentTimeMillis(); 
     //  SK16.StepTwo();
      // long T3 = System.currentTimeMillis(); 
      // SK16.StepThree();
      //  long T4 = System.currentTimeMillis(); 
       // i1 =i1 +(T4-T3)+(T2-T1);
       // i2 =i2 +(T3-T2);
    // CCC1 = CCC1+SK16.CCC;
 //        System.out.println("RSM=="+SK16.FIN2); 
        
     //  SEC SK14 = new SEC(E1,E2,paillier);
      //  long T5 = System.currentTimeMillis(); 
      // SK14.StepOne();
      //  long T6 = System.currentTimeMillis(); 
     //  SK14.StepTwoA();
      // SK14.StepTwoB();
     //  long T7 = System.currentTimeMillis(); 
     //  SK14.StepThree();
     //  long T8 = System.currentTimeMillis(); 
       
     //  CCC2 =CCC2 +SK14.CCC;
     //  i3 = i3 + (T6-T5)+ SK14.T11;
     //  i4 = i4 +  (T7-T6)+SK14.T12;
  //     System.out.println("FINNNN=="+SK14.Fin2); 
   //    System.out.println("FSECCCC=="+SK14.CCC);
       
       
       
    //   SECE SK1 = new SECE(E1,Bi,paillier);
     //  long T9 = System.currentTimeMillis();
     //  SK1.StepOne();
     //  long T10 = System.currentTimeMillis();
     //  SK1.StepTwo();
    //   long T11 = System.currentTimeMillis();
    //   SK1.StepThree();
    //   long T12 = System.currentTimeMillis();
    //   i5 = i5 + (T12-T9)-(T11-T10);
    //   i6 = i6 +  (T11-T10);
     //  CCC3 = CCC3 +SK1.CCC;
  //     BigInteger C4 =  paillier.SDecryption(SK1.F);
    //   System.out.println("SECE=="+SK1.CCC); 
      }   
      
       long Tk2 = System.currentTimeMillis(); 
       
             
       for(int ii=0;ii<KKKK;ii++) 
      {// System.out.println("ii=="+(ii));
          
          
         BigInteger  E22 = paillier.SDecryption(E2);
          
  
      }  
     long Tk3 = System.currentTimeMillis(); 
       
 
    long Tk4 = System.currentTimeMillis();  
    
                for(int ii=0;ii<KKKK;ii++) 
      {// System.out.println("ii=="+(ii));
          
          BigInteger  E22 = paillier.WDecryption(E2); 
       //  PPQ  =  paillier.PSDecryption1(E1);
          
  
      }  
    long Tk5 = System.currentTimeMillis();  
    
                    for(int ii=0;ii<KKKK;ii++) 
      {// System.out.println("ii=="+(ii));
          
         PPQ  =  paillier.PSDecryption1(E1);
          
  
      }  
    long Tk6 = System.currentTimeMillis();  
    
    
    
     for(int ii=0;ii<KKKK;ii++) 
      {// System.out.println("ii=="+(ii));
          
          BigInteger ECCC = paillier.DDecryption1(PPQ);
          
  
      }  
    long Tk7 = System.currentTimeMillis();
    
    
     System.out.println("ENC=="+(Tk2-Tk1)); 
     System.out.println("SDEC=="+(Tk3-Tk2)); 
     System.out.println("WDEC=="+(Tk5-Tk4));
     System.out.println("PSDec=="+(Tk6-Tk5));
       System.out.println("DDEC=="+(Tk7-Tk6)); 
    //   System.out.println("SM A=="+(i1/KKKK)); 
   //   System.out.println("SM B=="+(i2/KKKK)); 
   //   System.out.println("COmmunication SM B=="+(CCC1/KKKK)); 
   //   System.out.println("SEC A=="+(i3/KKKK)); 
    //  System.out.println("SEC B=="+(i4/KKKK)); 
    //  System.out.println("COmmunication SEC B=="+(CCC2/KKKK));
    //  System.out.println("SECE A=="+(i5/KKKK)); 
     // System.out.println("SECE B=="+(i6/KKKK)); 
    //  System.out.println("COmmunication SEC B=="+(CCC3/KKKK));
      
//       PMAX SK2 = new PMAX(E1,E2,paillier);
 //     SK2.StepOne();
  //     SK2.StepTwo();
  ////     SK2.StepThree();
  //     BigInteger C5 =  paillier.SDecryption(SK2.FIN);
  //     System.out.println("SECE=="+C5); 
  //     System.out.println("SECE=="+SK2.eee); 
       
 //      Ciphertext  E11=paillier.Encryption(Ai);
  //     Ciphertext  E12=paillier.Encryption(Bi);
 ////      Ciphertext  E13=paillier.Encryption(Ci);
 //      Ciphertext  E14=paillier.Encryption(Pi); 
  //     Di= new Ciphertext [4];
        
   //     Di[0]= E11;
   //     Di[1]= E12;
  //      Di[2]= E13;
  //      Di[3]= E14; 
        
 //       System.out.println("==========TOPK===========");
 //       TOPK  ppkk = new TOPK(Di,K,paillier);  
 //       ppkk.find_k(0, 3, 2);
         //ppkk.StepOne();
 //        for (int i = 0; i < 4; ++i) {
 //           System.out.println(paillier.SDecryption(ppkk.VC[i]));
            // System.out.println(ppkk.VC[i]);
  //          System.out.println(ppkk.vector[i]);
  //  }
    
    
       
}
}