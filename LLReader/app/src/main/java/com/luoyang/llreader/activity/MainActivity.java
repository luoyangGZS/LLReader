package com.luoyang.llreader.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.luoyang.llreader.BitIntentDataManager;
import com.luoyang.llreader.R;
import com.luoyang.llreader.base.MBaseActivity;
import com.luoyang.llreader.bean.BookShelfBean;
import com.luoyang.llreader.presenter.IMainPresenter;
import com.luoyang.llreader.presenter.impl.BookDetailPresenterImpl;
import com.luoyang.llreader.presenter.impl.MainPresenterImpl;
import com.luoyang.llreader.presenter.impl.ReadBookPresenterImpl;
import com.luoyang.llreader.view.IMainView;
import com.luoyang.llreader.view.adapter.BookShelfAdapter;
import com.luoyang.llreader.view.popupwindow.DownloadListPop;
import com.luoyang.llreader.widget.refreshview.OnRefreshWithProgressListener;
import com.luoyang.llreader.widget.refreshview.RefreshRecyclerView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;


public class MainActivity extends MBaseActivity<IMainPresenter>implements IMainView {
    private ImageButton ibMoney;
    private ImageButton ibLibrary;
    private ImageButton ibAdd;
    private ImageButton ibDownload;

    private RefreshRecyclerView rfRvShelf;
    private BookShelfAdapter bookShelfAdapter;

    private FrameLayout flWarn;
    private ImageView ivWarnClose;

    private DownloadListPop downloadListPop;


    @Override
    protected IMainPresenter initInjector() {
        return new MainPresenterImpl();
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {
        bookShelfAdapter = new BookShelfAdapter();

    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void bindView() {
        downloadListPop = new DownloadListPop(MainActivity.this);

        rfRvShelf =  findViewById(R.id.rf_rv_shelf);

        ibMoney =  findViewById(R.id.ib_money);
        ibLibrary =  findViewById(R.id.ib_library);
        ibAdd =  findViewById(R.id.ib_add);
        ibDownload =  findViewById(R.id.ib_download);

        rfRvShelf.setRefreshRecyclerViewAdapter(bookShelfAdapter, new LinearLayoutManager(this));

        flWarn =  findViewById(R.id.fl_warn);
        ivWarnClose =  findViewById(R.id.iv_warn_close);
    }

    @Override
    protected void bindEvent() {
        bindRvShelfEvent();
        ibDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadListPop.showAsDropDown(ibDownload);
            }
        });
        ibMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击打赏
            }
        });
        ibLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByAnim(new Intent(MainActivity.this, LibraryActivity.class), 0, 0);
            }
        });
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击更多
                startActivityByAnim(new Intent(MainActivity.this, ImportBookActivity.class), 0, 0);
            }
        });
        bookShelfAdapter.setItemClickListener(new BookShelfAdapter.OnItemClickListener() {
            @Override
            public void toSearch() {
                //点击去选书
                startActivityByAnim(new Intent(MainActivity.this, LibraryActivity.class), 0, 0);
            }

            @Override
            public void onClick(BookShelfBean bookShelfBean, int index) {
                Intent intent = new Intent(MainActivity.this, ReadBookActivity.class);
                intent.putExtra("from", ReadBookPresenterImpl.OPEN_FROM_APP);
                String key = String.valueOf(System.currentTimeMillis());
                intent.putExtra("data_key", key);
                try {
                    BitIntentDataManager.getInstance().putData(key, bookShelfBean.clone());
                } catch (CloneNotSupportedException e) {
                    BitIntentDataManager.getInstance().putData(key, bookShelfBean);
                    e.printStackTrace();
                }
                startActivityByAnim(intent, android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onLongClick(View animView, BookShelfBean bookShelfBean, int index) {
                Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
                intent.putExtra("from", BookDetailPresenterImpl.FROM_BOOKSHELF);
                String key = String.valueOf(System.currentTimeMillis());
                intent.putExtra("data_key", key);
                BitIntentDataManager.getInstance().putData(key, bookShelfBean);
                startActivityByAnim(intent, animView, "img_cover", android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        flWarn.setVisibility(View.GONE);
//        ivWarnClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flWarn.setVisibility(View.GONE);
//            }
//        });
    }
        private void bindRvShelfEvent () {
            rfRvShelf.setBaseRefreshListener(new OnRefreshWithProgressListener() {
                @Override
                public int getMaxProgress() {
                    return bookShelfAdapter.getBooks().size();
                }

                @Override
                public void startRefresh() {
                    mPresenter.queryBookShelf(true);
                }
            });
       }


    @Override
    protected void firstRequest() {
        //通过百度API 判断是否有更新
//        try {
//            BDAutoUpdateSDK.uiUpdateAction(this, new UICheckUpdateCallback() {
//                @Override
//                public void onNoUpdateFound() { }
//                @Override
//                public void onCheckComplete() { }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        mPresenter.queryBookShelf(false);
    }


    @Override
    public void refreshBookShelf(List<BookShelfBean> bookShelfBeanList) {
        bookShelfAdapter.replaceAll(bookShelfBeanList);
    }


    @Override
    public void activityRefreshView() {
        //执行刷新响应
        rfRvShelf.startRefresh();
    }

    @Override
    public void refreshFinish() {
        rfRvShelf.finishRefresh(false, true);
    }

    @Override
    public void refreshError(String error) {
        refreshFinish();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshRecyclerViewItemAdd() {
        rfRvShelf.getRpb().setDurProgress(rfRvShelf.getRpb().getDurProgress() + 1);
    }

    @Override
    public void setRecyclerMaxProgress(int x) {
        rfRvShelf.getRpb().setMaxProgress(x);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime = 0;
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        downloadListPop.onDestroy();
    }

}
