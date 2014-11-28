package com.bymarcin.minecraftservermanager.tasks;

import com.bymarcin.minecraftservermanager.ITask;
import com.bymarcin.minecraftservermanager.Tasks;
import com.bymarcin.minecraftservermanager.Utils;

public class SaveAll implements ITask{

	@Override
	public boolean runTask() {
		if(!Tasks.STATUS.run()){System.out.println("Server is not running!"); return false;}
		String response = Utils.executeCommand(Utils.serverCommand("save-all"));
		if(response.isEmpty()){
			return true;
		}else{
			System.out.println(response);
			return false;
		}
	}
	

}
