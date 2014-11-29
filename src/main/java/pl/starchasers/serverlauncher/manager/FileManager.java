package pl.starchasers.serverlauncher.manager;

import java.io.File;

import pl.starchasers.serverlauncher.manager.tasks.SetupClient;
import pl.starchasers.serverlauncher.manager.tasks.SetupServer;
import cpw.mods.fml.installer.ServerInstall;

public class FileManager {
	public static void checkProfile(String name){
		File profileDir = new File(((String)ProfileProperties.SERVERDIR.get(name)).replace("{profile}","profiles/"+name));
		
		if(!profileDir.isDirectory()){
			System.out.println(">>>> SETTING UP SERVER FOR PROFILE: " + name);
			profileDir.mkdirs();
			System.out.println("Installing forge to " + profileDir.getAbsolutePath());
			ServerInstall installer = new ServerInstall();
			if(!installer.isPathValid(profileDir)){
				throw new RuntimeException(installer.getFileError(profileDir));
			}
			installer.run(profileDir);
			
			System.out.println(">>>> Forge installed, setting up server");
			new SetupServer(name).runTask();
			
			System.out.println("Setting up client");
			
		}
		
		profileDir = new File(((String)ProfileProperties.CLIENTDIR.get(name)).replace("{profile}","profiles/"+name));		
		if(!profileDir.isDirectory()){
			System.out.println(">>>> SETTING UP CLIENT FOR PROFILE: " + name);
			profileDir.mkdirs();
			new SetupClient(name).runTask();
		}
	}
}
