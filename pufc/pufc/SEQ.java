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
 * 
 * If x = y, then output 0;
 * 
 * If x not equal y, then output 1;
 */
public class SEQ {
    
       public CipherPub  a = new CipherPub();
       public CipherPub  b = new CipherPub();
       public CipherPub  u1 = new CipherPub();
       public CipherPub  u2 = new CipherPub();
       public CipherPub  u3 = new CipherPub();
       public CipherPub  u4 = new CipherPub();
       public CipherPub  f1 = new CipherPub();
       public CipherPub  f2 = new CipherPub();
       public CipherPub  FIN = new CipherPub();
       public CipherPub  EEone = new CipherPub();
       
       public BigInteger pub = BigInteger.ZERO;
       public PaillierT paillier = null;
       public BigInteger RR1= BigInteger.ZERO;
       public BigInteger a1 = BigInteger.ZERO;
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger ZERO= BigInteger.ZERO;
       public BigInteger TWO = BigInteger.ZERO;
       public long TA =  0;
       public long TB =  0;
       public int CCC =  0;
    
    
       public SEQ(CipherPub  _VA, CipherPub _VB, BigInteger _pub, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
    pub = _pub;
   }  
          public SEQ(CipherPub  _VA, CipherPub _VB,  PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
     pub = paillier.Hsigma;
   }   
    
        public void StepOne (){
    
      a1= paillier.n.subtract(EONE);
      
      SLT SK1 = new SLT (a,b,pub,paillier);
          
          SK1.StepOne();
          long T1 = System.currentTimeMillis(); 
          SK1.StepTwo();
          long T2 = System.currentTimeMillis(); 
          SK1.StepThree();
          TB = TB + T2-T1 +SK1.TB;
      CCC= CCC+ SK1.CCC;
          u1 = SK1.FIN;
          
      SLT SK2 = new SLT (b,a,pub,paillier);
          
          SK2.StepOne();
          long T3 = System.currentTimeMillis(); 
          SK2.StepTwo();
          long T4 = System.currentTimeMillis(); 
          SK2.StepThree();    
          TB = TB + T4-T3 + SK2.TB;
      CCC= CCC+ SK2.CCC;
         u2 = SK2.FIN;
      
         EEone = paillier.Encryption(EONE, pub);
         
         
         u3.T1 = EEone.T1.multiply((u1.T1.modPow(a1, paillier.nsquare))).mod(paillier.nsquare);
         u3.T2 = EEone.T2.multiply((u1.T2.modPow(a1, paillier.nsquare))).mod(paillier.nsquare);
         u3.PUB = pub;
         
         u4.T1 = EEone.T1.multiply((u2.T1.modPow(a1, paillier.nsquare))).mod(paillier.nsquare);
         u4.T2 = EEone.T2.multiply((u2.T2.modPow(a1, paillier.nsquare))).mod(paillier.nsquare);
         u4.PUB = pub;
         
         
        RSM SK3 = new RSM(u3,u2,paillier);
       
        SK3.StepOne();
         
        SK3.StepTwo1();
        long T5 = System.currentTimeMillis(); 
        SK3.StepTwo2();
        
        long T6 = System.currentTimeMillis(); 
        SK3.StepThree();
         f1 = SK3.FIN;
         TB = TB + T6-T5 ;
         CCC= CCC+ SK3.CCC;
        RSM SK4 = new RSM(u1,u4,paillier);
       
        SK4.StepOne();
         
        SK4.StepTwo1();
        long T7 = System.currentTimeMillis(); 
        SK4.StepTwo2();
        long T8 = System.currentTimeMillis(); 
        SK4.StepThree();
         f2 = SK4.FIN;       
         TB = TB + T8-T7;
         CCC= CCC+ SK4.CCC;
      FIN.T1 = f1.T1.multiply(f2.T1).mod(paillier.nsquare);
      FIN.T2 = f1.T2.multiply(f2.T2).mod(paillier.nsquare);   
      FIN.PUB = pub;
      
      
        }
       
       
       
}
