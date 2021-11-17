/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pufc;
import java.math.BigInteger;

/**
 *
 * @author xmliu
 */
public class SRF {
    
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
       public BigInteger NRR1= BigInteger.ZERO;
       public BigInteger a1 = BigInteger.ZERO;
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger ZERO= BigInteger.ZERO;
       public BigInteger TWO = BigInteger.ZERO;
       public BigInteger L = BigInteger.ZERO;
       public BigInteger l = BigInteger.ZERO;
       public CipherPub U = new CipherPub();
       public CipherPub U1 = new CipherPub();
       public CipherPub FIN1 = new CipherPub();
       public CipherPub FIN2 = new CipherPub();
       public CipherPub C = new CipherPub();
       public CipherPub  EEone = new CipherPub();
       public CipherPub  LL = new CipherPub();
       public CipherPub  FIN = new CipherPub();
       public Ciphertext1 m11 = new Ciphertext1();
       public BigInteger m1 = BigInteger.ZERO;
       public int s = 0;
       public CipherPub EZERO = new CipherPub();
       public CipherPub sa = new CipherPub();
       public CipherPub sb = new CipherPub();
       public long TA = 0;
       public long TB = 0;
       public int CCC = 0;
       public int m = 0;
       
   public SRF(CipherPub  _VA, CipherPub _VB, BigInteger _pub,int _m, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
    pub = _pub;
    m = _m;
   } 
  
      public SRF(CipherPub  _VA, CipherPub _VB,  int _m, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
     pub = paillier.Hsigma;
    m = _m;
   } 
   
   
   
     public void StepOne() {
         
           a1= paillier.n.subtract(EONE);
          TWO = new   BigInteger("2");
          
          EEone = paillier.Encryption(EONE, pub);
          
             SSBA SK11  = new SSBA(a,  pub, paillier);
      
      SK11.StepOne();
       long T1 = System.currentTimeMillis(); 
      SK11.StepTwo();
       long T2 = System.currentTimeMillis(); 
      SK11.StepThree();
      TB = TB + T2-T1;
      CCC= CCC+ SK11.CCC;
      a11 = SK11.FIN;
      sa = SK11.FINS;
      
                   SSBA SK142  = new SSBA(b,  pub, paillier);
      
      SK142.StepOne();
       long T3 = System.currentTimeMillis(); 
      SK142.StepTwo();
       long T4 = System.currentTimeMillis(); 
      SK142.StepThree();
      TB = TB + T4-T3;
      CCC= CCC+ SK142.CCC;
      b11 = SK142.FIN;
      sb = SK142.FINS;
      
        
          SGCD SK20 = new SGCD(a11, b11, paillier.Hsigma, m, paillier);
                   long TT29 = System.currentTimeMillis() ;
                  SK20.StepOne();
                   long TT30 = System.currentTimeMillis() ; 
                   
                    C = SK20.FIN;
            TA = TA + TT30-TT29 - SK20.TB;
            TB = TB + SK20.TB;
            CCC = CCC + SK20.CCC; 
        
        
        Sdiv SK19 = new Sdiv(a11, C, paillier.Hsigma, m, paillier);
        long TT12 = System.currentTimeMillis();
        SK19.StepOne();
        long TT13 = System.currentTimeMillis();

        a12 = SK19.FR;
        TA = TA + TT13 - TT12 - SK19.TB;
        TB = TB + SK19.TB;
        CCC = CCC + SK19.CCC;

        Sdiv SK18 = new Sdiv(b11, C, paillier.Hsigma, m, paillier);
        long TT10 = System.currentTimeMillis();
        SK18.StepOne();
        long TT11 = System.currentTimeMillis();

        b12 = SK18.FR;
        TA = TA + TT11 - TT10 - SK18.TB;
        TB = TB + SK18.TB;
        CCC = CCC + SK18.CCC;
            
   
        U.T1 =  (sa.T1.modPow(TWO, paillier.nsquare)).multiply(EEone.T1.modPow(a1, paillier.nsquare));
        U.T2 =  (sa.T2.modPow(TWO, paillier.nsquare)).multiply(EEone.T2.modPow(a1, paillier.nsquare));
               
        U.PUB = pub;
                
        U1.T1 =  (sb.T1.modPow(TWO, paillier.nsquare)).multiply(EEone.T1.modPow(a1, paillier.nsquare));
        U1.T2 =  (sb.T2.modPow(TWO, paillier.nsquare)).multiply(EEone.T2.modPow(a1, paillier.nsquare));
               
        U1.PUB = pub;      
        
        
         RSM SK12 = new RSM(a12, U, paillier);
        long T43 = System.currentTimeMillis();
        SK12.StepOne();
        SK12.StepTwo1();
        long T44 = System.currentTimeMillis();
        
        SK12.StepTwo2();
         long T45 = System.currentTimeMillis();
        SK12.StepThree();
         long T46 = System.currentTimeMillis();
         
         
         
         
         
         
         
         
         FIN1 = SK12.FIN;
        TA = TA + T44-T43 + (T46-T45);
        TB = TB + T45-T44;
        CCC= CCC + SK12.CCC;  
        
         RSM SK112 = new RSM(b12, U1, paillier);
        long T413 = System.currentTimeMillis();
        SK112.StepOne();
        SK112.StepTwo1();
        long T414 = System.currentTimeMillis();
        
        SK112.StepTwo2();
         long T415 = System.currentTimeMillis();
        SK112.StepThree();
         long T416 = System.currentTimeMillis();
         
         
         
         
         
         
         
         
         FIN2 = SK112.FIN;
        TA = TA + T414-T413 + (T416-T415);
        TB = TB + T415-T414;
        CCC= CCC + SK112.CCC;  
       
            
            
    }
     
     
}
