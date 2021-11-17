package qk;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class sql {
    //按行读取文件内容到集合
    public static List<String> readFlie(File file){
        List<String> input = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                // System.out.println(s);
                input.add(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return input;
    }

    //处理内容
    public static List<String> dealdata(List<String> inputdata){
            List<String> outputdata = new ArrayList<String>();
        for(int i=0;i<5000;i++){
        String element=inputdata.get(i);
        String outElement=null;
        String DSDA =null;
        DSDA = element.split( " ")[0];
        int a =Integer.parseInt(DSDA) ;
           outputdata.add(element);


        }
        for(int j=0;j<outputdata.size();j++){
            String temp = outputdata.get(j);
            System.out.println(temp);
        }
        return outputdata;
    }

    //把内容写入文本文件中
    public static void writercontent(List<String> output,String filepath) throws IOException {
        File file = new File(filepath);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fileWriter);
        for(int i=0;i<output.size();i++){
            bw.write(output.get(i).toString());
            bw.newLine();
        }
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        //输入文件
        File infile = new File("E:\\data.txt");
        List<String> inputdata=new ArrayList<String>();
        inputdata=readFlie(infile);//读取输入的文件内容
        List<String> onputdata=new ArrayList<String>();
        onputdata=dealdata(inputdata);//对内容进行处理
        //输出文件的路径
        String outputpath="E:\\DATA5000.txt";
        writercontent(onputdata,outputpath);//把处理后的内容写入到文件
    }

}
