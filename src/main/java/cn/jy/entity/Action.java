package cn.jy.entity;

import java.util.Date;
import java.util.List;

public class Action {
    private Long id;

    private Long parentId;

    private Integer type;

    private String name;

    private String simple;

    private String href;

    private String treeCode;

    private String logoIcon;

    private Long sort;

    private Integer isMenu;

    private Short display;

    private Short mixed = 1;

    private Short isSuper = 1;

    private Short founder = 1;

    private String createTime;

    private String updateTime;

    private Short isDel = 0;

    private Short item = 0;

    private int has_children = 0;

    private boolean checked = false;

    private List<Action> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple == null ? null : simple.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode == null ? null : treeCode.trim();
    }

    public String getLogoIcon() {
        return logoIcon;
    }

    public void setLogoIcon(String logoIcon) {
        this.logoIcon = logoIcon == null ? null : logoIcon.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Short getDisplay() {
        return display;
    }

    public void setDisplay(Short display) {
        this.display = display;
    }

    public Short getMixed() {
        return mixed;
    }

    public void setMixed(Short mixed) {
        this.mixed = mixed;
    }

    public Short getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Short isSuper) {
        this.isSuper = isSuper;
    }

    public Short getFounder() {
        return founder;
    }

    public void setFounder(Short founder) {
        this.founder = founder;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }

    public int getHas_children() {
        return has_children;
    }

    public void setHas_children(int has_children) {
        this.has_children = has_children;
    }

    public List<Action> getChildren() {
        return children;
    }

    public void setChildren(List<Action> children) {
        this.children = children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Short getItem() {
        return item;
    }

    public void setItem(Short item) {
        this.item = item;
    }
}