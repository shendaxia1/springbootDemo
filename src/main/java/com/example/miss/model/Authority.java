package com.example.miss.model;

public class Authority {
    private Long id;

    private String dataUrl;

    private String menuClass;

    private String menuCode;

    private String menuName;

    private String parentMenucode;

    private Long sequence;

    private String menuType;

    private String createTime;

    private Byte menuState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl == null ? null : dataUrl.trim();
    }

    public String getMenuClass() {
        return menuClass;
    }

    public void setMenuClass(String menuClass) {
        this.menuClass = menuClass == null ? null : menuClass.trim();
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getParentMenucode() {
        return parentMenucode;
    }

    public void setParentMenucode(String parentMenucode) {
        this.parentMenucode = parentMenucode == null ? null : parentMenucode.trim();
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Byte getMenuState() {
        return menuState;
    }

    public void setMenuState(Byte menuState) {
        this.menuState = menuState;
    }
}