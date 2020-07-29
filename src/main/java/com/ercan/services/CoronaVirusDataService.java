package com.ercan.services;

import com.ercan.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void VirusDataGetir() throws IOException, InterruptedException { //Bu istisna bir threadin çalışması yarıda kesildiğinde fırlatılır.
        List<LocationStats> yeniIstatikler = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader); //iterable ile foreach kullanmayı sağlamış olacaz.
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            locationStats.setKonum(record.get("Province/State"));
            locationStats.setUlke(record.get("Country/Region"));
            int toplamVaka = Integer.parseInt(record.get(record.size() - 1));
            int birOncekiGundenFarki = Integer.parseInt(record.get(record.size() - 2));
            locationStats.setToplamVaka(toplamVaka);
            locationStats.setBirOncekiGundenFarki(birOncekiGundenFarki);
            yeniIstatikler.add(locationStats);
        }
        this.allStats = yeniIstatikler;
    }

    public List<LocationStats> getAllStats() {
        return allStats;
    }








  /*  public void test() throws InterruptedException { yarıda kestiği için exception fırlattı.
        Thread.sleep(500);
    }*/

}
