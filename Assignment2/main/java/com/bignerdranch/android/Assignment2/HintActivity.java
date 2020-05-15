package com.bignerdranch.android.Assignment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private static final String EXTRA_HINT_INDEX = "com.bignerdranch.android.Assignment2.index";

    private static final String EXTRA_HINT_SHOWN =
            "com.bignerdranch.android.Assignment2.hint_shown";   //was geoquiz

    private int mIndex;

    private TextView mHintTextView;
    private Button mShowHintButton;
    private Button mExitButton;

    private int mHint[] = {
      R.string.hint_australia,
      R.string.hint_oceans,
      R.string.hint_mideast,
      R.string.hint_africa,
      R.string.hint_americas,
      R.string.hint_asia
    };

    public static Intent newIntent(Context packageContext, int currentIndex) {
        Intent intent = new Intent(packageContext, HintActivity.class);
        intent.putExtra(EXTRA_HINT_INDEX, currentIndex);
        return intent;
    }

    public static boolean wasHintShown(Intent result) {
        return result.getBooleanExtra(EXTRA_HINT_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mIndex = getIntent().getIntExtra(EXTRA_HINT_INDEX, 0);
        mHintTextView = (TextView) findViewById(R.id.hint_text_view);

        mShowHintButton = (Button) findViewById(R.id.show_hint_button);
        mShowHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Hint = mHint[mIndex];
                mHintTextView.setText(Hint);
                setHintShownResult(true);
            }
        });

        mExitButton = (Button) findViewById(R.id.exit_button);
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void setHintShownResult(boolean isHintShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_HINT_SHOWN, isHintShown);
        setResult(RESULT_OK, data);
    }
}
