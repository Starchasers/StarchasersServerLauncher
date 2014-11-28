package com.bymarcin.minecraftservermanager.tasks;

import com.bymarcin.minecraftservermanager.Config;
import com.bymarcin.minecraftservermanager.ITask;
import com.bymarcin.minecraftservermanager.Utils;

public class ServerStatus implements ITask {
	
	public String buildCommand(){
		return "pgrep -f -c " + Config.SERVER_NAME.get();
	}
	
	public int getProcessCount(){
		String result = Utils.executeCommand(buildCommand());
		return Integer.valueOf(result.trim());
	}
	
	@Override
	public boolean runTask(){
		return getProcessCount()>0;
	}
}