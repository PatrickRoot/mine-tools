package cn.sixlab.mine.tools.api;

import cn.sixlab.mine.tools.models.Employee;
import cn.sixlab.mine.tools.repository.EmployeeJDBCRepository;
import cn.sixlab.mine.tools.utils.ToolsApi;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("b0101")
public class TestController implements ToolsApi {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Override
    public void render(TabPane tabPane) {
        Tab javaDocTab = new Tab("百度");
        WebView view = new WebView();
        javaDocTab.setContent(view);
        WebEngine engine = view.getEngine();
        engine.load("https://www.baidu.com");
        tabPane.getTabs().setAll(javaDocTab);
    }


    @Autowired
    private EmployeeJDBCRepository employeeRepository;
    public void test() {
        logger.info("Inserting -> {}", employeeRepository.insert(new Employee("Ramesh", "Fadatare", "ramesh@gmail.com")));
        logger.info("Inserting -> {}", employeeRepository.insert(new Employee("John", "Cena", "john@gmail.com")));
        logger.info("Inserting -> {}", employeeRepository.insert(new Employee("tony", "stark", "stark@gmail.com")));

        logger.info("Employee id 10011 -> {}", employeeRepository.findById(1));

        logger.info("Update 10003 -> {}", employeeRepository.update(new Employee(1, "ram", "Stark", "ramesh123@gmail.com")));

        employeeRepository.deleteById(2);

        logger.info("All users -> {}", employeeRepository.findAll());
    }

}
