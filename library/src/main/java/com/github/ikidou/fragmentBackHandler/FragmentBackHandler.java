
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

package com.github.ikidou.fragmentBackHandler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

public interface FragmentBackHandler {
    boolean onBackPressed();

    class Helper {
        public static boolean handleBackPress(FragmentManager fragmentManager) {

            List<Fragment> fragments = fragmentManager.getFragments();

            if (fragments == null) {
                return false;
            }

            for (int i = fragments.size() - 1; i >= 0; i--) {
                Fragment child = fragments.get(i);

                if (child != null
                        && child.isVisible()
                        && child instanceof FragmentBackHandler
                        && ((FragmentBackHandler) child).onBackPressed()) {
                    return true;
                }
            }

            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
                return true;
            }
            return false;
        }

        public static boolean handleBackPress(Fragment fragment) {
            return handleBackPress(fragment.getChildFragmentManager());
        }

        public static boolean handleBackPress(FragmentActivity fragmentActivity) {
            return handleBackPress(fragmentActivity.getSupportFragmentManager());
        }
    }
}
