package pl.starchasers.serverlauncher.webui;

import net.magik6k.jwwf.core.MainFrame;
import net.magik6k.jwwf.core.User;
import pl.starchasers.serverlauncher.webui.panels.LoginPanel;

public class LauncherClient extends User{

	@Override
	protected void initializeUser(MainFrame rootFrame) {
		rootFrame.put(new LoginPanel(rootFrame));
	}

}
