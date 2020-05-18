package ru.itmo.sdb.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "publication", schema = "itmo_db", catalog = "")
public class PublicationEntity {
    private int id;
    private String name;
    private Integer editionId;
    private Double citationIndex;
    private Date publicationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public Integer getEditionId() {
        return editionId;
    }

    public void setEditionId(Integer editionId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicationEntity that = (PublicationEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (editionId != null ? !editionId.equals(that.editionId) : that.editionId != null) return false;
        if (citationIndex != null ? !citationIndex.equals(that.citationIndex) : that.citationIndex != null)
            return false;
        if (publicationDate != null ? !publicationDate.equals(that.publicationDate) : that.publicationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (editionId != null ? editionId.hashCode() : 0);
        result = 31 * result + (citationIndex != null ? citationIndex.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }
}
