package com.velixo.bitchtalkandroid.command.clientside;

import com.velixo.bitchtalkandroid.clientSide.ClientGui;
import com.velixo.bitchtalkandroid.command.Command;


public class ClientWoolooloo implements Command {
	private ClientGui gui;

	public ClientWoolooloo(ClientGui g) {
		gui = g;
	}

	@Override
	public void run() {
		gui.playSound("woolooloo");
	}
}
