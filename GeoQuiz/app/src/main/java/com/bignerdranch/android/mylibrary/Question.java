package com.bignerdranch.android.mylibrary;

/**
 * Created by ADMIN on 6/7/2017.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    public Question(int mtextResId,boolean manswerTrue)
    {
        mTextResId=mtextResId;
        mAnswerTrue=manswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

}
