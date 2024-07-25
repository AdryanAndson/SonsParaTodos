package Auditivo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.List;

public class AparelhoAuditivoFinder {
    public static void main(String[] args) {
        // Configurar o caminho para o EdgeDriver
        System.setProperty("webdriver.edge.driver", "C:\\Users\\andso\\Downloads\\edgedriver_win64\\msedgedriver.exe");

        // Iniciar o navegador Edge
        WebDriver driver = new EdgeDriver();

        try {
            // Pesquisar em diferentes sites
            for (String site : new String[]{"https://www.audibel.com.br", "https://www.mercadolivre.com.br", "https://lojakator.com"}) {
                driver.get(site + "/s?k=aparelho+auditivo+barato+surdo");

                // Aplicar filtros se necessário
                // Exemplo de aplicação de filtros (isso pode variar dependendo do site)
                // WebElement filterButton = driver.findElement(By.cssSelector("button.filter-button"));
                // filterButton.click();

                // Encontrar produtos
                List<WebElement> products = driver.findElements(By.cssSelector(".s-result-item")); // Ajuste o seletor conforme necessário

                // Extrair dados e armazenar
                for (WebElement product : products) {
                    try {
                        // Encontrar e extrair o nome e o preço do produto
                        WebElement nameElement = product.findElement(By.cssSelector(".a-text-normal")); // Ajuste o seletor conforme necessário
                        WebElement priceElement = product.findElement(By.cssSelector(".a-price-whole")); // Ajuste o seletor conforme necessário

                        String name = nameElement.getText();
                        String price = priceElement != null ? priceElement.getText() : "Preço não disponível";

                        // Mostrar os resultados
                        System.out.println("Nome: " + name);
                        System.out.println("Preço: " + price);
                        System.out.println("----------------------");
                    } catch (NoSuchElementException e) {
                        // Ignorar produtos que não possuem os elementos esperados
                        System.out.println("Produto sem nome ou preço.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechar o navegador
            driver.quit();
        }
    }
}
