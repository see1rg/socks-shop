package org.example.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.InsufficientSocksException;
import org.example.models.Socks;
import org.example.repositories.SocksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SocksService {

    public enum SockOperation {
        MORE_THAN,
        LESS_THAN,
        EQUAL,
        NONE
    }

    private final SocksRepository socksRepository;

    public Socks addSocks(Socks socks) {
        log.info("addSocks " + socks);
        Socks socksForUpdate = socksRepository.getListOfSocksWithCottonPartEqualAndColorEqual(socks.getColor(),
                socks.getCottonPart());
        if (socksForUpdate == null) {
            return socksRepository.save(socks);
        }
        socksForUpdate.setQuantity(socksForUpdate.getQuantity() + socks.getQuantity());
        return socksRepository.save(socksForUpdate);
    }

    public void removeSocks(Socks socks) {
        log.info("removeSocks" + socks);
        int quantityToRemove = socks.getQuantity();
        int quantityOnTheStock = getTotalSocksByCriteria(socks.getColor(), SockOperation.EQUAL, socks.getCottonPart());
        Socks checkSocks = socksRepository.getListOfSocksWithCottonPartEqualAndColorEqual(socks.getColor(),
                socks.getCottonPart());
        if (checkSocks == null ||
                quantityToRemove > quantityOnTheStock) {
            throw new InsufficientSocksException("Not enough socks for this operation");
        }
        if (checkSocks.getQuantity() - quantityToRemove == 0) {
            socksRepository.delete(checkSocks);
        } else {
            checkSocks.setQuantity(checkSocks.getQuantity() - quantityToRemove);
            socksRepository.save(checkSocks);
        }
    }

    public int getTotalSocksByCriteria(String color, SockOperation operation, int cottonPart) {
        log.info(" getTotalSocksByCriteria");
        Integer quantity = 0;
        switch (operation) {
            case MORE_THAN -> quantity = socksRepository
                    .getTotalSocksWithCottonPartMoreThanAndColorEqual(cottonPart, color);

            case LESS_THAN -> quantity = socksRepository
                    .getTotalSocksWithCottonPartLessThanAndColorEqual(cottonPart, color);

            case EQUAL -> quantity = socksRepository
                    .getTotalSocksWithCottonPartEqualAndColorEqual(cottonPart, color);
        }

        if (quantity == null) {
            quantity = 0;
        }
        return quantity;
    }
}
