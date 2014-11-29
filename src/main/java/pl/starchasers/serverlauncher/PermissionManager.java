package pl.starchasers.serverlauncher;

public class PermissionManager {
	public static final PermissionManager instance = new PermissionManager();
	private PermissionManager(){}
	
	public boolean checkUser(String name, String password){
		return LauncherMain.config.hasPath("users."+name.replaceAll("\\.", "_")) &&
				LauncherMain.config.getConfig("users."+name.replaceAll("\\.", "_"))
				.getString("password").equals(password);
	}
}
