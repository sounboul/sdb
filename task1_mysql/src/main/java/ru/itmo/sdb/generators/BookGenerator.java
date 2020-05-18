package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.BookEntity;

@Service
public class BookGenerator implements Generator<BookEntity> {
    private Faker faker;

    @Autowired
    public BookGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public BookEntity generate() {
        final BookEntity book = new BookEntity();

        book.setAuthor(faker.book().author());
        book.setIsbn(faker.regexify("^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$"));
        book.setName(faker.book().title());
        book.setEditionId(faker.number().randomDigitNotZero());

        return book;
    }
}
