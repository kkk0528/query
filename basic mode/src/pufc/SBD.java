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
public class SBD {
       public CipherPub  a = new CipherPub();
       public CipherPub  T1 = new CipherPub();
       public BigInteger EONE = BigInteger.ONE;
       public BigInteger a1 = BigInteger.ZERO;
       public PaillierT paillier = null;
       public BigInteger RR1 = BigInteger.ZERO;
       public CipherPub Z = new CipherPub();
       public Ciphertext1 m11 = new Ciphertext1();
       public BigInteger m1 = BigInteger.ZERO;
       public BigInteger L = BigInteger.ZERO;
       public BigInteger TWO = BigInteger.ZERO;
       public CipherPub ERR1 = new CipherPub();
       public CipherPub FIN = new CipherPub();
       public CipherPub T = new CipherPub();
       public CipherPub ALPHA= new CipherPub();
       public int m = 0;
            public  int CCC = 0;
         public  long TB = 0;
         
         
         public SBD(CipherPub  _VA, int _m, PaillierT _paillier) {
         a =  _VA;
         m =  _m;
          paillier=_paillier;
    
   } 
         
          public CipherPub [] StepOne (){
             
              CipherPub [] EBD = new CipherPub [m];
              
              
            a1= paillier.n.subtract(EONE);
            TWO = new   BigInteger("2");
            L = TWO.modInverse(paillier.n) ;
            T1 = a;
            T.T1 = a.T1.multiply(EONE);
            T.T2 = a.T2.multiply(EONE);
            T.PUB = a.PUB;
            
              for (int jjj=0; jjj< m; jjj++)
              {
               ELSB SK12 = new ELSB(T ,paillier);
                  SK12.StepOne();
                  long T13 = System.currentTimeMillis();
                  SK12.StepTwo ();
                  long T14 = System.currentTimeMillis();
                  SK12.StepThree();
                  TB =TB +T14-T13;
                  CCC = CCC +SK12.CCC;
                  EBD[jjj] = SK12.FIN;
                  Z.T1 = T.T1.multiply((SK12.FIN.T1.modPow(a1, paillier.nsquare)));
                  Z.T2 = T.T2.multiply((SK12.FIN.T2.modPow(a1, paillier.nsquare)));
                  
                  T.T1 = Z.T1.modPow(L, paillier.nsquare);
                  T.T2 = Z.T2.modPow(L, paillier.nsquare);
              }
            
              return EBD;
          }
         
}
