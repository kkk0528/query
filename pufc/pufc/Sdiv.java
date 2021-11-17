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
public class Sdiv {
    
       public int  m = 0;
       public CipherPub  a = new CipherPub();
       public CipherPub  AA = new CipherPub();
       public CipherPub  AKKK = new CipherPub();
       public CipherPub  BB = new CipherPub();
       public CipherPub  b = new CipherPub();
       public CipherPub  a11 = new CipherPub();
       public CipherPub  b11 = new CipherPub();
       public CipherPub  a12 = new CipherPub();
       public CipherPub  b12 = new CipherPub();
       public CipherPub  sa = new CipherPub();
       public CipherPub  sb = new CipherPub();
       public CipherPub  nsa = new CipherPub();
       public CipherPub  nsb = new CipherPub();
       public CipherPub  a13 = new CipherPub();
       public CipherPub  b13 = new CipherPub();
       public CipherPub  f1 = new CipherPub();
       public CipherPub  u2 = new CipherPub();
       public CipherPub  u3 = new CipherPub();
       public CipherPub  u4 = new CipherPub();
       public CipherPub  fzero = new CipherPub();
       public CipherPub  fone = new CipherPub();
       public CipherPub  FIN = new CipherPub();
       public CipherPub  EEone = new CipherPub();
       public CipherPub  q01 = new CipherPub();
       public CipherPub  Q = new CipherPub();
       public CipherPub  R = new CipherPub();
              public CipherPub  FQ = new CipherPub();
       public CipherPub  FR = new CipherPub();
       public CipherPub  K1 = new CipherPub();
       
       public CipherPub  XM = new CipherPub();
       public BigInteger pub = BigInteger.ZERO;
       public PaillierT  paillier = null;
       public BigInteger RR1= BigInteger.ZERO;
       public BigInteger a1 = BigInteger.ZERO;
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger ZERO= BigInteger.ZERO;
       public CipherPub  EZERO= new CipherPub();
       public BigInteger TWO = BigInteger.ZERO;
        public long TA =  0;
        public long TB =  0;
        public int CCC =  0;
    
    
       public Sdiv(CipherPub  _VA, CipherPub _VB, BigInteger _pub, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
    pub = _pub;
   }  
    
       
  public Sdiv(CipherPub  _VA, CipherPub _VB, BigInteger _pub, int _m, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    m  =  _m;
    paillier=_paillier;
    pub = _pub;
   }  
       
    public Sdiv(CipherPub  _VA, CipherPub _VB,    int _m, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    m  =  _m;
    paillier=_paillier;
    pub = paillier.Hsigma;
   }      
       
    
        public void StepOne (){
    
          a1= paillier.n.subtract(EONE);
          EZERO =  paillier.Encryption(ZERO, pub);
          EEone =  paillier.Encryption(EONE, pub);
      
          SEQ SK1 = new SEQ (b,EZERO,pub,paillier); // b = 0??
          
          SK1.StepOne();
          
          TB = TB + SK1.TB;
          CCC = CCC + SK1.CCC;
          fzero = SK1.FIN;
      
            f1.T1 = fzero.T1.modPow(a1, paillier.nsquare);
            f1.T2 = fzero.T2.modPow(a1, paillier.nsquare);
      
      
            fone.T1 = (EEone.T1.multiply(f1.T1)).mod(paillier.nsquare); //1-f;
            fone.T2 = (EEone.T2.multiply(f1.T2)).mod(paillier.nsquare);
            
            
            
            RSM SK2 = new RSM(fzero,b,paillier);
       
              SK2.StepOne();
         
        SK2.StepTwo1();
        long T1 = System.currentTimeMillis();
        SK2.StepTwo2();
        long T2 = System.currentTimeMillis();
        SK2.StepThree();
        TB = TB +T2-T1;
        CCC = CCC  +SK2.CCC;
         b11 = SK2.FIN;  
            
          RSM SK3 = new RSM(fzero,a,paillier);
       
              SK3.StepOne();
         
        SK3.StepTwo1();
        long T3 = System.currentTimeMillis();
        SK3.StepTwo2();
        long T4 = System.currentTimeMillis();
        SK3.StepThree();
        TB =TB + T4-T3;
        CCC =CCC +SK3.CCC;
         a11 = SK3.FIN;           
            
         a12 = a11;
         
         b12.T1 = b11.T1.multiply(fone.T1).mod(paillier.nsquare);
         b12.T2 = b11.T2.multiply(fone.T2).mod(paillier.nsquare);
         b12.PUB = pub;
         
         
         
         
           SSBA SK11 = new SSBA(a11 ,paillier);
   //      long T1 = System.currentTimeMillis(); 
        SK11.StepOne();
         long T5 = System.currentTimeMillis(); 
        SK11.StepTwo ();
      
        long T6 = System.currentTimeMillis(); 
        SK11.StepThree();
         TB =TB + T6-T5+ SK11.TB;
         CCC = CCC + SK11.CCC;
         a13 = SK11.FIN;
         sa = SK11.FINS;
         
              SSBA SK12 = new SSBA(b12 ,paillier);
   //      long T1 = System.currentTimeMillis(); 
        SK12.StepOne();
         long T7 = System.currentTimeMillis(); 
        SK12.StepTwo ();
      
        long T8 = System.currentTimeMillis(); 
        SK12.StepThree();
         
        TB = TB + T8 - T7+ SK12.TB;
        CCC = CCC + SK12.CCC;
         b13 = SK12.FIN;      
         sb = SK12.FINS;
         
         
         
         
//////////////////////////////////////////////////////////////////////////////////////////////
         TWO = BigInteger.valueOf(2);   
           CipherPub []  Ba1 = new CipherPub[m];
           CipherPub []  Bb1 = new CipherPub[m];
           CipherPub []  q = new CipherPub[m];
           CipherPub []  r = new CipherPub[m];
           CipherPub []  App = new CipherPub[m];
        SBD SK15 = new SBD (a13,m, paillier);
        
        q = SK15.StepOne();
        TB = TB +SK15.TB;
        CCC = CCC +SK15.CCC;
        
        for(int iii = 0; iii < m; iii++)
        {
            App[iii] =  paillier.Encryption(ZERO, pub);
        }
        
        
        for (int iii = 0; iii < m; iii++)
        {
          for (int jjj = m-1; jjj > 0; jjj--)
          {
              
              App[jjj].T1 = App[jjj-1].T1.multiply(EONE);
              App[jjj].T2 = App[jjj-1].T2.multiply(EONE);
          }
        
              App[0].T1 = q[m-1].T1.multiply(EONE);
              App[0].T2 = q[m-1].T2.multiply(EONE);
          
                 for (int jjj = m-1; jjj > 0; jjj--)
          {
              
              q[jjj].T1 = q[jjj-1].T1.multiply(EONE);
              q[jjj].T2 = q[jjj-1].T2.multiply(EONE);
          }  
        
               
               AKKK.T1 = EZERO.T1.multiply(EZERO.T1);
               AKKK.T2 = EZERO.T2.multiply(EZERO.T2);        
                   
               
          for(int i = m-1; i >  0; i--)
          
         {    
                AKKK.T1 = AKKK.T1.multiply(App[i].T1);
                AKKK.T2 = AKKK.T2.multiply(App[i].T2);
             
                AKKK.T1 =   AKKK.T1.modPow(TWO, paillier.nsquare);
                AKKK.T2 =   AKKK.T2.modPow(TWO, paillier.nsquare);
  
         }    
               AKKK.T1 = AKKK.T1.multiply(App[0].T1);
               AKKK.T2 = AKKK.T2.multiply(App[0].T2); 
               AKKK.PUB = pub;
        
        //                      System.out.println("AKKK=="+   AKKK);       
         //   System.out.println("B12=="+   b12); 
               
        //       System.out.println("AKKK=="+   paillier.SDecryption(AKKK));       
       //     System.out.println("B12=="+   paillier.SDecryption(b12)); 
         SLT SK18 = new SLT (AKKK,b13,pub,paillier);
          
          SK18.StepOne();
          long T11 = System.currentTimeMillis();
          SK18.StepTwo();
          long T12 = System.currentTimeMillis();
          SK18.StepThree();        
          
          
          TB = TB + T12- T11 +SK18.TB;
          CCC =CCC + SK18.CCC;
          q01 = SK18.FIN;
                 
          q[0].T1 = EEone.T1.multiply((q01.T1.modPow(a1, paillier.nsquare)));
          q[0].T2 = EEone.T2.multiply((q01.T2.modPow(a1, paillier.nsquare)));
          q[0].PUB =pub;
          
          XM.T1 =  b13.T1.modPow(a1, paillier.nsquare);
          XM.T2 =  b13.T2.modPow(a1, paillier.nsquare);
          XM.PUB = pub;
          
          
         
        
            RSM SK33 = new RSM(XM, q[0],paillier);
       
              SK33.StepOne();
         
        SK33.StepTwo1();
        long T13 = System.currentTimeMillis();
        SK33.StepTwo2();
        long T14 = System.currentTimeMillis();
        SK33.StepThree();
              
        TB = TB + T14-T13;
        CCC =CCC + SK33.CCC;
        BB = SK33.FIN;
        
        AKKK.T1 = AKKK.T1.multiply(BB.T1).mod(paillier.nsquare);
        AKKK.T2 = AKKK.T2.multiply(BB.T2).mod(paillier.nsquare);    
        
        
               SBD SK115 = new SBD (AKKK,m, paillier);
       
        App = SK115.StepOne(); 
        
        
        TB = TB + SK115.TB;
        CCC = CCC + SK115.CCC;
        
        }
        
                 
               AKKK.T1 = EZERO.T1.multiply(EZERO.T1);
               AKKK.T2 = EZERO.T2.multiply(EZERO.T2);    
               Q.T1 = EZERO.T1.multiply(EZERO.T1);
               Q.T2 = EZERO.T2.multiply(EZERO.T2);   
               R.T1 = EZERO.T1.multiply(EZERO.T1);
               R.T2 = EZERO.T2.multiply(EZERO.T2);
               Q.PUB = pub;
               R.PUB = pub;
               
         for(int i = m-1; i >  0; i--)
          
         {    
             
                AKKK.T1 = AKKK.T1.multiply(App[i].T1);
                AKKK.T2 = AKKK.T2.multiply(App[i].T1);
             
                AKKK.T1 =   AKKK.T1.modPow(TWO, paillier.nsquare);
                AKKK.T2 =   AKKK.T2.modPow(TWO, paillier.nsquare);
                

             
 
           
         }    
               R.T1 = AKKK.T1.multiply(App[0].T1);
               R.T2 = AKKK.T2.multiply(App[0].T2);   
               
               
              AKKK.T1 = EZERO.T1.multiply(EZERO.T1);
              AKKK.T2 = EZERO.T2.multiply(EZERO.T2);  
               
                      for(int i = m-1; i >  0; i--)
          
         {    
             
                AKKK.T1 = AKKK.T1.multiply(q[i].T1);
                AKKK.T2 = AKKK.T2.multiply(q[i].T2);
             
                AKKK.T1 =   AKKK.T1.modPow(TWO, paillier.nsquare);
                AKKK.T2 =   AKKK.T2.modPow(TWO, paillier.nsquare);
                

             
 
           
         }    
               Q.T1 = AKKK.T1.multiply(q[0].T1);
               Q.T2 = AKKK.T2.multiply(q[0].T2);     
                             Q.PUB = pub;
               R.PUB = pub;
            
               
               nsa.T1 =  (sa.T1.modPow(TWO, paillier.nsquare)).multiply(EEone.T1.modPow(a1, paillier.nsquare));
               nsa.T2 =  (sa.T2.modPow(TWO, paillier.nsquare)).multiply(EEone.T2.modPow(a1, paillier.nsquare));
               nsb.T1 =  (sa.T1.modPow(TWO, paillier.nsquare)).multiply(EEone.T1.modPow(a1, paillier.nsquare));
               nsb.T2 =  (sa.T2.modPow(TWO, paillier.nsquare)).multiply(EEone.T2.modPow(a1, paillier.nsquare));
               nsa.PUB = pub;
               nsb.PUB = pub;
               
                      RSM SK19  = new RSM(nsa,nsb, pub, paillier);
      
       SK19.StepOne();
       SK19.StepTwo1();
       long T16 = System.currentTimeMillis();
       SK19.StepTwo2();
       long T17 = System.currentTimeMillis();
       SK19.StepThree();
      TB = TB + T17 - T16;
      CCC = CCC +SK19.CCC;
       K1 = SK19.FIN;
 
       
      RSM SK20  = new RSM(R,nsa, pub, paillier);
      
       SK20.StepOne();
       SK20.StepTwo1();
       long T18 = System.currentTimeMillis();
       SK20.StepTwo2();
       long T19 = System.currentTimeMillis();
       SK20.StepThree();
      TB = TB +T19-T18;
      CCC = CCC + SK20.CCC;
       FR = SK20.FIN;
       
       RSM SK21  = new RSM(Q,K1, pub, paillier);
      
       SK21.StepOne();
       SK21.StepTwo1();
       long T20 = System.currentTimeMillis();
       SK21.StepTwo2();
       long T21 = System.currentTimeMillis();
       SK21.StepThree();
      
       TB = TB + T21-T20;
       CCC = CCC +SK21.CCC;
       FQ = SK21.FIN;              
               
        }
    
}
