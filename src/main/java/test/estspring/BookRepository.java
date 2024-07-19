package test.estspring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // ISBN으로 책 찾기
    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Optional<Book> findByIsbn(@Param("isbn") String isbn);

    // 가격 범위로 책 목록 찾기
    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<Book> findByPriceBetween(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    // 재고가 특정 수량 이상인 책 목록 찾기
    @Query("SELECT b FROM Book b WHERE b.stock >= :minStock")
    List<Book> findByStockGreaterThanEqual(@Param("minStock") Integer minStock);

    // 제목에 특정 키워드가 포함된 책 목록 찾기
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword%")
    List<Book> findByTitleContaining(@Param("keyword") String keyword);

}