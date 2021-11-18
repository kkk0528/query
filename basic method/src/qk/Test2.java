package qk;


import Data.MyData;
import Data.Point;
import jdk.dynalink.beans.StaticClass;
import pufc.*;
import  java.math.*;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.time.temporal.Temporal;
import java.util.*;
import com.carrotsearch.sizeof.RamUsageEstimator;

import static java.lang.constant.ConstantDescs.NULL;

//import qk.com.alibaba.fastjson.*;


public class Test2 {
//CP and CSP help split and calculate the distance score
    public List<List<CipherPub>> ChaiFen_juli(CipherPub idxy_idxy, CipherPub CXD_SJS, CP cp, CSP csp, PaillierT paillierT) {
        BigInteger Miwen_temp[] = new BigInteger[2];
        List<List<CipherPub>> ID_JULI = new ArrayList<>();
        List<List<CipherPub>> ID_juli_SJS = new ArrayList<>();
        List<List<BigInteger>> Minwen_paixuji_DP = new ArrayList<>();
        List<List<BigInteger>> CXD_DP = new ArrayList<>();
        //Add random number r1id，r2x，r3y.....
        CipherPub Miwen_sjs = cp.Add_sjs(idxy_idxy);
        Miwen_temp[0] = cp.PSDEC_PUB1(Miwen_sjs);
        Miwen_temp[1] = csp.PSDEC_PUB2(Miwen_sjs);
        BigInteger Minwen = csp.Quanbu_jiemi(Miwen_temp);
        Minwen_paixuji_DP = csp.Fenge(Minwen);
        //decrypt r2x，r3y....
        BigInteger cxd_temp[] = new BigInteger[2];
        cxd_temp[0] = cp.PSDEC_PUB1(CXD_SJS);
        cxd_temp[1] = csp.PSDEC_PUB2(CXD_SJS);
        BigInteger cxd_sjs = csp.Quanbu_jiemi(cxd_temp);
        CXD_DP = csp.Fenge_xyxy(cxd_sjs);
        //Calculate the distance and retain the required information
        for (int i = 0; i < Minwen_paixuji_DP.size(); i++) {
            List<CipherPub> ID_juli_temp = new ArrayList<>();
            ID_juli_temp.add(paillierT.Encryption(Minwen_paixuji_DP.get(i).get(0), paillierT.H[0]));
            BigInteger CHA_xy = CXD_DP.get(i).get(0).subtract(Minwen_paixuji_DP.get(i).get(1)).add(CXD_DP.get(i).get(1).subtract(Minwen_paixuji_DP.get(i).get(2)));
            BigInteger CHA_FANG = (CXD_DP.get(i).get(0).subtract(Minwen_paixuji_DP.get(i).get(1)).pow(2)).add(CXD_DP.get(i).get(1).subtract(Minwen_paixuji_DP.get(i).get(2)).pow(2));
            ID_juli_temp.add(paillierT.Encryption(CHA_xy, paillierT.H[0]));
            ID_juli_temp.add(paillierT.Encryption(CHA_FANG, paillierT.H[0]));
            ID_juli_SJS.add(ID_juli_temp);
        }
        ID_JULI = cp.Delete_sjs(ID_juli_SJS);
        return ID_JULI;
    }
// cp take TFIDF score and calculate the total score
    public CipherPub[] zong_score(List<CipherPub> ID_JULI, int n, int Libary, List<CipherPub> TFIDF, CP cp, List<Integer> keyword_id) {
        CipherPub ZTFIDFscore = cp.paillierT.Encryption(BigInteger.ZERO, cp.paillierT.H[1]);
        CipherPub zscore = new CipherPub();
        CipherPub tfidf_he[] = new CipherPub[3];
        CipherPub ZONG[] = new CipherPub[3];
        //取查询关键字，算tfidf总分，
        for (int i = 0; i < cp.CXD_Keyword.length; i++) {
            tfidf_he[i] = TFIDF.get(n * Libary + keyword_id.get(i));
            ZTFIDFscore.T1 = tfidf_he[i].T1.multiply(ZTFIDFscore.T1);
            ZTFIDFscore.T2 = tfidf_he[i].T2.multiply(ZTFIDFscore.T2);
        }
        zscore.T1 = ID_JULI.get(1).T1.multiply(ZTFIDFscore.T1);
        zscore.T2 = ID_JULI.get(1).T2.multiply(ZTFIDFscore.T2);

        ZONG[0] = ID_JULI.get(0);//记录的id
        ZONG[1] = ZTFIDFscore;//tfidf得分
        ZONG[2] = zscore;//总分=距离+tfidf（多关键字）
        return ZONG;

    }
    //Total score for elements outside the stack
    public CipherPub[] zong_score2(List<CipherPub> ID_JULI, int n,int sjn, int Libary, List<CipherPub> TFIDF, CP cp, List<Integer> keyword_id) {
        CipherPub ZTFIDFscore = cp.paillierT.Encryption(BigInteger.ZERO, cp.paillierT.H[1]);
        CipherPub zscore = new CipherPub();
        CipherPub tfidf_he[] = new CipherPub[3];
        CipherPub ZONG[] = new CipherPub[4];

        //取查询关键字，算tfidf总分，
        for (int i = 0; i < cp.CXD_Keyword.length; i++) {
            tfidf_he[i] = TFIDF.get(n * Libary + keyword_id.get(i));
            ZTFIDFscore.T1 = tfidf_he[i].T1.multiply(ZTFIDFscore.T1);
            ZTFIDFscore.T2 = tfidf_he[i].T2.multiply(ZTFIDFscore.T2);
        }
        zscore.T1 = ID_JULI.get(1).T1.multiply(ZTFIDFscore.T1);
        zscore.T2 = ID_JULI.get(1).T2.multiply(ZTFIDFscore.T2);

        ZONG[0] = ID_JULI.get(0);//记录的id
        ZONG[1] = ZTFIDFscore;//tfidf得分
        ZONG[2] = zscore;//总分=距离+tfidf（多关键字）
        ZONG[3] = TFIDF.get(n * Libary + keyword_id.get(sjn));//该行的tfidf
        return ZONG;

    }
//    Always keep the maximum value at the top of the stack
    public List<CipherPub[]> MAX(List<CipherPub[]> A, CP cp, CSP csp) {
        CipherPub MAX[] = A.get(0);
        for (int i = 1; i < A.size(); i++) {
            int result = csp.slt_two(cp.SLT_one(MAX[2], A.get(i)[2]));
            CipherPub[] TEMP = A.get(0);
            if (result == -1) {
                MAX = A.get(i);
                A.set(0, MAX);
                A.set(i, TEMP);
            }
        }

        return A;
    }

    public static void main(String[] args) {
        //      定义

        for (int n = 1; n < 2; n++) {
            PaillierT paillierT = new PaillierT(1024, 64);
            TFIDF tfidf = new TFIDF();
            List<Integer> array_txtid = new ArrayList<Integer>();
            List<List<Integer>> ZONG_TEXT = new ArrayList<>();
            List<Point> Jihe_point = new ArrayList<>();//用来存放一整个文本的
            Map<Integer, Double> keySCORE = new LinkedHashMap<>();
            List<CipherPub> keySCORE_miwen = new ArrayList<>();
            List<CipherPub> keySCORE_MIWEN = new ArrayList<>();
            Map<Integer, Map<Integer, Double>> textId2tf_score = new LinkedHashMap<>();
            Map<Integer, Double> idf_score_map = new LinkedHashMap<>();
            List PINGJIE_paixu_miwen = new ArrayList<>();
            Map<Integer, Double> ZONGkeyscore = new LinkedHashMap<Integer, Double>();//
            List<CipherPub> keyscore_MIWEN = new ArrayList<>();//
            int filename = 5000 * n;
            String f = String.valueOf(filename);
            StringBuilder S = new StringBuilder();
            S.append("E:\\data20").append(".txt");
            String pathname = S.toString();
            //Read local dataset
            try (FileReader reader = new FileReader(pathname);
                 BufferedReader br = new BufferedReader(reader)
            ) {
                String line;

                while ((line = br.readLine()) != null) {
                    List<Integer> ZONG_TEXT_temp = new ArrayList<Integer>();
                    // 一次读入一行数据
//                    System.out.println(line);
                    Integer id = Integer.valueOf(line.split(" ")[0]); //id 集合
                    Integer x = Integer.valueOf(line.split(" ")[1]);
                    Integer y = Integer.valueOf(line.split(" ")[2]);
                    array_txtid.add(id);
                    for (int i = 3; i < 9; i++) {
                        ZONG_TEXT_temp.add(Integer.valueOf(line.split(" ")[i]));

                    }
                    ZONG_TEXT.add(ZONG_TEXT_temp);
                    Point point = new Point(id, x, y, ZONGkeyscore, keyscore_MIWEN);
                    Jihe_point.add(point);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            List zd = tfidf.removeDuplicate(ZONG_TEXT);
            textId2tf_score = tfidf.computeTF(ZONG_TEXT);
            idf_score_map = tfidf.computeIDF(ZONG_TEXT, zd);


//        Data initialization
            List<Integer> library = new ArrayList<>(zd);
            double[][] score = new double[zd.size()][array_txtid.size()];
            for (int i = 0; i < library.size(); i++) {
                for (int j = 0; j < array_txtid.size(); j++) {
                    if (ZONG_TEXT.get(j).contains(library.get(i))) {
                        Double score_tfidf = tfidf.computeTFIDF(idf_score_map, textId2tf_score, library.get(i), array_txtid.get(j));
                        score[i][j] = score_tfidf;
                    }
                }
            }
            long start_map = System.currentTimeMillis();
//将tfidf总分存入point中
            for (int i = 0; i < array_txtid.size(); i++) {
                for (int j = 0; j < library.size(); j++) {
                    keySCORE.put(library.get(j), score[j][i]*1000000);
                    keySCORE_miwen.add(paillierT.Encryption(BigInteger.valueOf((int) (score[j][i] * 1000000)), paillierT.H[1]));

                }
                keySCORE_MIWEN = keySCORE_miwen;
                ZONGkeyscore = keySCORE;
                keySCORE = new LinkedHashMap<>();
                keySCORE_miwen = new ArrayList<>();
                Jihe_point.get(i).setKey_score(ZONGkeyscore);
                Jihe_point.get(i).setKey_score_MIWEN(keySCORE_MIWEN);
            }
            long end_map = System.currentTimeMillis();
//            System.out.println("构建map：" + (end_map - start_map) + "毫秒");

//            Indexing
            long start_biao = System.currentTimeMillis();
            for (int i = 0; i < library.size(); i++) {
                ArrayList<Point> point_lits_tmp = new ArrayList<Point>();
                point_lits_tmp.addAll(Jihe_point);
                for (int k = 0; k < point_lits_tmp.size(); k++) {
                    for (int l = k; l < point_lits_tmp.size(); l++) {
                        if (point_lits_tmp.get(k).getKey_score().get(library.get(i)) > point_lits_tmp.get(l).getKey_score().get(library.get(i))) {
//             排序
                            Point.swap(point_lits_tmp.get(k), point_lits_tmp.get(l));

                        }
                    }

                }
                ArrayList<Integer> id_sort_temp = new ArrayList<>();
                ArrayList<Integer> x_sort_temp = new ArrayList<>();
                ArrayList<Integer> y_sort_temp = new ArrayList<>();
                List<CipherPub> tfidf_sort_temp = new ArrayList<>();
                List Z_DP_IDXY_TFIDF = new ArrayList<>();
                ArrayList<BigInteger> IDXY_sort_temp = new ArrayList<>();
                for (int q = 0; q < point_lits_tmp.size(); q++) {
                    List<CipherPub> tfidf_sort_temp1 = new ArrayList<>();
                    id_sort_temp.add(point_lits_tmp.get(q).getId());
                    x_sort_temp.add(point_lits_tmp.get(q).getLatitude_x());
                    y_sort_temp.add(point_lits_tmp.get(q).getLongitude_y());
//
                    tfidf_sort_temp1 = point_lits_tmp.get(q).getKey_score_MIWEN();
                    for (int j = 0; j < tfidf_sort_temp1.size(); j++) {
                        tfidf_sort_temp.add(tfidf_sort_temp1.get(j));
                    }
                }
                List<List<Integer>> TEMP1 = tfidf.splitList(id_sort_temp, 10);
                List<List<Integer>> TEMP2 = tfidf.splitList(x_sort_temp, 10);
                List<List<Integer>> TEMP3 = tfidf.splitList(y_sort_temp, 10);
                List<List<CipherPub>> TEMP4 = tfidf.splitList(tfidf_sort_temp, 10 * library.size());
                for (int j = 0; j < TEMP1.size(); j++) {
                    IDXY_sort_temp.add(tfidf.jointIDXY(TEMP1.get(j), TEMP2.get(j), TEMP3.get(j)));
                }
                for (int j = 0; j < IDXY_sort_temp.size(); j++) {
                    List DP_IDXY_TFIDF = new ArrayList<>();
                    DP_IDXY_TFIDF.add(paillierT.Encryption(IDXY_sort_temp.get(j), paillierT.H[1]));
                    DP_IDXY_TFIDF.add(TEMP4.get(j));
                    Z_DP_IDXY_TFIDF.add(DP_IDXY_TFIDF);
                }
                Z_DP_IDXY_TFIDF.add(0, paillierT.Encryption(BigInteger.valueOf(i), paillierT.H[1]));
                PINGJIE_paixu_miwen.add(Z_DP_IDXY_TFIDF);
            }
            int nnn = 5000 * n;
//            System.out.println(nnn + "个密文list为：" + RamUsageEstimator.sizeOf(PINGJIE_paixu_miwen));
            long end_biao = System.currentTimeMillis();
//            System.out.println("构建排序过的加密表：" + (end_biao - start_biao) + "毫秒");
            //User sends query
            BigInteger KEYWORD[] = new BigInteger[2];
            CipherPub CXGJC_KEY[] = new CipherPub[2];
            Random rand = new Random();
                BigInteger aa = BigInteger.valueOf(111);
                BigInteger bb = BigInteger.valueOf(555);
                KEYWORD[0] = BigInteger.valueOf(44);
                KEYWORD[1] = BigInteger.valueOf(12);
                BigInteger re = BigInteger.ZERO;
                long gjzgs = System.currentTimeMillis();
                for (int i = 0; i < 10; i++) {
                    BigInteger result = BigInteger.ZERO;
                    BigInteger x = BigInteger.valueOf(2).pow(40).multiply(aa);
                    BigInteger y = bb;
                    result = x.add(y);
                    re = re.multiply(BigInteger.valueOf(2).pow(80)).add(result);
                }
                CipherPub CXD = paillierT.Encryption(re, paillierT.H[0]);

//            System.out.println("原查询点X为" + aa + "原查询点y为" + bb + "\n" + "查询点处理后为" + re + "查询点处理加密后为" + CXD);
                for (int i = 0; i < KEYWORD.length; i++) {
                    CXGJC_KEY[i] = paillierT.Encryption(KEYWORD[i], paillierT.H[0]);
//                    System.out.println("原查询关键字为" + library.get(KEYWORD[i].intValue()) + "查询关键字编号" + KEYWORD[i] + "\n" + "查询关键字字典编号加密后" + CXGJC_KEY[i]);
                }
                long gjzgs_end = System.currentTimeMillis();
//            System.out.println("/关键字个数总耗时为：" + (gjzgs_end - gjzgs) + "毫秒");
//            System.out.println(nnn + "大小为：" + RamUsageEstimator.sizeOf(CXD)+" "+ RamUsageEstimator.sizeOf(CXGJC_KEY));
                CP cp = new CP(PINGJIE_paixu_miwen, CXD, CXGJC_KEY, paillierT);
                CSP csp = new CSP(paillierT);
                cp.Create_sjs();
                CipherPub CXD_SJS = new CipherPub();
                CXD_SJS = cp.Create_cxdsjs();
                SkipSet set = new SkipSet(cp, csp, library, paillierT);
                //用户构造跳表
                set.Skiplist_set();
                int k = 1;

//
                List<List> Pi_pei = new ArrayList<>();
                List<List> Pi_pei_MIWEN = new ArrayList<>();
                List<Integer> Pi_pei_ID = new ArrayList<>();
                List<List<CipherPub>> Pi_pei_TFIDF = new ArrayList<>();
                Test2 test = new Test2();
                List<List<CipherPub[]>> ZONG = new ArrayList<>();
                List<CipherPub[]> E_ZONG = new ArrayList<>();
//Query start keyword matching, take a line, take the first k, and split distributed decryption (id|x|y|tfidf)
                long start = System.currentTimeMillis();
                long startMili = System.currentTimeMillis();
                Pi_pei = set.Pipei();//取某两行
                long endMili = System.currentTimeMillis();
//            System.out.println("/匹配总耗时为：" + (endMili - startMili) + "毫秒");

                Pi_pei_MIWEN = Pi_pei.get(1);
                Pi_pei_ID = Pi_pei.get(0);

                for (int i = 0; i < Pi_pei_MIWEN.size(); i++) {
                    Pi_pei_MIWEN.get(i).remove(0);
                }

//Take a row and select the first package to split
//Compare the data in the stack with other data
                int sj_n = rand.nextInt(Pi_pei_ID.size());
                List<CipherPub[]> ZONG_score = new ArrayList<>();
                CipherPub IDXY_IDXY = new CipherPub();
                List<List<CipherPub>> id_juli = new ArrayList<>();
                Pi_pei_TFIDF = Pi_pei_MIWEN.get(sj_n);
                IDXY_IDXY = Pi_pei_TFIDF.get(0).get(0);
                long cf_score = System.currentTimeMillis();
                id_juli = test.ChaiFen_juli(IDXY_IDXY, CXD_SJS, cp, csp, paillierT);
                long cf_score_wc = System.currentTimeMillis();
//                System.out.println("拆分,计算距离运行时间：" + (cf_score_wc - cf_score) + "ms");
            //Calculate the total score, ID and text score of the first k
                long z_score = System.currentTimeMillis();
                for (int j = 0; j < k; j++) {
                    CipherPub K_score[] = test.zong_score(id_juli.get(j), j, library.size(), (List<CipherPub>) Pi_pei_TFIDF.get(0).get(1), cp, Pi_pei_ID);
                    ZONG_score.add(K_score);
                }
                ZONG.add(ZONG_score);
                long z_score_wc = System.currentTimeMillis();
//                System.out.println("两个包2k个数计算总分与tfidf分：" + (z_score_wc - z_score) + "ms");
                for (int j = 0; j < ZONG.size(); j++) {
                    for (int i = 0; i < ZONG.get(0).size(); i++) {
//                    System.out.println("id为：" + paillierT.SDecryption(ZONG.get(j).get(i)[0]) + "\n" + "TFIDF分数为：" + paillierT.SDecryption(ZONG.get(j).get(i)[1]) + "\n" + "总分数为：" + paillierT.SDecryption(ZONG.get(j).get(i)[2]));
                    }
                }


                long paixuscore = System.currentTimeMillis();
                long qiu_max = System.currentTimeMillis();
                E_ZONG = test.MAX(ZONG.get(0), cp, csp);
                long qiu_max_jieshu = System.currentTimeMillis();
                System.out.println("三个数，保证栈顶最大耗时：" + (qiu_max_jieshu - qiu_max) + "ms");
                long paixuqucscore = System.currentTimeMillis();
                for (int j = 0; j < E_ZONG.size(); j++) {
//                System.out.println("id为：" + paillierT.SDecryption(E_ZONG.get(j)[0]) + "\n" + "tfidf分数为：" + paillierT.SDecryption(E_ZONG.get(j)[1]) + "总分数为：" + paillierT.SDecryption(E_ZONG.get(j)[2]));

                }
//            System.out.println("==================================");
//            Comparison of elements inside and outside the stack
                long zhan_slt = System.currentTimeMillis();
                Zhan:
                for (int j = 0; j < Jihe_point.size() / 10; j++) {
                    CipherPub IDXY_IDXY2 = new CipherPub();
                    List<List<CipherPub>> id_juli2 = new ArrayList<>();
                    Pi_pei_TFIDF = Pi_pei_MIWEN.get(sj_n);
                    IDXY_IDXY2 = Pi_pei_TFIDF.get(j).get(0);
                    id_juli2 = test.ChaiFen_juli(IDXY_IDXY2, CXD_SJS, cp, csp, paillierT);
                    if (j == 0) {
                        for (int l = k; l < id_juli2.size(); l++) {
                            int temp = 0;
                            CipherPub K_score[] = test.zong_score2(id_juli2.get(l), l, sj_n, library.size(), (List<CipherPub>) Pi_pei_TFIDF.get(0).get(1), cp, Pi_pei_ID);
                            int slt_tfidf = csp.slt_two(cp.SLT_one(K_score[3], E_ZONG.get(0)[2]));
                            if (slt_tfidf == 1 || slt_tfidf == 0) {
                                break Zhan;
                            } else {
                                int slt_zong = csp.slt_two(cp.SLT_one(K_score[2], E_ZONG.get(0)[2]));
                                if (slt_zong == -1) {
                                    E_ZONG.remove(0);
                                    E_ZONG.add(0, K_score);
                                    E_ZONG = test.MAX(E_ZONG, cp, csp);
                                }
                            }

                        }
                    } else {
                        for (int l = 0; l < id_juli2.size(); l++) {
                            int temp = 0;
                            CipherPub K_score[] = test.zong_score2(id_juli2.get(l), l, sj_n, library.size(), (List<CipherPub>) Pi_pei_TFIDF.get(j).get(1), cp, Pi_pei_ID);
                            int slt_tfidf = csp.slt_two(cp.SLT_one(K_score[3], E_ZONG.get(0)[2]));
                            if (slt_tfidf == 1 || slt_tfidf == 0) {
                                break Zhan;
                            } else {
                                int slt_zong = csp.slt_two(cp.SLT_one(K_score[2], E_ZONG.get(0)[2]));
                                if (slt_zong == -1) {
                                    E_ZONG.remove(0);
                                    E_ZONG.add(0, K_score);
                                    E_ZONG = test.MAX(E_ZONG, cp, csp);

                                }
                            }

                        }
                    }


                }

                long zhan_end = System.currentTimeMillis();
                System.out.println("/栈与其他元素比较总耗时为：" + (zhan_end - zhan_slt) + "毫秒");
                long end = System.currentTimeMillis();
                System.out.println("/查询总耗时为：" + (end - start) + "毫秒");
                for (int i = 0; i < E_ZONG.size(); i++) {
                    System.out.println("k位" + n + "id：" + paillierT.SDecryption(E_ZONG.get(i)[0]) + "   tfidf分：" + paillierT.SDecryption(E_ZONG.get(i)[1]) + "  总分：" + paillierT.SDecryption(E_ZONG.get(i)[2]));
                }

                System.out.println("===============================");
            }


    }
}





