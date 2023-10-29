package pl.bookshop.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book", schema = "public", catalog = "bookshop_db")
public class BookEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "book_id")
    private Long bookId;

    @Basic
    @Column(name = "title", nullable = false)
    private String title;

    @Basic
    @Column(name = "meta_title")
    private String metaTitle;

    @Basic
    @Column(name = "summary", nullable = false)
    private String summary;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "num_pages", nullable = false)
    private Integer numPages;

    @Basic
    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Basic
    @Column(name = "language_id", nullable = false)
    private Long languageId;

    @Basic
    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @Basic
    @Column(name = "publisher_id", nullable = false)
    private Long publisherId;

    @Basic
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;

}