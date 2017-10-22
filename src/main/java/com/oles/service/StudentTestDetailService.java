package com.oles.service;


import com.oles.domain.StudentTestDetail;
import com.oles.repository.StudentTestDetailRepository;
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
public class StudentTestDetailService extends BasicService<StudentTestDetail,Long> {
    @Autowired
    StudentTestDetailRepository studentTestDetailRepository;

    public StudentTestDetail getByStudentIdAndTestDetailId(Long studentId,Long testDetailId) {
        return this.studentTestDetailRepository.getByStudentIdAndTestDetailId(studentId, testDetailId);
    }


    /**
     * 多条件查询
     */
    public Page<StudentTestDetail> findBySepc(Specification<StudentTestDetail> specification, Pageable pageable) {
        return this.studentTestDetailRepository.findAll(specification, pageable);
    }

    /**
     * 多条件查询
     */
    public List<StudentTestDetail> findBySepc(Specification<StudentTestDetail> specification) {
        return this.studentTestDetailRepository.findAll(specification);
    }
    public List<StudentTestDetail> findBySepc(Specification<StudentTestDetail> specification, Sort sort) {
        return this.studentTestDetailRepository.findAll(specification,sort);
    }


    public Specification<StudentTestDetail> query(
            Long studentId){
        return new Specification<StudentTestDetail>() {
            @Override
            public Predicate toPredicate(Root<StudentTestDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                predicate.add(cb.equal(root.get("student").get("id"), studentId));
                predicate.add(cb.equal(root.get("testDetail").get("test").get("isToConduct"), true));
                Predicate[] pre = new Predicate[predicate.size()];
                query.distinct(true);
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
