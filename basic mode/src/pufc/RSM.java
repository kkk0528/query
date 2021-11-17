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
public class RSM {
   public static final int alpha11 = 2;
       public Ciphertext a = new Ciphertext();
       public Ciphertext b = new Ciphertext();
       public CipherPub  a11 = new CipherPub();
       public CipherPub  b11 = new CipherPub();
       public PaillierT paillier = null;
       public BigInteger RR1= BigInteger.ZERO;
       public BigInteger RR2 = BigInteger.ZERO;
       public BigInteger RRR1 = BigInteger.ZERO;
       public BigInteger RRR2 = BigInteger.ZERO;
       public BigInteger RR3= BigInteger.ZERO;
       public CipherPub ERR1= new CipherPub();
  
       public CipherPub ERR2 = new CipherPub();
       public CipherPub HERR1= new CipherPub();
  
       public CipherPub HERR2 = new CipherPub();
       public CipherPub ERR3= new CipherPub();
       public BigInteger ERR4= BigInteger.ZERO;
    
    
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger a1 = BigInteger.ZERO;
       public BigInteger a2 = BigInteger.ZERO;
       public Ciphertext EERR1 = new Ciphertext();
       public Ciphertext EERR2 = new Ciphertext();
       public Ciphertext EERR3 = new Ciphertext();
       public CipherPub ERRR1 = new CipherPub();
       public CipherPub ERRR2 = new CipherPub();
       
       public CipherPub D1 = new CipherPub();
       public CipherPub D2 = new CipherPub();
       public Ciphertext D3 = new Ciphertext();
       public CipherPub S1 = new CipherPub();
       public CipherPub S2 = new CipherPub();
       public Ciphertext1 D11 = new Ciphertext1();
       public Ciphertext1 D12 = new Ciphertext1();
       
       
       public BigInteger m1 = BigInteger.ZERO;
       public BigInteger m2 = BigInteger.ZERO;
       public BigInteger m3 = BigInteger.ZERO;
       public BigInteger Rm1 = BigInteger.ZERO;
       public BigInteger Rm2 = BigInteger.ZERO;
       public BigInteger Rm3 = BigInteger.ZERO;
       public BigInteger Rm4 = BigInteger.ZERO;
 
       
       
       public Ciphertext m4 = new Ciphertext();
       public Ciphertext Em1 = new Ciphertext();
       public Ciphertext Em2 = new Ciphertext();
       public Ciphertext Em3 = new Ciphertext();
       public Ciphertext Em4 = new Ciphertext(); 
       
       public  Ciphertext1 m11 = new Ciphertext1();
       public  Ciphertext1 m12 = new Ciphertext1();
       public  Ciphertext1 m13 = new Ciphertext1();
       public  Ciphertext1 m14 = new Ciphertext1();
       
        
       
       
       
       public Ciphertext T1 = new Ciphertext();
       public Ciphertext T2 = new Ciphertext();
       public Ciphertext T3 = new Ciphertext();
       public Ciphertext T4 = new Ciphertext();
       public BigInteger P1 = BigInteger.ZERO;
       public BigInteger P2 = BigInteger.ZERO;     
       public CipherPub FIN = new CipherPub();
       public BigInteger FIN2 = BigInteger.ZERO;
        public  BigInteger pub = BigInteger.ZERO;
        public int CCC = 0;
         
    public RSM(Ciphertext  _VA, Ciphertext _VB, PaillierT _paillier) {
    a=  _VA;
    b=  _VB;
    paillier=_paillier;
    pub = paillier.Hsigma;
   }
         public RSM(CipherPub  _VA, CipherPub _VB, PaillierT _paillier) {
    a11 =  _VA;
    b11 =  _VB;
    paillier=_paillier;
    pub = paillier.Hsigma;
   } 
     public RSM(CipherPub  _VA, CipherPub _VB, BigInteger _pub, PaillierT _paillier) {
    a11 =  _VA;
    b11 =  _VB;
    paillier=_paillier;
    pub = _pub;
   }  
          public void StepOne (){
           
           
            
           RR1 = new BigInteger(200,  new Random());
           
           RR2 = new BigInteger(200,  new Random()); 
           
           RRR1 = new BigInteger(200,  new Random());
           RRR2 = new BigInteger(200,  new Random());
           
           RR3 = RR1.multiply(RR2);
           
           ERR1=paillier.Encryption(RR1, a11.PUB);
           ERR2=paillier.Encryption(RR2, b11.PUB);
           ERRR1=paillier.Encryption(RRR1, a11.PUB);
           ERRR2=paillier.Encryption(RRR2, b11.PUB);
           
          
           
           HERR1 =paillier.Encryption(RRR1, pub);
           HERR2 =paillier.Encryption(RRR2, pub);
           ERR3=paillier.Encryption(RR3, pub);
           a1= paillier.n.subtract(EONE);
           
           
           
           Em3.T1 = HERR1.T1.modPow(a1, paillier.nsquare); // E(R_1)^{N-1}
           Em3.T2 = HERR1.T2.modPow(a1, paillier.nsquare);
           Em4.T1 = HERR2.T1.modPow(a1, paillier.nsquare);// E(R_2)^{N-1}
           Em4.T2 = HERR2.T2.modPow(a1, paillier.nsquare);         
           
           T1.T1=a11.T1.modPow(RR2, paillier.nsquare); 
           T1.T2=a11.T2.modPow(RR2, paillier.nsquare); 
           T2.T1=b11.T1.modPow(RR1, paillier.nsquare); 
           T2.T2=b11.T2.modPow(RR1, paillier.nsquare); 
           T3.T1=T1.T1.modPow(a1, paillier.nsquare); //E(x)^{N-r_2}
           T3.T2=T1.T2.modPow(a1, paillier.nsquare); 
           T4.T1=T2.T1.modPow(a1, paillier.nsquare); 
           T4.T2=T2.T2.modPow(a1, paillier.nsquare);//E(y)^{N-r_1}
           
           EERR1.T1=ERR1.T1.modPow(a1, paillier.nsquare);
           EERR1.T2=ERR1.T2.modPow(a1, paillier.nsquare);
           EERR2.T1=ERR2.T1.modPow(a1, paillier.nsquare);
           EERR2.T2=ERR2.T2.modPow(a1, paillier.nsquare); 
         
           EERR3.T1=ERR3.T1.modPow(a1, paillier.nsquare);
           EERR3.T2=ERR3.T2.modPow(a1, paillier.nsquare);
           
           
           D1.T1=a11.T1.multiply(ERR1.T1);// E(x - r_1)
           D1.T2=a11.T2.multiply(ERR1.T2);
           D1.PUB = a11.PUB;
           
           D2.T1=b11.T1.multiply(ERR2.T1);
           D2.T2=b11.T2.multiply(ERR2.T2);//E(y - r_2)
           D2.PUB = b11.PUB;
           
           
           S1.T1 =T3.T1.multiply(ERRR1.T1);
           S1.T2 =T3.T2.multiply(ERRR1.T2);
           S1.PUB = a11.PUB;
           
           S2.T1 =T4.T1.multiply(ERRR2.T1);
           S2.T2 =T4.T2.multiply(ERRR2.T2);
           S2.PUB = b11.PUB;
           
         //  D11 = paillier.WPDecryption1(D1);
           //D12 = paillier.WPDecryption1(D2);
           
           
           
          
          
         } 
   
          
              
        public void StepTwo1 (){
           
            m11=paillier.AddPDec1(D1);
            m12=paillier.AddPDec1(D2); 
            m13=paillier.AddPDec1(S1); 
            m14=paillier.AddPDec1(S2);
         ///  for(int ii =0; ii<(alpha11);ii++)
         //       {  CCC = CCC + m11[ii].bitLength() + m12[ii].bitLength()+ m13[ii].bitLength()+ m14[ii].bitLength();
        //        }
         //   CCC = CCC + m4.T1.bitLength() + m4.T2.bitLength();
             CCC = CCC + m11.T1.bitLength() + m11.T2.bitLength()+  m11.T3.bitLength()+  m12.T1.bitLength()+  m12.T2.bitLength()+ m12.T3.bitLength() +m13.T1.bitLength()+ m13.T2.bitLength()+ m13.T3.bitLength()+ m14.T1.bitLength()+ m14.T2.bitLength()+ m14.T3.bitLength();
         }       
          
        public void StepTwo2 (){
           
            m1 = paillier.AddPDec2(m11);
            m2 = paillier.AddPDec2(m12); 
            Rm3 =  paillier.AddPDec2(m13);
            Rm4 = paillier.AddPDec2(m14);
            m3 = m1.multiply(m2);
            m4 = paillier.Encryption(m3);
            Em1 =  paillier.Encryption(Rm3);
            Em2 =  paillier.Encryption(Rm4);
           
            
            CCC = CCC + m4.T1.bitLength() + m4.T2.bitLength()+ Em1.T1.bitLength() + Em1.T2.bitLength() + Em2.T1.bitLength() + Em2.T2.bitLength() ;
         } 
          
         public void StepThree (){
     //      FIN =m4.multiply(T4);
           //  FIN.T1 = m4.T1.multiply(T3.T1).multiply(T4.T1).multiply(EERR3.T1);
          //   FIN.T2 = m4.T2.multiply(T3.T2).multiply(T4.T2).multiply(EERR3.T2);
             
             FIN.T1 = m4.T1.multiply(Em1.T1).multiply(Em2.T1).multiply(EERR3.T1).multiply(Em4.T1).multiply(Em3.T1);
             FIN.T2 = m4.T2.multiply(Em1.T2).multiply(Em2.T2).multiply(EERR3.T2).multiply(Em4.T2).multiply(Em3.T2);
             FIN.PUB = pub;
            FIN2= paillier.SDecryption(FIN);
 
         }           
          
          
          
    
    
}
