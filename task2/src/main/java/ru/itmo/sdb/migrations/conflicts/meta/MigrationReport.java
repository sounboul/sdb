package ru.itmo.sdb.migrations.conflicts.meta;

import java.util.List;

public class MigrationReport {
    private final List<MigrationMeta> migrations;
    private final Source source;

    public MigrationReport(Source source, List<MigrationMeta> migrations) {
        this.migrations = migrations;
        this.source = source;
    }

    public List<MigrationMeta> getMigrations() {
        return migrations;
    }

    public Source getSource() {
        return source;
    }
}
