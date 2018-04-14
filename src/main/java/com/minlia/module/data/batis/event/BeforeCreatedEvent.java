package com.minlia.module.data.batis.event;

import org.springframework.context.ApplicationEvent;


/**
 * @author will
 */
public class BeforeCreatedEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	public BeforeCreatedEvent(Object source) {
    super(source);
	}

	 
}
