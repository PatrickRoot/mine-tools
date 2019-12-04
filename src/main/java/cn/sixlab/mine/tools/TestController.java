package cn.sixlab.mine.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);
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
