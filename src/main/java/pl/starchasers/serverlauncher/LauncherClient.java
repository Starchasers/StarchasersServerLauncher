package pl.starchasers.serverlauncher;

import net.magik6k.jwwf.core.MainFrame;
import net.magik6k.jwwf.core.User;

public class LauncherClient extends User{

	@Override
	protected void initializeUser(MainFrame rootFrame) {
		rootFrame.put(new LoginPanel(rootFrame));
	}

}
