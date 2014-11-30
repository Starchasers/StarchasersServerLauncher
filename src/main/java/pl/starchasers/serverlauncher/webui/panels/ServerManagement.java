package pl.starchasers.serverlauncher.webui.panels;

import java.util.Map.Entry;

import net.magik6k.jwwf.handlers.ClickHandler;
import net.magik6k.jwwf.widgets.basic.TextLabel;
import net.magik6k.jwwf.widgets.basic.input.Button;
import net.magik6k.jwwf.widgets.basic.panel.TabbedPanel;
import net.magik6k.jwwf.widgets.basic.panel.VerticalPanel;
import pl.starchasers.serverlauncher.LauncherMain;
import pl.starchasers.serverlauncher.manager.tasks.StartServer;

import com.typesafe.config.ConfigValue;

public class ServerManagement extends TabbedPanel{

	
	
	public ServerManagement(String username) {
		super(LauncherMain.config.getObject("profiles").size());
		for(Entry<String, ConfigValue> profile : LauncherMain.config.getObject("profiles").entrySet()){
			this.put(new ServerManager(username, profile.getKey()), profile.getKey());
		}
	}
	
	private static class ServerManager extends VerticalPanel{
		private TextLabel serverStatus;
		
		public ServerManager(String username, final String server) {
			super(2);
			serverStatus = new TextLabel("[TODO-status] < in-button?");
			this.put(serverStatus);
			
			this.put(new Button("Start", new ClickHandler() {
				
				@Override
				public void clicked() {
					new StartServer(server).runTask();
				}
			}));
		}
		
	}
	
}
