package ch23.ex23_03;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class ProcessKicker {

	public static void main(String[] args){
		if(args.length == 0){
			throw new IllegalArgumentException();
		}
		ProcessKicker pk = new ProcessKicker();
		pk.start(args[0]);
	}

	public void start(String arg){
		try {
			Process p = new ProcessBuilder(arg).start();
			plugTogether(System.in , p.getOutputStream() , p);
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void plugTogether(final InputStream in , final OutputStream out , final Process p){
		Thread thread = new Thread(){
			public void run(){
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
				String str = null;
				int count = 0;
				try {
					while((str = br.readLine()) != null){
						if(str.contains("end")){
							p.destroy();
						}
						bw.write(count++ + " " + str);
						bw.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally{
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						bw.close();
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
