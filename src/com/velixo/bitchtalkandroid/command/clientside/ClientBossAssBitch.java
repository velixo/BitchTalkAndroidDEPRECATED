package com.velixo.bitchtalkandroid.command.clientside;

import com.velixo.bitchtalkandroid.clientSide.ClientGui;
import com.velixo.bitchtalkandroid.command.Command;
import com.velixo.bitchtalkandroid.statics.StaticVariables;


public class ClientBossAssBitch implements Command {
	private ClientGui g;
	
	public ClientBossAssBitch(ClientGui g) {
		this.g = g;
	}

	@Override
	public void run() {
		g.playSound(StaticVariables.BOSSASSBITCH);
	}

}
