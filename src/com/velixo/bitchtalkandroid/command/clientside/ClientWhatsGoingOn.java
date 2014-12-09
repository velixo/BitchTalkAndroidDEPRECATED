package com.velixo.bitchtalkandroid.command.clientside;

import com.velixo.bitchtalkandroid.clientSide.ClientGui;
import com.velixo.bitchtalkandroid.command.Command;


public class ClientWhatsGoingOn implements Command {
	private ClientGui gui;
	
	public ClientWhatsGoingOn(ClientGui gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		gui.playSound("whatsgoingon");
	}

	
}
