package cn.sixlab.mine.tools.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.io.IOException;

public class SpringFxmlLoader {

    public static Node load(String name) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(clz -> Ctx.getBean(clz));
            loader.setLocation(SpringFxmlLoader.class.getResource(name));
            // loader.setResources(ResourceBundle.getBundle(resources));

            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new Label("读取失败");
        }
    }
}
