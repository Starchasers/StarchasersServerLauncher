package pl.starchasers.serverlauncher.webui.permissions;

import pl.starchasers.serverlauncher.LauncherMain;

public class PermissionManager {
	public static final PermissionManager instance = new PermissionManager();
	private PermissionManager(){}
	
	public boolean checkUser(String name, String password){
		return LauncherMain.config.hasPath("users."+name.replaceAll("\\.", "_")) &&
				LauncherMain.config.getConfig("users."+name.replaceAll("\\.", "_"))
				.getString("password").equals(password);
	}
	
	public boolean hasPermission(String name, String permission){
		return LauncherMain.config.hasPath("users."+name.replaceAll("\\.", "_")) &&
				LauncherMain.config.getConfig("users."+name.replaceAll("\\.", "_")).getBoolean(permission);
	}
	
	public int getUserRightCount(String name){
		int c = 0;
		c += hasPermission(name, "update")?1:0;
		c += hasPermission(name, "manage")?1:0;
		c += hasPermission(name, "serverinfo")?1:0;
		return c;
	}
}
