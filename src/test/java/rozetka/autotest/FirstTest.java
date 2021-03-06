package rozetka.autotest;



import com.codeborne.selenide.Configuration;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import rozetka.autotest.pageObject.CategoryPage;
import rozetka.autotest.pageObject.MainPage;
import rozetka.autotest.support.Custom;

import java.util.List;



public class FirstTest {

    @BeforeClass
    public static void setUp() {
        Configuration.browser="chrome";
        Configuration.browserSize = "1980x1080";
        Configuration.baseUrl = "https://rozetka.com.ua/";
    }

    @Test
    public void tryToTest() {
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
}