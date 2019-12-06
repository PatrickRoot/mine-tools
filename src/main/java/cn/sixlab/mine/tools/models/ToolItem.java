package cn.sixlab.mine.tools.models;

import javafx.scene.control.TreeItem;

public class ToolItem {
    private String toolCode;
    private String toolName;

    public static ToolItem of(String toolCode, String toolName){
        return new ToolItem(toolCode, toolName);
    }

    public ToolItem(String toolCode, String toolName){
        this.toolCode = toolCode;
        this.toolName = toolName;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public TreeItem<ToolItem> createTreeItem() {
        return  new TreeItem<>(ToolItem.of(toolCode, toolName));
    }
}
