package ru.itmo.sdb.models;

import javax.persistence.*;

@Entity
@Table(name = "publication_authors", schema = "itmo_db", catalog = "")
public class PublicationAuthorsEntity {
    private Integer id;
    private Integer personId;
    private Integer publicationId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "person_id", nullable = true)
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "publication_id", nullable = true)
    public Integer getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicationAuthorsEntity that = (PublicationAuthorsEntity) o;

        if (personId != null ? !personId.equals(that.personId) : that.personId != null) return false;
        if (publicationId != null ? !publicationId.equals(that.publicationId) : that.publicationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId != null ? personId.hashCode() : 0;
        result = 31 * result + (publicationId != null ? publicationId.hashCode() : 0);
        return result;
    }
}
