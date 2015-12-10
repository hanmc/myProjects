package com.hmc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.hmc.base.BaseResultInfo;
import com.hmc.component.BeanOperateComponent;
import com.hmc.domain.BaseDomain;
import com.hmc.event.IBaseRequestEvent;
import com.hmc.repository.IBaseJpaRepository;

/**
 * 数据库查询操作组件的基类<br>
 * 没有事务控制，建议只进行数据库查询操作<br>
 * 默认通过请求参数中的id来进行单条数据查询<br>
 * 如果请求参数中id为null  则通过获得子类重写的getSpec()方法来进行条件查询
 * 子类可通过重写executing()方法自定义逻辑
 * @author hmc
 *
 * @param <D>
 */
public class BaseDomainSearchService<I extends IBaseRequestEvent,D extends BaseDomain> extends BaseService<I>{


	@Autowired
	private BeanOperateComponent beanOperateComponent;
	
	protected BaseResultInfo executing(I request) {
		
		BaseResultInfo response = BaseResultInfo.getBaseResult(false);
		
		IBaseJpaRepository<D> domainRepository = beanOperateComponent.getRepository(getClass());
		Object items = null;
		
		if(request.getId()!=null){
			items =  domainRepository.findOne(request.getId());
			response = BaseResultInfo.getBaseResult(true);
			response.setData(items);
		}else{
			Specification<D> spec = getSpec(request);
			if(spec!=null){
				items = domainRepository.findAll(spec, request.getSort());
			}else{
				response.setReturnMsg("未传入参数{id}或未重写getSpec()");
				return response;
			}
		}
		
		return response;
	}
	/**
	 * 子类覆盖此方法用来构建查询条件
	 * @param request
	 * @return
	 */
	//TDH 尚未实现
	protected Specification<D> getSpec(I request) {
		return null;
	}

}
