module mine.tools {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires spring.beans;
    requires javafx.web;
    requires spring.context;
    requires spring.boot;
    requires spring.jdbc;
    requires spring.boot.autoconfigure;
    requires java.sql;
    requires org.slf4j;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires org.controlsfx.controls;
    requires org.apache.commons.lang3;
    requires org.apache.commons.collections4;

    opens cn.sixlab.mine.tools;
    opens cn.sixlab.mine.tools.api;
    opens cn.sixlab.mine.tools.utils;
    opens cn.sixlab.mine.tools.repository;
}
