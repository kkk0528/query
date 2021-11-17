package qk;


import pufc.CipherPub;

import java.math.BigInteger;
import java.util.*;


public class TFIDF {

    public List removeDuplicate(List<List<Integer>> list) {
        List listTemp = new ArrayList();
        List listTemp_1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            listTemp_1 = list.get(i);
            for (int j = 0; j < listTemp_1.size(); j++) {
                if (!listTemp.contains(listTemp_1.get(j))) {
                    listTemp.add(listTemp_1.get(j));
                }
            }

        }
        return listTemp;
    }

    /**
     * 计算词频 IF * * @param word 词 * @param terms 分词结果集合 * @return IF
     */
    public Map<Integer, Map<Integer, Double>> computeTF(List<List<Integer>> text) {
        Map<Integer, Map<Integer, Double>> textId2tf_score = new HashMap<>();
        for (int i = 0; i < text.size(); i++) {
            List<Integer> content = text.get(i);
            Map<Integer, Double> text_score = new HashMap<>();
            for (int j = 0; j < content.size(); j++) {
                int count = Collections.frequency(content, content.get(j));
                Double tf_score = count / (double) content.size();
                text_score.put(content.get(j), tf_score);
            }
            textId2tf_score.put(i, text_score);
        }
        return textId2tf_score;
    }

    /** * 统计词语的逆文档频率 IDF * * @param path 存放 url 的文件路径 * @param word IDF */
    /**
     * text ---> 五星级 麻将房 海景房 酒店
     * 商务 电竞房 酒店
     * 青年 上下铺 旅舍                       * @param word IDF
     */
    public Map<Integer, Double> computeIDF(List<List<Integer>> text, List<Integer> terms) {
        Map<Integer, Double> idf_score_map = new HashMap<>();
        for (Integer term : terms) {
            int count_term = 0;
            for (int i = 0; i < text.size(); i++) {
                List content = text.get(i);
                if (content.contains(term)) {
                    count_term++;
                }

            }
            Double idf_score = Math.log10(text.size() / (double) count_term);
            idf_score_map.put(term, idf_score);
        }
        return idf_score_map;

    }

    /**
     * 计算词频-逆文档频率 TF—IDF * * @param filePath 存放url的文件路径 * @param terms 分词结果集合 * @param word 词 * @return TF—IDF
     */

    public Double computeTFIDF(Map<Integer, Double> idf_score_map, Map<Integer, Map<Integer, Double>> textId2tf_score, Integer term, Integer textId) {
        Double tf = textId2tf_score.get(textId).get(term);
        Double idf = idf_score_map.get(term);
        Double a =(1-tf * idf)/5;
        return a;
//            return computeTF(word, terms) * computeIDF(filePath, word);
    }

    public BigInteger jointIDXY(List<Integer> ID, List<Integer> x, List<Integer> y) {
        BigInteger SDSD =BigInteger.valueOf(2).pow(1000);
        BigInteger re = BigInteger.ZERO;
        for (int i = 0; i < ID.size(); i++) {
            BigInteger SADAD = BigInteger.valueOf(2).pow(100);
            BigInteger result = BigInteger.ZERO;
            BigInteger id = BigInteger.valueOf(2).pow(60).multiply(BigInteger.valueOf(ID.get(i)));
            BigInteger X = BigInteger.valueOf(2).pow(30).multiply(BigInteger.valueOf(x.get(i)));
            BigInteger Y = BigInteger.valueOf(y.get(i));
            result = id.add(X).add(Y);
            re = re.multiply(BigInteger.valueOf(2).pow(100)).add(result);
        }

        return re;
    }


    public <T> List<List<T>> splitList(List<T> list, int groupSize) {
        int length = list.size();
        // 计算可以分成多少组
        int num = (length + groupSize - 1) / groupSize; // TODO
        List<List<T>> newList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            // 开始位置
            int fromIndex = i * groupSize;
            // 结束位置
            int toIndex = (i + 1) * groupSize < length ? (i + 1) * groupSize : length;
            newList.add(list.subList(fromIndex, toIndex));
        }
        return newList;

    }
}



