package ru.itmo.sdb.migrations.conflicts;

import ru.itmo.sdb.migrations.conflicts.meta.MigrationReport;

import java.util.List;

public interface ConflictResolver {
    void resolveConflicts(List<MigrationReport> migrationReports);
}
