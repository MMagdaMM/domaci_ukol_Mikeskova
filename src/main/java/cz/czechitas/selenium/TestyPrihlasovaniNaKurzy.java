package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {
    //konstanty
    private static final String URL_APLIKACE = "https://cz-test-dva.herokuapp.com/";
    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void vlozitEmail() {
        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/prihlaseni"));

        WebElement inputEmail = prohlizec.findElement(By.id("email"));
        inputEmail.click();
        inputEmail.sendKeys("vlcamagda@gmail.com");
    }

    public void vlozitHeslo() {
        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/prihlaseni"));

        WebElement inputHeslo = prohlizec.findElement(By.id("password"));
        inputHeslo.click();
        inputHeslo.sendKeys("Tq4qS52J3zxH4vy");
        WebElement btnPrihlasit = prohlizec.findElement(By.xpath("//button[contains(@class, 'qa-login-button')]"));
        btnPrihlasit.click();
    }

    public void prihlasitUzivatele() {
        vlozitEmail();
        vlozitHeslo();
    }

    public void vybratKurz() {
        List<WebElement> btnsViceInformaci = prohlizec.findElements(By.xpath("//a[@class='btn btn-sm align-self-center btn-primary']"));
        WebElement btnTrimesicniKurzyWebu = btnsViceInformaci.get(1);
        btnTrimesicniKurzyWebu.click();

        WebElement btnVytvorPrihlasku = prohlizec.findElement(By.linkText("Vytvořit přihlášku"));
        btnVytvorPrihlasku.click();
    }

    public void vybratTermin() {
        String nazevStranky = prohlizec.getTitle();
        String ocekavanyNazevStranky = "Nová přihláška - Czechitas";
        Assertions.assertEquals(ocekavanyNazevStranky, nazevStranky);

        WebElement btnTermin = prohlizec.findElement(By.xpath("//div[text()='Vyberte termín...']"));
        btnTermin.click();

        WebElement inputPisDatum = prohlizec.findElement(By.xpath("//input[@type='search']"));
        inputPisDatum.click();

        inputPisDatum.sendKeys("21");
        inputPisDatum.sendKeys("\n");
    }

    public void vyplnitJmeno() {
        String nazevStranky = prohlizec.getTitle();
        String ocekavanyNazevStranky = "Nová přihláška - Czechitas";
        Assertions.assertEquals(ocekavanyNazevStranky, nazevStranky);

        WebElement inputKrestniJmeno = prohlizec.findElement(By.id("forename"));
        inputKrestniJmeno.sendKeys("Bobeš");
    }

    public void vyplnitPrijmeni() {
        String nazevStranky = prohlizec.getTitle();
        String ocekavanyNazevStranky = "Nová přihláška - Czechitas";
        Assertions.assertEquals(ocekavanyNazevStranky, nazevStranky);

        WebElement inputPrijmeni = prohlizec.findElement(By.id("surname"));
        inputPrijmeni.sendKeys("Malý " + System.currentTimeMillis());
    }

    public void vyplnitDatumNarozeni() {
        String nazevStranky = prohlizec.getTitle();
        String ocekavanyNazevStranky = "Nová přihláška - Czechitas";
        Assertions.assertEquals(ocekavanyNazevStranky, nazevStranky);

        WebElement inputDatumNarozeni = prohlizec.findElement(By.id("birthday"));
        inputDatumNarozeni.click();
        inputDatumNarozeni.sendKeys("21.07.2016");
    }

    public void vyplnitZpusobPlatby() {
        String nazevStranky = prohlizec.getTitle();
        String ocekavanyNazevStranky = "Nová přihláška - Czechitas";
        Assertions.assertEquals(ocekavanyNazevStranky, nazevStranky);

        WebElement inputHotove = prohlizec.findElement(By.xpath("//label[@for='payment_cash']"));
        inputHotove.click();
    }

    public void souhlasitSPodminkami() {
        String nazevStranky = prohlizec.getTitle();
        String ocekavanyNazevStranky = "Nová přihláška - Czechitas";
        Assertions.assertEquals(ocekavanyNazevStranky, nazevStranky);

        WebElement inputPodminky = prohlizec.findElement(By.xpath("//label[@for='terms_conditions']"));
        inputPodminky.click();
    }

    public void kliknoutVytvorPrihlasku() {
        String nazevStranky = prohlizec.getTitle();
        String ocekavanyNazevStranky = "Nová přihláška - Czechitas";
        Assertions.assertEquals(ocekavanyNazevStranky, nazevStranky);

        WebElement btnVytvorPrihlaskuFakt = prohlizec.findElement(By.xpath("//input[@value='Vytvořit přihlášku']"));
        btnVytvorPrihlaskuFakt.click();
    }

    public void vyplnitPrihlasku() {
        vybratTermin();
        vyplnitJmeno();
        vyplnitPrijmeni();
        vyplnitDatumNarozeni();
        vyplnitZpusobPlatby();
        souhlasitSPodminkami();
        kliknoutVytvorPrihlasku();
    }

    public void prihlasitDiteNaKurz() {
        prihlasitUzivatele();

        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/zaci"));

        WebElement btnVytvorNovouPrihlasku = prohlizec.findElement(By.linkText("Vytvořit novou přihlášku"));
        btnVytvorNovouPrihlasku.click();

        vybratKurz();
        vyplnitPrihlasku();
    }

    public void odhlasitUcastNemoc() {
        WebElement btnOdhlaseniUcasti = prohlizec.findElement(By.xpath("//a[@class='btn btn-sm btn-danger']"));
        btnOdhlaseniUcasti.click();

        WebElement moznostNemoc = prohlizec.findElement(By.xpath("//label[@for='logged_out_illness']"));
        moznostNemoc.click();

        WebElement btnOdhlasitZaka = prohlizec.findElement(By.xpath("//input[@value='Odhlásit žáka']"));
        btnOdhlasitZaka.click();
    }

    @Test
    public void uzivateleSuctemLzePrihlasit() {
        //Given
        prohlizec.navigate().to(URL_APLIKACE + "prihlaseni");

        //When
        prihlasitUzivatele();

        //Then
        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/zaci"));
    }

    @Test
    public void prihlasenyRodicMuzePrihlasitDiteNaKurz() {
        prohlizec.navigate().to(URL_APLIKACE + "prihlaseni");

        prihlasitUzivatele();

        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/zaci"));

        WebElement btnVytvorNovouPrihlasku = prohlizec.findElement(By.linkText("Vytvořit novou přihlášku"));
        btnVytvorNovouPrihlasku.click();

        vybratKurz();
        vyplnitPrihlasku();

        WebElement potvrzeniPrihlasky = prohlizec.findElement(By.xpath("//*[text()='Stáhnout potvrzení o přihlášení']"));
        Assertions.assertNotNull(potvrzeniPrihlasky);
    }

    @Test
    public void RodicNejprveVybereKurzApakSePrihlasiPrihlaskaSeUlozi() {
        prohlizec.navigate().to(URL_APLIKACE);
        vybratKurz();
        prihlasitUzivatele();
        vyplnitPrihlasku();

        WebElement potvrzeniPrihlasky = prohlizec.findElement(By.xpath("//*[text()='Stáhnout potvrzení o přihlášení']"));
        Assertions.assertNotNull(potvrzeniPrihlasky);
    }

    @Test
    public void prihlasenyRodicMuzeOdhlasitDiteZkurzZduvoduNemoci() {
        prohlizec.navigate().to(URL_APLIKACE + "prihlaseni");
        prihlasitDiteNaKurz();

        odhlasitUcastNemoc();

        WebElement popUpoknoZakOdhlasen = prohlizec.findElement(By.xpath("//script[3]"));
        Assertions.assertNotNull(popUpoknoZakOdhlasen, "žák nebyl odhlášen");
    }


    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
