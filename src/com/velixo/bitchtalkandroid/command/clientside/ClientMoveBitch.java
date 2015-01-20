package com.velixo.bitchtalkandroid.command.clientside;

import com.velixo.bitchtalkandroid.clientSide.ClientGui;
import com.velixo.bitchtalkandroid.command.Command;
import com.velixo.bitchtalkandroid.statics.StaticVariables;


public class ClientMoveBitch implements Command {
	private ClientGui g;
	
	public ClientMoveBitch(ClientGui g) {
		this.g = g;
	}

	@Override
	public void run() {
		g.playSound(StaticVariables.SERVERBOSSASSBITCH);
	}

}
