package pl.bookshop.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.bookshop.bookservice.entity.composedKey.BookAuthorId;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book_author", schema = "public", catalog = "bookshop_db")
public class BookAuthorEntity {

    @EmbeddedId
    private BookAuthorId id;

}