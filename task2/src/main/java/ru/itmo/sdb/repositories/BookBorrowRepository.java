package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Book;
import ru.itmo.sdb.models.BookBorrow;

@Repository
public interface BookBorrowRepository extends CrudRepository<BookBorrow, Long> {
}
