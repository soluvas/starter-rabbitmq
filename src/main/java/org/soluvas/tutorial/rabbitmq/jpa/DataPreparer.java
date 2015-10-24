package org.soluvas.tutorial.rabbitmq.jpa;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by ceefour on 10/24/15.
 */
@Component
public class DataPreparer {

    @Inject
    private PlaceRepository placeRepo;

    @PostConstruct
    public void init() {
        // if no place, add a sample place
        if (placeRepo.count() == 0) {
            final Place place = new Place();
            place.setName("Institut Teknologi Bandung");
            place.setDescription("Didirikan pada tanggal 2 Maret 1959, kampus utama ITB saat ini merupakan lokasi dari sekolah tinggi teknik pertama di Indonesia.");
            placeRepo.save(place);
        }
    }
}
