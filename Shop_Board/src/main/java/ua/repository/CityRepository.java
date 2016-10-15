package ua.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.City;

public interface CityRepository extends JpaRepository<City, Integer>, JpaSpecificationExecutor<City>{

	City findByName(String name);

}
