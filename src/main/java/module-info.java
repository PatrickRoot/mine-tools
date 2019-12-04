module mine.tools {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires spring.beans;
    requires spring.context;
    requires spring.boot;
    requires spring.core;
    requires spring.jdbc;
    requires java.sql;
    requires spring.boot.autoconfigure;
    requires org.slf4j;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens cn.sixlab.mine.tools;
}