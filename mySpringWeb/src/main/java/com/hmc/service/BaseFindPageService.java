package com.hmc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hmc.base.BaseConstants;
import com.hmc.base.BaseResultInfo;
import com.hmc.component.BeanOperateComponent;
import com.hmc.domain.BaseDomain;
import com.hmc.event.BaseFindPageRequestEvent;
import com.hmc.repository.IBaseJpaRepository;

/**
 * 分页查询Sercie基类
 * @author hmc
 *
 * @param <D>
 */
public class BaseFindPageService<I extends BaseFindPageRequestEvent,D extends BaseDomain> extends BaseDomainSearchService<I,D> {

	@Autowired
	private BeanOperateComponent beanOperateComponent;
	
	@Override
	public BaseResultInfo executing(I request) {
		IBaseJpaRepository<D> domainRepository = beanOperateComponent.getRepository(getClass());
		
		BaseResultInfo response = BaseResultInfo.getBaseResult(true);
		Map<String, Object> content = new HashMap<String, Object>();
		
		Page<D> page;
		
		if(getSpec(request)==null){
			page = domainRepository.findAll(getSpec(request), getPageable(request));
		}else{
			page = domainRepository.findAll(getPageable(request));
		}
		
		content.put("size",page.getSize());
		content.put("page",page.getNumber());
		content.put(BaseConstants.ITEMS.getCode(), page.getContent());
		
		response.setData(content);
		
		return response;
	}

	private Pageable getPageable(BaseFindPageRequestEvent request) {
		
		return request.getPageable();
	}

	

}
