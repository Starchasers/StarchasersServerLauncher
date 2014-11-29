package com.bymarcin.minecraftservermanager.tasks;

import pl.starchasers.serverlauncher.manager.tasks.Tasks;

import com.bymarcin.minecraftservermanager.Config;
import com.bymarcin.minecraftservermanager.ITask;
import com.bymarcin.minecraftservermanager.Utils;

public class StartServer implements ITask{

	private String buildCommand(){
		return "screen -dmS MSM_"+ Config.SCREEN_NAME.get() +" "+ Config.SERVER_COMMAND.get();
	}
	
	@Override
	public boolean runTask(){
		System.out.println(buildCommand());
		if(Tasks.STATUS.run()){
			System.out.println("Server is already running!");
			return false;
		}else{
			Utils.executeCommand(buildCommand(), Config.SERVER_PATH.get());
			Utils.sleep(3000);
			return Tasks.STATUS.run();
		}
	}
}
