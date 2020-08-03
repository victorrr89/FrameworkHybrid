package com.inetBanking.testCases;

import com.inetBanking.utilities.ReadConfig;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

   public ReadConfig readConfig = new ReadConfig();

    public String baseUrl = readConfig.getApplicationURL();
    public String username = readConfig.getUserName();
    public String password = readConfig.getPassword();
    public static WebDriver driver;
    public static Logger logger;


    @Parameters("browser")
    @BeforeClass
    public void setup(String br) throws IOException {


        logger = Logger.getLogger("eBanking");
        File log4jfile = new File("src/log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());
        //BasicConfigurator.configure();
        if (br.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
            driver = new ChromeDriver();
        }else if (br.equals("firefox")){
            System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
            driver = new FirefoxDriver();
        }

    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
