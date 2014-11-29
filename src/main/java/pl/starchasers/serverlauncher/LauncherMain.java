package pl.starchasers.serverlauncher;

import java.io.File;
import java.util.Map.Entry;

import pl.starchasers.serverlauncher.manager.FileManager;
import pl.starchasers.serverlauncher.webui.Webapp;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

import cpw.mods.fml.installer.ServerInstall;

public class LauncherMain {
    private static final String configFile = "serverlauncher.conf";
    public static final Config config;
	
    static{
    	config = ConfigFactory.parseFile(new File(configFile));
    }
    
	public static void main(String[] args){
		System.out.println("Starting");
		ServerInstall.headless = true;
		
		for(Entry<String, ConfigValue> profile : config.getObject("profiles").entrySet()){
			
			System.out.println("Checking profile " + profile.getKey());
			FileManager.checkProfile(profile.getKey());
		}
		Webapp.run(args);
	}
}
