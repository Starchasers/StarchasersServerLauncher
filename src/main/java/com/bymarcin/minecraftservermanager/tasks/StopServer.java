package com.bymarcin.minecraftservermanager.tasks;

import com.bymarcin.minecraftservermanager.ITask;
import com.bymarcin.minecraftservermanager.Tasks;
import com.bymarcin.minecraftservermanager.Utils;

public class StopServer implements ITask{

	
	@Override
	public boolean runTask() {
		if(Tasks.STATUS.run()){
			System.out.println("Server Stopping!");
			for(int i = 0; i<10; i++){
				Utils.executeCommand(Utils.serverCommand(String.format("say SERVER SHUTTING DOWN IN %d SECONDS.", 10 - i)));
				Utils.sleep(1000);
			}
			Utils.executeCommand(Utils.serverCommand("say Saving map..."));
			Utils.executeCommand(Utils.serverCommand("save-all"));
			Utils.executeCommand(Utils.serverCommand("stop"));
			Utils.sleep(10000);
			return !Tasks.STATUS.run();
		}else{
			System.out.println("Server was not running.");
			return true;
		}
	}

}
