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
public class SMMS {
    
           public CipherPub  a = new CipherPub();
       public CipherPub  b = new CipherPub();
       public CipherPub  a11 = new CipherPub();
       public CipherPub  b11 = new CipherPub();
       public CipherPub  a12 = new CipherPub();
       public CipherPub  b12 = new CipherPub();
       public CipherPub  b13 = new CipherPub();
       public CipherPub  l1 = new CipherPub();
       
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
       public CipherPub  FINA = new CipherPub();
       public CipherPub  FINI = new CipherPub();
       public CipherPub  K1 = new CipherPub();
       public CipherPub  K2 = new CipherPub();
       public Ciphertext1 m11 = new Ciphertext1();
       public BigInteger m1 = BigInteger.ZERO;
        public int s =  0;
                public long TA =  0;
        public long TB =  0;
        public int CCC =  0;
          public CipherPub EZERO = new CipherPub();
          
   public SMMS(CipherPub  _VA, CipherPub _VB, BigInteger _pub, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
    pub = _pub;
   }  
   
      public SMMS(CipherPub  _VA, CipherPub _VB , PaillierT _paillier) {
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
      EEone = paillier.Encryption(EONE,  pub);
  
            EZERO = paillier.Encryption(ZERO, pub);
      
    
          SAD SK11  = new SAD(a,EZERO, pub, paillier);
      
      SK11.StepOne();
      long T1 = System.currentTimeMillis(); 
      SK11.StepTwo();
      long T2 = System.currentTimeMillis(); 
      SK11.StepThree();
      
      TB = TB + T2-T1;
      CCC = CCC + SK11.CCC;
      a11 = SK11.FIN;
      
      SAD SK12  = new SAD(b,EZERO, pub, paillier);
      
      SK12.StepOne();
      long T3 = System.currentTimeMillis(); 
      SK12.StepTwo();
      long T4 = System.currentTimeMillis(); 
     // SK12.StepTwo2();
      SK12.StepThree();
      
      TB = TB + T4-T3;
      CCC = CCC + SK12.CCC;
      
      b11 = SK12.FIN;
      
      
      
       SLT SK13 = new SLT(a,b ,paillier);
   //      long T1 = System.currentTimeMillis(); 
        SK13.StepOne();
        long T5 = System.currentTimeMillis(); 
        SK13.StepTwo ();
      
         long T6 = System.currentTimeMillis(); 
        SK13.StepThree();
         TB = TB + T6-T5 + SK13.TB;
          CCC = CCC + SK13.CCC; 
        
      U = SK13.FIN;
      
        RSM SK14  = new RSM(U,a, pub, paillier);
      
       SK14.StepOne();
       SK14.StepTwo1();
       long T7 = System.currentTimeMillis();
       SK14.StepTwo2();
       long T8 = System.currentTimeMillis();
       SK14.StepThree();
      
                TB = TB + T8-T7;
          CCC = CCC + SK14.CCC; 
       K1 = SK14.FIN;
       
       
              
       RSM SK15  = new RSM(U,b, pub, paillier);
      
       SK15.StepOne();
       SK15.StepTwo1();
       long T9 = System.currentTimeMillis();
       SK15.StepTwo2();
       long T10 = System.currentTimeMillis();
       SK15.StepThree();
      
         TB = TB + T10-T9;
          CCC = CCC + SK15.CCC; 
          
       K2 = SK15.FIN;
        
      
      FINA.T1 = (a11.T1.multiply(K2.T1)).multiply(K1.T1.modPow(a1, paillier.nsquare));
      FINA.T2 = (a11.T2.multiply(K2.T2)).multiply(K1.T2.modPow(a1, paillier.nsquare));
      FINA.PUB = pub;
      
      FINI.T1 = (b11.T1.multiply(K1.T1)).multiply(K2.T1.modPow(a1, paillier.nsquare));
      FINI.T2 = (b11.T2.multiply(K1.T2)).multiply(K2.T2.modPow(a1, paillier.nsquare));
      FINI.PUB = pub;    
      
      
      
      
      }
      
      
}
