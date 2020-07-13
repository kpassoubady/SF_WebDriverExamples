package com.kavinschool.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JsUtils {

    public static final String SCROLL_HEIGHT = "return document.documentElement.scrollHeight;";
    public static final String VERT_BAR_PRESENT = "return document.documentElement.scrollHeight >= " +
            "document.documentElement.clientHeight;";
    public static final String DRAG_CODE = "function createEvent(typeOfEvent) {\n"
            + "var event =document.createEvent(\"CustomEvent\");\n"
            + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
            + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
            + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
            + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
            + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
            + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
            + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
            + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
            + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
            + "var dropEvent = createEvent('drop');\n"
            + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
            + "var dragEndEvent = createEvent('dragend');\n"
            + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
            + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
            + "simulateHTML5DragAndDrop(source,destination);";

    public static Long getWindowPageXOffset(final WebDriver driver) {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Long) js.executeScript("return window.pageXOffset;");
    }

    public static Long getWindowPageYOffset(final WebDriver driver) {
        final JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        return (Long) jsExec.executeScript("return window.pageYOffset;");
    }

    public static void scrollToBottomOfPage(final WebDriver driver) {
        final JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToPageTop(final WebDriver driver) {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    public void scrollIntoViewByXpath(WebDriver driver, String xpath) {
        String script = "document.evaluate(\"" + xpath + "\", document, null, " +
                "XPathResult.UNORDERED_NODE_ITERATOR_TYPE, null)"
                + ".iterateNext().scrollIntoView(true);";
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public void scrollIntoView(WebDriver driver, By by) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

    public static void setZIndexToElement(final WebDriver driver, String elementID, int zIndex) {
        ((JavascriptExecutor) driver).executeScript(
                "document.getElementById(arguments[0]).style.zIndex = arguments[1]", elementID, zIndex);
    }

    public static void focusElement(final WebDriver driver, final String elementId) {
        final JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("document.getElementById('" + elementId + "').focus()");
    }

    public static void waitForLoad(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        } catch(TimeoutException e) {
            System.out.println("document.readyState is not complete");
        }
    }

    public static void blurAll(final WebDriver driver) {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var tmp = document.createElement(\"input\"); " +
                "document.body.appendChild(tmp); " +
                "tmp.focus(); " +
                "document.body.removeChild(tmp);"
        );
    }

    public static Object checkElementLoaded(final WebDriver driver, final WebElement element) {
        return ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && "
                + "typeof arguments[0].naturalWidth != \"undefined\" && " + "arguments[0].naturalWidth > 0", element);
    }

    public static void jsClick(final WebDriver driver, final WebElement element) {
        final WebElement weElement = driver.findElement((By) element);
        final JavascriptExecutor executor = (JavascriptExecutor) driver;
        ((RemoteWebDriver) executor).executeScript("arguments[0].click();", weElement);
    }

    public void elementHighlight(final WebDriver driver, final WebElement element) {
        for (int i = 0; i < 2; i++) {
            final JavascriptExecutor jsExec = (JavascriptExecutor) driver;
            jsExec.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 3px solid red;");
            jsExec.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }

    public Long getScrollHeight(final WebDriver driver) {
        final JavascriptExecutor scrollBarPresent = (JavascriptExecutor) driver;
        return (Long) scrollBarPresent.executeScript(SCROLL_HEIGHT);
    }

    public boolean isScrollBarPresent(final WebDriver driver) {
        final JavascriptExecutor scrollBarPresent = (JavascriptExecutor) driver;
        return (Boolean) scrollBarPresent.executeScript(VERT_BAR_PRESENT);
    }

    public void scrollDown(final WebDriver driver, final Integer pixelToScroll) {
        final Integer detPixelToScroll = pixelToScroll == 0 ? 250 : pixelToScroll;
        final JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("scrollBy(0, " + detPixelToScroll.toString() + ");");
    }

    public void scrollHorizontalLeft(final WebDriver driver) {
        final int pixelToScroll = -2000;
        final JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("scroll(" + pixelToScroll + ", 0);");
    }

    public void scrollHorizontalRight(final WebDriver driver) {
        final int pixelToScroll = 2000;
        final JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("scroll(" + pixelToScroll + ", 0);");
    }

    public static void dropJS(final WebDriver driver, WebElement source, WebElement dest) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(DRAG_CODE, source, dest);
    }

    public void zoomPercentBrowser(final WebDriver driver, final int percent) {
        final JavascriptExecutor jExec = (JavascriptExecutor) driver;
        jExec.executeScript("document.body.style.zoom = '" + percent + "%';");
    }

}
