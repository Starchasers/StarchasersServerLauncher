package pl.starchasers.serverlauncher;

import net.magik6k.jwwf.core.MainFrame;
import net.magik6k.jwwf.handlers.ClickHandler;
import net.magik6k.jwwf.widgets.basic.TextLabel;
import net.magik6k.jwwf.widgets.basic.input.Button;
import net.magik6k.jwwf.widgets.basic.input.PasswordInput;
import net.magik6k.jwwf.widgets.basic.input.TextInput;
import net.magik6k.jwwf.widgets.basic.panel.VerticalPanel;

public class LoginPanel extends VerticalPanel {

	public LoginPanel(final MainFrame root) {
		super(3);
		final TextInput login = new TextInput("Login");
		final PasswordInput password = new PasswordInput("Password");
		
		this.put(login);
		this.put(password);
		this.put(new Button("Login", new ClickHandler() {
			
			@Override
			public void clicked() {
				if(PermissionManager.instance.checkUser(login.getText(), password.getText())){
					root.put(new TextLabel("Logged in"));
				}
			}
		}));
	}
	
}
