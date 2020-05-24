package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.BookBorrowEntity;

import java.sql.Date;
import java.util.concurrent.TimeUnit;


@Service
public class BookBorrowGenerator implements Generator<BookBorrowEntity> {
    private Faker faker;

    @Autowired
    public BookBorrowGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public BookBorrowEntity generate() {
        final BookBorrowEntity bookBorrow = new BookBorrowEntity();

        bookBorrow.setName(faker.book().title());
        bookBorrow.setBorrowDate(
                new Date(faker.date().past(100, TimeUnit.DAYS).getTime())
        );
        bookBorrow.setReturnDate(
                new Date(faker.date().future(100, TimeUnit.DAYS).getTime())
        );

        return bookBorrow;
    }
}
