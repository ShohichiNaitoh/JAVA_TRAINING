package ch10.ex10_01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import junit.framework.TestCase;

public class ReplaceStringTest extends TestCase{

	@Test
	public void testReplace(){
		ReplaceStringMain rs = new ReplaceStringMain();
	    // ファイルオブジェクトの生成
	    File inputFile = new File("JPL/ch10/ex10_01/file.txt");

	    try {
	      // 入力ストリームの生成
	      FileInputStream fis = new FileInputStream(inputFile);
	      InputStreamReader isr = new InputStreamReader(fis);
	      BufferedReader br = new BufferedReader(isr);

	      // テキストファイルからの読み込み
	      String msg;
	      while ( ( msg = br.readLine()) != null ) {
	        System.out.println(rs.replace(msg));
	      }

	      // 後始末
	      br.close();

	      // エラーがあった場合は、スタックトレースを出力
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	}
}
