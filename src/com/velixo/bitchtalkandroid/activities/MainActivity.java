package com.velixo.bitchtalkandroid.activities;


import java.util.List;

import com.velixo.bitchtalkandroid.R;
import com.velixo.bitchtalkandroid.clientSide.ClientGui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity implements ClientGui {
	private EditText chatWindow;
	private EditText chatInput;
	
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatWindow = (EditText) findViewById(R.id.chatWindow);
        chatInput = (EditText) findViewById(R.id.chatInput);
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
	public void showMessage(String m) {
		chatWindow.append(m + "\n");
		playNotificationSound();
	}
	
	private void playNotificationSound() {
		if (notificationSoundMuted) {
			notificationSound.start();
		}
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

		default:
			break;
		}
	}
}