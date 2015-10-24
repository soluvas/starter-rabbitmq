package org.soluvas.tutorial.rabbitmq.jpa;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.soluvas.tutorial.rabbitmq.core.StarterThing;

import javax.persistence.*;

/**
 * Created by ceefour on 10/24/15.
 * @see <a href="https://schema.org/Place">schema:Place</a>
 */
@Entity
@EntityListeners(Place.PlaceListener.class)
@Table(indexes = {@Index(name="ik_place_creationtime", columnList = "creationtime")})
public class Place implements StarterThing {

    public static class PlaceListener {
        @PrePersist
        public void setInitialValues(Place place) {
            place.setCreationTime(new DateTime());
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
