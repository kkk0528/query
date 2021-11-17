package qk;

import pufc.CipherPub;
import pufc.PaillierT;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//跳表的实现
public class SkipSet <E extends Comparable <? super E>>{//Implement skip table
    // MAX_LEVEL = log(1/p)(N)   -1 =  Math.log(1/p)/Math.log(N);
    public static final int MAX_LEVEL = 6;
    //最下层链表的长度
    int lenght;
    //用于保存搜索节点时经过的节点。
    List<Node> stack;
    //左上角用于遍历使用的起点
    Node head;
    //当前跳表的层级
    int level;
    //随机函数
    Random  ra;
    //控制当前的随机层数	用于测试算法是否正确
    int conLevel = -1;

    PaillierT paillierT;
    CipherPub HEAD;
    CP cp;
    CSP csp;
    List<Integer> ZD;
     List<Integer> Number = new ArrayList<>();
     List<List> Paixuji= new ArrayList<>();
    List<List<List>> Paixuji_temp= new ArrayList<>();
    CipherPub start_miwen;
    List<List> Keyword_id = new ArrayList<>();

    public SkipSet(CP cp1 ,CSP csp1 ,List zd,PaillierT PT)
    {   paillierT =PT;
        stack =  new LinkedList<Node>();
        head = new Node(-1,paillierT.Encryption(BigInteger.valueOf(-1),paillierT.H[1]));	//起点不放置值
        level = 1;
        ra = new Random();
        cp =cp1;
        csp =csp1;
        ZD = zd;
        start_miwen = paillierT.Encryption(BigInteger.valueOf(-1),paillierT.H[1]);
    }

    //查找节点
    public Node searchNode(int target)
    {
        Node re = null;
        Node temp = head;

        while(temp != null)
        {
            if(temp.value != target)
            {
                //如果当前节点不匹配那么当前节点的下一个节点若比要找的值大则下跳一层，否则则下跳一个节点
                if(temp.right != null )temp= temp.right.value>target ? temp.down :temp.right;
                else temp = temp.down;
            }
            else if(temp.value == target)
            {
                re = temp; //保存倒数第二个点，即最后一层的那个数据点
                temp = temp.down;
            }
        }

        return re;
    }
    //找到最上层的第一个节点
    public Node searchFirstNode(int target)
    {
        Node re = null;
        Node temp = head;

        while(temp != null)
        {
            if(temp.value != target)
            {
                //如果当前节点不匹配那么当前节点的下一个节点若比要找的值大则下跳一层，否则则下跳一个节点
                if(temp.right != null )temp= temp.right.value>target ? temp.down :temp.right;
                else temp = temp.down;
            }
            else if(temp.value == target)
            {
                re = temp; //保存倒数第二个点，即最后一层的那个数据点
                return re;
            }
        }

        return re;
    }


    //这里只允许插入不重复的节点  有序节点
    public int insertNode(int value)
    {
        int re = -1;
        //creadSKipList();
        Node result = searchNode(value);
        //没有存在就进行插入
        if(result == null)
        {
            int lev = randomLevel();
            Node data = new Node(value,start_miwen);
            //从上往下插入
            //随机的层比当前最高层还多
            if(level < lev)
            {
                //添加层 //给level 层及以下的所有层加入该数据  并将添加层的最下层与原始层的最上层的 加入的新数据节点想链接
                //这里注意顺序 , 两个都使用了head 节点，但是 appendLevel方法 更新过head节点
                Node endData = insertLevelData(level,value);
                Node appendLevel = appendLevel(lev-level,value);

                appendLevel.down = endData;
                //appendLevel(lev-level,value).down = insertLevelData(level,value);
                this.level = lev; //重置当前层级
            }
            else
            {
                //随机的层小于等于原有最高层，即本来就有层，先找到最上需要进行插入的一层（从上往下插入）
                insertLevelData( (level - lev)==0 ? 1 :level - lev,value);
            }
        }else
        {
            System.out.println("节点已经存在！节点值为："+result.value);
        }

        return re;
    }
    //添加层
    /**
     * @Descrption:		添加层
     * @param i			需要添加的层数
     * @param value		添加的内容
     * @return Node 	返回插入层数最后一层的插入数据节点
     */
    private Node appendLevel(int i, int value)
    {
        Node temp = null;//用于保存将所有新加入每层头节点 都链接起来
        Node daTemp = null;//用于保存将所有新加入的节点每层都链接起来
        Node top = null;//保存最上层的节点
        for(;i > 0;i --)
        {
            Node newHead = new Node(0,start_miwen); //每层头结点
            Node newData = new Node(value,start_miwen);

            newHead.right = newData;
            if(daTemp == null)
            {
                daTemp = newData;
            }
            else
            {
                daTemp.down = newData;
                daTemp = newData;
            }

            if(temp == null)
            {
                temp = newHead;
                top = newHead;
            }
            else
            {
                temp.down = newHead;
                temp = newHead;
            }

        }
        //重置左上角头结点  这里重置过了最上层的节点 所以要先给下面的层加
        temp.down = head;
        head = top;//重置head 指针

        return daTemp;
    }
    /**
     *
     * @Descrption:		对当前节点层以及所有下层插入数据
     * @param i			从第i层开始往下加数据
     * @param value		数据
     * @return Node		返回最上一层加入数据的节点 （用于与上层有添加层的时候相连接）
     */
    private Node insertLevelData(int i,int value)
    {
        Node temHead = head;
        boolean flag = true;
        Node topData=null;
        while(--i > 0)
        {
            temHead = temHead.down;
        }
        Node temp = null;//用于保存将所有新加入的节点每层都链接起来
        while(temHead != null)
        {
            Node newTemp = insetLine(temHead,value);
            if(flag)
            {
                topData = newTemp;
                flag = false; //只保留第一个
            }
            if(temp != null) temp.down = newTemp;
            temp = newTemp;//重置新加入的节点
            temHead = temHead.down;
        }

        return topData;
    }

    /**
     *
     * @Descrption:			在当前层中插入数据
     * @param start			每一层的头节点
     * @param value			要插入的值
     * @return
     */
    private Node insetLine(Node start,int value)
    {
        Node temp = null;
        while(start != null)
        {
            if(start.value < value) temp = start;
            start = start.right;
        }
        Node data = new Node(value,start_miwen);
        if(temp.right != null)
        {
            data.right = temp.right;
            temp.right = data;
        }
        else
        {
            temp.right = data;
        }
        return data;
    }

    // 从上往下 打印所有层数据
    public void printBottomFloor()
    {
        Node temp = head;
        int i=1;
        while(temp != null)
        {
            System.out.println("==========第"+(i++)+"层==========\n");
            Node lineTemp = temp;
            while(lineTemp != null)
            {
                System.out.println(lineTemp);
                lineTemp = lineTemp.right;
            }
            temp = temp.down;
        }
    }
    // 从上往下 加密所有层数据
    public void enc(PaillierT paillierT)
    {
        Node temp = head;
        int i=1;
        while(temp != null)
        {
            List<Node> Keyword_id_TEMP = new ArrayList<>();
            Node lineTemp = temp;
            while(lineTemp != null)
            {
                lineTemp.value1 = paillierT.Encryption(BigInteger.valueOf(lineTemp.value),paillierT.H[1]);
                Keyword_id_TEMP.add(lineTemp);

                lineTemp = lineTemp.right;
            }
            Keyword_id_TEMP.remove(0);
            Keyword_id.add(Keyword_id_TEMP);
            temp = temp.down;
        }
    }
    public Node searchNode_miwen(CipherPub target)
    {
        Node re = null;
        head.value1 =paillierT.Encryption(BigInteger.valueOf(-1),paillierT.H[1]);
        Node temp = head;

        while(temp != null)
        {
            int a  = csp.slt_two(cp.SLT_one(temp.value1,target));
            if(a != 0)
            {
                //如果当前节点不匹配那么当前节点的下一个节点若比要找的值大则下跳一层，否则则下跳一个节点
                if(temp.right != null )
                {
                    int res  = csp.slt_two(cp.SLT_one(target,temp.right.value1));
                    if (res == -1) {
                        temp = temp.down;
                    } else {
                        temp = temp.right;
                    }
                }
                else {
                    temp = temp.down;
                }
            }

            else
            {
                re = temp; //保存倒数第二个点，即最后一层的那个数据点
                temp = temp.down;
            }
        }

        return re;
    }
    //获取随机数层数
    public int randomLevel()
    {
        int re= 0;
        //记录连续为true 的次数
        while(ra.nextInt(2) == 1){
            re ++;
        }
        return re;
    }

    //节点类
    static class Node
    {
        //节点中的值
        int value;
        CipherPub value1;
        //当前层级的下一个节点
        Node right;
        //当前节点对应的下一级的节点
        Node down;

        public Node(int value,CipherPub value1 )
        {
            this.value = value;
            this.value1 =value1;
        }
        //down 有递归问题 另外处理
        @Override
        public String toString() {
            return "Node [value=" + value + " 密文="+value1+" down="+ ((down != null) ? down.value : null)+""
                    + " right :"+((right != null) ? right.value : null)+"]";
        }
    }
    public  void  Skiplist_set(){
        long start=System.currentTimeMillis();
        for (int i = 0; i < ZD.size(); i++) {
            insertNode(i);
        }
        long end=System.currentTimeMillis();
        System.out.println("/建表耗时为："+(end-start)+"毫秒");
//        printBottomFloor();
        long start_encskip=System.currentTimeMillis();
        enc(paillierT);
        long end_encskip=System.currentTimeMillis();
        System.out.println("/加密跳表耗时为："+(end_encskip-start_encskip)+"毫秒");
//        printBottomFloor();
    }
    public  List Pipei()//取某一行
    {
        for (int i = 0; i <cp.CXD_Keyword.length ; i++) {
            long start=System.currentTimeMillis();
            SkipSet.Node search = searchNode_miwen(cp.CXD_Keyword[i]);
            long end=System.currentTimeMillis();
            System.out.println("/跳表匹配一次耗时为："+(end-start)+"毫秒");
            Number.add(Keyword_id.get(Keyword_id.size()-1).indexOf(search));
            Paixuji_temp.add(cp.Point_MIWEN.get(Keyword_id.get(Keyword_id.size()-1).indexOf(search)));
        }
        Paixuji.add(Number);
        Paixuji.add(Paixuji_temp);
        return Paixuji;
    }


}
