/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pufc;
import qk.CP;
import qk.CSP;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import com.carrotsearch.sizeof.RamUsageEstimator;

/**
 *
 * @author simon
 */
public class TestPaillier {
    
    public static void main(String[] args) {
     int a= 10;
      int b=5;
     int K=2;
       PaillierT       paillier = new PaillierT(1024, 64);              
       BigInteger B = new BigInteger(Integer.toString(  1));
        BigInteger C = new BigInteger(Integer.toString(  3));
        BigInteger B3 = new BigInteger(Integer.toString(  8));
        BigInteger AAAA[] =new BigInteger[2];
        BigInteger BBBB[] =new BigInteger[2];
        BigInteger temp_AA= new BigInteger(Integer.toString(3));
        BigInteger temp_BB= new BigInteger(Integer.toString(3));
        BigInteger SjS2 = new BigInteger(Integer.toString(6));
        BigInteger SjS1 = new BigInteger(Integer.toString(2));
        BigInteger cxdcs= new BigInteger(Integer.toString(2));
        CipherPub temp_b = new CipherPub();
        BigInteger S_TEMP,Q_TEMP;
        BigInteger S[]= new BigInteger[2] ;;
//        BigInteger MM=new BigInteger("2");
        BigInteger SDW =BigInteger.ONE;
        BigInteger SsDW = BigInteger.TWO.modInverse(paillier.n);
        CipherPub D1=paillier.Encryption(BigInteger.valueOf(99999),paillier.H[1]);
        CipherPub D2=paillier.Encryption(BigInteger.valueOf(600),paillier.H[1]);//a，b大于相等0，小于1
        System.out.println("d1密文的大小是多少"+RamUsageEstimator.sizeOf(D1));
        System.out.println("d2密文的大小是多少"+RamUsageEstimator.sizeOf(D2));



        List MIWEN_LIS  =new ArrayList<>();
        List<CipherPub> MIWEN_LIST  =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MIWEN_LIST.add(paillier.Encryption(BigInteger.valueOf(i),paillier.H[1]));
        }
        MIWEN_LIS.add(MIWEN_LIST);
        System.out.println("1个密文的大小是多少"+RamUsageEstimator.sizeOf(D1));
        System.out.println("十个密文的list是多少"+RamUsageEstimator.sizeOf(MIWEN_LIS));





        CipherPub AAAAAAA =new CipherPub();
        AAAAAAA.T1 =D1 .T1 .multiply(D2.T1);
        AAAAAAA.T2 =D1 .T2 .multiply(D2.T2);
        BigInteger Bb[] =new BigInteger[2];
         Bb[0] =paillier.PSDecryption1(AAAAAAA);
         Bb[1] =paillier.PSDecryption2(AAAAAAA);
        BigInteger SDASD   =paillier.DDecryption(Bb);
        CipherPub MMS =new CipherPub();
        CipherPub TEST1 =paillier.Encryption(BigInteger.valueOf(-5),paillier.H[1]);
        CipherPub TEST2 =paillier.Encryption(BigInteger.valueOf(100),paillier.H[1]);
        MMS.T1 = TEST1.T1.modPow(BigInteger.valueOf(-5),paillier.nsquare);
        MMS.T2 = TEST1.T2.modPow(BigInteger.valueOf(-5),paillier.nsquare);
        BigInteger SsSDSDSDSDSDSD = paillier.SDecryption(MMS);

        CipherPub SDADASDSADS =paillier.Encryption(BigInteger.valueOf(50),paillier.H[0]);
        CipherPub jfisdjff =paillier.Refreash(SDADASDSADS);
        MMS.T1 = D2.T1.modPow(D1.T1,paillier.nsquare);
        MMS.T2 = D2.T2.modPow(D1.T2,paillier.nsquare);
        List <CipherPub> AAAASDSDS =new ArrayList<>();
        CipherPub AAAASDSDSD = paillier.Encryption(BigInteger.ZERO,paillier.H[1]);
        AAAASDSDS.add(AAAASDSDSD);
        int aSDS =AAAASDSDS.indexOf(AAAASDSDSD);
        String s = "12345678987654321";
        BigInteger aSD = new BigInteger(s);




        BigInteger aaaa = BigInteger.valueOf(325);
        BigInteger bbbb = BigInteger.valueOf(145);
        BigInteger KEYWORD[] = new BigInteger[4];
        CipherPub CXGJC_KEY[] = new CipherPub[4];
        KEYWORD[0] = BigInteger.valueOf(4);
        KEYWORD[1] = BigInteger.valueOf(11);
        KEYWORD[2] = BigInteger.valueOf(20);
        KEYWORD[3] = BigInteger.valueOf(22);
//        KEYWORD[4] = BigInteger.valueOf(77);
        BigInteger re = BigInteger.ZERO;


        for (int i = 0; i < 10; i++) {
            BigInteger result = BigInteger.ZERO;
            BigInteger x = BigInteger.valueOf(2).pow(40).multiply(aaaa);
            BigInteger y = bbbb;
            result = x.add(y);
            re = re.multiply(BigInteger.valueOf(2).pow(80)).add(result);
        }
        long gjzgs = System.currentTimeMillis();
        CipherPub CXD = paillier.Encryption(re, paillier.H[0]);
//            System.out.println("原查询点X为" + aa + "原查询点y为" + bb + "\n" + "查询点处理后为" + re + "查询点处理加密后为" + CXD);
        for (int i = 0; i < KEYWORD.length; i++) {
            CXGJC_KEY[i] = paillier.Encryption(KEYWORD[i], paillier.H[0]);
//                System.out.println("原查询关键字为" + library.get(KEYWORD[i].intValue()) + "查询关键字编号" + KEYWORD[i] + "\n" + "查询关键字字典编号加密后" + CXGJC_KEY[i]);
        }
        long gjzgs_end = System.currentTimeMillis();
            System.out.println("/新方法关键字个数总耗时为：" + (gjzgs_end - gjzgs) + "毫秒");















        CipherPub CXD_X = new CipherPub();
        CipherPub CXD_Y = new CipherPub();
        BigInteger aaa = BigInteger.valueOf(325);
        BigInteger bbb = BigInteger.valueOf(145);
        BigInteger KEYWORD1[] = new BigInteger[5];
        CipherPub CXGJC_KEY2[] = new CipherPub[5];
        KEYWORD1[0] = BigInteger.valueOf(33);
        KEYWORD1[1] = BigInteger.valueOf(66);
        KEYWORD1[2] = BigInteger.valueOf(77);
        KEYWORD1[3] = BigInteger.valueOf(79);
        KEYWORD1[4] = BigInteger.valueOf(2);

        long gjzgs1 = System.currentTimeMillis();
        CXD_X = paillier.Encryption(aaa, paillier.H[1]);
        CXD_Y = paillier.Encryption(bbb, paillier.H[1]);
//            System.out.println("原查询点X为" + aa + "原查询点y为" + bb + "\n" + "查询点加密后x为" + CXD_X + "查询点加密后y为" + CXD_Y);
        for (int i = 0; i < KEYWORD1.length; i++) {
            CXGJC_KEY2[i] = paillier.Encryption(KEYWORD1[i], paillier.H[1]);
//                System.out.println("原查询关键字为" + library.get(KEYWORD[i].intValue()) + "查询关键字编号" + KEYWORD[i] + "\n" + "查询关键字字典编号加密后" + CXGJC_KEY[i]);
        }
        long gjzgs_end2 = System.currentTimeMillis();
        System.out.println("/原方法关键字个数总耗时为：" + (gjzgs_end2 - gjzgs1) + "毫秒");


    

    
       
}
}