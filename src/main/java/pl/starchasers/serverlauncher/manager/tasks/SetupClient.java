package pl.starchasers.serverlauncher.manager.tasks;

import java.io.File;

import pl.starchasers.serverlauncher.manager.ProfileProperties;
import pl.starchasers.serverlauncher.manager.tasks.sync.FileListGenerator;

public class SetupClient extends SetupServer{
	private String profile;
	
	public SetupClient(String profile) {
		this.profile = profile;
		
		blacklist = new File(((String)ProfileProperties.SYNCDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/blacklist_client.json");
		configDir = new File(((String)ProfileProperties.CLIENTDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/config");
		modsDir = new File(((String)ProfileProperties.CLIENTDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/mods");
		
		gitConfigDir  = new File(((String)ProfileProperties.SYNCDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/config");
		gitModsDir = new File(((String)ProfileProperties.SYNCDIR.get(profile)).replace("{profile}","profiles/"+profile) + "/mods");
	}
	
	@Override
	public boolean runTask() {
		return super.runTask() && new FileListGenerator(profile).runTask();
	}
	
}
