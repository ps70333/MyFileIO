package tw.com.ps70333.myfileio;

import java.io.Serializable;

/**
 * Created by Simon on 2017/8/13.
 */

public class Student implements Serializable{
    private String name;
    private int math,eng,ch;
    public String sum(){
        return name+":"+(math+eng+ch);
    }
    public String avg(){
        return name+":"+(math+eng+ch)/3;
    }
    public Student(String name,int math,int eng,int ch){
        this.name=name;
        this.eng=eng;
        this.math=math;
        this.ch=ch;
    }

}
