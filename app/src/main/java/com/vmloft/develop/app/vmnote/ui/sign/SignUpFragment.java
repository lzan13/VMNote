package com.vmloft.develop.app.vmnote.ui.sign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.vmloft.develop.app.vmnote.R;
import com.vmloft.develop.app.vmnote.base.AppFragment;
import com.vmloft.develop.library.tools.utils.VMReg;
import com.vmloft.develop.library.tools.widget.VMEditView;
import com.vmloft.develop.library.tools.widget.toast.VMToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lzan13 on 2018/4/16.
 * 注册 UI 界面
 */
public class SignUpFragment extends AppFragment {

    @BindView(R.id.sign_account_et)
    VMEditView mAccountEdit;
    @BindView(R.id.sign_password_et)
    VMEditView mPasswordEdit;
    @BindView(R.id.sign_up_btn)
    Button mSignUpBtn;

    private SignActivity mActivity;
    private String mAccount, mPassword;

    /**
     * 创建实例对象的工厂方法
     */
    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 初始化 Fragment 界面 layout_id
     *
     * @return 返回布局 id
     */
    @Override
    protected int layoutId() {
        return R.layout.fragment_sign_up;
    }

    /**
     * 初始化界面控件，将 Fragment 变量和 View 建立起映射关系
     */
    @Override
    protected void init() {
        super.init();
        mAccountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                verifyInputBox();
            }
        });
        mPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                verifyInputBox();
            }
        });
    }

    @OnClick({R.id.sign_up_btn, R.id.sign_in_go_btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_btn:
                signUp();
                break;
            case R.id.sign_in_go_btn:
                mActivity.switchFragment(0);
                break;
        }
    }

    /**
     * 登录
     */
    private void signUp() {
        if (!VMReg.isEmail(mAccount)) {
            VMToast.make((Activity) mContext, R.string.toast_invalid_email).error();
            return;
        }
        if (!VMReg.isNormalPassword(mPassword)) {
            VMToast.make((Activity) mContext, R.string.toast_invalid_password).error();
            return;
        }
        mActivity.signUp(mAccount, mPassword);
    }

    /**
     * 校验输入框
     */
    private void verifyInputBox() {
        mAccount = mAccountEdit.getText();
        mPassword = mPasswordEdit.getText();

        if (TextUtils.isEmpty(mPassword) || TextUtils.isEmpty(mAccount)) {
            mSignUpBtn.setEnabled(false);
        } else {
            mSignUpBtn.setEnabled(true);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (SignActivity) context;
    }
}