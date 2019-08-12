package com.ecs.wonderful200.calendars;

/**
 * 作者：RedKeyset on 2019/8/9 15:16
 * 邮箱：redkeyset@aliyun.com
 */
public class sqlit {
    String date;
    String isselct;

    public sqlit() {
    }

    public sqlit(String date, String isselct) {
        this.date = date;
        this.isselct = isselct;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsselct() {
        return isselct;
    }

    public void setIsselct(String isselct) {
        this.isselct = isselct;
    }

}
