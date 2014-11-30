package pl.starchasers.serverlauncher.manager.tasks;

import pl.starchasers.serverlauncher.manager.ProfileProperties;

import com.bymarcin.minecraftservermanager.ITask;
import com.bymarcin.minecraftservermanager.Utils;

public class StartServer implements ITask{

	private final String profile;
	
	public StartServer(String profile){
		this.profile = profile;
	}
	
	private String buildCommand(){
		return "screen -dmS " + ProfileProperties.SCREENPREFIX.get(profile) + profile +" "
			+ ProfileProperties.JAVA.get(profile) + " " + ProfileProperties.VMARGS.get(profile) + " -jar "
			+ ProfileProperties.FORGEJAR.get(profile) + " nogui";
	}
	
	@Override
	public boolean runTask(){
		System.out.println(buildCommand());
		if(Tasks.STATUS.run()){
			System.out.println("Server is already running!");
			return false;
		}else{
			System.out.println("Starting server " + profile + "@" + ProfileProperties.SCREENPREFIX.get(profile) + profile);
			Utils.executeCommand(buildCommand(), ((String)ProfileProperties.SERVERDIR.get(profile)).replace("{profile}","profiles/"+profile));
			//Utils.sleep(3000);
			//return Tasks.STATUS.run();
			return true; //We should belive in ourselves
		}
	}
}
