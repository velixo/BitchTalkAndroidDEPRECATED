package com.velixo.bitchtalkandroid.command.clientside;

import com.velixo.bitchtalkandroid.clientSide.ClientGui;
import com.velixo.bitchtalkandroid.command.Command;
import com.velixo.bitchtalkandroid.statics.StaticVariables;

public class ClientCelebrate implements Command {
	private ClientGui gui;
	
	public ClientCelebrate(ClientGui gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		gui.playSound(StaticVariables.CELEBRATE);
	}

}
