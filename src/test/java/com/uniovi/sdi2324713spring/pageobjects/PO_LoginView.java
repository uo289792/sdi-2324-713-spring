package com.uniovi.sdi2324713spring.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {
    static public void fillLoginForm(WebDriver driver, String dnip, String passwordp) {
        WebElement dni = driver.findElement(By.name("username"));
        dni.click();
        dni.clear();
        dni.sendKeys(dnip);
        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordp);
        //Pulsar el boton de Login.
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }

    public static void login(WebDriver driver, String dni, String password, String texto) {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, dni, password);
        PO_View.checkElementBy(driver, "text", texto);
    }

}