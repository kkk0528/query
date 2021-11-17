package Data;

public class MyData {
    private int id;
    private int coordinate;
    private String Description;


//    public MyData() {
//
//    }

//    public int getId(int i ) {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getCoordinate(int i) {
//        return coordinate;
//    }
//
//    public void setCoordinate(int coordinate) {
//        this.coordinate = coordinate;
//    }
//
//    public String getDescription() {
//        return Description;
//    }
//
//    public void setDescription(String description) {
//        Description = description;
//    }
//
//    public MyData(int id, int coordinate, String description) {
//        this.id = id;
//        this.coordinate = coordinate;
//        Description = description;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public MyData(int id, int coordinate, String description) {
        this.id = id;
        this.coordinate = coordinate;
        Description = description;
    }

    public MyData() {
    }
}
