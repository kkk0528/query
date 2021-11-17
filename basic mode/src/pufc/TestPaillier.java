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
       Ciphertext [] Di = null;      
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
        Ciphertext temp_a = new Ciphertext();
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
        BigInteger ASDSDSDSADADAA = paillier.WDecryption(jfisdjff,paillier.X[0]);
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

        SLT BDX = new SLT(D1,D2 ,paillier);
        BDX.StepOne();
        BDX.StepTwo ();
        BDX.StepThree();
        BigInteger SSD =paillier.SDecryption(BDX.FIN);
        AAAAAAA.T1 = ( (MMS.T1)).multiply(D2.T1);
        AAAAAAA.T2 = ( (MMS.T2)).multiply(D2.T2);

//        AAAAAAA.PUB = D2.PUB;
        SLT SK1100 = new SLT(D1,D2 ,paillier);
        SK1100.StepOne();
        SK1100.StepTwo ();
        SK1100.StepThree();
       BigInteger SDADSADADADAF=  paillier.SDecryption(SK1100.FIN);

       SMD smd = new SMD();
       AAAAAAA = smd.SMD(D1,D2,paillier);
       BigInteger LIN = paillier.SDecryption(AAAAAAA);
        SDIV2 div = new SDIV2();
        CipherPub MOK;
        MOK = div.Sdiv(D1,D2,paillier);
        BigInteger LSDSIN = paillier.SDecryption(MOK);
        long start2=System.currentTimeMillis();
        CipherPub ASDSDSDSDSD= new CipherPub();
        ASDSDSDSDSD =paillier.Encryption(BigInteger.valueOf(25),paillier.H[1]);
        long end2=System.currentTimeMillis();
        System.out.println("加密一次新方法："+(end2-start2)+"毫秒");

        int a2 = 5;
       int a3 = 4;
       BigInteger CCC11= new BigInteger("1");
       BigInteger SJS = new BigInteger("2");
        SAD SK115 = new SAD(paillier.Encryption(CCC11,paillier.H[1]), paillier.Encryption(SJS,paillier.H[1]),paillier);
        SK115.StepOne();
        SK115.StepTwo ();
        SK115.StepThree();
        BigInteger Miwen_temp11[] = new BigInteger[2];
        Miwen_temp11[0] = paillier.PSDecryption1(SK115.FIN);
        Miwen_temp11[1] = paillier.PSDecryption2(SK115.FIN);
        System.out.println("222222"+paillier.DDecryption(Miwen_temp11));
        System.out.println("22222222222222"+paillier.SDecryption(SK115.FIN));


        
       
       BigInteger []  ccc =  new    BigInteger  [paillier.beta];
       
          BigInteger      ab = new BigInteger(Integer.toString(16));
          BigInteger      bb = new BigInteger(Integer.toString(10));
                EGCD       paillier2 = new EGCD(); 
                BigInteger gcd[] = paillier2.ExtEuclidean(ab, bb);
                //2 -3 2
                System.out.println(ab + "*" + gcd[0] + " + " + bb + "*" + gcd[1] + " = " + gcd[2]);
       System.out.println("2======================="+BigInteger.valueOf(2));
       BigInteger.valueOf(2);
       Ciphertext E1 = paillier.Encryption(C);
        CipherPub cxd_temp=paillier.Encryption(cxdcs,paillier.H[1]);

        temp_a=paillier.Encryption(SjS2);
        temp_a.T1 = temp_a.T1.multiply(E1.T1);
        temp_a.T2 = temp_a.T2.multiply(E1.T2);
        AAAA[0] =paillier.PSDecryption11(temp_a);
        System.out.println("AAAA0为："+AAAA[0]);
        AAAA[1] =paillier.PSDecryption22(temp_a);
        System.out.println("AAAA1为："+AAAA[1]);
       temp_AA =paillier.DDecryption(AAAA);

      System.out.println("C加了随机数="+temp_AA+"\n");
      System.out.println("C="+temp_AA.subtract(SjS2));
        CipherPub E10 = paillier.Encryption(B, paillier.H[1]);
        CipherPub E100 = paillier.Encryption(SjS1, paillier.H[1]);

        temp_b=paillier.Encryption(SjS1,paillier.H[1]);
        temp_b.T1 = temp_b.T1.multiply(E10.T1);
        temp_b.T2 = temp_b.T2.multiply(E10.T2);
        BBBB[0] =paillier.PSDecryption1(temp_b);
        System.out.println("BBBB0为："+BBBB[0]);
        BBBB[1] =paillier.PSDecryption2(temp_b);
        System.out.println("BBBB1为："+BBBB[1]);
        temp_BB =paillier.DDecryption(BBBB);
      System.out.println("B加了随机数="+temp_BB+"\n");
       System.out.println("B="+temp_BB.subtract(SjS1));
        S_TEMP = paillier.DDecryption(AAAA);
        Q_TEMP = paillier.DDecryption(BBBB);
        S[0]=S_TEMP.subtract(Q_TEMP);
        S[1]=S[0].pow(2);
        System.out.println("减法"+S[0]);
        System.out.println("减法平方"+S[1]);





       Ciphertext1 C10 = paillier.AddPDec1(E10);
       BigInteger C11 = paillier.AddPDec2(C10);
       for(int p =0 ; p <paillier.beta-1; p++)
       {
       ccc[p] = paillier.WPDecryption1(E10,paillier.X[p]);
       }
      
       CipherPub E20  = paillier.Encryption(B3,paillier.H[2]);
        CipherPub E200  = paillier.Encryption(SjS2,paillier.H[1]);
       
       
       System.out.println("NEW ALGO ==== " + C11);
       System.out.println("E20 ==== " + E20);
       System.out.println("E20=="+   paillier.SDecryption(E20));
       
        System.out.println("======SSBA/SLT=========");
        long TT1=0, TT2=0;
int CCC =0;
long CCCG1 = 0;
long T111 = System.currentTimeMillis(); 
      for(int iii=0; iii< 1; iii++){
 BigInteger C101 = paillier.WPDecryption2(E10,ccc,2,paillier.X[paillier.beta-1]);
      
      }
long T112 = System.currentTimeMillis(); 
System.out.println("======TT1========="+(T112-T111));
TT1=0;

       for(int iii=0; iii< 1; iii++){

            System.out.println("i==" + (iii)); 
   //  SSBA SK11 = new SSBA(E10 ,paillier);
          SLT SK11 = new SLT (E10, E20, paillier);
       long T1 = System.currentTimeMillis(); 
        SK11.StepOne();
         
      long T2 = System.currentTimeMillis(); 
         
        SK11.StepTwo ();
      
       long T3 = System.currentTimeMillis(); 
         
        SK11.StepThree();
      long T4 = System.currentTimeMillis(); 
      TT1 = TT1 + T4-T1 -(T3-T2)-SK11.TB ;
      TT2 = TT2 + T3-T2 +SK11.TB ;
      
        CCCG1 = CCCG1 + (long)SK11.CCC;
       }
        
         System.out.println("======TT1========="+TT1);
         System.out.println("======TT2========="+TT2);
         System.out.println("======CCCG1========="+CCCG1);
        
         
                   TT1=0; TT2=0;
  CCC =0;
  CCCG1 = 0;
       for(int iii=0; iii< 1; iii++){

            System.out.println("iEQ==" + (iii)); 
        // SSBA SK12 = new SSBA(E10 ,paillier);
     //     SEQ SK12 = new SEQ (E10, E20,   paillier);
          Sdiv SK12 = new Sdiv (E10,E20,paillier.Hsigma,10,paillier);
          //    SGCD SK12 = new SGCD (E10,E20,paillier.Hsigma,20,paillier);
       long T5 = System.currentTimeMillis(); 
        SK12.StepOne();
      long T6 = System.currentTimeMillis(); 
        
      TT1 = TT1 + T6-T5 - SK12.TB;
      TT2 = TT2 +  SK12.TB;
        CCCG1 = CCCG1 + (long)SK12.CCC;
       }
        
         System.out.println("======TT1========="+TT1);
         System.out.println("======TT2========="+TT2);
         System.out.println("======CCCG1========="+CCCG1);
         
         
        
   //    System.out.println("FIN=="+   paillier.SDecryption(SK11.FIN));
  //     System.out.println("FINS=="+   paillier.SDecryption(SK11.FINS));
   //    System.out.println("U=="+   paillier.SDecryption(SK11.U));
      // System.out.println("U=="+   paillier.SDecryption(SK11.a));
      // System.out.println("s=="+   paillier.SDecryption(SK11.s));
        System.out.println("======SSBA=========");
       
       
           System.out.println("======SLT=========");
//       比大小 E10=E(1)   E(20)=E(8)
                  SLT SK100 = new SLT(E10,E20 ,paillier);
   //      long T1 = System.currentTimeMillis(); 
        SK100.StepOne();
     //    long T2 = System.currentTimeMillis(); 
        SK100.StepTwo ();
      
       // long T3 = System.currentTimeMillis(); 
        SK100.StepThree();
       // long T4 = System.currentTimeMillis();
       System.out.println("FIN=="+   paillier.SDecryption(SK100.FIN));
       System.out.println("E10=="+   paillier.SDecryption(E10));
       System.out.println("E20=="+   paillier.SDecryption(E20));
//        System.out.println("UX=="+   paillier.SDecryption(SK100.UX));
      // System.out.println("s=="+   paillier.SDecryption(SK11.s));
        System.out.println("======SLT=========");   
       
       
      
   
        
        
                 System.out.println("======SMMS=========");
//                 求最大最小
       
                  SMMS SK101 = new SMMS(E10,E20 ,paillier);
   //      long T1 = System.currentTimeMillis(); 
        SK101.StepOne();
     //    long T2 = System.currentTimeMillis(); 
        
       // long T4 = System.currentTimeMillis(); 
       System.out.println("FINA=="+   paillier.SDecryption(SK101.FINA));//8
       System.out.println("FINI=="+   paillier.SDecryption(SK101.FINI));//1
       System.out.println("E10=="+   paillier.SDecryption(E10));
       System.out.println("E20=="+   paillier.SDecryption(E20));
      // System.out.println("s=="+   paillier.SDecryption(SK11.s));
        System.out.println("======SMMS=========");   
        System.out.println("======Sdiv=========");   
        Sdiv SK103 = new Sdiv(D2,D1,10 ,paillier);
        SK103.StepOne();
        
         System.out.println("D2=="+   paillier.SDecryption(D2));
       System.out.println("D1=="+   paillier.SDecryption(D1));
              System.out.println("FQ=="+   paillier.SDecryption(SK103.FQ));//0
       System.out.println("FR=="+   paillier.SDecryption(SK103.FR));//1
                   System.out.println("Q=="+   paillier.SDecryption(SK103.Q));//0
       System.out.println("R=="+   paillier.SDecryption(SK103.R));  //1
        System.out.println("======Sdiv=========");   
        
        
        
        
       
       BigInteger C1 =  paillier.WDecryption(E1);//E(1)=对3的Ctext加密   弱解密
        System.out.println(C1);
       BigInteger C2 =  paillier.SDecryption(E1);//强解密
        System.out.println(C2);
       
       BigInteger[] TKK =  paillier.PSDecryption1(E1);//部分强解密
        for (int i = 0; i < 2; i++) {
            System.out.println("部分解密为："+TKK[i]+"\n");
        }

//       tkk[1],tkk[2];
        BigInteger []    PPQ =  new    BigInteger [3];
       BigInteger FII = paillier.DDecryption1(TKK);//对于Ctext密文，先PSDecryption1部分解密再DDecryption1解密 可得完全结果
        System.out.println(FII);//3
       
       
       Ciphertext1 ECC = paillier.WPDecryption1(E1);//E(1)是对于3的Ctext加密
       BigInteger CCK = paillier.WPDecryption2(ECC);//CCK=3,弱私钥解密两步？

       
        Ciphertext E222  = paillier.Encryption(B3);
       
            RSM SK16 = new RSM(E10,E20,paillier);
         long T1 = System.currentTimeMillis(); 
        SK16.StepOne();
         long T2 = System.currentTimeMillis(); 
        SK16.StepTwo1();
        SK16.StepTwo2();
        long T3 = System.currentTimeMillis(); 
        SK16.StepThree();
        long T4 = System.currentTimeMillis(); 
        
        OddTest SK111 = new OddTest();
        System.out.println("ODD B3=="+ SK111.odd(B3));   
      
        System.out.println("ODD B=="+ SK111.odd(B));   
        
        
        ELSB SK12 = new ELSB(E10 ,paillier);
        
        SK12.StepOne();
         SK12.StepTwo ();
     
         SK12.StepThree();
         System.out.println("E20=="+   paillier.SDecryption(E20));
        System.out.println("SKELSB B=="+ paillier.SDecryption(SK12.FIN)); //B==1
        
        SBD SK15 = new SBD (E20,10, paillier);
        CipherPub [] EBD = new CipherPub [10];
        EBD = SK15.StepOne();
        
        for (int jjj=0; jjj< 10; jjj++)
              {
              System.out.println("EBD=="+ paillier.SDecryption(EBD[jjj])); 
              }
        
        
                  
             CipherPub  AKKK = new CipherPub();
          
          
             BigInteger TWO = BigInteger.valueOf(2); 
            BigInteger  ZERO = BigInteger.valueOf(0);
           CipherPub  EZERO =  paillier.Encryption(ZERO, paillier.Hsigma);
               AKKK.T1 = EZERO.T1.multiply(EZERO.T1);
               AKKK.T2 = EZERO.T2.multiply(EZERO.T2);        
                AKKK.PUB = paillier.Hsigma;    
         for(int i = 9; i >  0; i--)
          
         {    
             
                AKKK.T1 = AKKK.T1.multiply(EBD[i].T1);
                AKKK.T2 = AKKK.T2.multiply(EBD[i].T1);
             
                AKKK.T1 =   AKKK.T1.modPow(TWO, paillier.nsquare);
                AKKK.T2 =   AKKK.T2.modPow(TWO, paillier.nsquare);
                

             System.out.println("AKKK=="+   paillier.SDecryption(AKKK));
 
           
         }    
               AKKK.T1 = AKKK.T1.multiply(EBD[0].T1);
               AKKK.T2 = AKKK.T2.multiply(EBD[0].T2); 
               AKKK.PUB = paillier.Hsigma;
          
          System.out.println("SBD Before=="+   paillier.SDecryption(E20));
          System.out.println("SBD recover=="+   paillier.SDecryption(AKKK));
        
        
        
        
        
        
        System.out.println("E20=="+   paillier.SDecryption(SK15.T1));
        SAD SK17 = new SAD(E10,E20,paillier);
         SK17.StepOne();
         SK17.StepTwo ();

         SK17.StepThree();
        System.out.println("SAD REAULT=="+ SK17.FIN2);
           System.out.println("E20=="+   paillier.SDecryption(E20));
      
       
          System.out.println("3 com 15=="+ B.compareTo(B3));
          System.out.println("15 com 3=="+ B3.compareTo(B));
        
        System.out.println("E20=="+   paillier.SDecryption(E20));
        SGT SK18 = new SGT (E20,E10,paillier.Hsigma,paillier);
          
          SK18.StepOne();
          SK18.StepTwo();
          SK18.StepThree();
           System.out.println("E20=="+   paillier.SDecryption(E20));
            System.out.println("E10=="+   paillier.SDecryption(E10));
          System.out.println("SGT!!!=="+   paillier.SDecryption(SK18.FIN));
          System.out.println("BITLENGTH=="+   paillier.n.bitLength()/2);
  
          SMAX SK30 = new SMAX (E10,E20,paillier.Hsigma,paillier);
          
          SK30.StepOne();
          SK30.StepTwo();
          SK30.StepThree();
          System.out.println("MAX=="+   paillier.SDecryption(SK30.FINMX));
          System.out.println("MIN=="+   paillier.SDecryption(SK30.FINMI));
         // System.out.println("BITLENGTH=="+   paillier.n.bitLength()/2);







        SEQ SK191 = new SEQ (E100,E200,paillier.Hsigma,paillier);
        CipherPub A =new CipherPub();
        A.T1=E100.T1.multiply(E200.T1);
        A.T2=E100.T2.multiply(E200.T2);
        A.PUB=E100.PUB.multiply(E200.PUB);
        CipherPub AA =new CipherPub();
        AA.T1=E100.T1.modPow(paillier.n,paillier.nsquare);
        AA.T2=E100.T2.modPow(E200.T2,paillier.nsquare);
        AA.PUB = E100.PUB.add(E200.PUB);
        System.out.println("ssssssssssssssssss"+paillier.SDecryption(E100));
        System.out.println("ssssssssssssssssss"+paillier.SDecryption(E200));
        System.out.println("sssssssssssssssssdlisndjandjanjkdnajkdnjandjas"+paillier.SDecryption(AA));

        BigInteger AAAAA[] = new BigInteger[2];
        AAAAA[0]=paillier.PSDecryption1(A);
        AAAAA[1]= paillier.PSDecryption2(A);
        System.out.println(paillier.DDecryption(AAAAA));

        System.out.println("ssssssssssssssssss"+paillier.SDecryption(A));


        SK191.StepOne();

        System.out.println("SEQ u1=="+   paillier.SDecryption(SK191.u1));
        System.out.println("SEQ u2=="+   paillier.SDecryption(SK191.u2));
        System.out.println("SEQ=="+   paillier.SDecryption(SK191.FIN));
        BigInteger XXX= new BigInteger("0");
        if(paillier.SDecryption(SK191.FIN).equals(XXX)){
            System.out.println("zhaodaole ");
        }
        long startTime = System.currentTimeMillis();
        SEQ SK19 = new SEQ (E10,E20,paillier.Hsigma,paillier);
          SK19.StepOne();
        long endTime = System.currentTimeMillis();
        System.out.println("程序ssssssssssssssssssss运行时间：" + (endTime - startTime) + "ms");
        System.out.println("SEQ u1=="+   paillier.SDecryption(SK19.u1));
          System.out.println("SEQ u2=="+   paillier.SDecryption(SK19.u2));
          System.out.println("SEQ=="+   paillier.SDecryption(SK19.FIN));
          
    //       Sdiv SK20 = new Sdiv (E10,E20,paillier.Hsigma,10,paillier);
  //         SK20.StepOne();
        Integer r1=1;
        BigInteger r12 = new BigInteger("3");

        CipherPub T1222 = paillier.Encryption(r12,paillier.H[1]);
        CipherPub R1 = paillier.Encryption(BigInteger.valueOf(-r1.intValue()),paillier.H[1]);
        SAD SK1711 = new SAD(T1222,R1,paillier);
        SK1711.StepOne();
        SK1711.StepTwo ();
        SK1711.StepThree();

        System.out.println(paillier.SDecryption(SK1711.FIN));
           
           
           
 //          System.out.println("Sdiv E10 orignial=="+   paillier.SDecryption(E10));
           System.out.println("Sdiv E20 orignial=="+   paillier.SDecryption(E20));
  //         System.out.println("Sdiv Q orignial=="+   paillier.SDecryption(SK20.Q));
   //       System.out.println("Sdiv remainder orignial=="+   paillier.SDecryption(SK20.R));
           System.out.println("Sdiv E20 orignial=="+   paillier.SDecryption(E20));
    //       System.out.println("Sdiv E10=="+   paillier.SDecryption(SK20.a12));
    //      System.out.println("Sdiv E20=="+   paillier.SDecryption(SK20.b12));
          
        System.out.println("================SGCD============");  
          
          
           SGCD SK210 = new SGCD (E10,E20,paillier.Hsigma,10,paillier);
        SGCD SK2100 = new SGCD (E100,E200,paillier.Hsigma,10,paillier);
           SK210.StepOne();
        SK2100.StepOne();

          System.out.println("SGCD=="+   paillier.SDecryption(SK210.FIN));
        System.out.println("SGCD=="+   paillier.SDecryption(SK2100.FIN));
          
          
          
          
          
          
          
          
          
        System.out.println("RSM time A=="+ ((T2-T1)+(T4-T3))); 
        System.out.println("RSM time B=="+ (T3-T2));
         System.out.println("RSM REAULT=="+ SK16.FIN2);
        System.out.println("SAD REAULT=="+ SK17.FIN2);
         System.out.println("SECB Com=="+  SK16.CCC);
             
      
     //  System.out.println("SEC =="+SK14.Fin2); 
       System.out.println("======================================="); 
       System.out.println("C1=="+B); 
       System.out.println("C1=="+C1); 
       System.out.println("C2=="+C2); 
        System.out.println("CCk=="+CCK);
        System.out.println("TKK=="+TKK[0]);
        System.out.println("FII=="+FII);
       System.out.println("n=="+paillier.n); 
       System.out.println("=======================================");   
       
        BigInteger Bi = new BigInteger(Integer.toString(a2));
        BigInteger B2 = new BigInteger(Integer.toString(a3));
        Ciphertext E2 = paillier.Encryption(Bi);
       //      BigInteger Bi = new BigInteger(Integer.toString(15));
        BigInteger Ai = new BigInteger(Integer.toString(60));
        BigInteger Ci = new BigInteger(Integer.toString(70));
        BigInteger Pi = new BigInteger(Integer.toString(1));
          long i1=0,i2=0,i3=0,i4=0,i5=0,i6=0;
          int CCC1=0,CCC2 = 0,CCC3 =0;
          int KKKK =1;
  //     SECB SK = new SECB(E1,Bi,paillier);
   //    SK.StepOne();
   //    SK.StepTwo();
  //     SK.StepThree();
  //     BigInteger C3 =  paillier.SDecryption(SK.F);
     //  System.out.println("SECB=="+C3); 
     //  System.out.println("SECBCC1=="+SK.CCC); 
    //   System.out.println("SECBCC2=="+SK.CCC1);
    
          long Tk1 = System.currentTimeMillis(); 
          
          int cck =  3;
          for(int ii=0;ii<KKKK;ii++) 
      {  System.out.println("ii=="+(ii));
          
          
          Ciphertext E22 = paillier.Encryption(Bi);
          
      
       
  //      System.out.println("PSM=="+SK11.FIN2); 
     // System.out.println("PSM=="+SK11.CCC); 
       
        
               
          
     //  RSM SK16 = new RSM(E1,E2,paillier);
      //  long T1 = System.currentTimeMillis(); 
     //  SK16.StepOne();
     //   long T2 = System.currentTimeMillis(); 
     //  SK16.StepTwo();
      // long T3 = System.currentTimeMillis(); 
      // SK16.StepThree();
      //  long T4 = System.currentTimeMillis(); 
       // i1 =i1 +(T4-T3)+(T2-T1);
       // i2 =i2 +(T3-T2);
    // CCC1 = CCC1+SK16.CCC;
 //        System.out.println("RSM=="+SK16.FIN2); 
        
     //  SEC SK14 = new SEC(E1,E2,paillier);
      //  long T5 = System.currentTimeMillis(); 
      // SK14.StepOne();
      //  long T6 = System.currentTimeMillis(); 
     //  SK14.StepTwoA();
      // SK14.StepTwoB();
     //  long T7 = System.currentTimeMillis(); 
     //  SK14.StepThree();
     //  long T8 = System.currentTimeMillis(); 
       
     //  CCC2 =CCC2 +SK14.CCC;
     //  i3 = i3 + (T6-T5)+ SK14.T11;
     //  i4 = i4 +  (T7-T6)+SK14.T12;
  //     System.out.println("FINNNN=="+SK14.Fin2); 
   //    System.out.println("FSECCCC=="+SK14.CCC);
       
       
       
    //   SECE SK1 = new SECE(E1,Bi,paillier);
     //  long T9 = System.currentTimeMillis();
     //  SK1.StepOne();
     //  long T10 = System.currentTimeMillis();
     //  SK1.StepTwo();
    //   long T11 = System.currentTimeMillis();
    //   SK1.StepThree();
    //   long T12 = System.currentTimeMillis();
    //   i5 = i5 + (T12-T9)-(T11-T10);
    //   i6 = i6 +  (T11-T10);
     //  CCC3 = CCC3 +SK1.CCC;
  //     BigInteger C4 =  paillier.SDecryption(SK1.F);
    //   System.out.println("SECE=="+SK1.CCC); 
      }   
      
       long Tk2 = System.currentTimeMillis(); 
       
             
       for(int ii=0;ii<KKKK;ii++) 
      {// System.out.println("ii=="+(ii));
          
          
         BigInteger  E22 = paillier.SDecryption(E2);
          
  
      }  
     long Tk3 = System.currentTimeMillis(); 
       
 
    long Tk4 = System.currentTimeMillis();  
    
                for(int ii=0;ii<KKKK;ii++) 
      {// System.out.println("ii=="+(ii));
          
          BigInteger  E22 = paillier.WDecryption(E2); 
       //  PPQ  =  paillier.PSDecryption1(E1);
          
  
      }  
    long Tk5 = System.currentTimeMillis();  
    
                    for(int ii=0;ii<KKKK;ii++) 
      {// System.out.println("ii=="+(ii));
          
         PPQ  =  paillier.PSDecryption1(E1);
          
  
      }  
    long Tk6 = System.currentTimeMillis();  
    
    
    
     for(int ii=0;ii<KKKK;ii++) 
      {// System.out.println("ii=="+(ii));
          
          BigInteger ECCC = paillier.DDecryption1(PPQ);
          
  
      }  
    long Tk7 = System.currentTimeMillis();
    
    
     System.out.println("ENC=="+(Tk2-Tk1)); 
     System.out.println("SDEC=="+(Tk3-Tk2)); 
     System.out.println("WDEC=="+(Tk5-Tk4));
     System.out.println("PSDec=="+(Tk6-Tk5));
       System.out.println("DDEC=="+(Tk7-Tk6)); 
    //   System.out.println("SM A=="+(i1/KKKK)); 
   //   System.out.println("SM B=="+(i2/KKKK)); 
   //   System.out.println("COmmunication SM B=="+(CCC1/KKKK)); 
   //   System.out.println("SEC A=="+(i3/KKKK)); 
    //  System.out.println("SEC B=="+(i4/KKKK)); 
    //  System.out.println("COmmunication SEC B=="+(CCC2/KKKK));
    //  System.out.println("SECE A=="+(i5/KKKK)); 
     // System.out.println("SECE B=="+(i6/KKKK)); 
    //  System.out.println("COmmunication SEC B=="+(CCC3/KKKK));
      
//       PMAX SK2 = new PMAX(E1,E2,paillier);
 //     SK2.StepOne();
  //     SK2.StepTwo();
  ////     SK2.StepThree();
  //     BigInteger C5 =  paillier.SDecryption(SK2.FIN);
  //     System.out.println("SECE=="+C5); 
  //     System.out.println("SECE=="+SK2.eee); 
       
 //      Ciphertext  E11=paillier.Encryption(Ai);
  //     Ciphertext  E12=paillier.Encryption(Bi);
 ////      Ciphertext  E13=paillier.Encryption(Ci);
 //      Ciphertext  E14=paillier.Encryption(Pi); 
  //     Di= new Ciphertext [4];
        
   //     Di[0]= E11;
   //     Di[1]= E12;
  //      Di[2]= E13;
  //      Di[3]= E14; 
        
 //       System.out.println("==========TOPK===========");
 //       TOPK  ppkk = new TOPK(Di,K,paillier);  
 //       ppkk.find_k(0, 3, 2);
         //ppkk.StepOne();
 //        for (int i = 0; i < 4; ++i) {
 //           System.out.println(paillier.SDecryption(ppkk.VC[i]));
            // System.out.println(ppkk.VC[i]);
  //          System.out.println(ppkk.vector[i]);
  //  }
    
    
       
}
}