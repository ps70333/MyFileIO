package tw.com.ps70333.myfileio;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private TextView tv;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        //sp-->儲存一些名稱 (key) 與值 (value) 的對應資料 (只限基本型態的資料如：int, float, boolean, long 等)
        sp=getSharedPreferences("my_data",MODE_PRIVATE);//取得SP物件。MODE_PRIVATE:無法讓其他 App 存取，MODE_WORLD_READABLE、MODE_WORLD_WRITEABLE...等等
        editor=sp.edit();


    }
    public void doSaveSp(View v){
        editor.putString("username","Simon");
        editor.putBoolean("sound",true);
        editor.putInt("stage",7);
        editor.commit();
        Toast.makeText(this,"Save OK",Toast.LENGTH_SHORT).show();
    }
    public void doLoadSp(View v){
        Boolean sound=sp.getBoolean("sound",false);
        String username=sp.getString("username","x");
        Float temp=sp.getFloat("temp",2f);
        int stage=sp.getInt("stage",-1);
        tv.setText("username:"+username+"\n"+"sound:"+sound+"\n"+"stage:"+stage+"\n"+"temp:"+temp);
        //Toast.makeText(this,username+":"+sound+":"+stage,Toast.LENGTH_SHORT).show();

    }
    public void doSaveTxt(View v){
        try {
            FileOutputStream fout = openFileOutput("simon.txt",MODE_PRIVATE);
            fout.write("Simon\n".getBytes());
            fout.write("這是FileOutputStream寫入文字檔的範例\n".getBytes());
            //fout.write("這是FileOutputStream寫入文字檔的範例\n".getBytes());
            fout.write("2017/08/12\n".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"Save TXT ok",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.i("simon","doSaveTxt="+e.toString());
        }

    }
    public void doLoadTxt(View v){
        try {
            FileInputStream fin =openFileInput("simon.txt");
            byte[]buf=new byte[8];
            int len;
            StringBuffer sb=new StringBuffer();
            while((len=fin.read(buf))!=-1){
                sb.append(new String(buf,0,len));
            }
            //一次讀一些BYTE，如果遇到中文字串起來就會出問題...
            Toast.makeText(this,sb,Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Log.i("simon","doLoadTxt="+e.toString());
        }

    }
    public void doSaveTxt2(View v){
        try {
            FileOutputStream fout = openFileOutput("simon2.txt",MODE_PRIVATE);
            fout.write("Simon2\n".getBytes());
            fout.write("這是FileOutputStream寫入文字檔的範例2\n".getBytes());
            //fout.write("這是FileOutputStream寫入文字檔的範例\n".getBytes());
            fout.write("2017/08/12-2\n".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"Save TXT2 ok",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.i("simon","doSaveTxt2="+e.toString());
        }

    }
    public void doLoadTxt2(View v){
        try {
            FileInputStream fin =openFileInput("simon2.txt");
            InputStreamReader inr=new InputStreamReader(fin);
            BufferedReader br=new BufferedReader(inr);
            String line;
            StringBuffer sb=new StringBuffer();
            while((line=br.readLine())!=null){
                sb.append(line.toString()+"\n");//因為一次會讀一行，所以換行符號要自己再加上去
            }
            Toast.makeText(this,sb,Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Log.i("simon","doLoadTxt2="+e.toString());
        }

    }
    public void doSaveObj(View v){
        Student student=new Student("simon",70,80,90);
        Log.i("simon",student.sum()+":"+student.avg());
        try {
            FileOutputStream fout=openFileOutput("student.obj",MODE_PRIVATE);
            ObjectOutputStream oos=new ObjectOutputStream(fout);
            oos.writeObject(student);
            fout.flush();
            fout.close();
            Toast.makeText(this,"doSaveObj寫入完成"+Toast.LENGTH_LONG,Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Log.i("simon","doSaveObj="+e.toString());
        }

    }
    public void doLoadObj(View v){
        try {
            FileInputStream fin=openFileInput("student.obj");
            ObjectInputStream ois=new ObjectInputStream(fin);
            Student student=(Student)ois.readObject();
            tv.setText(student.sum()+"\n"+student.avg());
        }catch(Exception e){
            Log.i("simon","doLoadTxt2="+e.toString());
        }

    }


}
