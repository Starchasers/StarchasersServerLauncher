package com.bymarcin.minecraftservermanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    private static Properties prop = new Properties();
    private static final String config = "msm.properties";
    private static File file = new File(config);
    public static void main(String[] args) {
        loadConfig();
        if(args.length==0) printHelp();
        if(args.length>0 && "COMMAND".equals(args[0].toUpperCase())){
        	String message="";
        	for(int i=1;i<args.length;i++){
        		if(i!=1) message+=" ";
        		message+=args[i];
        	}
        	boolean s = Utils.executeCommand(Utils.serverCommand(message)).isEmpty();
        	System.out.println("RUN COMMAND ENDS WITH STATUS:" + (s?"OK":"ERROR"));
        	System.exit(s?0:1);
        }
        ArrayList<Tasks> todo = new ArrayList<Tasks>();
        for(String arg: args){
        	try{
        		Tasks temp = Tasks.valueOf(arg.toUpperCase());
        		if(temp==null) throw new IllegalArgumentException(); else todo.add(temp);
        	}catch(IllegalArgumentException e){
        		System.out.println("Task:\""+arg+"\" not found");
        		printHelp();
        		System.exit(1);
        	}
        }        
        
        for(Tasks task: todo){
        	System.out.println("RUN TASK:" + task);
        	boolean status = task.run();
        	System.out.println("TASK:"+ task +" FINISHED WITH STATUS:" + (status?"OK":"ERROR"));
        	if(!status)
        		System.exit(1);
        }

		saveConfig();
    }

    protected static void saveConfig(){
		try {
			for(Config c: Config.values())
				prop.setProperty(c.name(), c.get());
			prop.store(new FileOutputStream(file), "msm configuration File");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    protected static void loadConfig(){
        InputStream in;
            try{
                if(!file.exists()){
                    if(!file.createNewFile()){
                        throw new IOException("CouldNotCreateNewFile");
                    }
                }
                in = new FileInputStream(file);
                prop.load(in);
            }catch(IOException e){
                e.printStackTrace();
                System.exit(1);
            }
    }

    public static Properties getProperties(){
        return prop;
    }
    
    public static void printHelp(){
    	System.out.println("/*************************************************/\n"
    			+ "Task list: \n"
    			+ "-start:\t\t\tlaunch server\n"
    			+ "-stop:\t\t\tclose server\n"
    			+ "-status:\t\tdisplay server status\n"
    			+ "-saveAll:\t\tsave all worlds\n"
    			+ "-backup:\t\tcreat backup in backup directory\n"
    			+ "-setupServer:\t\tremove files from server directory and copy new file from git directory\n"
    			+ "-setupClient:\t\tremove files from download directory and copy new file from git directory\n"
    			+ "-generateFileList:\tgenerate new filelist.json from git\n"
    			+ "-command <command>:\trun command on server\n"
    			+ "/*************************************************/\n");
    }
    
}
