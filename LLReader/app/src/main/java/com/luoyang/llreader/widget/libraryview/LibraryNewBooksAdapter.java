package com.luoyang.llreader.widget.libraryview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.luoyang.llreader.R;
import com.luoyang.llreader.bean.LibraryNewBookBean;
import com.luoyang.llreader.widget.flowlayout.FlowLayout;
import com.luoyang.llreader.widget.flowlayout.TagAdapter;

import java.util.ArrayList;

/**
 * package: com.luoyang.llreader.widget.libraryview
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public class LibraryNewBooksAdapter extends TagAdapter<LibraryNewBookBean> {
    private LibraryNewBooksView.OnClickAuthorListener clickNewBookListener;
    @Override
    public View getView(FlowLayout parent, int position, final LibraryNewBookBean libraryNewBookBean) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_library_hotauthor_item,
                parent, false);
        tv.setText(libraryNewBookBean.getName());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != clickNewBookListener){
                    clickNewBookListener.clickNewBook(libraryNewBookBean);
                }
            }
        });
        return tv;
    }

    public LibraryNewBooksAdapter() {
        super(new ArrayList<LibraryNewBookBean>());
    }

    public LibraryNewBookBean getItemData(int position){
        return mTagDatas.get(position);
    }

    public int getDataSize(){
        return mTagDatas.size();
    }

    public void setClickNewBookListener(LibraryNewBooksView.OnClickAuthorListener clickNewBookListener) {
        this.clickNewBookListener = clickNewBookListener;
    }
}
