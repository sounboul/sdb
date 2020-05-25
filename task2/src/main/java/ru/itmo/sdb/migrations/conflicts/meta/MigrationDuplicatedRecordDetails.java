package ru.itmo.sdb.migrations.conflicts.meta;

import java.util.List;

public class MigrationDuplicatedRecordDetails {
    private Long originalId;
    private List<Long> existingDuplicates;

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public List<Long> getExistingDuplicates() {
        return existingDuplicates;
    }

    public void setExistingDuplicates(List<Long> existingDuplicates) {
        this.existingDuplicates = existingDuplicates;
    }
}
