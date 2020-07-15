package com.luoyang.llreader.view.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.luoyang.llreader.R;
import com.luoyang.llreader.bean.BookShelfBean;
import com.luoyang.llreader.widget.ChapterListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * package: com.luoyang.llreader.view.adapter
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/8
 */
public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.Viewholder> {
        private BookShelfBean bookShelfBean;
        private ChapterListView.OnItemClickListener itemClickListener;
        private int index = 0;
        private Boolean isAsc = true;

        public ChapterListAdapter(BookShelfBean bookShelfBean, @NonNull ChapterListView.OnItemClickListener itemClickListener) {
            this.bookShelfBean = bookShelfBean;
            this.itemClickListener = itemClickListener;
        }

        @Override
        public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_adapter_chapterlist, parent, false));
        }

        @Override
        public void onBindViewHolder(Viewholder holder, final int posiTion) {
            if (posiTion == getItemCount() - 1) {
                holder.vLine.setVisibility(View.INVISIBLE);
            } else
                holder.vLine.setVisibility(View.VISIBLE);

            final int position;
            if (isAsc) {
                position = posiTion;
            } else {
                position = getItemCount() - 1 - posiTion;
            }
            holder.tvName.setText(bookShelfBean.getBookInfoBean().getChapterlist().get(position).getDurChapterName());
           Boolean flag =bookShelfBean.getBookInfoBean().getChapterlist().get(position).getHasCache();
            if(flag){
                holder.tv_HasCache.setVisibility(View.VISIBLE);
            }//RecyclerView 复用混乱，局部刷新逻辑要全面 加上else
            else{
                holder.tv_HasCache.setVisibility(View.INVISIBLE);
            }

            holder.flContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setIndex(position);
                    itemClickListener.itemClick(position);
                }
            });
            if (position == index) {
                holder.flContent.setBackgroundColor(Color.parseColor("#cfcfcf"));
                holder.flContent.setClickable(false);
            } else {
                holder.flContent.setBackgroundResource(R.drawable.bg_ib_pre2);
                holder.flContent.setClickable(true);
            }
        }

        @Override
        public int getItemCount() {
            if (bookShelfBean == null)
                return 0;
            else
                return bookShelfBean.getBookInfoBean().getChapterlist().size();
        }

        public class Viewholder extends RecyclerView.ViewHolder {
            private FrameLayout flContent;
            private TextView tvName;
            private TextView tv_HasCache;
            private View vLine;

            public Viewholder(View itemView) {
                super(itemView);
                flContent =itemView.findViewById(R.id.fl_content);
                tvName = itemView.findViewById(R.id.tv_name);
                tv_HasCache = itemView.findViewById(R.id.tv_hasCache);
                vLine = itemView.findViewById(R.id.v_line);
            }
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            notifyItemChanged(this.index);
            this.index = index;
            notifyItemChanged(this.index);
        }
}
