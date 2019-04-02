package com.dtoprak.beko1000tr.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dtoprak.beko1000tr.R;

public class MainActivity extends AppCompatActivity {
	private ImageView homeButton;
	private ImageView settingsButton;
	private Fragment currentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		homeButton = findViewById(R.id.bottom_navigation_home);
		settingsButton = findViewById(R.id.bottom_navigation_settings);

		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		HomeFragment homeFragment = new HomeFragment();
		currentFragment = homeFragment;
		homeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
		fragmentTransaction.replace(R.id.main_content_container, homeFragment);
		fragmentTransaction.commit();

		homeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!(currentFragment instanceof HomeFragment)) {
					HomeFragment homeFragment = new HomeFragment();
					currentFragment = homeFragment;
					settingsButton.setBackgroundColor(getResources().getColor(R.color.background_color, getTheme()));
					homeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
					FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
					fragmentTransaction.replace(R.id.main_content_container, homeFragment);
					fragmentTransaction.commit();
				}
			}
		});

		settingsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!(currentFragment instanceof SettingsFragment)) {
					SettingsFragment settingsFragment = new SettingsFragment();
					currentFragment = settingsFragment;
					homeButton.setBackgroundColor(getResources().getColor(R.color.background_color, getTheme()));
					settingsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
					FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
					fragmentTransaction.replace(R.id.main_content_container, settingsFragment);
					fragmentTransaction.commit();
				}
			}
		});
	}
}
