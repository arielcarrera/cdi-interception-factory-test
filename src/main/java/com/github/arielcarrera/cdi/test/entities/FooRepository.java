package com.github.arielcarrera.cdi.test.entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FooRepository extends JpaRepository<FooEntity, Integer>{


}