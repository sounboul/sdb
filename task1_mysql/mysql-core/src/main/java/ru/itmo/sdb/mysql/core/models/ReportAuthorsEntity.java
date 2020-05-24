package ru.itmo.sdb.mysql.core.models;

import javax.persistence.*;

@Entity
@Table(name = "report_authors", schema = "itmo_db", catalog = "")
public class ReportAuthorsEntity {
    private Integer personId;
    private Integer reportId;

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
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
    @Column(name = "report_id", nullable = true)
    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportAuthorsEntity that = (ReportAuthorsEntity) o;

        if (personId != null ? !personId.equals(that.personId) : that.personId != null) return false;
        if (reportId != null ? !reportId.equals(that.reportId) : that.reportId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personId != null ? personId.hashCode() : 0;
        result = 31 * result + (reportId != null ? reportId.hashCode() : 0);
        return result;
    }
}
