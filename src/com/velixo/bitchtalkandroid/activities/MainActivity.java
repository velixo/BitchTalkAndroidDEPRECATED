package com.velixo.bitchtalkandroid.activities;


import java.util.List;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.velixo.bitchtalkandroid.R;
import com.velixo.bitchtalkandroid.clientSide.Client;
import com.velixo.bitchtalkandroid.clientSide.ClientGui;

public class MainActivity extends Activity implements ClientGui {
	private TextView chatWindow;
	private EditText chatInput;
	private Client client;
	
	private boolean notificationSoundMuted = false;
	
	private MediaPlayer notificationSound;
	private MediaPlayer userJoinedSound;
	private MediaPlayer userLeftSound;
	private MediaPlayer wooloolooSound;
	private MediaPlayer bossAssBitchSound;
	private MediaPlayer whatsGoingSound;
	private MediaPlayer moveBitchSound;
	
	public final String WOOLOOLOO = "woolooloo";
	public final String BOSSASSBITCH = "bossassbitch";
	public final String WHATSGOINGON = "whatsgoingon";
	public final String MOVEBITCH = "movebitchgetoutdaway";
	private final String NOTIFICATION = "notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatWindow = (TextView) findViewById(R.id.chatWindow);
        chatInput = (EditText) findViewById(R.id.chatInput);
        client = new Client(this);
        setMessageSending();
        
        loadSounds();
    }
    
    private void loadSounds() {
    	notificationSound = MediaPlayer.create(this, R.raw.notificationsound);
    	userJoinedSound = MediaPlayer.create(this, R.raw.joinchatsound);
    	userLeftSound = MediaPlayer.create(this, R.raw.leavechatsound);
    	wooloolooSound = MediaPlayer.create(this, R.raw.woolooloo);
    	bossAssBitchSound = MediaPlayer.create(this, R.raw.bossassbitch);
    	whatsGoingSound = MediaPlayer.create(this, R.raw.whatsgoingon);
    	moveBitchSound = MediaPlayer.create(this, R.raw.movebitchgetoutdaway);
    }
    
	@Override
	public void showMessage(final String m) {
		chatWindow.post(new Runnable() {

			@Override
			public void run() {
				chatWindow.append(m + "\n");
				playSound(NOTIFICATION);
//				playNotificationSound();
			}
		});
	}
	
	@Override
	public void showSilentMessage(String m) {
		chatWindow.append(m + "\n");
	}

	@Override
	public void setMuteNotificationSound(boolean b) {
		notificationSoundMuted = b;
	}

	@Override
	public boolean getNotificationSoundMuted() {
		return notificationSoundMuted;
	}

	@Override
	public void updateUsersWindow(List<String> usersInConvo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSound(String soundName) {
		switch (soundName) {
		case WOOLOOLOO:
			wooloolooSound.start();
			break;
			
		case BOSSASSBITCH:
			bossAssBitchSound.start();
			break;
			
		case WHATSGOINGON:
			whatsGoingSound.start();
			break;
			
		case MOVEBITCH:
			moveBitchSound.start();
			break;
			
		case NOTIFICATION:
			if (notificationSoundMuted)
				notificationSound.start();
		default:
			break;
		}
	}
	
	private void setMessageSending() { //TODO fix method name
		chatInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					String message = chatInput.getText().toString();
					if(!message.equals("")) {
						client.send(message);
						chatInput.setText("");
					}
					return true;
				}
				return false;
			}
		});
	}
}
