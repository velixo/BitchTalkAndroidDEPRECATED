package com.velixo.bitchtalkandroid.clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import android.os.AsyncTask;

import com.velixo.bitchtalkandroid.command.ClientCommandFactory;
import com.velixo.bitchtalkandroid.command.Command;

public class Client {
	
	private Socket connection;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private ClientGui gui;
	private ListenForMessagesThread listenForMessagesThread = new ListenForMessagesThread();
	private ClientCommandFactory factory;
	
	public Client(ClientGui g){
		gui = g;
		factory = new ClientCommandFactory(gui,this);
		gui.showMessage(factory.help());
	}
	
	//TODO check that this code is correct
	public boolean connected() {
		if (connection != null)
			return !connection.isClosed();
		return false;
	}
	
	public void connect(String ip){
		ConnectTask ct = new ConnectTask(ip);
		ct.execute();
//		try {
//			connection = new Socket(InetAddress.getByName(ip),9513);
//			output = new ObjectOutputStream(connection.getOutputStream());
//			output.flush();
//			input = new ObjectInputStream(connection.getInputStream());
//			if (listenForMessagesThread != null)
//				listenForMessagesThread.stopThread();
//			listenForMessagesThread = new ListenForMessagesThread();
//			listenForMessagesThread.start();
//		} catch (IOException e) {
//			gui.showMessage("I'm afraid I can't let you do that, bitch.");
//		}
	}
	public void send(String message){
		try {
			if (message.charAt(0) == '/' && message.charAt(1) != ':' && factory.canBuild(message)) {
				Command c = factory.build(message);
				c.run();
			}
			else if(output!=null){
				output.writeObject(message);
				output.flush();
				System.out.println("flushed, bitch");
			}
			else{
				gui.showMessage("You are not connected to any server.");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void closeCrap(){
		gui.showMessage("bitch, I'm out.");
		try{
			output.close();
			input.close();
			connection.close();
         }catch(IOException ioe){
        	ioe.printStackTrace();
         }
	}
	
	
	private class ListenForMessagesThread extends Thread {
		private boolean runThread;
		public ListenForMessagesThread() {
			runThread = true;
		}
		
		public void run() {
			//TODO disconnect functionality
			while(runThread) {
				try {
					//TODO refactor this code. assuming the object is a String or a List<String> is iffy design.
					Object received = input.readObject();
					if (received instanceof String) {
						String message = (String) received;
						System.out.println(message);
						if (message.charAt(0) == '/') {	//TODO varf�r !contains?
							Command c = factory.build(message);
							c.run();
						} else {
							gui.showMessage(message);
						}
					} else if (received instanceof List){
						@SuppressWarnings("unchecked")
						List<String> usernames = (List<String>) received;
						gui.updateUsersWindow(usernames);
					}
				} catch (ClassNotFoundException | IOException e) {
					gui.showMessage("Disconnected from server");
					closeCrap();
					break;
				}
			}
		}
		
		public void stopThread() {
			runThread = false;
		}
	}
	
	private class ConnectTask extends AsyncTask {
		private String ip;
		
		public ConnectTask(String ip) {
			 this.ip = ip;
		}

		@Override
		protected Object doInBackground(Object... params) {
			try {
				connection = new Socket(InetAddress.getByName(ip),9513);
				output = new ObjectOutputStream(connection.getOutputStream());
				output.flush();
				input = new ObjectInputStream(connection.getInputStream());
				if (listenForMessagesThread != null)
					listenForMessagesThread.stopThread();
				listenForMessagesThread = new ListenForMessagesThread();
				listenForMessagesThread.start();
			} catch (IOException e) {
				gui.showMessage("I'm afraid I can't let you do that, bitch.");
			}
			return null;
		}

	}
	
}
