package com.hmc.event;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author hmc
 *
 */
public class BaseFindPageRequestEvent extends BaseRequestEvent implements IBaseFindPageEvent{
	
	private int page;
	
	private int size = 10;
	
	private Pageable pageable =  new PageRequest(page, size, getSort());

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

}
