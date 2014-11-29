package com.bymarcin.minecraftservermanager.tasks;

import java.util.Calendar;

import pl.starchasers.serverlauncher.manager.tasks.Tasks;

import com.bymarcin.minecraftservermanager.Config;
import com.bymarcin.minecraftservermanager.ITask;
import com.bymarcin.minecraftservermanager.Utils;

public class ServerBackup implements ITask{
	Calendar date = Calendar.getInstance();
	public String buildCommand(){
		String currentDate = date.get(Calendar.DAY_OF_MONTH) +"-"+ (date.get(Calendar.MONTH)+1) + "-" + 
								date.get(Calendar.YEAR) + "_"+date.get(Calendar.HOUR_OF_DAY) + "-" + date.get(Calendar.MINUTE);

		return "zip -q -r "+ Config.BACKUP_PATH.get() + "/" + Config.BACKUP_NAME.get() +"_"+  currentDate + ".zip " + Config.SERVER_PATH.get()+
				" -x " + Config.SERVER_PATH.get() + "/dynmap/*";
	}

	@Override
	public boolean runTask() {
		if(Tasks.STATUS.run()){System.out.println("Server is running!"); return false;}
		String response = Utils.executeCommand(buildCommand());
		if(response.isEmpty()){
			return true;
		}else{
			System.out.println(response);
			return false;
		}
	}
}
