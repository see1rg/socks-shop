package org.example.repositories;

import org.example.models.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SocksRepository extends JpaRepository<Socks, Long> {

    @Query(value = "SELECT SUM(s.quantity) FROM socks s WHERE s.cotton_part > :cottonPart AND s.color = :color", nativeQuery = true)
    Integer getTotalSocksWithCottonPartMoreThanAndColorEqual(@Param("cottonPart") int cottonPart, @Param("color") String color);

    @Query(value = "SELECT SUM(s.quantity) FROM socks s WHERE s.cotton_part < :cottonPart AND s.color = :color", nativeQuery = true)
    Integer getTotalSocksWithCottonPartLessThanAndColorEqual(@Param("cottonPart") int cottonPart, @Param("color") String color);

    @Query(value = "SELECT SUM(s.quantity) FROM socks s WHERE s.cotton_part = :cottonPart AND s.color = :color", nativeQuery = true)
    Integer getTotalSocksWithCottonPartEqualAndColorEqual(@Param("cottonPart") int cottonPart, @Param("color") String color);

    @Query(value = "SELECT * FROM socks WHERE color = :color AND cotton_part = :cottonPart", nativeQuery = true)
    Socks getListOfSocksWithCottonPartEqualAndColorEqual(String color, int cottonPart);
}
