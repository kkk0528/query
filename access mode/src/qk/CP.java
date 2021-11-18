package qk;

import pufc.*;

import java.math.BigInteger;
import java.util.*;
import java.util.Random;
import java.util.zip.ZipEntry;

public class CP {
    public List<List<List>> Point_MIWEN= new ArrayList();
    private int ZD_size;
    public CipherPub CXD;
    public CipherPub CXD_Keyword[];
    private CipherPub SLT = new CipherPub();
    private Random random = new Random();
    private   Integer r[] =  new Integer[30];
    private   Integer r2[] =  new Integer[6];
    private BigInteger rrr_temp[] = new BigInteger[6];
    private  BigInteger r_ts =BigInteger.valueOf(random.nextInt( 1024+ 1) +0) ;
    private BigInteger slt_r2 =  BigInteger.ONE;
    private BigInteger slt_r1 = new BigInteger(1000,random);
    private Object SLT_TEMP1[] =new Object[4];
    private BigInteger SjS1 = new BigInteger(String.valueOf(random.nextInt(100)));
    private BigInteger SjS2= new BigInteger(String.valueOf(random.nextInt(100)));
    private  BigInteger TEMP_PIPEI= BigInteger.ZERO;
    public  int t_size;
    public   List<List<List<List>>>Shuffle_index_zz = new ArrayList<>();
    private  BigInteger[] r_tong  = new BigInteger[100];
    public PaillierT paillierT;

    public CP (List<List<List>> Points_PAIXUJI,CipherPub cxd,CipherPub CXD_keyword[],PaillierT pt,int zd_size){
        Point_MIWEN = Points_PAIXUJI;
        CXD = cxd;
        CXD_Keyword =CXD_keyword;
         paillierT = pt;
        ZD_size =zd_size;
    }
    //Creates a random number of idxy splices
    public void Create_sjs(){
        for (int i = 0; i < r.length; i++) {
            r[i] = random.nextInt( 1024*1024+ 1) +0;
        }

    }
    //Creates a random number of keyword
    public void Create_sjs2(){
        for (int i = 0; i < r2.length; i++) {
            r2[i] = random.nextInt( 1024+ 1) +0;
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
    //Add random number for keyword
    public CipherPub  Add_sjs_keyword(CipherPub keyword){

        CipherPub keyword_SJS=new CipherPub();
        BigInteger r_temp=  BigInteger.valueOf(0);

        rrr_temp[0] = BigInteger.valueOf(2).pow(110).multiply(BigInteger.valueOf(r2[0]));
        rrr_temp[1] = BigInteger.valueOf(2).pow(90).multiply(BigInteger.valueOf(r2[1]));
        rrr_temp[2] = BigInteger.valueOf(2).pow(70).multiply(BigInteger.valueOf(r2[2]));
        rrr_temp[3] = BigInteger.valueOf(2).pow(50).multiply(BigInteger.valueOf(r2[3]));
        rrr_temp[4] = BigInteger.valueOf(2).pow(30).multiply(BigInteger.valueOf(r2[4]));
        rrr_temp[5] = BigInteger.valueOf(2).pow(10).multiply(BigInteger.valueOf(r2[5]));
        r_temp= rrr_temp[0].add(rrr_temp[1]).add(rrr_temp[2]).add(rrr_temp[3]).add(rrr_temp[4]).add(rrr_temp[5]);
        CipherPub R= paillierT.Encryption(r_temp,paillierT.H[1]);
        keyword_SJS.T1 = keyword.T1.multiply(R.T1);
        keyword_SJS.T2 = keyword.T2.multiply(R.T2);

        return keyword_SJS;
    }
    //    Remove the difference returned by CSP and the random number of the difference square
    public CipherPub[] Delete_KEYWORD_sjs(List<CipherPub> keyword) {
        CipherPub KEYWORD [] = new CipherPub[6];
        for (int i = 0; i < keyword.size(); i++) {
            CipherPub A =new CipherPub();
            A.T1 = keyword.get(i).T1.multiply(paillierT.Encryption(BigInteger.valueOf(r2[i]).multiply(BigInteger.TWO.pow(10)),paillierT.H[0]).T1.modPow(BigInteger.valueOf(-1),paillierT.nsquare));
            A.T2 = keyword.get(i).T2.multiply(paillierT.Encryption(BigInteger.valueOf(r2[i]).multiply(BigInteger.TWO.pow(10)),paillierT.H[0]).T2.modPow(BigInteger.valueOf(-1),paillierT.nsquare));
            KEYWORD[i] = A;
        }
        return KEYWORD;

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


            //（A-C+B-D）的平方
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
//    CP build bucket
    public   List<List<CipherPub>> JIAN_TONG (){

        List<List<List<List>>> Shuffle_index = new ArrayList<>();

         t_size =(int)Math.sqrt(ZD_size);
         TFIDF SS = new TFIDF();
         Shuffle_index = SS.splitList(Point_MIWEN, t_size);
        int yu =Point_MIWEN.size()%t_size;
        int permu[] =CP.permutation(Shuffle_index.size());
        for (int i = 0; i < permu.length; i++) {
            permu[i] = i;
        }
        List<Integer> PERMU_ORDER = new ArrayList<>();

            for (int j = 0; j < permu.length; j++) {
                if (permu[j]!=permu.length-1){
                    for (int i = permu[j]*t_size; i < (permu[j]+1)*t_size; i++) {
                        PERMU_ORDER.add(i);
                    }

                }
                else {
                    for (int i = permu[j]*t_size; i < permu[j]*t_size+yu; i++) {
                        PERMU_ORDER.add(i);
                    }
                }

            }


        for (int i = 0; i < Shuffle_index.size(); i++) {

            Shuffle_index_zz.add(i, Shuffle_index.get(permu[i]));
        }
        for (int i = 0; i < Shuffle_index_zz.size(); i++) {//10
            for (int j = 0; j < Shuffle_index_zz.get(i).size(); j++) {//8
                for (int k = 1; k < Shuffle_index_zz.get(i).get(j).size(); k++) {//3
                    for (int m = 0; m < 10; m++) {
                        for (int l = 0; l < ZD_size; l++) {
                            CP.swap((List<?>) Shuffle_index_zz.get(i).get(j).get(k).get(1),m*ZD_size+l,m*ZD_size+PERMU_ORDER.get(l));
                        }

                    }

                }

            }

        }
        List<List<CipherPub>>Z_tong = new ArrayList<>();
        List<CipherPub>tong_YZ = new ArrayList<>();
        for (int i = 0; i < Shuffle_index_zz.size(); i++) {
                    tong_YZ.add( (CipherPub)Shuffle_index_zz.get(i).get(0).get(0));
                    tong_YZ.add((CipherPub)Shuffle_index_zz.get(i).get(Shuffle_index_zz.get(i).size()-1).get(0));
            Z_tong.add(tong_YZ);
            tong_YZ = new ArrayList<>();
        }
        for (int i = 0; i < Z_tong.size(); i++) {
            r_tong[i] = BigInteger.valueOf(random.nextInt( 1024+ 1) +0);
        }
        for (int i = 0; i < Z_tong.size(); i++) {

                Z_tong.get(i).get(0).T1 = Z_tong.get(i).get(0).T1.multiply(paillierT.Encryption(r_tong[i],paillierT.H[1]).T1);
                Z_tong.get(i).get(0).T2 = Z_tong.get(i).get(0).T2.multiply(paillierT.Encryption(r_tong[i],paillierT.H[1]).T2);
                 Z_tong.get(i).get(1).T1 = Z_tong.get(i).get(1).T1.multiply(paillierT.Encryption(r_tong[i],paillierT.H[1]).T1);
                Z_tong.get(i).get(1).T2 = Z_tong.get(i).get(1).T2.multiply(paillierT.Encryption(r_tong[i],paillierT.H[1]).T2);

        }
        return Z_tong;
    }
    //CP adds random numbers to the upper and lower bounds of the bucket
    public  List<CipherPub[]> cxd_sjs_tong (){
        List<CipherPub[]> cxd_tong = new ArrayList<>();

        for (int i = 0; i <Shuffle_index_zz.size() ; i++) {
            CipherPub TEMP[] =new CipherPub[CXD_Keyword.length];
            for (int j = 0; j < CXD_Keyword.length; j++) {
                CipherPub TEMP1 = new CipherPub();
                TEMP1.T1 = CXD_Keyword[j].T1.multiply(paillierT.Encryption(r_tong[i],paillierT.H[0]).T1);
                TEMP1.T2 = CXD_Keyword[j].T2.multiply(paillierT.Encryption(r_tong[i],paillierT.H[0]).T2);
                TEMP[j] = TEMP1;
            }
            cxd_tong.add(TEMP);
        }
        return cxd_tong;
    }
    //Select disturbance sequence
    private static int[] permutation(int n){
        Random rand = new Random();
        int[] permutation = new int[n];
        for(int i = n; i >1; i--){
            int exch = rand.nextInt(i);
            int tempi = permutation[i-1];
            permutation[i-1] = permutation[exch] == 0 ? exch : permutation[exch];
            permutation[exch] = tempi == 0 ? i - 1 : tempi;
        }
        return permutation;
    }

    public static void swap(List<?> list, int i, int j) {
        // instead of using a raw type here, it's possible to capture
        // the wildcard but it will require a call to a supplementary
        // private method
        final List l = list;
        l.set(i, l.set(j, l.get(i)));

    }



}
