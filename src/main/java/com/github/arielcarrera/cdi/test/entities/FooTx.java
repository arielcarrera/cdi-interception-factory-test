package com.github.arielcarrera.cdi.test.entities;

import javax.transaction.Transactional;

@Transactional
public class FooTx implements FooTxInterface {

	/* (non-Javadoc)
	 * @see com.github.arielcarrera.cdi.test.entities.FooTxInterface#ping()
	 */
	@Override
	public String ping() {
		return "pong";
	}

}