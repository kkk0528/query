package qk;

import pufc.*;

import java.math.BigInteger;
import java.util.*;
import java.util.Random;

public class CP {
    public List<List<CipherPub>> Point_MIWEN= new ArrayList();
    private   Map<CipherPub, Integer> KeyWord =new HashMap<>();
    public CipherPub CXD;
    public CipherPub CXD_Keyword[];
    private CipherPub SLT = new CipherPub();
    private Random random = new Random();
    private   Integer r[] =  new Integer[30];
    private  BigInteger r_ts =BigInteger.valueOf(random.nextInt( 1024+ 1) +0) ;
    private BigInteger slt_r2 =  BigInteger.ONE;
    private BigInteger slt_r1 = new BigInteger(1000,random);
    private Object SLT_TEMP1[] =new Object[4];
    private BigInteger SjS1 = new BigInteger(String.valueOf(random.nextInt(100)));
    private BigInteger SjS2= new BigInteger(String.valueOf(random.nextInt(100)));
    private  BigInteger TEMP_PIPEI= BigInteger.ZERO;
    public PaillierT paillierT;
    public CP (List<List<CipherPub>> Points_PAIXUJI,CipherPub cxd,CipherPub CXD_keyword[],PaillierT pt){
        Point_MIWEN = Points_PAIXUJI;
        CXD = cxd;
        CXD_Keyword =CXD_keyword;
         paillierT = pt;

    }
    //Creates a random number of idxy splices
    public void Create_sjs(){
        for (int i = 0; i < r.length; i++) {
            r[i] = random.nextInt( 1024*1024+ 1) +0;
        }

    }
//    Create random number of query point splicing
    public CipherPub Create_cxdsjs(){

        CipherPub CXD_SJS=new CipherPub();
        BigInteger r_temp[] = new BigInteger[10];
        BigInteger rr =BigInteger.ZERO;

        for (int i = 0; i < 10; i++) {
            BigInteger r1_temp=  BigInteger.valueOf(2).pow(50).multiply(BigInteger.valueOf(r[1+3*i]));
            BigInteger r2_temp=   BigInteger.valueOf(2).pow(10).multiply(BigInteger.valueOf(r[2+3*i]));
            r_temp[i] = r1_temp.add(r2_temp).add(r_ts.multiply(BigInteger.valueOf(2).pow(30))).add(r_ts.multiply(BigInteger.valueOf(2).pow(70)));
            rr=rr.multiply(BigInteger.valueOf(2).pow(80)).add(r_temp[i]);
        }

        CipherPub R= paillierT.Encryption(rr,paillierT.H[0]);
        CXD_SJS.T1 = CXD.T1.multiply(R.T1);
        CXD_SJS.T2 = CXD.T2.multiply(R.T2);
        return CXD_SJS;

    }
//cp partial decryption
    public BigInteger PSDEC_PUB1(CipherPub e){
        return paillierT.PSDecryption1(e);
    }
    //Add random number for idxy
    public CipherPub  Add_sjs(CipherPub IDXYSCORE){

        CipherPub IDXYSCORE_SJS=new CipherPub();
        BigInteger r_temp[] = new BigInteger[10];
        BigInteger rr =BigInteger.ZERO;
        for (int i = 0; i < 10; i++) {
            BigInteger r1_temp=  BigInteger.valueOf(2).pow(80).multiply(BigInteger.valueOf(r[0+3*i]));
            BigInteger r2_temp=  BigInteger.valueOf(2).pow(40).multiply(BigInteger.valueOf(r[1+3*i]));
            BigInteger r3_temp=   BigInteger.valueOf(2).pow(10).multiply(BigInteger.valueOf(r[2+3*i]));
            r_temp[i] = r1_temp.add(r2_temp).add(r3_temp);
            rr=rr.multiply(BigInteger.valueOf(2).pow(100)).add(r_temp[i]);
        }
        CipherPub R= paillierT.Encryption(rr,paillierT.H[1]);
        IDXYSCORE_SJS.T1 = IDXYSCORE.T1.multiply(R.T1);
        IDXYSCORE_SJS.T2 = IDXYSCORE.T2.multiply(R.T2);

         return IDXYSCORE_SJS;
    }
//    Remove the difference returned by CSP and the random number of the difference square
public List<List <CipherPub>>  Delete_sjs(List<List <CipherPub>> A){//对于E(ID+r1)，E(平方差+sjs)去除随机数
        List<List <CipherPub>> zscore_one_pack  = new ArrayList<>();
        CipherPub R_ER_FANG = paillierT.Encryption((r_ts.multiply(BigInteger.valueOf(2).pow(30)).pow(2)).multiply(BigInteger.TWO).multiply(BigInteger.valueOf(-1)),paillierT.H[0]);
         CipherPub R = paillierT.Encryption(r_ts.multiply(BigInteger.valueOf(2).pow(31)),paillierT.H[0]);
        for (int i = 0; i < A.size() ; i++) {
            CipherPub XY_cha = new CipherPub();
            CipherPub XY_chaFANG = new CipherPub();
            List <CipherPub> zscore_one_pack_TEMP  = new ArrayList<>();
            CipherPub Rid = paillierT.Encryption(BigInteger.valueOf(2).pow(20).multiply(BigInteger.valueOf(r[0+3*i].intValue())).multiply(BigInteger.valueOf(-1)),paillierT.H[0]);
            A.get(i).get(0).T1 = A.get(i).get(0).T1.multiply(Rid.T1);
            A.get(i).get(0).T2 = A.get(i).get(0).T2.multiply(Rid.T2);
            zscore_one_pack_TEMP.add(A.get(i).get(0));//id


            //（A-C+B-D）
            XY_cha.T1 = A.get(i).get(1).T1.multiply(R.T1.modPow(BigInteger.valueOf(-1),paillierT.nsquare));
            XY_cha.T2 = A.get(i).get(1).T2.multiply(R.T2.modPow(BigInteger.valueOf(-1),paillierT.nsquare));


            XY_chaFANG.T1 = A.get(i).get(2).T1.multiply(R_ER_FANG.T1).multiply(XY_cha.T1.modPow(r_ts.multiply(BigInteger.valueOf(2).pow(31)),paillierT.nsquare).modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare));
            XY_chaFANG.T2 = A.get(i).get(2).T2.multiply(R_ER_FANG.T2).multiply(XY_cha.T2.modPow(r_ts.multiply(BigInteger.valueOf(2).pow(31)),paillierT.nsquare).modPow(paillierT.n.subtract(BigInteger.ONE),paillierT.nsquare));

            zscore_one_pack_TEMP.add(XY_chaFANG);//平方差
            zscore_one_pack.add(zscore_one_pack_TEMP);
        }
        return zscore_one_pack;
}
//    The first step of comparing the size of CP ciphertext
    public Object[] SLT_one(CipherPub A,CipherPub B){
        CipherPub  slt_CHA = paillierT.Encryption(slt_r1.subtract(slt_r2),paillierT.H[1]);
        SLT.T1 = A.T1.multiply(B.T1.modPow(BigInteger.valueOf(-1),paillierT.nsquare)).multiply(slt_CHA.T1);
        SLT.T2 = A.T2.multiply(B.T2.modPow(BigInteger.valueOf(-1),paillierT.nsquare)).multiply(slt_CHA.T2);
        SLT_TEMP1 [0] = PSDEC_PUB1(SLT);
        SLT_TEMP1 [1] = PSDEC_PUB1(slt_CHA);
        SLT_TEMP1 [2] = SLT;
        SLT_TEMP1 [3] = slt_CHA;
        return SLT_TEMP1;
    }
}
