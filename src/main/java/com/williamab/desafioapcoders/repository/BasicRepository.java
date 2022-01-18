package com.williamab.desafioapcoders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.williamab.desafioapcoders.model.BasicEntity;

/**
 * Reposit�rio base para os reposit�rios da API.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 * @param E a entidade que o reposit�rio representa
 */
@NoRepositoryBean
public interface BasicRepository<E extends BasicEntity> extends JpaRepository<Long, E> {

}
