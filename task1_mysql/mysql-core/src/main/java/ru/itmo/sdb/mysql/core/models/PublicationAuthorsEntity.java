package ru.itmo.sdb.mysql.core.models;

import javax.persistence.*;

@Entity
@Table(name = "publication_authors", schema = "itmo_db", catalog = "")
public class PublicationAuthorsEntity {
    private Long id;
    private Long personId;
    private Long publicationId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "person_id", nullable = true)
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "publication_id", nullable = true)
    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }
}
