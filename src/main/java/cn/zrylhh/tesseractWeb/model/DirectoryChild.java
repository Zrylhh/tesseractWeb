package cn.zrylhh.tesseractWeb.model;

/**
 * 项目名：TesseractWeb.
 * 文件名：
 * 模块说明：用于描述一个文件系统节点
 * 修改历史：
 * 2021-07-01 - ZhaoLongTao - 创建.
 */
public class DirectoryChild {
    // 路径前缀
    private String prePath;
    // 相对路径
    private String relativePath;
    // 是否为文件夹
    private Boolean isDir;
    // 文件类型
    private Enum childType;
    // 文件后缀
    private String childSuffix;
    // 节点名,如果是文件会包含后缀
    private String childName;

    public DirectoryChild() {
    }

    public DirectoryChild(String prePath, String relativePath, Boolean isDir, Enum childType, String childSuffix, String childName) {
        this.prePath = prePath;
        this.relativePath = relativePath;
        this.isDir = isDir;
        this.childType = childType;
        this.childSuffix = childSuffix;
        this.childName = childName;
    }

    public String getPrePath() {
        return prePath;
    }

    public void setPrePath(String prePath) {
        this.prePath = prePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public Boolean getDir() {
        return isDir;
    }

    public void setDir(Boolean dir) {
        isDir = dir;
    }

    public Enum getChildType() {
        return childType;
    }

    public void setChildType(Enum childType) {
        this.childType = childType;
    }

    public String getChildSuffix() {
        return childSuffix;
    }

    public void setChildSuffix(String childSuffix) {
        this.childSuffix = childSuffix;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Override
    public String toString() {
        return "DirectoryChild{" +
                "prePath='" + prePath + '\'' +
                ", relativePath='" + relativePath + '\'' +
                ", isDir=" + isDir +
                ", childType=" + childType +
                ", childSuffix='" + childSuffix + '\'' +
                ", childName='" + childName + '\'' +
                '}';
    }
}
