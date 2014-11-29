package pl.starchasers.serverlauncher.manager;

import pl.starchasers.serverlauncher.LauncherMain;

public enum ProfileProperties {
	AUTOSTART("autostart", false),
	SERVERDIR("server", "{profile}/server"),
	CLIENTDIR("client", "{profile}/client"),
	SYNCDIR("sync", "{profile}/sync")
	;
	
	private String name;
	private Object defaultValue;
	
	ProfileProperties(String name, Object defaultValue){
		this.defaultValue = defaultValue;
		this.name = name;
	}
	
	public Object get(String profile){
		return LauncherMain.config.hasPath("profiles."+profile.replaceAll("\\.", "_")+"."+name) ?
				LauncherMain.config.getAnyRef("profiles."+profile.replaceAll("\\.", "_")+"."+name)
				: defaultValue;
	}
}
