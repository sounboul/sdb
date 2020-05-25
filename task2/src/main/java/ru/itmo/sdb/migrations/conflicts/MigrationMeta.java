package ru.itmo.sdb.migrations.conflicts;

import java.util.List;

public class MigrationMeta<T> {
    private final Class<T> dataClass;
    private final List<Long> ids;

    public MigrationMeta(Class<T> dataClass, List<Long> ids) {
        this.dataClass = dataClass;
        this.ids = ids;
    }

    public Class<T> getDataClass() {
        return dataClass;
    }

    public List<Long> getIds() {
        return ids;
    }
}
