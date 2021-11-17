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
public class SGCD {
   
         public CipherPub  a = new CipherPub();
         public CipherPub  Xa = new CipherPub();
         public CipherPub  Xb = new CipherPub();
         public CipherPub  aa = new CipherPub();
       public CipherPub  b = new CipherPub();
       public CipherPub  bb = new CipherPub();
       public CipherPub  u1 = new CipherPub();
       public CipherPub  u2 = new CipherPub();
       public CipherPub  u3 = new CipherPub();
       public CipherPub  u4 = new CipherPub();
       public CipherPub  f1 = new CipherPub();
       public CipherPub  f2 = new CipherPub();
       public CipherPub  FIN = new CipherPub();
       public CipherPub  EEone = new CipherPub();
       public int  m = 0;
       public BigInteger pub = BigInteger.ZERO;
       public PaillierT paillier = null;
       public BigInteger RR1= BigInteger.ZERO;
       public BigInteger a1 = BigInteger.ZERO;
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger ZERO= BigInteger.ZERO;
       public BigInteger TWO = BigInteger.ZERO; 
       public CipherPub EZERO = new CipherPub();
      public CipherPub EEONE = new CipherPub();
               public long TA =  0;
        public long TB =  0;
        public int CCC =  0;
       
       public SGCD(CipherPub  _VA, CipherPub _VB, BigInteger _pub, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
    pub = _pub;
   }  
    
                  public SGCD(CipherPub  _VA, CipherPub _VB, int _m, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
    pub = paillier.Hsigma;
    m = _m;
   }  
           public SGCD(CipherPub  _VA, CipherPub _VB, BigInteger _pub,int _m, PaillierT _paillier) {
    a  =  _VA;
    b  =  _VB;
    paillier=_paillier;
    pub = _pub;
    m = _m;
   }  
       
       
       
     public void StepOne (){
         
          a1= paillier.n.subtract(EONE);
         
           SMMS SK30 = new SMMS (a,b,paillier.Hsigma,paillier);
          
          SK30.StepOne();
         TB = TB + SK30.TB;
         CCC = CCC + SK30.CCC;
         
          
  //        System.out.println("MAX=="+   paillier.SDecryption(SK30.FINMX));
     //     System.out.println("MIN=="+   paillier.SDecryption(SK30.FINMI));
          aa = SK30.FINA;
          bb = SK30.FINI;
            CipherPub [] Q = new CipherPub[m];
            CipherPub [] R = new CipherPub[m+1];
            CipherPub [] U = new CipherPub[m+1];
            CipherPub [] F = new CipherPub[m];
            CipherPub [] FINV = new CipherPub[m];
            CipherPub [] C = new CipherPub[m];
            CipherPub [] UINv = new CipherPub[m+1];
            CipherPub F1 = new CipherPub();
            CipherPub F2 = new CipherPub();
            
            EZERO = paillier.Encryption(ZERO, pub);
            EEONE =  paillier.Encryption(EONE, pub);
                    
            U[0] = paillier.Encryption(ZERO, pub);
    //       System.out.println("U[0] orignial=="+   paillier.SDecryption(U[0]));
     //      System.out.println("EEONE orignial=="+   paillier.SDecryption(EEONE));
     //       System.out.println("a1 orignial=="+   a1);
     //       System.out.println("paillier.nsquare orignial=="+   paillier.nsquare);
         
            
            
            
            
            
            UINv[0] = paillier.Encryption(EONE, pub);
            
            

         //   UINv[0].PUB = pub;
             R[0] = paillier.Encryption(EONE, pub);
         for (int jjj = 0; jjj < m; jjj++ )
         {
                Sdiv SK20 = new Sdiv (aa,bb,pub,10,paillier);
                SK20.StepOne();
                TB = TB + SK20.TB;
                CCC = CCC + SK20.CCC;
                
                Q[jjj] = SK20.FQ;
                R[jjj+1] = SK20.FR;       
        //        System.out.println("==========jjj==========="+jjj);
       //         System.out.println("==========aaa==========="+paillier.SDecryption(aa));
       //         System.out.println("==========bbb==========="+paillier.SDecryption(bb));
        //        System.out.println("==========Q==========="+paillier.SDecryption(Q[jjj]));
         //       System.out.println("==========R==========="+paillier.SDecryption(R[jjj]));
                
               if (jjj==0){
                   
                   R[jjj].T1 = Q[jjj].T1.multiply(EZERO.T1).mod(paillier.nsquare);
                   R[jjj].T2 = Q[jjj].T2.multiply(EZERO.T1).mod(paillier.nsquare);        
                           }
                
                aa = bb;
                bb = R[jjj+1];
                
                
                 if (jjj==0){
                      SEQ SK19 = new SEQ (R[jjj],EZERO,pub,paillier);
          
                 SK19.StepOne();
                TB = TB + SK19.TB;
                CCC = CCC + SK19.CCC;
                
                 U[jjj] = SK19.FIN;
             //    System.out.println("==========U0==========="+paillier.SDecryption(U[jjj]));
                UINv[0].T1 = EEONE.T1.multiply(U[0].T1.modPow(a1, paillier.nsquare)).mod(paillier.nsquare);
                UINv[0].T2 = EEONE.T2.multiply(U[0].T2.modPow(a1, paillier.nsquare)).mod(paillier.nsquare);
                UINv[0].PUB = pub;
            //     System.out.println("==========UINV==========="+paillier.SDecryption( UINv[0]));    
                 }
                
                
                
                 SEQ SK19 = new SEQ (R[jjj+1],EZERO,pub,paillier);
          
                 SK19.StepOne();
                 
                 
                 TB =TB+SK19.TB;
                 CCC = CCC + SK19.CCC;
                
                 U[jjj+1] = SK19.FIN;
         //    System.out.println("==========U==========="+paillier.SDecryption(U[jjj]));
                 
              UINv[jjj+1] = paillier.Encryption(ZERO, pub);   
                 
            UINv[jjj+1].T1 = EEONE.T1.multiply(U[jjj+1].T1.modPow(a1, paillier.nsquare)).mod(paillier.nsquare);
            UINv[jjj+1].T2 = EEONE.T2.multiply(U[jjj+1].T2.modPow(a1, paillier.nsquare)).mod(paillier.nsquare);
              UINv[jjj+1].PUB = pub;    
          //     System.out.println("==========UINV==========="+paillier.SDecryption( UINv[jjj+1]));  
                 RSM SK33 = new RSM(U[jjj], UINv[jjj+1],paillier);
       
                 SK33.StepOne();
         
                 SK33.StepTwo1();
                 
                  long T1 = System.currentTimeMillis(); 
                 SK33.StepTwo2();
         long T2= System.currentTimeMillis(); 
         
                 SK33.StepThree();
              
                 TB = TB + T2-T1;
                 CCC = CCC + SK33.CCC;
                 
                  F1 = SK33.FIN;
                  
                RSM SK332 = new RSM(U[jjj+1], UINv[jjj],paillier);
       
                 SK332.StepOne();
         
                 SK332.StepTwo1();
                  long T3 = System.currentTimeMillis(); 
                 SK332.StepTwo2();
         long T4 = System.currentTimeMillis(); 
                 SK332.StepThree();
              TB =TB + T4-T3;
              CCC = CCC + SK332.CCC;
                  F2 = SK332.FIN;
          
          //      System.out.println("==========F1==========="+paillier.SDecryption( F1));  
        //        System.out.println("==========F2==========="+paillier.SDecryption( F2)); 
               F[jjj]   = paillier.Encryption(ZERO, pub); 
            F[jjj].T1 = F1.T1.multiply(F2.T1).mod(paillier.nsquare);
            F[jjj].T2 = F1.T2.multiply(F2.T2).mod(paillier.nsquare);
            F[jjj].PUB = pub;
            
            FINV[jjj] = new CipherPub ();
            
       //     FINV[jjj]   = paillier.Encryption(ZERO, pub); 
            
            FINV[jjj].T1 = EEONE.T1.multiply(F[jjj].T1.modPow(a1, paillier.nsquare)).mod(paillier.nsquare);
            FINV[jjj].T2 = EEONE.T2.multiply(F[jjj].T2.modPow(a1, paillier.nsquare)).mod(paillier.nsquare);
            FINV[jjj].PUB = pub;
      //     System.out.println("==========FINV==========="+paillier.SDecryption( FINV[jjj]));  
            RSM SK333 = new RSM(R[jjj], F[jjj],paillier);
       
                 SK333.StepOne();
         
                 SK333.StepTwo1();
                 long T5 = System.currentTimeMillis(); 
                 SK333.StepTwo2();
        long T6 = System.currentTimeMillis(); 
                 SK333.StepThree();
              TB =TB +T6-T5;
              CCC = CCC + SK333.CCC;
            
                  C[jjj] = SK333.FIN;
        //          System.out.println("==========C=========="+paillier.SDecryption(C[jjj]));
         }
          
          
         FIN= paillier.Encryption(ZERO, pub);
    
         for (int jjj = 0; jjj < m; jjj++ )
         {
             FIN.T1 = FIN.T1.multiply(C[jjj].T1).mod(paillier.nsquare);
             FIN.T2 = FIN.T2.multiply(C[jjj].T2).mod(paillier.nsquare);
             FIN.PUB = pub;
              
         }
         
         
     }
    
}
