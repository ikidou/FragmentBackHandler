
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

public abstract class BackHandledFragment extends Fragment implements FragmentBackHandler {
    public BackHandledFragment() {
    }

    @Override
    public final boolean onBackPressed() {
        return interceptBackPressed() || Helper.handleBackPress(this);
    }

    public boolean interceptBackPressed() {
        return false;
    }
}