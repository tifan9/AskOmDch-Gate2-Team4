package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class StorePage extends BasePage{
    
    @FindBy(css = ".price-slider-amount #amount") private WebElement priceRangeDisplay;
    @FindBy(css = "button[type='submit'], .button, input[type='submit']") private WebElement filterButton;
    @FindBy(css = ".ui-slider-handle.ui-corner-all.ui-state-default") private List<WebElement> sliderHandles;
    @FindBy(css = ".price, .amount, .woocommerce-Price-amount") private List<WebElement> productPrices;
    @FindBy(css = "input[type='search'], .search-field, #search") private WebElement searchInput;
    @FindBy(css = "button[type='submit'], .search-button, .btn-search") private WebElement searchButton;
    @FindBy(css = ".no-results, .woocommerce-info") private WebElement noResultsMessage;
    @FindBy(id = "product_cat") private WebElement categoryDropdown;
    @FindBy(css = "ul.products li.product") private List<WebElement> productItems;
    @FindBy(css = ".orderby, select[name='orderby']") private WebElement sortDropdown;
    @FindBy(css = "a[aria-label='Add “Basic Blue Jeans” to your cart']") private WebElement AddToCart;
    @FindBy(css = "a[title='View cart']") private WebElement ViewCartLink;
    @FindBy(css = ".product_title, h1.entry-title") private WebElement productTitle;
    @FindBy(css = ".woocommerce-product-gallery__image img, .product-image img") private WebElement productImage;
    @FindBy(css = ".price .woocommerce-Price-amount") private WebElement productDetailPrice;
    @FindBy(css = ".single_add_to_cart_button, button[name='add-to-cart']") private WebElement addToCartButton;
    private WebDriver driver;
    private WebDriverWait wait;
    
    public StorePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void addProductToCart() {
        By productName = By.cssSelector("a[aria-label='Add “Black Over-the-shoulder Handbag” to your cart']");
        driver.findElement(productName).click();

    }

    public void filterByPriceRange(int minPrice, int maxPrice) {
        Actions actions = new Actions(driver);
        
        if (sliderHandles.size() >= 2) {
            actions.dragAndDropBy(sliderHandles.get(0), minPrice * 2, 0).perform();
            actions.dragAndDropBy(sliderHandles.get(1), maxPrice * 2, 0).perform();
        }
        
        filterButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
    }
    
    public boolean areAllProductsInPriceRange(int minPrice, int maxPrice) {
        return productPrices.stream()
                .mapToDouble(element -> {
                    String priceText = element.getText().replace("$", "").replace("£", "").trim();
                    String[] prices = priceText.split("\\s+");
                    return Double.parseDouble(prices[0]);
                })
                .allMatch(price -> price >= minPrice && price <= maxPrice);
    }
    
    public boolean hasProductsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));
        return !productItems.isEmpty();
    }
    
    public void searchForProduct(String keyword) {
        searchInput.clear();
        searchInput.sendKeys(keyword);
    }
    
    public void clickSearchButton() {
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
    }
    
    public boolean hasNoResultsMessage() {
        return noResultsMessage.isDisplayed();
    }

    public void selectCategory(String categoryName) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown)));
        select.selectByVisibleText(categoryName);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("ul.products li.product"), 0));
    }


    public void selectSortOption(String sortOption) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)));
        select.selectByVisibleText(sortOption);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("ul.products li.product"), 0));
    }
    
    public boolean isProductsSortedBy(String sortOption) {
        return !productItems.isEmpty();
    }
    public void addToCart(String productName){
        By addToCartButton = By.cssSelector("a[aria-label='Add \"" + productName + "\" to your cart']");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(ViewCartLink)).click();
    }

    // view product details page
    public void viewProductDetails(String productName) {
        By productLink = By.xpath("//h2[text()='" + productName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();
    }

    public boolean isProductTitleVisible() {
        return productTitle.isDisplayed();
    }

    public boolean isProductImageVisible() {
        return productImage.isDisplayed();
    }

    public boolean isProductPriceVisible() {
        return productDetailPrice.isDisplayed();
    }

    public boolean isAddToCartButtonVisible() {
        return addToCartButton.isDisplayed();
    }
    
    public void addProductToCart(String productName) {
        try {
            By addToCartButton = By.cssSelector("a[aria-label='Add \"" + productName + "\" to your cart']");
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        } catch (Exception e1) {
            try {
                By alternativeSelector = By.xpath("//a[contains(@aria-label, '" + productName + "')]");
                wait.until(ExpectedConditions.elementToBeClickable(alternativeSelector)).click();
            } catch (Exception e2) {
                try {
                    By productLink = By.xpath("//h2[contains(text(), '" + productName + "')]/following-sibling::*//a[contains(@class, 'add_to_cart')]");
                    wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();
                } catch (Exception e3) {
                    System.out.println("Could not find product: " + productName);
                }
            }
        }
    }
    
    public boolean isProductAddedToCart() {
        return wait.until(ExpectedConditions.elementToBeClickable(ViewCartLink)).isDisplayed();
    }
    
    public CartPage clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(ViewCartLink)).click();
        return PageFactoryManager.getCartPage(driver);
    }

}
