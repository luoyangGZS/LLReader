package com.luoyang.llreader.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * package: com.luoyang.llreader.bean
 * created by luoyang
 * QQ:1845313665
 * on 2018/11/5
 */
public class SearchBookBean implements Parcelable {

    private String noteUrl;

    private String coverUrl;

    private String name;

    private String author;

    private long words;

    private String state;

    private String lastChapter;

    private Boolean isAdd = false;

    private String tag;

    private String kind;

    private String origin;

    private String desc;


    public SearchBookBean(){

    }
    protected SearchBookBean(Parcel in) {
        noteUrl = in.readString();
        coverUrl = in.readString();
        name = in.readString();
        author = in.readString();
        words = in.readLong();
        state = in.readString();
        lastChapter = in.readString();
        byte tmpIsAdd = in.readByte();
        isAdd = tmpIsAdd == 0 ? null : tmpIsAdd == 1;
        tag = in.readString();
        kind = in.readString();
        origin = in.readString();
        desc = in.readString();
    }

    public static final Creator<SearchBookBean> CREATOR = new Creator<SearchBookBean>() {
        @Override
        public SearchBookBean createFromParcel(Parcel in) {
            return new SearchBookBean(in);
        }

        @Override
        public SearchBookBean[] newArray(int size) {
            return new SearchBookBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noteUrl);
        dest.writeString(coverUrl);
        dest.writeString(name);
        dest.writeString(author);
        dest.writeLong(words);
        dest.writeString(state);
        dest.writeString(lastChapter);
        dest.writeByte((byte) (isAdd == null ? 0 : isAdd ? 1 : 2));
        dest.writeString(tag);
        dest.writeString(kind);
        dest.writeString(origin);
        dest.writeString(desc);
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getWords() {
        return words;
    }

    public void setWords(long words) {
        this.words = words;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public Boolean getAdd() {
        return isAdd;
    }

    public void setAdd(Boolean add) {
        isAdd = add;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Creator<SearchBookBean> getCREATOR() {
        return CREATOR;
    }
}
