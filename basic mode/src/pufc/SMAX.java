/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pufc;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author xmliu
 */
public class SMAX {
   
    
        public CipherPub  a = new CipherPub();
       public CipherPub  b = new CipherPub();
       public CipherPub  a11 = new CipherPub();
       public CipherPub  b11 = new CipherPub();
       public CipherPub  a12 = new CipherPub();
       public CipherPub  b12 = new CipherPub();
       public CipherPub  a14 = new CipherPub();
       public CipherPub  b14 = new CipherPub();
       public CipherPub  b13 = new CipherPub();
       public CipherPub  l1 = new CipherPub();
       public CipherPub  l2 = new CipherPub();
       public CipherPub  l3 = new CipherPub();
       public CipherPub  D1 = new CipherPub();
       public CipherPub  D2 = new CipherPub();
       public CipherPub  ERr1 = new CipherPub();
       public CipherPub  ERr2 = new CipherPub();
       
       public BigInteger pub = BigInteger.ZERO;
       public PaillierT paillier = null;
       public BigInteger RR1= BigInteger.ZERO;
       public BigInteger Rr1= BigInteger.ZERO;
       public BigInteger Rr2= BigInteger.ZERO;
       public BigInteger RRr1= BigInteger.ZERO;
       public BigInteger RRr2= BigInteger.ZERO;
       
       
       
       public BigInteger a1 = BigInteger.ZERO;
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger ZERO= BigInteger.ZERO;
       public BigInteger TWO = BigInteger.ZERO;
       public BigInteger L = BigInteger.ZERO;
       public BigInteger l = BigInteger.ZERO;
       public CipherPub U = new CipherPub();
       public CipherPub  EEone = new CipherPub();
       public CipherPub  FINMX = new CipherPub();
       public CipherPub  FINMI = new CipherPub();
       public Ciphertext1 m11 = new Ciphertext1();
       public BigInteger m1 = BigInteger.ZERO;
        public int s =  0;
       public CipherPub EZERO = new CipherPub();
        
        
   public SMAX(CipherPub  _VA, CipherPub _VB, BigInteger _pub, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
    pub = _pub;
   }  
     public void StepOne (){
    
      a1= paillier.n.subtract(EONE);
      TWO = new   BigInteger("2");
      L = TWO.modInverse(paillier.n) ;
      l = new BigInteger(paillier.n.bitLength()/2,  new Random());;
      EEone = paillier.Encryption(EONE, pub);
      
      EZERO = paillier.Encryption(ZERO, pub);
      
      
      
      SAD SK11  = new SAD(a,EZERO, pub, paillier);
      
      SK11.StepOne();
      SK11.StepTwo();
      SK11.StepThree();
      
      a11 = SK11.FIN;
      
      SAD SK12  = new SAD(b,EZERO, pub, paillier);
      
      SK12.StepOne();
      SK12.StepTwo();
     // SK12.StepTwo2();
      SK12.StepThree();
      
      b11 = SK12.FIN;
      
      
      
      a12.T1 = (a11.T1.modPow(TWO, paillier.nsquare)).multiply(EEone.T1).mod(paillier.nsquare);
      a12.T2 = ( a11.T2.modPow(TWO, paillier.nsquare)).multiply(EEone.T2).mod(paillier.nsquare);;
      a12.PUB = a11.PUB;
      
      b12.T1 = b11.T1.modPow(TWO, paillier.nsquare);
      b12.T2 = b11.T2.modPow(TWO, paillier.nsquare);
      b12.PUB = b11.PUB;
      
    
      RR1 = new BigInteger(200,  new Random());
      Rr1 = new BigInteger(200,  new Random());
      Rr2 = new BigInteger(200,  new Random());
      RRr1 =  paillier.n.subtract(Rr1);
      RRr2 =  paillier.n.subtract(Rr2);
      
      
      ERr1 = paillier.Encryption(Rr1, pub);
      ERr2 = paillier.Encryption(Rr2, pub);
      
       Random rand = new Random();
       // int max = 1000000;
       // int min = 0;
        s = rand.nextInt(100000000)%2;
        
        if(s == 1)
        { 
            b13.T1 = b12.T1.modPow(a1, paillier.nsquare);
            b13.T2 = b12.T2.modPow(a1, paillier.nsquare);
            a14.T1 = a11.T1.modPow(a1, paillier.nsquare);
            a14.T2 = a11.T2.modPow(a1, paillier.nsquare);
            b14.T1 = b11.T1.modPow(a1, paillier.nsquare);
            b14.T2 = b11.T2.modPow(a1, paillier.nsquare);
            
            l1.T1 = (a12.T1.multiply(b13.T1)).modPow(RR1, paillier.nsquare);
            l1.T2 = (a12.T2.multiply(b13.T2)).modPow(RR1, paillier.nsquare);
            l1.PUB = pub;
            
            l2.T1 = (b11.T1.multiply(a14.T1)).multiply(ERr1.T1);
            l2.T2 = (b11.T2.multiply(a14.T2)).multiply(ERr1.T2);
            l2.PUB = pub;          

            l3.T1 = (a11.T1.multiply(b14.T1)).multiply(ERr2.T1);
            l3.T2 = (a11.T2.multiply(b14.T2)).multiply(ERr2.T2);
            l3.PUB = pub;
            
            
            
            // System.out.println("===11111===");
//             System.out.println("===a ==="+paillier.SDecryption(a));
           //  System.out.println("===a11==="+paillier.SDecryption(a11));
          //   System.out.println("===a12==="+paillier.SDecryption(a12));
//             System.out.println("===b==="+paillier.SDecryption(b));
           // System.out.println("===l1==="+paillier.SDecryption(l1));
        }
        else
        {
            b13.T1 = a12.T1.modPow(a1, paillier.nsquare);
            b13.T2 = a12.T2.modPow(a1, paillier.nsquare);
            a14.T1 = a11.T1.modPow(a1, paillier.nsquare);
            a14.T2 = a11.T2.modPow(a1, paillier.nsquare);
            b14.T1 = b11.T1.modPow(a1, paillier.nsquare);
            b14.T2 = b11.T2.modPow(a1, paillier.nsquare);           
            
            l1.T1 = (b12.T1.multiply(b13.T1)).modPow(RR1, paillier.nsquare);
            l1.T2 = (b12.T2.multiply(b13.T2)).modPow(RR1, paillier.nsquare);
            l1.PUB = pub;
            
            l3.T1 = (b11.T1.multiply(a14.T1)).multiply(ERr2.T1);
            l3.T2 = (b11.T2.multiply(a14.T2)).multiply(ERr2.T2);
            l3.PUB = pub;          

            l2.T1 = (a11.T1.multiply(b14.T1)).multiply(ERr1.T1);
            l2.T2 = (a11.T2.multiply(b14.T2)).multiply(ERr1.T2);
            l2.PUB = pub;
            
//            System.out.println("===22222===");
//            System.out.println("===a ==="+paillier.SDecryption(a));
         //   System.out.println("===a11==="+paillier.SDecryption(a11));
        //    System.out.println("===a12==="+paillier.SDecryption(a12));
//            System.out.println("===b1==="+paillier.SDecryption(b));
        }
      
        
        m11=paillier.AddPDec1(l1);
    //  a11 = 
      
    }
    
    public void StepTwo (){
    
        m1 = paillier.AddPDec2(m11);
        
    if (m1.compareTo(l) == -1){
        U = paillier.Encryption(ZERO, pub);
        D1 = paillier.Encryption(ZERO, pub);
        D2 = paillier.Encryption(ZERO, pub);
//        System.out.println("===S1==="+ s);
//
//        System.out.println("D1=="+   paillier.SDecryption(D1));
//        System.out.println("D2=="+   paillier.SDecryption(D2));
//          System.out.println("===SGT CMP==="+ m1.compareTo(l));
    }
    else if (m1.compareTo(l) == 1){
        U = paillier.Encryption(EONE, pub);
        
        D1 = paillier.Refreash(l2);
        D2 = paillier.Refreash(l3);
//        System.out.println("===S2==="+ s);
//        System.out.println("===SGT CMP==="+ m1.compareTo(l));
    }
    else
        {
//        System.out.println("===SGT ERROR==="+m1.compareTo(l));
        U = paillier.Encryption(a1, pub);
        }
        
        
    }
     
        public void StepThree (){
        
       if(s == 1)
        {
            
        FINMX.T1 = a.T1.multiply(D1.T1).multiply((U.T1.modPow(RRr1, paillier.nsquare))).mod(paillier.nsquare); 
        FINMX.T2 = a.T2.multiply(D1.T2).multiply((U.T2.modPow(RRr1, paillier.nsquare))).mod(paillier.nsquare); 
        FINMX.PUB = pub;
        FINMI.T1 = b.T1.multiply(D2.T1).multiply((U.T1.modPow(RRr2, paillier.nsquare))).mod(paillier.nsquare); 
        FINMI.T2 = b.T2.multiply(D2.T2).multiply((U.T2.modPow(RRr2, paillier.nsquare))).mod(paillier.nsquare);
        FINMI.PUB = pub;
        }
  
       if(s == 0)
        {
        FINMX.T1 = b.T1.multiply(D1.T1).multiply((U.T1.modPow(RRr1, paillier.nsquare))).mod(paillier.nsquare); 
        FINMX.T2 = b.T2.multiply(D1.T2).multiply((U.T2.modPow(RRr1, paillier.nsquare))).mod(paillier.nsquare); 
        FINMX.PUB = pub;
        FINMI.T1 = a.T1.multiply(D2.T1).multiply((U.T1.modPow(RRr2, paillier.nsquare))).mod(paillier.nsquare); 
        FINMI.T2 = a.T2.multiply(D2.T2).multiply((U.T2.modPow(RRr2, paillier.nsquare))).mod(paillier.nsquare);
        FINMI.PUB = pub;
        }
       
       
       
     } 
    
    
    
     
}
