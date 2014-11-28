package com.bymarcin.minecraftservermanager.tasks;

import java.io.File;

import com.bymarcin.minecraftservermanager.Config;
import com.bymarcin.minecraftservermanager.Tasks;

public class SetupClient extends SetupServer{
	public SetupClient() {
		blacklist = new File(Config.GIT_REPO_PATH.get() + "/blacklist_client.json");
		configDir = new File(Config.CLIENT_PATH.get() + "/config");
		modsDir = new File(Config.CLIENT_PATH.get() + "/mods");
		
		gitConfigDir  = new File(Config.GIT_REPO_PATH.get() + "/config");
		gitModsDir = new File(Config.GIT_REPO_PATH.get() + "/mods");
	}
	
	@Override
	public boolean runTask() {
		return super.runTask() && Tasks.GENERATEFILELIST.run();
	}
	
}
