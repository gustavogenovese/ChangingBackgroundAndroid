package com.gustavogenovese.changingbackground;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		private Button backButton;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_second,
					container, false);
			return rootView;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
			
			TransitionDrawable transition = (TransitionDrawable) getResources().getDrawable(R.drawable.transition_blue2green);
			transition.startTransition(2000);
			LinearLayout layout = (LinearLayout)getActivity().findViewById(R.id.layout_second_activity);
			layout.setBackground(transition);
			
			backButton = (Button)getActivity().findViewById(R.id.btn_go_back);
			backButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					getActivity().onBackPressed();
				}
			});
		}
	}
}
