package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;


public class StorePage extends BasePage {
@FindBy(css = ".price-slider-amount #amount") private WebElement priceRangeDisplay;
    @FindBy(css = "button[type='submit']") private List<WebElement> filterButton;
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
    @FindBy(className = "from")
    private WebElement fromPrice;

    @FindBy(className = "to")
    private WebElement toPrice;

    public StorePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void filterByPriceRange(int minPrice, int maxPrice) {
        wait.until(ExpectedConditions.visibilityOfAllElements(sliderHandles));

        // Adjust minimum slider
        while (getFromPrice() < minPrice) {
            sliderHandles.get(0).sendKeys(Keys.ARROW_RIGHT);
        }
        while (getFromPrice() > minPrice) {
            sliderHandles.get(0).sendKeys(Keys.ARROW_LEFT);
        }

        // Adjust maximum slider
        while (getToPrice() > maxPrice) {
            sliderHandles.get(1).sendKeys(Keys.ARROW_LEFT);
        }
        while (getToPrice() < maxPrice) {
            sliderHandles.get(1).sendKeys(Keys.ARROW_RIGHT);
        }

        WebElement oldProduct = productItems.get(0);
        filterButton.get(1).click();

        wait.until(ExpectedConditions.stalenessOf(oldProduct));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("ul.products li.product")
        ));
    }

    private int getFromPrice() {
        return Integer.parseInt(fromPrice.getText().replaceAll("[^0-9]", ""));
    }

    private int getToPrice() {
        return Integer.parseInt(toPrice.getText().replaceAll("[^0-9]", ""));
    }

    public boolean areAllProductsInPriceRange(int minPrice, int maxPrice) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));

        return productItems.stream().allMatch(product -> {
            WebElement priceContainer = product.findElement(By.cssSelector(".price"));

            // Prefer sale price if present
            List<WebElement> salePrices = priceContainer.findElements(By.cssSelector("ins .woocommerce-Price-amount"));
            WebElement priceElement = salePrices.isEmpty()
                    ? priceContainer.findElement(By.cssSelector(".woocommerce-Price-amount"))
                    : salePrices.get(0);

            double price = Double.parseDouble(
                    priceElement.getText().replaceAll("[^0-9.]", "")
            );

            return price >= minPrice && price <= maxPrice;
        });
    }




    public boolean hasFilteredProductsDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.products li.product")));
            List<WebElement> filteredProducts = driver.findElements(By.cssSelector("ul.products li.product"));
            return !filteredProducts.isEmpty();
        } catch (Exception e) {
            return false;
        }
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
        Select select = new Select(wait.until(
                ExpectedConditions.elementToBeClickable(categoryDropdown)
        ));
        categoryDropdown.click();
        select.selectByVisibleText(categoryName);
        categoryDropdown.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("ul.products li.product")
        ));
    }
    public boolean areAllProductsInCategory(String expectedCategory) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItems));

        String normalizedExpectedCategory = expectedCategory
                // remove anything in parentheses
                .replaceAll("\\(.*?\\)", "")
                .trim()
                .toLowerCase();

        return productItems.stream().allMatch(product -> {
            String categoryText = product
                    .findElement(By.cssSelector(".ast-woo-product-category"))
                    .getText()
                    .toLowerCase();
            return categoryText.contains(normalizedExpectedCategory);
        });
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
        By addToCartButton = By.cssSelector("a[aria-label='Add \"" + productName + "\" to your cart']");
        if (driver.findElements(addToCartButton).isEmpty()) {
            addToCartButton = By.xpath("//a[contains(@aria-label, '" + productName + "')]");
        }
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public boolean isProductAddedToCart() {
        return wait.until(ExpectedConditions.elementToBeClickable(ViewCartLink)).isDisplayed();
    }

    public CartPage clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(ViewCartLink)).click();
        return PageFactoryManager.getCartPage(driver);
    }

}
