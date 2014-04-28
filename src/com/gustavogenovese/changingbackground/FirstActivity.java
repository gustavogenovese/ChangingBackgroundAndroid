package com.gustavogenovese.changingbackground;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class FirstActivity extends Activity {
	private boolean wasChangedToGreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (wasChangedToGreen){
			TransitionDrawable transition = (TransitionDrawable) getResources().getDrawable(R.drawable.transition_green2blue);
			transition.startTransition(2000);
			LinearLayout layout = (LinearLayout)findViewById(R.id.layout_first_activity);
			layout.setBackground(transition);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		LinearLayout layout = (LinearLayout)findViewById(R.id.layout_first_activity);
		layout.setBackgroundResource(R.drawable.green);
		wasChangedToGreen = true;
	}

	public static class PlaceholderFragment extends Fragment {

		private Button button;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_first,
					container, false);
			return rootView;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			LinearLayout layout = (LinearLayout)getActivity().findViewById(R.id.layout_first_activity);
			layout.setBackgroundResource(R.drawable.blue);
			
			button = (Button)getActivity().findViewById(R.id.btn_go_to_second);
			button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(), SecondActivity.class);
					startActivity(intent);
				}
			});
		}
	}
}
