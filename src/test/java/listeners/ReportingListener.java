package listeners;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import testBase.BaseTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportingListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testClass).driver;
            if (driver != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                    Path screenshotsDir = Paths.get("target", "screenshots");
                    String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmssSSS").format(new Date());
                    String safeTestName = result.getName().replaceAll("[^a-zA-Z0-9]", "_");
                    String filename = safeTestName + "-" + timestamp + ".png";

                    Allure.addAttachment(filename, new ByteArrayInputStream(screenshot));

                    try {
                        if (!Files.exists(screenshotsDir)) {
                            Files.createDirectories(screenshotsDir);
                        }
                        Path screenshotPath = screenshotsDir.resolve(filename);
                        Files.write(screenshotPath, screenshot);
                    } catch (IOException e) {
                        System.err.println("Failed to write screenshot file for test '" + result.getName() + "': " + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Failed to capture screenshot for test '" + result.getName() + "': " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // no-op
    }

    @Override
    public void onStart(ITestContext context) {
        // no-op
    }

    @Override
    public void onFinish(ITestContext context) {
        // no-op
    }
}
