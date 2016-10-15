package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.City;
import ua.entity.Commodity;
import ua.service.impl.specification.CommodityFilterAdapter;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>, JpaSpecificationExecutor<Commodity>{

	Commodity findByTitle(String title);

	Commodity findByPrice(double price);

	Commodity findByCount(int count);

	Commodity findByDiscription(String discription);

	@Query("SELECT c FROM Commodity c LEFT JOIN FETCH c.category WHERE c.id=:id")
	Commodity findOneCommodityInited(@Param("id")int id);
	
	@Query("SELECT c FROM Commodity c LEFT JOIN FETCH c.user WHERE c.id=:id")
	Commodity findOneUserCommodityInited(@Param("id")int id);
	
//	@Query("SELECT c FROM Commodity c LEFT JOIN FETCH c.category WHERE c.categoryId=:categoryId")
//	Commodity findOneCommodityInitedCat(@Param("categoryId")int categoryId);

}
