package ru.itmo.sdb.mysql.core.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "publication", schema = "itmo_db", catalog = "")
public class PublicationEntity {
    private long id;
    private String name;
    private long editionId;
    private Double citationIndex;
    private Date publicationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "edition_id", nullable = true)
    public long getEditionId() {
        return editionId;
    }

    public void setEditionId(long editionId) {
        this.editionId = editionId;
    }

    @Basic
    @Column(name = "citation_index", nullable = true, precision = 0)
    public Double getCitationIndex() {
        return citationIndex;
    }

    public void setCitationIndex(Double citationIndex) {
        this.citationIndex = citationIndex;
    }

    @Basic
    @Column(name = "publication_date", nullable = true)
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
