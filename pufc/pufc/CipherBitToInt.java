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
public class CipherBitToInt {
    
       public int  m = 0;
       public CipherPub []  a = new CipherPub [m];
       public CipherPub  b = new CipherPub();
       public CipherPub  a11 = new CipherPub();
       public CipherPub  b11 = new CipherPub();
       public CipherPub  a12 = new CipherPub();
       public CipherPub  b12 = new CipherPub();
       public CipherPub  f1 = new CipherPub();
       public CipherPub  u2 = new CipherPub();
       public CipherPub  u3 = new CipherPub();
       public CipherPub  u4 = new CipherPub();
       public CipherPub  fzero = new CipherPub();
       public CipherPub  fone = new CipherPub();
       public CipherPub  FIN = new CipherPub();
       public CipherPub  EEone = new CipherPub();
       public CipherPub  Q = new CipherPub();
       public CipherPub  R = new CipherPub();
       
       public BigInteger pub = BigInteger.ZERO;
       public PaillierT  paillier = null;
       public BigInteger RR1= BigInteger.ZERO;
       public BigInteger a1 = BigInteger.ZERO;
       public BigInteger EONE= BigInteger.ONE;
       public BigInteger ZERO= BigInteger.ZERO;
       public CipherPub  EZERO= new CipherPub();
       public BigInteger TWO = BigInteger.ZERO;
    
   public CipherBitToInt(CipherPub []  _VA, int _m, BigInteger _pub, PaillierT _paillier) {
    m  =  _m;
    a  =  _VA;
    paillier=_paillier;
    pub = _pub;
   }  
    
}
