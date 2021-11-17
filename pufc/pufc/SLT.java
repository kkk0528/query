/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pufc;
import java.math.BigInteger;
import java.util.Random;
/**
/**
 *
 * @author xmliu
 */
public class SLT {
       public CipherPub  a = new CipherPub();
       public CipherPub  b = new CipherPub();
       public CipherPub  sa = new CipherPub();
       public CipherPub  sb = new CipherPub();
       public CipherPub  nsa = new CipherPub();
       public CipherPub  nsb = new CipherPub();
       public CipherPub  a11 = new CipherPub();
       public CipherPub  b11 = new CipherPub();
       public CipherPub  a12 = new CipherPub();
       public CipherPub  b12 = new CipherPub();
       public CipherPub  b13 = new CipherPub();
       public CipherPub  l1 = new CipherPub();
       public CipherPub  l2 = new CipherPub();
       public CipherPub  O1 = new CipherPub();
       public CipherPub  O2 = new CipherPub();
       public CipherPub  O  = new CipherPub();
       public CipherPub  NO  = new CipherPub();
       public CipherPub  UX  = new CipherPub();
       public CipherPub  K1  = new CipherPub();
       public CipherPub  K2  = new CipherPub();
       
       public BigInteger pub = BigInteger.ZERO;
       public PaillierT paillier = null;
       public BigInteger RR1= BigInteger.ZERO;
       public BigInteger a1 = BigInteger.ZERO;
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger ZERO= BigInteger.ZERO;
       public BigInteger TWO = BigInteger.ZERO;
       public BigInteger L = BigInteger.ZERO;
       public BigInteger l = BigInteger.ZERO;
       public CipherPub U = new CipherPub();
       public CipherPub  EEone = new CipherPub();
       public CipherPub  EUone = new CipherPub();
       public CipherPub  FIN = new CipherPub();
       public Ciphertext1 m11 = new Ciphertext1();
       public BigInteger m1 = BigInteger.ZERO;
        public int s =  0;
        public long TA =  0;
        public long TB =  0;
        public int CCC =  0;
          public CipherPub EZERO = new CipherPub();
          
   public SLT(CipherPub  _VA, CipherPub _VB, BigInteger _pub, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
    pub = _pub;
   }  
 
      public SLT(CipherPub  _VA, CipherPub _VB,   PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
     pub = paillier.Hsigma;
   }  
   
   
    
    public void StepOne (){
    
      a1= paillier.n.subtract(EONE);
      TWO = new   BigInteger("2");
      L = TWO.modInverse(paillier.n) ;
      l = new BigInteger(paillier.n.bitLength()/2,  new Random());;
      EEone = paillier.Encryption(EONE, pub);
    //  EUone = paillier.Encryption(EONE, pub);
            EZERO = paillier.Encryption(ZERO, pub);
      
      
      
 
      
      
      a12.T1 = (a.T1.modPow(TWO, paillier.nsquare)).multiply(EEone.T1).mod(paillier.nsquare);
      a12.T2 = (a.T2.modPow(TWO, paillier.nsquare)).multiply(EEone.T2).mod(paillier.nsquare);;
      a12.PUB = a.PUB;
      
      b12.T1 = b.T1.modPow(TWO, paillier.nsquare);
      b12.T2 = b.T2.modPow(TWO, paillier.nsquare);
      b12.PUB = b.PUB;
      
      
  //    a11.T1 = a.T1.modPow(TWO, paillier.nsquare);
 //    a11.T2 = a.T2.modPow(TWO, paillier.nsquare);
  //    a11.PUB = a.PUB;
      
//     b11.T1 = b.T1.modPow(TWO, paillier.nsquare);
 //     b11.T2 = b.T2.modPow(TWO, paillier.nsquare);
 //     b11.PUB = b.PUB;
      
      
      
   //   SAD SK11  = new SAD(a11,EEone, pub, paillier);
      
  //    SK11.StepOne();
  //    SK11.StepTwo();
  //    SK11.StepThree();
      
   //   a12 = SK11.FIN;
      
   //   RSM SK12  = new RSM(b11,EEone, pub, paillier);
      
   //   SK12.StepOne();
   //   SK12.StepTwo1();
   //   SK12.StepTwo2();
   //   SK12.StepThree();
      
   //   b12 = SK12.FIN;
    
      RR1 = new BigInteger(200,  new Random());
      
       Random rand = new Random();
       // int max = 1000000;
       // int min = 0;
        s = rand.nextInt(100000000)%2;
        
        if(s == 1)
        { 
            b13.T1 = b12.T1.modPow(a1, paillier.nsquare);
            b13.T2 = b12.T2.modPow(a1, paillier.nsquare);
            b13.PUB = b12.PUB;
                   SAD SK11  = new SAD(a12,b13, pub, paillier);
       long T1 = System.currentTimeMillis();
       SK11.StepOne();
        long T2 = System.currentTimeMillis();
       SK11.StepTwo();
        long T3 = System.currentTimeMillis();
       SK11.StepThree();
       long T4 = System.currentTimeMillis();
       
       l2 = SK11.FIN;
            
                TA = TA + T2-T1 + (T4-T3);
      TB = TB + T3-T2;
      CCC = CCC+ SK11.CCC;  
            
            
            l1.T1 = (l2.T1).modPow(RR1, paillier.nsquare);
            l1.T2 = (l2.T2).modPow(RR1, paillier.nsquare);
            l1.PUB = pub;
            // System.out.println("===11111===");
      //       System.out.println("===a ==="+paillier.SDecryption(a));
           //  System.out.println("===a11==="+paillier.SDecryption(a11));
          //   System.out.println("===a12==="+paillier.SDecryption(a12));
       //      System.out.println("===b==="+paillier.SDecryption(b));
           // System.out.println("===l1==="+paillier.SDecryption(l1));
        }
        else
        {
            b13.T1 = a12.T1.modPow(a1, paillier.nsquare);
            b13.T2 = a12.T2.modPow(a1, paillier.nsquare);
            b13.PUB = a12.PUB;
            
             SAD SK11  = new SAD(b12,b13, pub, paillier);
       long T1 = System.currentTimeMillis();
       SK11.StepOne();
        long T2 = System.currentTimeMillis();
       SK11.StepTwo();
        long T3 = System.currentTimeMillis();
       SK11.StepThree();
       long T4 = System.currentTimeMillis();
       
       l2 = SK11.FIN;
            
                TA = TA + T2-T1 + (T4-T3);
      TB = TB + T3-T2;
      CCC = CCC+ SK11.CCC; 
            
            
            
            l1.T1 = (l2.T1).modPow(RR1, paillier.nsquare);
            l1.T2 = (l2.T2).modPow(RR1, paillier.nsquare);
            l1.PUB = pub;
          //  System.out.println("===22222===");
        //    System.out.println("===a ==="+paillier.SDecryption(a));
         //   System.out.println("===a11==="+paillier.SDecryption(a11));
        //    System.out.println("===a12==="+paillier.SDecryption(a12));
         //   System.out.println("===b1==="+paillier.SDecryption(b));
        }
      
        
        m11=paillier.AddPDec1(l1);
    //  a11 = 
      CCC = CCC + m11.T1.bitLength()+m11.T2.bitLength()+m11.T3.bitLength();
    }
   
    public void StepTwo (){
    
        m1 = paillier.AddPDec2(m11);
        
    if (m1.compareTo(l) == 1){
        U = paillier.Encryption(EONE, pub);
 //       System.out.println("===S1==="+ s);
   //       System.out.println("===SGT CMP==="+ m1.compareTo(l));
    }
    else if (m1.compareTo(l) == -1){
        U = paillier.Encryption(ZERO, pub);
     //   System.out.println("===S2==="+ s);
    //    System.out.println("===SGT CMP==="+ m1.compareTo(l));
    }
    else
        {
    //    System.out.println("===SGT ERROR==="+m1.compareTo(l));
        U = paillier.Encryption(a1, pub);
        }
        
        CCC = CCC + U.T1.bitLength()+U.T2.bitLength();
    }
   
     public void StepThree (){
        
       if(s == 1)
        {
        FIN = U; 
        }
  
       if(s == 0)
        {
        FIN.T1 = EEone.T1.multiply((U.T1.modPow(a1, paillier.nsquare))).mod(paillier.nsquare); 
        FIN.T2 = EEone.T2.multiply((U.T2.modPow(a1, paillier.nsquare))).mod(paillier.nsquare); 
        FIN.PUB = pub;
        }
       
  
     
       
     }
    
}
