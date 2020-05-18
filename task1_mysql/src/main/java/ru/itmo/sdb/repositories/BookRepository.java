package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {
}
