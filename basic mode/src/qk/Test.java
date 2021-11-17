package qk;

import com.carrotsearch.sizeof.RamUsageEstimator;

import java.math.BigInteger;
import java.util.ArrayList;
import pufc.PaillierT;
import java.util.List;

public class Test {
//    static class Person{
//        private int id;
//        private String name;
//        private String address;
//        public Person(int id, String name, String address) {
//            this.id = id;
//            this.name = name;
//            this.address = address;
//        }
static class Person{
        int age = 10;
        public Person(int i){
            age = i;
        }
    }
    public static void main(String[] args) throws Exception {
//        Person p = new Person(12, "xujsh","bj");
        Person q = new Person(1);
        System.out.println(RamUsageEstimator.sizeOf(q));
        int a[] = {1,2,3,41,5,1,1,1,1};
        List<Person> L = new ArrayList<>();

        for(int i = 0 ;i < 10 ;i++)
        {
            Person p = new Person(i);
            L.add(p);
        }
int aa=1;
        int bb=9999999;
        BigInteger A =BigInteger.ZERO;
        BigInteger B =BigInteger.ONE;
        System.out.println(RamUsageEstimator.sizeOf(aa));
        System.out.println(RamUsageEstimator.sizeOf(bb));
        System.out.println(RamUsageEstimator.sizeOf(A));
        System.out.println(RamUsageEstimator.sizeOf(B));
    }
}
