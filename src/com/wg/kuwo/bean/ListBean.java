package com.wg.kuwo.bean;

import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by EXP on 2015/7/26.
 */
public class ListBean  implements BaseColumns {
    public final static String TAB_NAME = "list_tab";
    public static final String NAME = "name";
    public static final String CREATEDATA = "createDate";

    private String name;
    private Date createData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateData() {
        return createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }
}
