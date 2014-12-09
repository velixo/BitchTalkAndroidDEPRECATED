package com.velixo.bitchtalkandroid.command.clientside;

import com.velixo.bitchtalkandroid.clientSide.ClientGui;
import com.velixo.bitchtalkandroid.command.Command;


public class ToggleMute implements Command {
	private ClientGui c;
	
	public ToggleMute(ClientGui c) {
		this.c = c;
	}

	@Override
	public void run() {
		if (c.getNotificationSoundMuted()) {
			c.setMuteNotificationSound(false);
			c.showMessage("Your notification sounds are now unmuted, bitch.");
		} else {
			c.setMuteNotificationSound(true);
			c.showMessage("Your notification sounds are now muted, bitch.");
		}
	}

}
