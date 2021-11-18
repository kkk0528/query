package qk;

import pufc.CipherPub;

import pufc.PaillierT;

import javax.print.DocFlavor;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CSP {

    private PaillierT paillierT;
    private CipherPub[] S = new CipherPub[2];
    private BigInteger[] SLT_1 = new BigInteger[2];
    private BigInteger[] SLT_2 = new BigInteger[2];

    public CSP(PaillierT pt) {
        paillierT = pt;
    }
//    CSP for partial decryption
    public BigInteger PSDEC_PUB2(CipherPub F) {
        return  paillierT.PSDecryption2(F);
    }
//    CSP decrypts all
    public BigInteger Quanbu_jiemi(BigInteger sj[]){
        BigInteger  Min_wen = paillierT.DDecryption(sj);
        return  Min_wen;
    }
//    CSP is divided into  r1id|r2x|r3y.....
    public  List<List<BigInteger>> Fenge(BigInteger A){
        String Minwen_2jinzhi = A.toString(2);
        int bitNum=1000;
        while(Minwen_2jinzhi.length() < bitNum) {
            Minwen_2jinzhi = "0" + Minwen_2jinzhi;
        }
        List<BigInteger> id =new ArrayList<>();
        List<Integer> x =new ArrayList<>();
        List<Integer> y =new ArrayList<>();
        for (int i = 0; i < Minwen_2jinzhi.length()/100; i++) {
            String idxy =Minwen_2jinzhi.substring(100*i,100*(i+1));
            id.add( new BigInteger(idxy.substring(0,40), 2));
            x.add(Integer.parseInt(idxy.substring(40,70),2) );
            y.add(Integer.parseInt(idxy.substring(70,100),2) );
        }
        List<List<BigInteger>> IDXY_fenge_jihe =new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            List<BigInteger> minwen_fenge_sjs =new ArrayList<>();
            minwen_fenge_sjs.add(id.get(i));
            minwen_fenge_sjs.add(BigInteger.valueOf(x.get(i)));
            minwen_fenge_sjs.add(BigInteger.valueOf(y.get(i)));
            IDXY_fenge_jihe.add(minwen_fenge_sjs);
        }

        return IDXY_fenge_jihe;
    }
//    CSP is divided into  xy|xy.....
    public  List<List<BigInteger>> Fenge_xyxy(BigInteger A){
        String Minwen_2jinzhi = A.toString(2);
        int bitNum = 800;
        int a =Minwen_2jinzhi.length();
        while(Minwen_2jinzhi.length() < bitNum) {
            Minwen_2jinzhi = "0" + Minwen_2jinzhi;
        }

        List<BigInteger> x =new ArrayList<>();
        List<BigInteger> y =new ArrayList<>();
        for (int i = 0; i < Minwen_2jinzhi.length()/80; i++) {
            String idxy =Minwen_2jinzhi.substring(80*i,80*(i+1));
            x.add(new BigInteger(idxy.substring(0,40), 2) );
            y.add(new BigInteger(idxy.substring(40,80), 2));
        }
        List<List<BigInteger>> XYXY_fenge_jihe =new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            List<BigInteger> minwen_fenge_sjs =new ArrayList<>();
            minwen_fenge_sjs.add(x.get(i));
            minwen_fenge_sjs.add(y.get(i));
            XYXY_fenge_jihe.add(minwen_fenge_sjs);
        }

        return XYXY_fenge_jihe;
    }

//    CSP ciphertext size comparison step 2
    public Integer slt_two(Object A[]){
        SLT_1[0] = PSDEC_PUB2((CipherPub) A[2]);
        SLT_1[1] = (BigInteger) A[0];
        SLT_2[0] = PSDEC_PUB2((CipherPub) A[3]);
        SLT_2[1] = (BigInteger) A[1];
       int res =  Quanbu_jiemi(SLT_1).compareTo(Quanbu_jiemi(SLT_2));
        return res;
    }

}
