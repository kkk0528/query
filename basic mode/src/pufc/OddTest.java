/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pufc;
import java.math.BigInteger;

/**
 *
 * @author xmliu
 */
//测试是否为奇数（true）
public class OddTest {
    
    public boolean odd(BigInteger val) {
    if(!val.mod(new BigInteger("2")).equals(BigInteger.ZERO))
        return true;
    return false;
}
    
    public boolean odd1(BigInteger val) {
    return (!val.mod(new BigInteger("2")).equals(BigInteger.ZERO));
}
}
