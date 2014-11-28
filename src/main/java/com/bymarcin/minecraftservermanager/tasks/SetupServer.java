package com.bymarcin.minecraftservermanager.tasks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.bymarcin.minecraftservermanager.Config;
import com.bymarcin.minecraftservermanager.ITask;
import com.bymarcin.minecraftservermanager.Tasks;
import com.bymarcin.minecraftservermanager.Utils;

public class SetupServer implements ITask{
	File blacklist;
	File configDir;
	File modsDir;
	File gitModsDir;
	File gitConfigDir;
	
	public SetupServer() {
		blacklist = new File(Config.GIT_REPO_PATH.get() + "/blacklist_server.json");
		configDir = new File(Config.SERVER_PATH.get() + "/config");
		modsDir = new File(Config.SERVER_PATH.get() + "/mods");
		
		gitConfigDir  = new File(Config.GIT_REPO_PATH.get() + "/config");
		gitModsDir = new File(Config.GIT_REPO_PATH.get() + "/mods");
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
