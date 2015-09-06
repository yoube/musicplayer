package com.wg.kuwo.util;

import android.media.MediaPlayer;
import com.wg.kuwo.bean.MusicBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EXP on 2015/7/30.
 */
public class MusicPalyer extends MediaPlayer {

    private List<MusicBean> list = new ArrayList<MusicBean>();
    private int curredMusicIndex = -1;
    private String curredMusicPath = "";
    private boolean pauseStust;

    public MusicPalyer() {
        super();
    }

    //��ͣ
    @Override
    public void pause() throws IllegalStateException {
        super.pause();
        pauseStust = true;
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
        pauseStust = false;
    }

    /**
     * ָ���б�������������ţ������ͬ����ͣ
     * @param index
     * @return
     */
    public boolean play(int index) {
        if (index == -1 ) {
            return false;
        }else if(list==null)
            return false;
        if (curredMusicIndex==index) {
            return play();
        }else {
            curredMusicIndex = index;
            rePlay();
            return true;
        }
    }

    /**
     * ����ڲ�������ͣ
     * @return
     */
    public boolean play(){
        if(isPlaying()){
            pause();
            return false;
        }else{
            start();
            return true;
        }

    }

    public void rePlay() {
        reset();
        try {
            setDataSource(list.get(curredMusicIndex).getPath());
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    /**
     * ������һ��
     */
    public void playupper() {
        if (--curredMusicIndex < 0)
            setCurredMusicIndex(list.size() - 1);
        else
            setCurredMusicIndex(curredMusicIndex);
        rePlay();
    }

    /**
     * ������һ��
     */
    public void playNext() {
        int size = list.size();
        if(size==0)return;
        setCurredMusicIndex(++curredMusicIndex % size);
        rePlay();
    }

    public List<MusicBean> getList() {
        return list;
    }

    public void setList(List<MusicBean> list) {
        this.list = list;
    }

    public int getCurredMusicIndex() {
        return curredMusicIndex;
    }

    public void setCurredMusicIndex(int curredMusicIndex) {
        if (list.size() > curredMusicIndex)
            this.curredMusicIndex = curredMusicIndex;
        else {

        }
    }

    public boolean isPause() {
        return pauseStust;
    }

}
