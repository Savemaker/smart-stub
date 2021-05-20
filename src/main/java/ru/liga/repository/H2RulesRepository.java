package ru.liga.repository;

import org.springframework.data.repository.CrudRepository;
import ru.liga.model.Rule;

public interface H2RulesRepository extends CrudRepository<Rule, Long> {

}
