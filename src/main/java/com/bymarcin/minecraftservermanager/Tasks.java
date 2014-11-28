package com.bymarcin.minecraftservermanager;

import com.bymarcin.minecraftservermanager.sync.FileListGenerator;
import com.bymarcin.minecraftservermanager.tasks.SaveAll;
import com.bymarcin.minecraftservermanager.tasks.ServerBackup;
import com.bymarcin.minecraftservermanager.tasks.ServerStatus;
import com.bymarcin.minecraftservermanager.tasks.SetupClient;
import com.bymarcin.minecraftservermanager.tasks.SetupServer;
import com.bymarcin.minecraftservermanager.tasks.StartServer;
import com.bymarcin.minecraftservermanager.tasks.StopServer;

/**
 * Created by marcin212 on 2014-10-03.
 */
public enum Tasks {
    START(StartServer.class),
    STATUS(ServerStatus.class),
    STOP(StopServer.class),
    SAVEALL(SaveAll.class),
    BACKUP(ServerBackup.class),
    SETUPSERVER(SetupServer.class),
    SETUPCLIENT(SetupClient.class),
    GENERATEFILELIST(FileListGenerator.class);

    private ITask task;
    Tasks(Class<? extends ITask> clazz){
        try {
            task = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean run(){
        return task.runTask();
    }
}
