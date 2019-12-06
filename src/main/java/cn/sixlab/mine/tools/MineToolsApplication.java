package cn.sixlab.mine.tools;

import cn.sixlab.mine.tools.models.ToolItem;
import cn.sixlab.mine.tools.utils.Ctx;
import cn.sixlab.mine.tools.utils.ToolsApi;
import cn.sixlab.mine.tools.values.Menus;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MineToolsApplication extends Application {
    private TreeView<ToolItem> toolsTreeView;
    private TreeItem<ToolItem> root;
    private ToolItem selectTool;
    private TabPane tabPane;

    public static void main(String[] args) {
        SpringApplication.run(MineToolsApplication.class, args);
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // stage.getIcons().add(new Image("logo.png"));

        // 整个 pane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);

        // 左侧顶部搜索框
        TextField searchBox = new TextField();
        searchBox.setPromptText("搜索");
        searchBox.getStyleClass().add("search-box");
        searchBox.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                String text = searchBox.getText();
                if(StringUtils.isNoneEmpty(text)){
                    updateLeftTree(text.trim());
                }
            }
        });
        GridPane.setMargin(searchBox, new Insets(5, 0, 0, 0));
        grid.add(searchBox, 0, 0);

        // 左侧下面 TreeView
        root = new TreeItem<>(ToolItem.of("0000", "工具箱"));
        toolsTreeView = new TreeView<>(root);
        toolsTreeView.setShowRoot(false);
        toolsTreeView.getStyleClass().add("tools-tree");
        toolsTreeView.setMinWidth(200);
        toolsTreeView.setMaxWidth(200);
        toolsTreeView.setCellFactory(new Callback<>() {
            @Override
            public TreeCell<ToolItem> call(TreeView<ToolItem> stringTreeView) {
                return new TreeCell<>() {
                    @Override
                    protected void updateItem(ToolItem toolItem, boolean empty) {
                        super.updateItem(toolItem, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(toolItem.getToolName());
                        }
                    }
                };
            }
        });
        toolsTreeView.getSelectionModel().selectedItemProperty().addListener((observableValue, stringTreeItem, item) -> {
            if (item == null || item.getValue() == null) {
                return;
            }

            selectTool = item.getValue();
            changeTools();
        });
        GridPane.setVgrow(toolsTreeView, Priority.ALWAYS);
        grid.add(toolsTreeView, 0, 1);

        // 右侧工具栏
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING);
        tabPane.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
            @Override public void invalidated(Observable arg0) {
                updateTab();
            }
        });
        GridPane.setHgrow(tabPane, Priority.ALWAYS);
        GridPane.setVgrow(tabPane, Priority.ALWAYS);
        grid.add(tabPane, 1, 0, 1, 2);

        // 显示默认页面
        List<TreeItem<ToolItem>> projects = toolsTreeView.getRoot().getChildren();
        if(!projects.isEmpty()) {
            TreeItem<ToolItem> firstProject = projects.get(0);
            toolsTreeView.getSelectionModel().select(firstProject);
        } else {
            Tab welcomeTab = new Tab("欢迎");
            Label welcomeLabel1 = new Label("Welcome to Mine Tools!");
            welcomeLabel1.setStyle("-fx-font-size: 2em; -fx-padding: 0 0 0 5;");
            welcomeTab.setContent(new VBox(5, new Label(""), welcomeLabel1,
                    new Label("By 六楼的雨"),
                    new Label("六楼实验室 · 矿软科技出品")));
            tabPane.getTabs().setAll(welcomeTab);
        }

        // 整个界面
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("tools.css").toExternalForm());
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(600);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setWidth(screenBounds.getWidth() * 0.75);
        stage.setHeight(screenBounds.getHeight() * .75);

        stage.setTitle("Mine Tools");
        stage.show();

        updateLeftTree("");

        toolsTreeView.requestFocus();
    }

    private void updateTab() {

    }

    private void changeTools() {
        String toolCode = selectTool.getToolCode();
        System.out.println(toolCode);
        if(!toolCode.endsWith("00")){
            tabPane.getTabs().clear();
            Ctx.getBean(toolCode, ToolsApi.class).render(tabPane);
        }
    }

    private void updateLeftTree(String text) {
        root = new TreeItem<>(ToolItem.of("a0000", "工具箱"));

        boolean searchModel = StringUtils.isNotEmpty(text);

        Map<String, TreeItem<ToolItem>> map = new HashMap<>();
        for (ToolItem toolItem : Menus.menus) {
            String toolCode = toolItem.getToolCode();
            String toolName = toolItem.getToolName();
            TreeItem<ToolItem> treeItem = toolItem.createTreeItem();

            if(searchModel){
                if(!toolCode.endsWith("00") && toolName.contains(text)){
                    root.getChildren().add(treeItem);
                }
            }else{
                if(toolCode.endsWith("00")){
                    treeItem.setExpanded(true);
                    root.getChildren().add(treeItem);
                    map.put(toolCode, treeItem);
                }else{
                    String parentCode = toolCode.substring(0, 3) + "00";
                    TreeItem<ToolItem> parent = map.get(parentCode);
                    parent.getChildren().add(treeItem);
                }
            }
        }

        root.setExpanded(true);
        toolsTreeView.getRoot().getChildren().clear();
        toolsTreeView.getRoot().getChildren().setAll(root);
    }
}
