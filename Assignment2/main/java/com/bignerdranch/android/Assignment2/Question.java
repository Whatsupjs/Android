package com.bignerdranch.android.Assignment2;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mUserCheated;
    private boolean mUserHinted;
    private boolean mUserAnswered;

    public Question(int textResId, boolean answerTrue, boolean userCheated, boolean userHinted, boolean userAnswered) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mUserCheated = userCheated;
        mUserHinted = userHinted;
        mUserAnswered = userAnswered;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean didUserCheat() { return mUserCheated; }

    public boolean didUserHint() { return mUserHinted; }

    public boolean didUserAnswer() { return mUserAnswered; }

    public void setUserCheated(boolean userCheated) { mUserCheated = userCheated; }

    public void setUserHinted(boolean userHinted) { mUserHinted = userHinted; }

    public void setUserAnswered(boolean userAnswered) { mUserAnswered = userAnswered; }
}
