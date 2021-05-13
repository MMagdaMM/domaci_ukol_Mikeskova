package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void uzivateleSuctemLzePrihlasit() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");

        WebElement inputEmail = prohlizec.findElement(By.xpath("//input[@id='email']"));
        inputEmail.click();
        inputEmail.sendKeys("vlcamagda@gmail.com");
        WebElement inputHeslo = prohlizec.findElement(By.xpath("//input[@id='password']"));
        inputHeslo.click();
        inputHeslo.sendKeys("Tq4qS52J3zxH4vy");
        WebElement btnPrihlasit = prohlizec.findElement(By.xpath("//button[@class='btn btn-primary']"));
        btnPrihlasit.click();
        String aktualniStranka = prohlizec.getTitle();
        String ocekavanaStranka = "Přihlášky - Czechitas";
        Assertions.assertEquals(ocekavanaStranka, aktualniStranka);
    }

    @Test
    public void prihlasenyRodicMuzePrihlasitDiteNaKurz() {
        //prihlaseni
        uzivateleSuctemLzePrihlasit();

        //klik na Vytvořit novou přihlášku
        WebElement btnVytvorNovouPrihlasku = prohlizec.findElement(By.xpath("//a[@class='btn btn-sm btn-info']"));
        btnVytvorNovouPrihlasku.click();

        //klik na Tříměsíční kurzy webu 2. z listu //a[@class='btn btn-sm align-self-center btn-primary']
        List<WebElement> btnsViceInformaci = prohlizec.findElements(By.xpath("//a[@class='btn btn-sm align-self-center btn-primary']"));
        WebElement btnTrimesicniKurzyWebu = btnsViceInformaci.get(1);
        btnTrimesicniKurzyWebu.click();

        //klik na Vytvoř přihlášku //a[@class='btn btn-sm align-self-center btn-primary']
        WebElement btnVytvorPrihlasku = prohlizec.findElement(By.xpath("//a[@class='btn btn-sm align-self-center btn-primary']"));
        btnVytvorPrihlasku.click();

        //klik na Termín //button[@data-id='term_id']
        WebElement btnTermin = prohlizec.findElement(By.xpath("//div[text()='Vyberte termín...']"));
        btnTermin.click();

        WebElement inputPisDatum = prohlizec.findElement(By.xpath("//input[@type='search']"));
        inputPisDatum.click();
        //input "21"  a entr
        inputPisDatum.sendKeys("21");
        inputPisDatum.sendKeys("\n");

        //klik na Křestní jméno žáka //input[@id='forename']
        //input "Bobeš"
        WebElement inputKrestniJmeno = prohlizec.findElement(By.xpath("//input[@id='forename']"));
        inputKrestniJmeno.sendKeys("Bobeš");

        //klik na Přijmení //input[@id='surname']
        //input "Malý"
        WebElement inputPrijmeni = prohlizec.findElement(By.xpath("//input[@id='surname']"));
        inputPrijmeni.sendKeys("Malý");

        //klik na Datum narozeni   //input[@id='birthday']
        //input "20.07.2016"
        WebElement inputDatumNarozeni = prohlizec.findElement(By.xpath("//input[@id='birthday']"));
        inputDatumNarozeni.click();
        inputDatumNarozeni.sendKeys("21.07.2016");

        //klik na Hotově //label[text()='Hotově']
        WebElement inputHotove = prohlizec.findElement(By.xpath("//label[text()='Hotově']"));
        inputHotove.click();

        //klik na podmínky //label[contains (text(), 'podmínkami')]
        WebElement inputPodminky = prohlizec.findElement(By.xpath("//label[contains (text(), 'podmínkami')]"));
        inputPodminky.click();

        //klik na vytvoř přihlášku
        WebElement btnVytvorPrihlaskuFakt = prohlizec.findElement(By.xpath("//input[@value='Vytvořit přihlášku']"));
        btnVytvorPrihlaskuFakt.click();

        //klik na Přihlášky
        WebElement btnPrihlasky = prohlizec.findElement(By.xpath("//span/a[@href='https://cz-test-jedna.herokuapp.com/zaci']"));
        btnPrihlasky.click();

        //assert
        WebElement btnUpravit = prohlizec.findElement(By.xpath("//i[@class='fa fa-fw fa-edit pr-1']"));
        Assertions.assertNotNull(btnUpravit, "Přihláška se nezobrazila v seznamu přihlášek");
    }

    @Test
    public void RodicNejprveVybereKurzApakSePrihlasiPrihlaskaSeUlozi() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        //klik na Tříměsíční kurzy webu
        WebElement btnTrimesicniKurzyWebu = prohlizec.findElement(By.xpath("//a[@href='https://cz-test-jedna.herokuapp.com/11-trimesicni-kurzy-webu']"));
        btnTrimesicniKurzyWebu.click();

        //klik na Vytvoř přihlášku //a[@class='btn btn-sm align-self-center btn-primary']
        WebElement btnVytvorPrihlasku = prohlizec.findElement(By.xpath("//a[@class='btn btn-sm align-self-center btn-primary']"));
        btnVytvorPrihlasku.click();

        //přihlašení rodiče
        WebElement inputEmail = prohlizec.findElement(By.xpath("//input[@id='email']"));
        inputEmail.click();
        inputEmail.sendKeys("vlcamagda@gmail.com");
        WebElement inputHeslo = prohlizec.findElement(By.xpath("//input[@id='password']"));
        inputHeslo.click();
        inputHeslo.sendKeys("Tq4qS52J3zxH4vy");
        WebElement btnPrihlasit = prohlizec.findElement(By.xpath("//button[@class='btn btn-primary']"));
        btnPrihlasit.click();

        //vyplnění příhlášky

        WebElement btnTermin = prohlizec.findElement(By.xpath("//div[text()='Vyberte termín...']"));
        btnTermin.click();
        ///html/body/div/div/div/div/div/form/table/tbody/tr[2]/td[2]/div/div/div[1]/input
        WebElement inputPisDatum = prohlizec.findElement(By.xpath("//input[@type='search']"));
        inputPisDatum.click();
        inputPisDatum.sendKeys("21");
        inputPisDatum.sendKeys("\n");

        WebElement inputKrestniJmeno = prohlizec.findElement(By.xpath("//input[@id='forename']"));
        inputKrestniJmeno.sendKeys("Bobeš");

        WebElement inputPrijmeni = prohlizec.findElement(By.xpath("//input[@id='surname']"));
        inputPrijmeni.sendKeys("Malý");

        WebElement inputDatumNarozeni = prohlizec.findElement(By.xpath("//input[@id='birthday']"));
        inputDatumNarozeni.click();
        inputDatumNarozeni.sendKeys("21.07.2016");

        WebElement inputHotove = prohlizec.findElement(By.xpath("//label[text()='Hotově']"));
        inputHotove.click();

        WebElement inputPodminky = prohlizec.findElement(By.xpath("//label[contains (text(), 'podmínkami')]"));
        inputPodminky.click();

        WebElement btnVytvorPrihlaskuFakt = prohlizec.findElement(By.xpath("//input[@value='Vytvořit přihlášku']"));
        btnVytvorPrihlaskuFakt.click();

        WebElement btnPrihlasky = prohlizec.findElement(By.xpath("//span/a[@href='https://cz-test-jedna.herokuapp.com/zaci']"));
        btnPrihlasky.click();

        //assert
        WebElement btnUpravit = prohlizec.findElement(By.xpath("//i[@class='fa fa-fw fa-edit pr-1']"));
        Assertions.assertNotNull(btnUpravit, "Přihláška se nezobrazila v seznamu přihlášek");
    }

    @Test
    public void prihlasenyRodicMuzeOdhlasitDiteZkurzZduvoduNemoci() {

        //přihlášení kurzu
        prihlasenyRodicMuzePrihlasitDiteNaKurz();

        //klik na btn odhlášení účasti //a[@class='btn btn-sm btn-danger']
        WebElement btnOdhlaseniUcasti = prohlizec.findElement(By.xpath("//a[@class='btn btn-sm btn-danger']"));
        btnOdhlaseniUcasti.click();

        //zaškrtnutí Nemoc  //label[@for='logged_out_illness']
        WebElement moznostNemoc = prohlizec.findElement(By.xpath("//label[@for='logged_out_illness']"));
        moznostNemoc.click();

        //klik na Odhlásit žáka //input[@value='Odhlásit žáka']
        WebElement btnOdhlasitZaka = prohlizec.findElement(By.xpath("//input[@value='Odhlásit žáka']"));
        btnOdhlasitZaka.click();

        //assert že bude přítomno popup okno //script[3]
        WebElement popUpoknoZakOdhlasen = prohlizec.findElement(By.xpath("//script[3]"));
        Assertions.assertNotNull(popUpoknoZakOdhlasen, "žák nebyl odhlášen");
    }

    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
