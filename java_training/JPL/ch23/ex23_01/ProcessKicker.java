package ch23.ex23_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ProcessKicker {

	public void plugTogether(final InputStream in , final OutputStream out){
		Thread thread = new Thread(){
			public void run(){
				int ch = 0;
				try {
					while((ch = in.read()) != -1){
						out.write(ch);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally{
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}

	public void plugTogether(final PrintStream out , final InputStream in){
		Thread thread = new Thread(){
			public void run(){
				int ch = 0;
				try {
					while((ch = in.read()) != -1){
						out.write(ch);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally{
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					out.close();
				}
			}
		};
		thread.start();
	}


}
