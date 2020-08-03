package com.inetBanking.testCases;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.testCases.BaseClass;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;


public class TC_Login_001 extends BaseClass {


    @Test
    public void loginTest() throws InterruptedException {

        driver.get(baseUrl);
        Thread.sleep(3000);
        logger.info("URL is opened");
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        logger.info("Entered username");
        lp.setPassword(password);
        logger.info("Entered password");
        lp.clickSubmit();
        logger.info("Clicked submit");
        if (driver.getTitle().equals("Guru99 Bank Manager HomePage")){
            Assert.assertTrue(true);
            logger.info("Login test passed");
        }
        else {
            Assert.assertTrue(false);
            logger.info("Login test failed");
        }

    }



}
