package com.velixo.bitchtalkandroid.command;

import java.util.StringTokenizer;

import com.velixo.bitchtalkandroid.clientSide.Client;
import com.velixo.bitchtalkandroid.clientSide.ClientGui;
import com.velixo.bitchtalkandroid.command.clientside.AlreadyConnected;
import com.velixo.bitchtalkandroid.command.clientside.ClientBossAssBitch;
import com.velixo.bitchtalkandroid.command.clientside.ClientCelebrate;
import com.velixo.bitchtalkandroid.command.clientside.ClientMoveBitch;
import com.velixo.bitchtalkandroid.command.clientside.ClientOpen;
import com.velixo.bitchtalkandroid.command.clientside.ClientWhatsGoingOn;
import com.velixo.bitchtalkandroid.command.clientside.ClientWoolooloo;
import com.velixo.bitchtalkandroid.command.clientside.Connect;
import com.velixo.bitchtalkandroid.command.clientside.Mute;
import com.velixo.bitchtalkandroid.command.clientside.Unmute;
import com.velixo.bitchtalkandroid.statics.StaticVariables;


public class ClientCommandFactory {
	public final static String HELP = StaticVariables.HELP;
	public final static String MUTE = StaticVariables.MUTE;
	public final static String UNMUTE = StaticVariables.UNMUTE;
	public final static String CONNECT = StaticVariables.CONNECT;
	
	public final static String SERVERWOOLOOLOO = StaticVariables.SERVERWOOLOOLOO;
	public final static String SERVERBOSSASSBITCH = StaticVariables.SERVERBOSSASSBITCH;
	public final static String SERVERWHATSGOINGON = StaticVariables.SERVERWHATSGOINGON;
	public final static String SERVERMOVEBITCHGETOUTDAWAY = StaticVariables.SERVERMOVEBITCHGETOUTDAWAY;
	public final static String SERVEROPEN = StaticVariables.SERVEROPEN;
	public final static String SERVERCELEBRATE = StaticVariables.SERVERCELEBRATE;
	
	private Client client;
	private ClientGui clientGui;
	
	public ClientCommandFactory(ClientGui cg, Client c) {
		clientGui = cg;
		client = c;
		
	}
	public boolean canBuild(String in){
		return !(build(in) instanceof NotACommand);
	}
	public String help(){
		return "type /connect <ip-address> to connect, bitch.";
	}
	
	public Command build(String input) {
		
		StringTokenizer st = new StringTokenizer(input);
		
		
		switch (st.nextToken()) {
		case MUTE:
			return new Mute(clientGui);
			
		case UNMUTE:
			return new Unmute(clientGui);
			
		case SERVERWOOLOOLOO:
			return new ClientWoolooloo(clientGui);
			
		case SERVERBOSSASSBITCH:
			return new ClientBossAssBitch(clientGui);
			
		case SERVERWHATSGOINGON:
			return new ClientWhatsGoingOn(clientGui);
			
		case SERVEROPEN:
			return new ClientOpen(clientGui);
			
		case SERVERCELEBRATE:
			return new ClientCelebrate(clientGui);

		case SERVERMOVEBITCHGETOUTDAWAY:
			return new ClientMoveBitch(clientGui);
		
		case CONNECT:
			if (client.connected())
				return new AlreadyConnected(clientGui);
			if(st.hasMoreTokens())
				return new Connect(st.nextToken(),client);
			else
				return new NotACommand(clientGui);
		
		default:
			return new NotACommand(clientGui);
		}
	}
	
}
