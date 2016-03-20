/*
 * Copyright 2016 ikidou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.ikidou.FragmentBackHandler.sample;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ikidou.fragmentBackHandler.BackHandledFragment;


public class MyFragment extends BackHandledFragment implements View.OnClickListener {

    private String toastText;

    public MyFragment() {
    }

    public static MyFragment newInstance(String toastText) {
        Bundle args = new Bundle();
        args.putString("text", toastText);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    boolean backHandled;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        toastText = getArguments().getString("text");
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.tv_title)).setText(toastText);
        view.findViewById(R.id.btn_add_child).setOnClickListener(this);
        view.findViewById(R.id.btn_replace_child).setOnClickListener(this);
        view.findViewById(R.id.btn_pop).setOnClickListener(this);
        ((RadioGroup) view.findViewById(R.id.rg_interceptor)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_interceptor_yes) {
                    backHandled = true;
                } else {
                    backHandled = false;
                }
            }
        });
    }

    @Override
    public boolean interceptBackPressed() {
        if (backHandled) {
            Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_child:
                v.setClickable(false);
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.child_content, MyFragmentFactory.getInstance().getFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_replace_child:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.child_content, MyFragmentFactory.getInstance().getFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_pop:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
