package Data;

import pufc.CipherPub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Point{

    private Integer Id;

    private Integer Latitude_x;



    private Integer Longitude_y;

    private Map<Integer,Double> Key_score;

    private List<CipherPub>  Key_score_MIWEN;

    private  List<Integer> Key_id;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getLatitude_x() {
        return Latitude_x;
    }

    public void setLatitude_x(Integer latitude_x) {
        Latitude_x = latitude_x;
    }

    public Integer getLongitude_y() {
        return Longitude_y;
    }

    public void setLongitude_y(Integer longitude_y) {
        Longitude_y = longitude_y;
    }

    public Map<Integer, Double> getKey_score() {
        return Key_score;
    }

    public void setKey_score(Map<Integer, Double> key_score) {
        Key_score = key_score;
    }
    public List<CipherPub>  getKey_score_MIWEN() {
        return Key_score_MIWEN;
    }

    public void setKey_score_MIWEN(List<CipherPub> key_score_miwen ) {
        Key_score_MIWEN = key_score_miwen;
    }
    public List<Integer>  getKey_id() {
        return Key_id;
    }

    public void setKey_id(List<Integer> key_id_miwen ) {
        Key_id = key_id_miwen;
    }

    public Point(Integer id, Integer latitude_x, Integer longitude_y, Map<Integer,Double> key_score,  List<CipherPub> key_score_miwen, List<Integer>key_id){
        Id = id;
        Latitude_x = latitude_x;
        Longitude_y = longitude_y;
        Key_score = key_score;
        Key_score_MIWEN =key_score_miwen;
        Key_id = key_id;
    }

    public static void swap( Point P1,Point P2)
    {
        Integer IDtemp = P1.Id;
        Integer latitude_xTEMP=P1.Latitude_x;
        Integer longitude_yTEMP=P1.Longitude_y;
        Map<Integer,Double> key_scoreTEMP=P1.Key_score;
        List<CipherPub>  key_scoreTEMP1=P1.Key_score_MIWEN;
        List<Integer>  key_IDTEMP=P1.Key_id;
        P1.setId(P2.getId());
        P1.setLongitude_y(P2.getLongitude_y());
        P1.setLatitude_x(P2.getLatitude_x());
        P1.Key_score=P2.Key_score;
        P1.Key_score_MIWEN =  P2.Key_score_MIWEN;
        P1.Key_id =  P2.Key_id;
        P2.setId(IDtemp);
        P2.setLatitude_x(latitude_xTEMP);
        P2.setLongitude_y(longitude_yTEMP);
        P2.Key_score=key_scoreTEMP;
        P2.Key_score_MIWEN = key_scoreTEMP1;
        P2.Key_id = key_IDTEMP;

    }



    public Point() {
    }

}
