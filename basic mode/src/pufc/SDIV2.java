package pufc;

import java.math.BigInteger;
import java.util.Random;

public class SDIV2 {
    private Random random = new Random();
    PaillierT paillierT;
    public CipherPub Sdiv(CipherPub A,CipherPub B,PaillierT PT){
        paillierT =PT;
        CipherPub EA =new CipherPub();
        CipherPub EB = new CipherPub();
        CipherPub Er3 ;
        CipherPub EH ;
        CipherPub RESULT = new CipherPub() ;
        BigInteger TEMP1[] = new  BigInteger[2];
        BigInteger TEMP2[] = new  BigInteger[2];
        BigInteger r1 = BigInteger.valueOf(random.nextInt(1000000));
        BigInteger r2 = BigInteger.valueOf(random.nextInt(1000000));
        BigInteger r3 = BigInteger.valueOf(random.nextInt(r1.intValue()-0)+0);
        Er3 =paillierT.Encryption(r3,paillierT.H[1]);


        EA.T1= A.T1.modPow(r1,paillierT.nsquare).multiply(B.T1.modPow(r2.multiply(r1),paillierT.nsquare)).multiply(Er3.T1);
        EA.T2= A.T2.modPow(r1,paillierT.nsquare).multiply(B.T2.modPow(r2.multiply(r1),paillierT.nsquare)).multiply(Er3.T2);


        EB.T1= B.T1.modPow(r1,paillierT.nsquare);
        EB.T2= B.T2.modPow(r1,paillierT.nsquare);



        TEMP1[0]=paillierT.PSDecryption1(EA);
        TEMP2[0]=paillierT.PSDecryption1(EB);
        TEMP1[1]=paillierT.PSDecryption2(EA);
        TEMP2[1]=paillierT.PSDecryption2(EB);

        EH=paillierT.Encryption(paillierT.DDecryption(TEMP1).divide(paillierT.DDecryption(TEMP2)),paillierT.H[1]);
        RESULT.T1 = EH.T1.multiply(paillierT.Encryption(paillierT.n.subtract(r2),paillierT.H[0]).T1);
        RESULT.T2 = EH.T2.multiply(paillierT.Encryption(paillierT.n.subtract(r2),paillierT.H[0]).T2);






        return  RESULT;
    }
}
