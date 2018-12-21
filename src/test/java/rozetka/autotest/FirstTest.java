package rozetka.autotest;



import com.codeborne.selenide.Configuration;

import org.testng.annotations.AfterClass;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import rozetka.autotest.pageObject.CategoryPage;
import rozetka.autotest.pageObject.MainPage;
import rozetka.autotest.support.Custom;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class FirstTest {

    @BeforeClass
    public static void setUp() {
        Configuration.browser="chrome";
        Configuration.startMaximized=true;
        Configuration.baseUrl = "https://rozetka.com.ua/";
    }

    @Test
    public void userCanLoginByUsername() {
        boolean deleteFileStatus, writeToFileStatus, sendEmailStatus;

        MainPage mainPage = new MainPage();
        CategoryPage categoryPage = new CategoryPage();

        mainPage.open();
        mainPage.navigateToSmartphones();

        categoryPage.getMoreProductsList(2);
        List firstPage = categoryPage.getProductsName(3);

        deleteFileStatus = Custom.deleteFile();
        writeToFileStatus = Custom.writeToFile(firstPage);
        sendEmailStatus = Custom.sendEmail("MyFile.txt");

        Assert.assertEquals(true, deleteFileStatus);
        Assert.assertEquals(true, writeToFileStatus);
        Assert.assertEquals(true, sendEmailStatus);
    }

    /*@AfterClass
    public static void logout() {
        closeWebDriver();
    }*/

}