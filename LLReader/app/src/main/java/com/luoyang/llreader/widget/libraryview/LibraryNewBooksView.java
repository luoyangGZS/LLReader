package com.luoyang.llreader.widget.libraryview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.luoyang.llreader.R;
import com.luoyang.llreader.bean.LibraryNewBookBean;
import com.luoyang.llreader.widget.flowlayout.TagFlowLayout;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * package: com.luoyang.llreader.widget.libraryview
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/7
 */
public class LibraryNewBooksView extends LinearLayout {
    private TagFlowLayout tflBooks;
    private LibraryNewBooksAdapter libraryNewBooksAdapter;

    public interface OnClickAuthorListener {
        public void clickNewBook(LibraryNewBookBean libraryNewBookBean);
    }

    public LibraryNewBooksView(Context context) {
        super(context);
        init();
    }

    public LibraryNewBooksView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LibraryNewBooksView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public LibraryNewBooksView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_library_hotauthor, this, true);
        setOrientation(VERTICAL);
        setVisibility(GONE);
        libraryNewBooksAdapter = new LibraryNewBooksAdapter();
        tflBooks = (TagFlowLayout) findViewById(R.id.tfl_author);
        tflBooks.setAdapter(libraryNewBooksAdapter);
    }

    public void updateData(List<LibraryNewBookBean> datas, OnClickAuthorListener clickAuthorListener) {
        if (datas != null && datas.size() > 0) {
            setVisibility(VISIBLE);
            libraryNewBooksAdapter.setClickNewBookListener(clickAuthorListener);
            libraryNewBooksAdapter.replaceAll(datas);
        } else {
            setVisibility(GONE);
        }
    }
}
