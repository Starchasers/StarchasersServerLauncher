package com.bymarcin.minecraftservermanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Utils {
	
	
	public static ArrayList<String> getBlackList(File blacklistFile){
		ArrayList<String> blacklist = new ArrayList<String>();
		Scanner read = null;
		try {
			read = new Scanner(blacklistFile);
			while(read.hasNextLine()){
				String line = read.nextLine();
				blacklist.addAll(Arrays.asList(line.split(";")));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}finally{
			read.close();
		}

		return blacklist;
	}
	
	
	
    public static void copyFolder(File src, File dest, ArrayList<String> blacklist)
        	throws IOException{
     
        	if(src.isDirectory()){
     
        		//if directory not exists, create it
        		if(!dest.exists()){
        		   dest.mkdir();
        		   System.out.println("Directory copied from " 
                                  + src + "  to " + dest);
        		}
     
        		//list all the directory contents
        		String files[] = src.list();
     
        		for (String file : files) {
        		   //construct the src and dest file structure
        		   File srcFile = new File(src, file);
        		   File destFile = new File(dest, file);
        		   //recursive copy
        		   copyFolder(srcFile,destFile,blacklist);
        		}
     
        	}else{
        		if(blacklist.contains(src.getName())){
        			System.out.println("File skiped: "+ src);
        			return;
        		}
        		
        		//if file, then copy it
        		//Use bytes stream to support all file types
        			InputStream in = new FileInputStream(src);
        	        OutputStream out = new FileOutputStream(dest); 
     
        	        byte[] buffer = new byte[1024];
     
        	        int length;
        	        //copy the file content in bytes 
        	        while ((length = in.read(buffer)) > 0){
        	    	   out.write(buffer, 0, length);
        	        }
     
        	        in.close();
        	        out.close();
        	        System.out.println("File copied from " + src + " to " + dest);
        	}
        }
	
	
	
	public static void removeFiles(File f) throws IOException{
			String prename = "File";
			  if (f.isDirectory()) {
				  prename = "Directory";
			    for (File c : f.listFiles())
			    	removeFiles(c);
			  }
			  
			  if (!f.delete())
			    throw new FileNotFoundException("Failed to delete file: " + f);
			  else{
				  System.out.println(prename + " removed: " + f.getAbsolutePath());
			  }
	}

	
	public static String[]  serverCommand(String message){
			return new String[]{"screen", "-p", "0", "-S", "MSM_" + Config.SCREEN_NAME.get(),
					"-X", "eval","stuff \"" + message + "\"\\015"};
	}
	
	public static String executeCommand(String command) {
		return executeCommand(command, "./");
	}
	
	public static String executeCommand(String[] command) {
		return executeCommand(command, "./");
	}
	
	
	public static String executeCommand(String command, String path) {
		 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command, null, new File(path));
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
 
	}
	
	
	public static String executeCommand(String[] command, String path) {
		 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command, null, new File(path));
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
 
	}
	
	public static void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
