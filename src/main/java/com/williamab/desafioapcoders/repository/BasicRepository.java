package com.williamab.desafioapcoders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.williamab.desafioapcoders.model.BasicEntity;

/**
 * Repositório base para os repositórios da API.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 * @param E a entidade que o repositório representa
 */
@NoRepositoryBean
public interface BasicRepository<E extends BasicEntity> extends JpaRepository<Long, E> {

}
