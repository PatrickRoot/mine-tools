package cn.sixlab.mine.tools.api;

import cn.sixlab.mine.tools.utils.SpringFxmlLoader;
import cn.sixlab.mine.tools.utils.ToolsApi;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("b0102")
public class FxmlTestController implements ToolsApi {
    private static Logger logger = LoggerFactory.getLogger(FxmlTestController.class);
    public CheckBox checkId;

    @Override
    public void render(TabPane tabPane) {
        Tab tab = new Tab("工具");
        tabPane.getTabs().setAll(tab);

        Node parent = SpringFxmlLoader.load("/a.fxml");

        tab.setContent(parent);
    }

    public void testClick(){
        checkId.setSelected(!checkId.isSelected());
    }
}
