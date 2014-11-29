package pl.starchasers.serverlauncher.manager.tasks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import pl.starchasers.serverlauncher.manager.ProfileProperties;

import com.bymarcin.minecraftservermanager.Config;
import com.bymarcin.minecraftservermanager.ITask;
import com.bymarcin.minecraftservermanager.Utils;

public class SetupServer implements ITask{
	File blacklist;
	File configDir;
	File modsDir;
	File gitModsDir;
	File gitConfigDir;
	
	public SetupServer(){}
	
	public SetupServer(String profile) {
		blacklist = new File(((String)ProfileProperties.SYNCDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/blacklist_server.json");
		configDir = new File(((String)ProfileProperties.SERVERDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/config");
		modsDir = new File(((String)ProfileProperties.SERVERDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/mods");
		
		gitConfigDir  = new File(((String)ProfileProperties.SYNCDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/config");
		gitModsDir = new File(((String)ProfileProperties.SYNCDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/mods");
	}

	@Override
	public boolean runTask() {
		if(Tasks.STATUS.run()){System.out.println("Server is running!"); return false;}
		try {
			ArrayList<String> blacklist = Utils.getBlackList(this.blacklist);
			if(configDir.exists()){
				Utils.removeFiles(configDir);
			}
			if(modsDir.exists()){
				Utils.removeFiles(modsDir);
			}
			Utils.copyFolder(gitModsDir, modsDir, blacklist);
			Utils.copyFolder(gitConfigDir, configDir, blacklist);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
		}
		return true;
	}

}
