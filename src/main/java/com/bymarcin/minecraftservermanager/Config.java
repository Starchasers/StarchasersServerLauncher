package com.bymarcin.minecraftservermanager;

/**
 * Created by marcin212 on 2014-10-03.
 */
public enum Config {
	GIT_REPO_PATH("/home/marcin/gitlab"),
    SERVER_PATH("/home/marcin/server"),
    CLIENT_PATH("/home/marcin/clientmod"),
    BACKUP_PATH("/home/marcin/backup"),
    BACKUP_NAME("StarchasersModpackBackup"),
    SERVER_NAME("forge-1.7.10-10.13.0.1208-universal.jar"),
    SERVER_COMMAND("java -Xmx1G -jar " + Config.SERVER_NAME.get() + " nogui"),
    SCREEN_NAME("StarchasersModpackServer")
    ;

    String value;

    Config(){
        value = null;
    }

    Config(String dv){
       value = dv;
    }

    public String get(){
        return Main.getProperties().getProperty(this.name(),getDefaultValue());
    }

    public String getDefaultValue(){
        return value;
    }

}
