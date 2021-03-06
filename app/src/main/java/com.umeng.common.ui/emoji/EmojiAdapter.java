/*
 * Copyright 2014 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.umeng.common.ui.emoji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.umeng.comm.core.utils.ResFinder;

/**
 * @author Hieu Rocker (rockerhieu@gmail.com)
 */
class EmojiAdapter extends ArrayAdapter<EmojiBean> {

    LayoutInflater mInflater;

    public EmojiAdapter(Context context, EmojiBean[] data) {
        super(context, ResFinder.getLayout("umeng_comm_emoji_item"), data);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(ResFinder.getLayout("umeng_comm_emoji_item"), parent, false);
            ViewHolder holder = new ViewHolder();
            holder.icon = (EmojiTextView) view;
            view.setTag(holder);
        }
        EmojiBean emoji = getItem(position);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.icon.setText(emoji.getEmoji());
        return view;
    }

    class ViewHolder {
        EmojiTextView icon;
    }
}
