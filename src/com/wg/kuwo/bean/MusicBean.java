package com.wg.kuwo.bean;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

/**
 * Created by EXP on 2015/7/24.
 */
public class MusicBean implements BaseColumns, Parcelable {

    public static final String TAB_NAME = "t_music";
    public static final String NAME = "_name";
    public static final String DOWNLOAD_STUST = "_downloadStust";
    public static final String PATH = "_path";
    public static final String LIKE = "_like";

    private int id;
    private String name;
    private boolean downloadStust;
    private String path;
    private boolean like;
    private String artist;//作者
    private String album;

    /**
     * 获取插入sql
     *
     * @param
     * @return
     */
    public String getInsertSQL() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("insert into ");
        sbf.append(MusicBean.TAB_NAME);
        sbf.append("(");
        if (name == null || name.trim().equals("")) {
            throw new RuntimeException("歌名不能为空！");
        } else {
            sbf.append(MusicBean.NAME);
        }
        sbf.append(",");
        sbf.append(MusicBean.DOWNLOAD_STUST);
        if (name == null || name.trim().equals("")) {
            throw new RuntimeException("路径不能为空不能为空！");
        } else {
            sbf.append(",");
            sbf.append(MusicBean.PATH);
        }
        sbf.append(",");
        sbf.append(MusicBean.LIKE);
        sbf.append(")");

        sbf.append(" values('");
        sbf.append(name);
        sbf.append("','");
        sbf.append(downloadStust);
        sbf.append("','");
        sbf.append(path);
        sbf.append("','");
        sbf.append(like);
        sbf.append("'");
        sbf.append(")");


        return sbf.toString();
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public boolean isDownloadStust() {
//        return downloadStust;
//    }

    public void setDownloadStust(boolean downloadStust) {
        this.downloadStust = downloadStust;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

//    public boolean isLike() {
//        return like;
//    }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", downloadStust=" + downloadStust +
                ", path='" + path + '\'' +
                ", like=" + like +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
//        parcel.writeByte();
//        parcel.write(downloadStust);
        parcel.writeString(path);
//        parcel.writeBundle(like);
        parcel.writeString(artist);
        parcel.writeString(album);
    }
    //需要创建此对象
    public static final Parcelable.Creator<MusicBean> CREATOR = new Creator<MusicBean>() {
        @Override
        public MusicBean createFromParcel(Parcel parcel) {
            MusicBean bean = new MusicBean();
            bean.id = parcel.readInt();
            bean.name = parcel.readString();
//            bean.downloadStust;
            bean.path = parcel.readString();
//            bean.like;
            bean.artist = parcel.readString();
            bean.album = parcel.readString();

            return bean;
        }

        @Override
        public MusicBean[] newArray(int i) {
            return new MusicBean[0];
        }
    };
}
