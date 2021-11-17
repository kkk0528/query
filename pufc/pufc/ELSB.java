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
public class ELSB {
       public CipherPub  a = new CipherPub();
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger a1 = BigInteger.ZERO;
       public PaillierT paillier = null;
       public BigInteger RR1= BigInteger.ZERO;
       public CipherPub EA= new CipherPub();
       public Ciphertext1 m11 = new Ciphertext1();
        public BigInteger m1 = BigInteger.ZERO;
       
       public CipherPub ERR1= new CipherPub();
       public CipherPub FIN= new CipherPub();
       public CipherPub ALPHA= new CipherPub();
        public  int CCC = 0;
         public  long TB = 0;
    
    
        public ELSB(CipherPub  _VA, PaillierT _paillier) {
   
            a=  _VA;
            paillier=_paillier;
        }
    
    
     public void StepOne (){
     
           RR1 = new BigInteger(200,  new Random());
           ERR1 = paillier.Encryption(RR1, a.PUB);
           a1= paillier.n.subtract(EONE);
           
           EA.T1 = a.T1.multiply(ERR1.T1);
           EA.T2 = a.T2.multiply(ERR1.T2);
           EA.PUB = a.PUB;
           
           m11=paillier.AddPDec1(EA);
           
           CCC = CCC + m11.T1.bitLength()+m11.T2.bitLength()+m11.T3.bitLength();
     }
     
       public void StepTwo (){
           
   
            m1 = paillier.AddPDec2(m11);
            OddTest SK111 = new OddTest();
            
         if(SK111.odd(m1))  {
             ALPHA = paillier.Encryption(BigInteger.ONE, a.PUB);}
         else
         {ALPHA = paillier.Encryption(BigInteger.ZERO, a.PUB);}
        
        CCC = CCC + ALPHA.T1.bitLength() + ALPHA.T2.bitLength();
       //     CCC = CCC + m4.T1.bitLength() + m4.T2.bitLength()+ Em1.T1.bitLength() + Em1.T2.bitLength() + Em2.T1.bitLength() + Em2.T2.bitLength() ;
         }
     
     
    public void StepThree (){
        a1= paillier.n.subtract(EONE);
         OddTest SK111 = new OddTest();
         if(SK111.odd(RR1))  {
           FIN.T1 =  (paillier.Encryption(BigInteger.ONE, a.PUB).T1).multiply(ALPHA.T1.modPow(a1, paillier.nsquare));
           FIN.T2 =  (paillier.Encryption(BigInteger.ONE, a.PUB).T2).multiply(ALPHA.T2.modPow(a1, paillier.nsquare));
         }
         else
         {FIN = ALPHA;}
    
    }
}
