package cn.sixlab.mine.tools;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class MineToolsApplication extends Application {
    private static ApplicationContext ctx;
    public static Parent rootNode;

    public static void main(String[] args) throws IOException {
        ctx = SpringApplication.run(MineToolsApplication.class, args);

        FXMLLoader fxmlLoader = new FXMLLoader(MineToolsApplication.class.getResource("/a.fxml"));
        fxmlLoader.setControllerFactory(Ctx.ctx::getBean);
        rootNode =  fxmlLoader.load();

        MineToolsApplication.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(rootNode, 700, 700));
        stage.setMinWidth(700);
        stage.setMinHeight(700);
        stage.show();
    }
}
