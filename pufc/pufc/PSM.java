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
 * @author SimonLau
 */
public class PSM {
  
       public pufc.Ciphertext a = new Ciphertext();
       public Ciphertext b = new Ciphertext();
      
       public PaillierT paillier = null;
       public BigInteger RR1= BigInteger.ZERO;
       public BigInteger RR2 = BigInteger.ZERO;
       public BigInteger RR3= BigInteger.ZERO;
       public Ciphertext ERR1= new Ciphertext();
  
       public Ciphertext ERR2 = new Ciphertext();
       public Ciphertext ERR3= new Ciphertext();
       public BigInteger ERR4= BigInteger.ZERO;
    
    
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger a1 = BigInteger.ZERO;
       public BigInteger a2 = BigInteger.ZERO;
       public Ciphertext EERR1 = new Ciphertext();
       public Ciphertext EERR2 = new Ciphertext();
       public Ciphertext EERR3 = new Ciphertext();
       public Ciphertext D1 = new Ciphertext();
       public Ciphertext D2 = new Ciphertext();

       
       public Ciphertext D3 = new Ciphertext();
       
       public BigInteger m1 = BigInteger.ZERO;
       public BigInteger m2 = BigInteger.ZERO;
       public BigInteger m3 = BigInteger.ZERO;
       public Ciphertext m4 = new Ciphertext();
       public Ciphertext T1 = new Ciphertext();
       public Ciphertext T2 = new Ciphertext();
       public Ciphertext T3 = new Ciphertext();
       public Ciphertext T4 = new Ciphertext();
       public BigInteger P1 = BigInteger.ZERO;
       public BigInteger P2 = BigInteger.ZERO;     
       public Ciphertext FIN = new Ciphertext();
       public BigInteger FIN2 = BigInteger.ZERO;
        public int CCC = 0;
         
    public PSM(Ciphertext  _VA, Ciphertext _VB, PaillierT _paillier) {
    a=  _VA;
    b=  _VB;
    paillier=_paillier;
   }
    
          public void StepOne (){
           
           
            
           RR1 = new BigInteger(200,  new Random());
           RR2 = new BigInteger(200,  new Random()); 
           RR3 = RR1.multiply(RR2);
           
           ERR1=paillier.Encryption( RR1);
           ERR2=paillier.Encryption( RR2);
           ERR3=paillier.Encryption( RR3);
           a1= paillier.n.subtract(EONE);
           
           
           T1.T1=a.T1.modPow(RR2, paillier.nsquare); 
           T1.T2=a.T2.modPow(RR2, paillier.nsquare); 
           T2.T1=b.T1.modPow(RR1, paillier.nsquare); 
           T2.T2=b.T2.modPow(RR1, paillier.nsquare); 
           T3.T1=T1.T1.modPow(a1, paillier.nsquare); 
           T3.T2=T1.T2.modPow(a1, paillier.nsquare); 
           T4.T1=T2.T1.modPow(a1, paillier.nsquare); 
           T4.T2=T2.T2.modPow(a1, paillier.nsquare);
           
           EERR1.T1=ERR1.T1.modPow(a1, paillier.nsquare);
           EERR1.T2=ERR1.T2.modPow(a1, paillier.nsquare);
           EERR2.T1=ERR2.T1.modPow(a1, paillier.nsquare);
           EERR2.T2=ERR2.T2.modPow(a1, paillier.nsquare); 
         
           EERR3.T1=ERR3.T1.modPow(a1, paillier.nsquare);
           EERR3.T2=ERR3.T2.modPow(a1, paillier.nsquare);
           
           
           D1.T1=a.T1.multiply(ERR1.T1);
           D1.T2=a.T2.multiply(ERR1.T2);
           D2.T1=b.T1.multiply(ERR2.T1);
           D2.T2=b.T2.multiply(ERR2.T2);
           
           CCC = CCC + D1.T1.bitLength() + D1.T2.bitLength()+ D2.T1.bitLength() + D2.T2.bitLength();
          
         } 
   
                    public void StepTwo (){
           
            m1=paillier.SDecryption(D2);
            m2=paillier.SDecryption(D1); 
            m3=m1.multiply(m2);
            m4=paillier.Encryption(m3);
            
            CCC = CCC + m4.T1.bitLength() + m4.T2.bitLength();
         } 
          
                    public void StepThree (){
     //      FIN =m4.multiply(T4);
             FIN.T1 = m4.T1.multiply(T3.T1).multiply(T4.T1).multiply(EERR3.T1);
             FIN.T2 = m4.T2.multiply(T3.T2).multiply(T4.T2).multiply(EERR3.T2);
        //   FIN2= paillier.SDecryption(FIN);
 
         }           
          
          
          
    
    
}
