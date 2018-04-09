package co.aeons.zombie.shooter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import co.aeons.zombie.shooter.ZombieShooter;

public class AndroidLauncher extends AndroidApplication implements PlayServices {

	private final int RC_SIGN_IN = 100;

	Context appContext;
	GoogleSignInClient mGoogleSignInClient;
	GoogleSignInAccount mGoogleSignInAccount;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mGoogleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
		signInSilently();

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new ZombieShooter(this), config);
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		this.appContext = this.getApplicationContext();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		// TODO: Fix
		// gameHelper.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		signInSilently();
	}

	protected void signInSilently() {
		mGoogleSignInClient.silentSignIn().addOnCompleteListener(
				new OnCompleteListener<GoogleSignInAccount>() {
					@Override
					public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
						if (task.isSuccessful()) {
							mGoogleSignInAccount = task.getResult();
						} else {
							signIn();
						}
					}
				}
		);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RC_SIGN_IN) {
			GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
			if (result.isSuccess()) {
				mGoogleSignInAccount = result.getSignInAccount();
			} else {
				String message = result.getStatus().getStatusMessage();
				if (message == null || message.isEmpty()) {
					message = "Login failed. I have no clue why.";
				}
				new AlertDialog.Builder(this).setMessage(message)
						.setNeutralButton(android.R.string.ok, null).show();

			}
		}

	}

	@Override
	public void signIn()
	{
		Intent intent = mGoogleSignInClient.getSignInIntent();
		startActivityForResult(intent, RC_SIGN_IN);
	}

	@Override
	public void signOut()
	{
		try
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					// TODO: FIX
					// gameHelper.signOut();
				}
			});
		}
		catch (Exception e)
		{
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void rateGame()
	{
		String str = "Your PlayStore Link";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void unlockAchievement()
	{
		// TODO FIX
		// Games.Achievements.unlock(gameHelper.getApiClient(),
		//		getString(R.string.achievement_dum_dum));
	}

	@Override
	public void submitScore(int highScore)
	{
		/* if (isSignedIn() == true)
		{
		// TODO FIX
		 	Games.Leaderboards.submitScore(gameHelper.getApiClient(),
				getString(R.string.leaderboard_highest), highScore);
		} */
	}

	@Override
	public void showAchievement()
	{
		/* TODO FIX
		if (isSignedIn())
		{
			startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient(),
					getString(R.string.achievement_dum_dum)), requestCode);
		}
		else
		{
			signIn();
		}*/
	}

	@Override
	public void showScore()
	{
		/*
		if (isSignedIn())
		{
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
					getString(R.string.leaderboard_highest)), requestCode);
		}
		else
		{
			signIn()
		}*/
	}

	@Override
	public boolean isSignedIn()
	{
		return GoogleSignIn.getLastSignedInAccount(this) != null;

	}
}
