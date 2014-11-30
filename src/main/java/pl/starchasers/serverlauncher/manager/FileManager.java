package pl.starchasers.serverlauncher.manager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.google.common.io.Files;

import pl.starchasers.serverlauncher.LauncherMain;
import pl.starchasers.serverlauncher.manager.tasks.SetupClient;
import pl.starchasers.serverlauncher.manager.tasks.SetupServer;
import cpw.mods.fml.installer.ServerInstall;

public class FileManager {
	public static void checkProfile(String name){
		File profileDir = new File(((String)ProfileProperties.SERVERDIR.get(name)).replace("{profile}","profiles/"+name));
		
		if(!profileDir.isDirectory()){
			System.out.println(">>>> SETTING UP SERVER FOR PROFILE: " + name);
			profileDir.mkdirs();
			if(!(LauncherMain.config.hasPath("minecraft.eula") && LauncherMain.config.getBoolean("minecraft.eula")))
			{
				System.out.println(">> YOU MUST ACCEPT MOJANG EULA TO SETUP SERVER");
				System.out.println(">> https://account.mojang.com/documents/minecraft_eula");
				System.out.println(">> TO DO THIS SET >> minecraft.eula to true IN CONFIG");
				throw new RuntimeException("Mojang EULA not accepted");
			}
			
			System.out.println("Installing forge to " + profileDir.getAbsolutePath());
			ServerInstall installer = new ServerInstall();
			if(!installer.isPathValid(profileDir)){
				throw new RuntimeException(installer.getFileError(profileDir));
			}
			installer.run(profileDir);
			
			System.out.println(">>>> Forge installed, setting up server");
			new SetupServer(name).runTask();
			
			try {
				File file = new File(((String)ProfileProperties.SERVERDIR.get(name)).replace("{profile}","profiles/" + name) +"/eula.txt");
				file.createNewFile();
				Files.write("eula=true", file, StandardCharsets.UTF_8);
			} catch (IOException e) {
				System.out.println("Failed to write eula.txt");
				e.printStackTrace();
			}			
		}
		
		profileDir = new File(((String)ProfileProperties.CLIENTDIR.get(name)).replace("{profile}","profiles/"+name));		
		if(!profileDir.isDirectory()){
			System.out.println(">>>> SETTING UP CLIENT FOR PROFILE: " + name);
			profileDir.mkdirs();
			new SetupClient(name).runTask();
		}
	}
}
