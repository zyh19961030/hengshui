package com.qu.modules.web.vo;

import java.util.List;

public class optionVo {
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private List<String> his;
    public List<String> getHis() {
        return his;
    }
    public void setHis(List<String> his) {
        this.his = his;
    }
}
