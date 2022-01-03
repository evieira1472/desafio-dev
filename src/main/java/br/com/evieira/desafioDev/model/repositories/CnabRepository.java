package br.com.evieira.desafioDev.model.repositories;

import br.com.evieira.desafioDev.model.entities.Cnab;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CnabRepository extends PagingAndSortingRepository<Cnab, Integer> {

    public Iterable<Cnab> findByNmLojaContainingIgnoreCase(String loja);

}
