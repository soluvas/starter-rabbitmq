package org.soluvas.tutorial.rabbitmq.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ceefour on 10/24/15.
 */
public interface PlaceRepository extends PagingAndSortingRepository<Place, Long> {
}
