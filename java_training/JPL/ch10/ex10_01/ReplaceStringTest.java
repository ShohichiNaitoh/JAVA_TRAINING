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
	    // �t�@�C���I�u�W�F�N�g�̐���
	    File inputFile = new File("JPL/ch10/ex10_01/file.txt");

	    try {
	      // ���̓X�g���[���̐���
	      FileInputStream fis = new FileInputStream(inputFile);
	      InputStreamReader isr = new InputStreamReader(fis);
	      BufferedReader br = new BufferedReader(isr);

	      // �e�L�X�g�t�@�C������̓ǂݍ���
	      String msg;
	      while ( ( msg = br.readLine()) != null ) {
	        System.out.println(rs.replace(msg));
	      }

	      // ��n��
	      br.close();

	      // �G���[���������ꍇ�́A�X�^�b�N�g���[�X���o��
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	}
}
