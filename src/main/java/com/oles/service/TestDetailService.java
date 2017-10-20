package com.oles.service;


import com.oles.domain.TestDetail;
import com.oles.repository.TestDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class TestDetailService extends BasicService<TestDetail,Long> {
    @Autowired
    TestDetailRepository testDetailRepository;

    /**
     * 多条件查询
     */
    public Page<TestDetail> findBySepc(Specification<TestDetail> specification, Pageable pageable) {
        return this.testDetailRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<TestDetail> findBySepc(Specification<TestDetail> specification) {
        return this.testDetailRepository.findAll(specification);
    }
    public List<TestDetail> findBySepc(Specification<TestDetail> specification, Sort sort) {
        return this.testDetailRepository.findAll(specification,sort);
    }


    public Specification<TestDetail> query(
            String kind){
        return new Specification<TestDetail>() {
            @Override
            public Predicate toPredicate(Root<TestDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(cb.equal(root.get("test").get("isToConduct"), true));
                predicate.add(cb.equal(root.get("kind"), kind));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

}
