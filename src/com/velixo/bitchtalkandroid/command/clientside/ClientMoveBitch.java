package com.velixo.bitchtalkandroid.command.clientside;

import com.velixo.bitchtalkandroid.clientSide.ClientGui;
import com.velixo.bitchtalkandroid.command.Command;


public class ClientMoveBitch implements Command {
	private ClientGui gui;
	
	public ClientMoveBitch(ClientGui gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		gui.playSound("movebitchgetoutdaway");
	}

}
