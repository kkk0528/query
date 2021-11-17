package pufc;

import com.sun.source.tree.ReturnTree;

import java.math.BigInteger;
import java.util.Random;

public class SMD{
    public PaillierT paillierT = null;
    private Random random = new Random();

    public CipherPub SMD(CipherPub A,CipherPub B,PaillierT PT){
        paillierT = PT;
        BigInteger SJS1 = BigInteger.valueOf(1);
        BigInteger SJS2 =BigInteger.valueOf(2);
        BigInteger SJS12 =SJS2.multiply(SJS1);


        CipherPub A_SJS1 = new CipherPub();
        CipherPub B_SJS2 = new CipherPub();
        CipherPub AB_SJ =new CipherPub();
        CipherPub xr1 = new CipherPub();
        CipherPub yr2 =new CipherPub();
        CipherPub r1r2 =paillierT.Encryption(SJS12,paillierT.H[1]);
        CipherPub XY = new CipherPub();
        BigInteger A_SJS[] =new BigInteger[2];
        BigInteger B_SJS[] =new BigInteger[2];

        CipherPub sjs1 = paillierT.Encryption(SJS1,paillierT.H[1]);
        CipherPub sjs2 = paillierT.Encryption(SJS2,paillierT.H[1]);


       A_SJS1.T1 = A.T1.multiply(sjs1.T1);
        A_SJS1.T2 = A.T2.multiply(sjs1.T2);
        A_SJS1.PUB = A.PUB.multiply(sjs1.PUB);
        B_SJS2.T1 = B.T1.multiply(sjs2.T1);
        B_SJS2.T2 = B.T2.multiply(sjs2.T2);
        B_SJS2.PUB = B.PUB.multiply(sjs2.PUB);

        xr1.T1 = A.T1.modPow(SJS2,paillierT.nsquare);
        xr1.T2 = A.T2.modPow(SJS2,paillierT.nsquare);
        xr1.PUB = A.PUB.modPow(SJS2,paillierT.nsquare);
        yr2.T1 = B.T1.modPow(SJS1,paillierT.nsquare);
        yr2.T2 = B.T2.modPow(SJS1,paillierT.nsquare);
        yr2.PUB = B.PUB.modPow(SJS1,paillierT.nsquare);
        BigInteger Fxr11 = paillierT.SDecryption(xr1);
        BigInteger Fyr22 = paillierT.SDecryption(yr2);


        A_SJS[0] =paillierT.PSDecryption1(A_SJS1);
        A_SJS[1] =paillierT.PSDecryption2(A_SJS1);
        B_SJS[0] =paillierT.PSDecryption1(B_SJS2);
        B_SJS[1] =paillierT.PSDecryption2(B_SJS2);

        AB_SJ=paillierT.Encryption(paillierT.DDecryption(A_SJS).multiply(paillierT.DDecryption(B_SJS)),paillierT.H[1]);
        BigInteger ADDSDS = paillierT.SDecryption(AB_SJ);
        XY.T1 = AB_SJ.T1.multiply(xr1.T1.modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare)).multiply(yr2.T1.modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare)).multiply(r1r2.T1.modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare));
        XY.T2 = AB_SJ.T2.multiply(xr1.T2.modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare)).multiply(yr2.T2.modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare)).multiply(r1r2.T2.modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare));
        XY.PUB = AB_SJ.PUB.multiply(xr1.PUB.modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare)).multiply(yr2.PUB.modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare)).multiply(r1r2.PUB.modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare));




//        BigInteger ASDS= paillierT.SDecryption(XY);



            return XY;
    }
}
