package cn.sixlab.mine.tools.values;

import cn.sixlab.mine.tools.models.ToolItem;

import java.util.ArrayList;
import java.util.List;

public class Menus {
    public static List<ToolItem> menus = new ArrayList<>();

    static {
        menus.add(new ToolItem("b0100", "工具包1"));
        menus.add(new ToolItem("b0101", "工具1"));
        menus.add(new ToolItem("b0102", "工具2"));
        menus.add(new ToolItem("b0103", "工具3"));

        menus.add(new ToolItem("b0200", "工具包2"));
        menus.add(new ToolItem("b0201", "工具4"));
        menus.add(new ToolItem("b0202", "工具5"));
        menus.add(new ToolItem("b0203", "工具6"));
    }
}
