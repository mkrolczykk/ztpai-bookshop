package pl.bookshop.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book_author", schema = "public", catalog = "bookshop_db")
public class BookAuthorEntity {

    @Id
    @Basic
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Id
    @Basic
    @Column(name = "author_id", nullable = false)
    private Long authorId;

}