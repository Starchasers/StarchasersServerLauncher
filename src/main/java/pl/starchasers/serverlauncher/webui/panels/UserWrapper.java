package pl.starchasers.serverlauncher.webui.panels;

import net.magik6k.jwwf.widgets.basic.TextLabel;
import net.magik6k.jwwf.widgets.basic.panel.TabbedPanel;
import pl.starchasers.serverlauncher.webui.permissions.PermissionManager;

public class UserWrapper extends TabbedPanel {
	
	public UserWrapper(String username) {
		super(PermissionManager.instance.getUserRightCount(username)+1);
		
		this.put(new TextLabel("TODO"), "Overview");
		
		if(PermissionManager.instance.hasPermission(username, "update"))
			this.put(new TextLabel("TODO"), "Updating");
		
		if(PermissionManager.instance.hasPermission(username, "manage"))
			this.put(new ServerManagement(username), "Management");
		
		if(PermissionManager.instance.hasPermission(username, "serverinfo"))
			this.put(new TextLabel("TODO"), "System");
	}
}
