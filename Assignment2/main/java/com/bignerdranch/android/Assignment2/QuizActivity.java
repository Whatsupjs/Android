package com.bignerdranch.android.Assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final int REQUEST_CODE_HINT = 0;
    private int completeCounter = 0;
    private int totalCounter = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private Button mHintButton;
    private TextView mQuestionTextView;
    private TextView mTotalTextView;
    private TextView mCompletedTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true, false, false, false),
            new Question(R.string.question_oceans, true, false, false, false),
            new Question(R.string.question_mideast, false, false, false, false),
            new Question(R.string.question_africa, false, false, false, false),
            new Question(R.string.question_americas, true, false, false, false),
            new Question(R.string.question_asia, true, false, false, false),
    };

    private int mCurrentIndex = 0;
//    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mTotalTextView = (TextView) findViewById(R.id.total_mark);
        mCompletedTextView = (TextView) findViewById(R.id.question_completed);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//                mIsCheater = false;
                updateQuestion();
            }
        });

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex > 0){
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                } else{
                    mCurrentIndex = mQuestionBank.length - 1;
                }
//                mIsCheater = false;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        mHintButton = (Button) findViewById(R.id.hint_button);
        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = mCurrentIndex;
                Intent intent = HintActivity.newIntent(QuizActivity.this, index);
                startActivityForResult(intent, REQUEST_CODE_HINT);
            }
        });

        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
//            mIsCheater = CheatActivity.wasAnswerShown(data);
            mQuestionBank[mCurrentIndex].setUserCheated(CheatActivity.wasAnswerShown(data));
        }

        if (requestCode == REQUEST_CODE_HINT) {
            if (data == null) {
                return;
            }
            mQuestionBank[mCurrentIndex].setUserHinted(HintActivity.wasHintShown(data));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

//        if (mIsCheater){
        if (mQuestionBank[mCurrentIndex].didUserAnswer()){
            messageResId = R.string.answered_toast;
        } else {
            if (mQuestionBank[mCurrentIndex].didUserCheat()){
                messageResId = R.string.judgment_toast;
            } else if (mQuestionBank[mCurrentIndex].didUserHint()){
                if (userPressedTrue == answerIsTrue) {
                    messageResId = R.string.correct_toast_hint;
                    mQuestionBank[mCurrentIndex].setUserAnswered(true);
                    totalCounter += 1;
                    completeCounter++;
                    mTotalTextView.setText("Total Marks  " + totalCounter);
                    mCompletedTextView.setText("Questions Completed " + completeCounter);
                } else {
                    messageResId = R.string.incorrect_toast_hint;
                    mQuestionBank[mCurrentIndex].setUserAnswered(true);
                    totalCounter -= 2;
                    completeCounter++;
                    mTotalTextView.setText("Total Marks  " + totalCounter);
                    mCompletedTextView.setText("Questions Completed " + completeCounter);
                }
            } else {
                if (userPressedTrue == answerIsTrue) {
                    messageResId = R.string.correct_toast;
                    mQuestionBank[mCurrentIndex].setUserAnswered(true);
                    totalCounter += 2;
                    completeCounter++;
                    mTotalTextView.setText("Total Marks  " + totalCounter);
                    mCompletedTextView.setText("Questions Completed " + completeCounter);
                } else {
                    messageResId = R.string.incorrect_toast;
                    mQuestionBank[mCurrentIndex].setUserAnswered(true);
                    totalCounter -= 1;
                    completeCounter++;
                    mTotalTextView.setText("Total Marks  " + totalCounter);
                    mCompletedTextView.setText("Questions Completed " + completeCounter);
                }
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }
}
