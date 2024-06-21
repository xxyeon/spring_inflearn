package jpabook.jpashop.service;


import jpabook.jpashop.api.OrderApiController;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
public class OrderQueryService {


}
