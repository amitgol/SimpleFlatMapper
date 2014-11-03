package org.sfm.map.impl;

import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sfm.map.MapperBuilderErrorHandler;

public final class LogRethrowMapperBuilderErrorHandler implements
		MapperBuilderErrorHandler {
	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void getterNotFound(final String msg) {
		logger.log(Level.WARNING, msg);
	}

	@Override
	public void propertyNotFound(final Type target, final String property) {
		logger.log(Level.WARNING, "Setter for " + property + " on  " + target + " not found");
	}
}